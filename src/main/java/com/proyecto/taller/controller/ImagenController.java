package com.proyecto.taller.controller;



import com.proyecto.taller.model.ImagenModel;
import com.proyecto.taller.model.UsuariosModel;
import com.proyecto.taller.service.ImagenService;
import com.proyecto.taller.service.UsuariosService;
import com.proyecto.taller.service.Impl.UsuariosServiceImpl;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
@Getter
@Setter
@RestController
@RequestMapping("/imagenes")
@CrossOrigin(origins = "*")
public class ImagenController {
  @Autowired
    private ImagenService imagenService;

    @Autowired
    private UsuariosService usuarioService;  // Usa la interfaz en lugar de la implementación concreta

    @PostMapping("/subir/{login}")
public ResponseEntity<Map<String, String>> subirImagen(@PathVariable String login, @RequestParam("archivo") MultipartFile archivo) throws IOException {
    Optional<UsuariosModel> usuarioOpt = usuarioService.obtenerUsuarioPorLogin(login);

    if (usuarioOpt.isPresent()) {
        UsuariosModel usuario = usuarioOpt.get();
        ImagenModel imagen = imagenService.guardarImagen(archivo, usuario);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Imagen subida exitosamente");
        response.put("ruta", imagen.getRuta());
        
        return ResponseEntity.ok(response);
    } else {
        throw new RuntimeException("Usuario no encontrado");
    }
}

@GetMapping("/ver/{login}")
public ResponseEntity<byte[]> obtenerImagen(@PathVariable String login) throws IOException {
    Optional<UsuariosModel> usuarioOpt = usuarioService.obtenerUsuarioPorLogin(login);

    if (usuarioOpt.isPresent()) {
        UsuariosModel usuario = usuarioOpt.get();
        if (usuario.getImagen() != null) {
            Path rutaArchivo = Paths.get(usuario.getImagen().getRuta());
            byte[] imagenBytes = Files.readAllBytes(rutaArchivo);
            return ResponseEntity.ok().body(imagenBytes);
        } else {
            return ResponseEntity.noContent().build();
        }
    } else {
        return ResponseEntity.notFound().build();
    }
}


//////////////////////Producto
 // Subir imagen para un producto
 @PostMapping("/subir/producto/{productoId}")
 public ResponseEntity<Map<String, String>> subirImagenProducto(
         @PathVariable Long productoId,
         @RequestParam("archivo") MultipartFile archivo) throws IOException {
     
     ImagenModel imagen = imagenService.guardarImagenProducto(archivo, productoId);

     Map<String, String> response = new HashMap<>();
     response.put("message", "Imagen subida exitosamente");
     response.put("ruta", imagen.getRuta());
     
     return ResponseEntity.ok(response);
 }

  // Eliminar imagen por ID
  @DeleteMapping("/eliminar/{imagenId}")
  public ResponseEntity<Map<String, String>> eliminarImagen(@PathVariable Long imagenId) {
      imagenService.eliminarImagen(imagenId);

      Map<String, String> response = new HashMap<>();
      response.put("message", "Imagen eliminada exitosamente");
      
      return ResponseEntity.ok(response);
  }

  // Eliminar todas las imágenes de un producto
  @DeleteMapping("/eliminar/todas/{productoId}")
  public ResponseEntity<Map<String, String>> eliminarTodasImagenesDeProducto(@PathVariable Long productoId) {
      imagenService.eliminarTodasImagenesProducto(productoId);

      Map<String, String> response = new HashMap<>();
      response.put("message", "Todas las imágenes del producto han sido eliminadas");
      
      return ResponseEntity.ok(response);
  }

 // Desvincular imagen de un producto
    @PutMapping("/desvincular/{imagenId}")
    public ResponseEntity<Map<String, String>> desvincularImagen(@PathVariable Long imagenId) {
        imagenService.desvincularImagenDeProducto(imagenId);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Imagen desvinculada del producto");

        return ResponseEntity.ok(response);
    }

}

