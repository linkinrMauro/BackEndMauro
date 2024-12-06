package com.proyecto.taller.service;


import com.proyecto.taller.model.Proveedor;
import com.proyecto.taller.repository.ProveedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorService {

    private final ProveedorRepository proveedorRepository;

    public ProveedorService(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    public List<Proveedor> listarProveedores() {
        return proveedorRepository.findAll();
    }

    public Optional<Proveedor> buscarPorId(Long id) {
        return proveedorRepository.findById(id);
    }

    public List<Proveedor> buscarPorNombre(String nombre) {
        return proveedorRepository.findByNombreContaining(nombre);
    }

    public Proveedor crearProveedor(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    public Proveedor editarProveedor(Long id, Proveedor proveedorActualizado) {
        return proveedorRepository.findById(id).map(proveedor -> {
            proveedor.setNombre(proveedorActualizado.getNombre());
            proveedor.setDireccion(proveedorActualizado.getDireccion());
            proveedor.setTelefono(proveedorActualizado.getTelefono());
            proveedor.setEmail(proveedorActualizado.getEmail());
            proveedor.setEstado(proveedorActualizado.getEstado());
            return proveedorRepository.save(proveedor);
        }).orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
    }

    public void eliminarProveedor(Long id) {
        proveedorRepository.deleteById(id);
    }
}
