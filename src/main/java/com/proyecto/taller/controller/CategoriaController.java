package com.proyecto.taller.controller;

import com.proyecto.taller.model.Categoria;
import com.proyecto.taller.service.CategoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<Categoria> crearCategoria(
            @RequestPart("categoria") Categoria categoria,
            @RequestPart(value = "imagen", required = false) MultipartFile imagen) {
        Categoria nuevaCategoria = categoriaService.crearCategoria(categoria, imagen);
        return ResponseEntity.ok(nuevaCategoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> editarCategoria(
            @PathVariable Long id,
            @RequestPart("categoria") Categoria categoria,
            @RequestPart(value = "imagen", required = false) MultipartFile imagen) {
        Categoria categoriaEditada = categoriaService.editarCategoria(id, categoria, imagen);
        return ResponseEntity.ok(categoriaEditada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Long id) {
        categoriaService.eliminarCategoria(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> listarCategorias() {
        List<Categoria> categorias = categoriaService.listarCategorias();
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> obtenerCategoriaPorId(@PathVariable Long id) {
        Categoria categoria = categoriaService.obtenerCategoriaPorId(id);
        return ResponseEntity.ok(categoria);
    }

      @GetMapping("/imagen/{nombreImagen}")
    public ResponseEntity<Resource> obtenerImagen(@PathVariable String nombreImagen) {
        try {
            Resource resource = categoriaService.cargarImagen(nombreImagen);
            
            return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG) // Ajusta según tipos de imagen que manejes
                .header(HttpHeaders.CONTENT_DISPOSITION, 
                    "inline; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
        } catch (IOException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
