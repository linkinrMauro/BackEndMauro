package com.proyecto.taller.service;


import com.proyecto.taller.model.Inventario;
import com.proyecto.taller.repository.InventarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventarioService {

    private final InventarioRepository inventarioRepository;

    public InventarioService(InventarioRepository inventarioRepository) {
        this.inventarioRepository = inventarioRepository;
    }

    public List<Inventario> listarInventarios() {
        return inventarioRepository.findAll();
    }

    public Optional<Inventario> buscarPorId(Long id) {
        return inventarioRepository.findById(id);
    }
/*
    public List<Inventario> buscarPorProducto(Long productoId) {
        return inventarioRepository.findByProductoId(productoId);
    }
*/
    public Inventario crearInventario(Inventario inventario) {
        return inventarioRepository.save(inventario);
    }
    public Inventario editarInventario(Long id, Inventario inventarioActualizado) {
        return inventarioRepository.findById(id).map(inventario -> {
            inventario.setCantidad(inventarioActualizado.getCantidad());
            inventario.setFechaEntrada(inventarioActualizado.getFechaEntrada());
            inventario.setNombre(inventario.getNombre());
            inventario.setEstado(inventarioActualizado.getEstado());
            return inventarioRepository.save(inventario);
        }).orElseThrow(() -> new RuntimeException("Inventario no encontrado"));
    }

    public void eliminarInventario(Long id) {
        inventarioRepository.deleteById(id);
    }
}
