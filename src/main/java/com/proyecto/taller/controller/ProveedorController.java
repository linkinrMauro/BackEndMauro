package com.proyecto.taller.controller;


import com.proyecto.taller.model.Proveedor;
import com.proyecto.taller.service.ProveedorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/proveedores")
@CrossOrigin(origins = "*")
public class ProveedorController {

    private final ProveedorService proveedorService;

    public ProveedorController(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    @GetMapping
    public List<Proveedor> listarProveedores() {
        return proveedorService.listarProveedores();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> buscarProveedorPorId(@PathVariable Long id) {
        Optional<Proveedor> proveedor = proveedorService.buscarPorId(id);
        return proveedor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/nombre/{nombre}")
    public List<Proveedor> buscarProveedorPorNombre(@PathVariable String nombre) {
        return proveedorService.buscarPorNombre(nombre);
    }

    @PostMapping
    public Proveedor crearProveedor(@RequestBody Proveedor proveedor) {
        return proveedorService.crearProveedor(proveedor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Proveedor> editarProveedor(@PathVariable Long id, @RequestBody Proveedor proveedorActualizado) {
        return ResponseEntity.ok(proveedorService.editarProveedor(id, proveedorActualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProveedor(@PathVariable Long id) {
        proveedorService.eliminarProveedor(id);
        return ResponseEntity.noContent().build();
    }
}
