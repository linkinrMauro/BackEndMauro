package com.proyecto.taller.controller;


import com.proyecto.taller.model.Inventario;
import com.proyecto.taller.service.InventarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/inventarios")
@CrossOrigin(origins = "*")
public class InventarioController {

    private final InventarioService inventarioService;

    public InventarioController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    @GetMapping
    public List<Inventario> listarInventarios() {
        return inventarioService.listarInventarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventario> buscarInventarioPorId(@PathVariable Long id) {
        return inventarioService.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
/*
    @GetMapping("/producto/{productoId}")
    public List<Inventario> buscarInventarioPorProducto(@PathVariable Long productoId) {
        return inventarioService.buscarPorProducto(productoId);
    }
*/
@PostMapping
public ResponseEntity<Inventario> crearInventario(@RequestBody Inventario inventario) {
    Inventario nuevoInventario = inventarioService.crearInventario(inventario);
    return ResponseEntity.status(201).body(nuevoInventario);
}


    @PutMapping("/{id}")
    public ResponseEntity<Inventario> editarInventario(@PathVariable Long id, @RequestBody Inventario inventarioActualizado) {
        return ResponseEntity.ok(inventarioService.editarInventario(id, inventarioActualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarInventario(@PathVariable Long id) {
        inventarioService.eliminarInventario(id);
        return ResponseEntity.noContent().build();
    }
}
