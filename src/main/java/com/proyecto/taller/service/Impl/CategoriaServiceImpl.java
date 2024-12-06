package com.proyecto.taller.service.Impl;


import com.proyecto.taller.model.Categoria;
import com.proyecto.taller.repository.CategoriaRepository;
import com.proyecto.taller.service.CategoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final Path directorioImagenes;
    private final CategoriaRepository categoriaRepository;

    @Autowired
    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.directorioImagenes = Paths.get("imagenes").toAbsolutePath().normalize();
        this.categoriaRepository = categoriaRepository;

        try {
            Files.createDirectories(this.directorioImagenes);
        } catch (IOException e) {
            throw new RuntimeException("No se pudo crear el directorio para imágenes.", e);
        }
    }

    @Override
    public Categoria crearCategoria(Categoria categoria, MultipartFile imagen) {
        if (imagen != null && !imagen.isEmpty()) {
            String nombreArchivo = guardarImagen(imagen);
            categoria.setFoto(nombreArchivo);
        }
        return categoriaRepository.save(categoria);
    }

    @Override
    public Categoria editarCategoria(Long id, Categoria categoriaActualizada, MultipartFile imagen) {
        Categoria categoriaExistente = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        categoriaExistente.setNombre(categoriaActualizada.getNombre());
        categoriaExistente.setEstado(categoriaActualizada.getEstado());
        categoriaExistente.setDescripcion(categoriaActualizada.getDescripcion());

        if (imagen != null && !imagen.isEmpty()) {
            String nombreArchivo = guardarImagen(imagen);
            categoriaExistente.setFoto(nombreArchivo);
        }

        return categoriaRepository.save(categoriaExistente);
    }

    @Override
    public void eliminarCategoria(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        categoriaRepository.delete(categoria);
    }

    @Override
    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria obtenerCategoriaPorId(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
    }

    private String guardarImagen(MultipartFile imagen) {
        try {
            String nombreArchivo = System.currentTimeMillis() + "_" + imagen.getOriginalFilename();
            Path destino = this.directorioImagenes.resolve(nombreArchivo);
            Files.copy(imagen.getInputStream(), destino);
            return nombreArchivo;
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar la imagen", e);
        }
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

}
