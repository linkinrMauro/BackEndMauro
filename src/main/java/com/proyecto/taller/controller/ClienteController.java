package com.proyecto.taller.controller;

import com.proyecto.taller.model.Cliente;
import com.proyecto.taller.service.ClienteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteService.listarClientes();
    }

    @GetMapping("/{idCliente}")
    public Optional<Cliente> buscarClientePorId(@PathVariable Long idCliente) {
        return clienteService.buscarPorId(idCliente);
    }

    @GetMapping("/nombre/{nombre}")
    public List<Cliente> buscarClientePorNombre(@PathVariable String nombre) {
        return clienteService.buscarPorNombre(nombre);
    }

    @PostMapping
    public Cliente crearCliente(@RequestBody Cliente cliente) {
        return clienteService.crearCliente(cliente);
    }

    @PutMapping("/{idCliente}")
    public Cliente editarCliente(@PathVariable Long idCliente, @RequestBody Cliente cliente) {
        return clienteService.editarCliente(idCliente, cliente);
    }

    @DeleteMapping("/{idCliente}")
    public void eliminarCliente(@PathVariable Long idCliente) {
        clienteService.eliminarCliente(idCliente);
    }
}
