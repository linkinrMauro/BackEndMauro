package com.proyecto.taller.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto.taller.model.Producto;
import com.proyecto.taller.service.ProductoService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private ObjectMapper objectMapper;

    // Endpoint para crear un nuevo producto con imagen
    @PostMapping(consumes = {
        MediaType.MULTIPART_FORM_DATA_VALUE, 
        MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity<?> crearProducto(
            @RequestPart(value = "producto", required = true) String productoJson,
            
            @RequestPart(value = "file", required = false) MultipartFile file) {
        try {
            // Convertir el JSON del producto a un objeto Producto
            Producto producto = objectMapper.readValue(productoJson, Producto.class);
            
            // Crear producto con imagen opcional
            Producto nuevoProducto = productoService.crearProducto(producto, file);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Error al crear producto: " + e.getMessage());
        }
    }

    // Endpoint para listar productos con información de imagen
    @GetMapping
    public ResponseEntity<List<Producto>> listarProductos() {
        List<Producto> productos = productoService.listarProductos();
        return ResponseEntity.ok(productos);
    }

    // Endpoint para obtener imagen de un producto
    @GetMapping("/imagen/{nombreImagen}")
    public ResponseEntity<Resource> obtenerImagen(@PathVariable String nombreImagen) {
        try {
            Resource resource = productoService.cargarImagen(nombreImagen);
            
            return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG) // Ajusta según tipos de imagen que manejes
                .header(HttpHeaders.CONTENT_DISPOSITION, 
                    "inline; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
        } catch (IOException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para actualizar un producto, incluyendo la imagen
    @PutMapping(value = "/{id}", consumes = {"multipart/form-data"})
    public ResponseEntity<?> actualizarProducto(
            @PathVariable Long id,
            @RequestPart("producto") String productoJson,
            @RequestPart(value = "file", required = false) MultipartFile file) {
        try {
            // Convertir el JSON del producto a un objeto Producto
            Producto producto = objectMapper.readValue(productoJson, Producto.class);
            
            // Actualizar producto con imagen opcional
            Producto productoActualizado = productoService.actualizarProducto(id, producto, file);
            return ResponseEntity.ok(productoActualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Error al actualizar producto: " + e.getMessage());
        }
    }

    // Endpoint para obtener un producto por su ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerProductoPorId(@PathVariable Long id) {
        try {
            Producto producto = productoService.obtenerProductoPorId(id);
            if (producto == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Producto no encontrado con ID: " + id);
            }
            return ResponseEntity.ok(producto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al obtener producto: " + e.getMessage());
        }
    }

    // Endpoint para eliminar un producto
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long id) {
        try {
            productoService.eliminarProducto(id);
            return ResponseEntity.ok().body("Producto eliminado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Error al eliminar producto: " + e.getMessage());
        }
    }
}