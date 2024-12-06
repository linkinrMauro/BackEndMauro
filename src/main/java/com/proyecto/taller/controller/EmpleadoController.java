package com.proyecto.taller.controller;

import com.proyecto.taller.model.Empleado;
import com.proyecto.taller.service.EmpleadoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/empleados")
@CrossOrigin(origins = "*")
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @GetMapping
    public List<Empleado> listarEmpleados() {
        return empleadoService.listarEmpleados();
    }

    @GetMapping("/{idEmpleado}")
    public Optional<Empleado> buscarEmpleadoPorId(@PathVariable Long idEmpleado) {
        return empleadoService.buscarPorId(idEmpleado);
    }

    @GetMapping("/nombre/{nombre}")
    public List<Empleado> buscarEmpleadoPorNombre(@PathVariable String nombre) {
        return empleadoService.buscarPorNombre(nombre);
    }

    @PostMapping
    public Empleado crearEmpleado(@RequestBody Empleado empleado) {
        return empleadoService.crearEmpleado(empleado);
    }

    @PutMapping("/{idEmpleado}")
    public Empleado editarEmpleado(@PathVariable Long idEmpleado, @RequestBody Empleado empleado) {
        return empleadoService.editarEmpleado(idEmpleado, empleado);
    }

    @DeleteMapping("/{idEmpleado}")
    public void eliminarEmpleado(@PathVariable Long idEmpleado) {
        empleadoService.eliminarEmpleado(idEmpleado);
    }
}
