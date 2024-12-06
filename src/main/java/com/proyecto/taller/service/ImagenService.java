package com.proyecto.taller.service;

import com.proyecto.taller.model.ImagenModel;
import com.proyecto.taller.model.Producto;
import com.proyecto.taller.model.UsuariosModel;
import com.proyecto.taller.repository.ImagenRepository;
import com.proyecto.taller.repository.ProductoRepository;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class ImagenService {

    // Ruta de imágenes establecida en src/imagenes
    private final String rutaImagenes = "src/imagenes";

    private final ImagenRepository imagenRepository;
    private final ProductoRepository productoRepository;

  public ImagenService(ImagenRepository imagenRepository, ProductoRepository productoRepository) {
        this.imagenRepository = imagenRepository;
        this.productoRepository = productoRepository;
    }



    

    public ImagenModel guardarImagen(MultipartFile archivo, UsuariosModel usuario) throws IOException {
        // Verifica si la carpeta de imágenes existe, si no, la crea
        File directorio = new File(rutaImagenes);
        if (!directorio.exists()) {
            boolean directorioCreado = directorio.mkdirs();
            if (!directorioCreado) {
                throw new IOException("No se pudo crear el directorio de imágenes.");
            }
        }

        // Guarda el archivo en la carpeta src/imagenes
        String nombreArchivo = archivo.getOriginalFilename();
        if (nombreArchivo == null || nombreArchivo.isEmpty()) {
            throw new IllegalArgumentException("El nombre del archivo no puede ser nulo o vacío.");
        }
        Path rutaArchivo = Paths.get(rutaImagenes, nombreArchivo);
        Files.write(rutaArchivo, archivo.getBytes());

        // Crea el objeto ImagenModel
        ImagenModel imagen = new ImagenModel();
        imagen.setRuta(rutaArchivo.toString());
        imagen.setUsuario(usuario);

        // Guarda la imagen en la base de datos
        return imagenRepository.save(imagen);
    }
   // Guardar imagen para un producto
    public ImagenModel guardarImagenProducto(MultipartFile archivo, Long productoId) throws IOException {
        // Verifica si el producto existe
        Optional<Producto> productoOpt = productoRepository.findById(productoId);
        if (!productoOpt.isPresent()) {
            throw new IllegalArgumentException("Producto no encontrado");
        }
        
        Producto producto = productoOpt.get();

        // Verifica si la carpeta de imágenes existe
        File directorio = new File(rutaImagenes);
        if (!directorio.exists()) {
            directorio.mkdirs();
        }

        // Guarda el archivo en la carpeta
        String nombreArchivo = archivo.getOriginalFilename();
        Path rutaArchivo = Paths.get(rutaImagenes, nombreArchivo);
        Files.write(rutaArchivo, archivo.getBytes());

        // Crea y guarda la imagen asociada al producto
        ImagenModel imagen = new ImagenModel();
        imagen.setRuta(rutaArchivo.toString());
        imagen.setProducto(producto);

        return imagenRepository.save(imagen);
    }

     // Eliminar imagen por ID
     public void eliminarImagen(Long id) {
        imagenRepository.deleteById(id);
    }

    // Eliminar todas las imágenes de un producto
    public void eliminarTodasImagenesProducto(Long productoId) {
        List<ImagenModel> imagenes = imagenRepository.findByProductoId(productoId);
        imagenRepository.deleteAll(imagenes);
    }

    // Desvincular una imagen de un producto (mantiene la imagen, pero rompe la relación)
    public void desvincularImagenDeProducto(Long imagenId) {
        Optional<ImagenModel> imagenOpt = imagenRepository.findById(imagenId);
        if (imagenOpt.isPresent()) {
            ImagenModel imagen = imagenOpt.get();
            imagen.setProducto(null); // Rompe la relación
            imagenRepository.save(imagen);
        }
    }
    
}
