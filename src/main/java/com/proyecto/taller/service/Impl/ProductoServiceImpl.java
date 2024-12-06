package com.proyecto.taller.service.Impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.proyecto.taller.model.Producto;
import com.proyecto.taller.repository.ProductoRepository;
import com.proyecto.taller.service.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final Path directorioImagenes;

    @Autowired
    private ProductoRepository productoRepository;

    public ProductoServiceImpl() {
        // Crear directorio de imágenes al inicializar
        this.directorioImagenes = Paths.get("imagenes").toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.directorioImagenes);
        } catch (IOException e) {
            throw new RuntimeException("No se pudo crear el directorio para imágenes.", e);
        }
    }

    @Override
    public Producto crearProducto(Producto producto, MultipartFile file) throws Exception {
    	

        if (file != null && !file.isEmpty()) {
        	
            String nombreArchivo = guardarImagen(file);
            producto.setFoto(nombreArchivo);
        }
        return productoRepository.save(producto);
    }

    @Override
    public Producto actualizarProducto(Long id, Producto producto, MultipartFile file) throws Exception {
        Optional<Producto> productoExistente = productoRepository.findById(id);
        if (!productoExistente.isPresent()) {
            throw new Exception("Producto no encontrado");
        }
        Producto prod = productoExistente.get();

        // Si se proporciona una nueva imagen, guardarla y actualizar
        if (file != null && !file.isEmpty()) {
            String nombreArchivo = guardarImagen(file);
            // Eliminar imagen anterior si existe
            if (prod.getFoto() != null) {
                eliminarImagen(prod.getFoto());
            }
            prod.setFoto(nombreArchivo);
        }

        // Actualizar campos del producto
        prod.setNombre(producto.getNombre());
        prod.setDescripcion(producto.getDescripcion());
        prod.setPrecioCompra(producto.getPrecioCompra());
        prod.setPrecioVenta(producto.getPrecioVenta());
        prod.setPeso(producto.getPeso());
        prod.setEstado(producto.getEstado());
        prod.setFechaRegistro(producto.getFechaRegistro());
        prod.setFechaCaducidad(producto.getFechaCaducidad());
        prod.setProveedor(producto.getProveedor());
        prod.setInventario(producto.getInventario());
        prod.setCategoria(producto.getCategoria());

        return productoRepository.save(prod);
    }

    @Override
    public void eliminarProducto(Long id) throws Exception {
        Producto producto = productoRepository.findById(id)
            .orElseThrow(() -> new Exception("Producto no encontrado"));
        
        // Eliminar imagen asociada si existe
        if (producto.getFoto() != null) {
            eliminarImagen(producto.getFoto());
        }
        
        productoRepository.deleteById(id);
    }

    @Override
    public Producto obtenerProductoPorId(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    // Método para guardar imagen con nombre único
    private String guardarImagen(MultipartFile file) throws IOException {
        // Generar nombre de archivo único
        String nombreOriginal = file.getOriginalFilename();
        String extension = nombreOriginal.substring(nombreOriginal.lastIndexOf("."));
        String nombreUnico = UUID.randomUUID().toString() + extension;
        
        // Guardar archivo
        Path rutaDestino = this.directorioImagenes.resolve(nombreUnico);
        Files.copy(file.getInputStream(), rutaDestino, StandardCopyOption.REPLACE_EXISTING);
        
        return nombreUnico;
    }

    // Método para cargar imagen como recurso
    public Resource cargarImagen(String nombreImagen) throws IOException {
        Path rutaImagen = this.directorioImagenes.resolve(nombreImagen).normalize();
        Resource resource = new UrlResource(rutaImagen.toUri());
        
        if (resource.exists()) {
            return resource;
        } else {
            throw new IOException("Imagen no encontrada: " + nombreImagen);
        }
    }

    // Método para eliminar imagen
    private void eliminarImagen(String nombreImagen) throws IOException {
        Path rutaImagen = this.directorioImagenes.resolve(nombreImagen).normalize();
        Files.deleteIfExists(rutaImagen);
    }
}