package com.proyecto.taller.service;

import com.proyecto.taller.model.Empleado;
import com.proyecto.taller.repository.EmpleadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    public List<Empleado> listarEmpleados() {
        return empleadoRepository.findAll();
    }

    public Optional<Empleado> buscarPorId(Long idEmpleado) {
        return empleadoRepository.findById(idEmpleado);
    }

    public List<Empleado> buscarPorNombre(String nombre) {
        return empleadoRepository.findByNombre(nombre);
    }

    public Empleado crearEmpleado(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    public Empleado editarEmpleado(Long idEmpleado, Empleado empleado) {
        if(empleadoRepository.existsById(idEmpleado)) {
            empleado.setIdEmpleado(idEmpleado);
            return empleadoRepository.save(empleado);
        } else {
            throw new RuntimeException("Empleado no encontrado");
        }
    }

    public void eliminarEmpleado(Long idEmpleado) {
        empleadoRepository.deleteById(idEmpleado);
    }
}
