package com.proyecto.taller.service;

import com.proyecto.taller.model.Cliente;
import com.proyecto.taller.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarPorId(Long idCliente) {
        return clienteRepository.findById(idCliente);
    }

    public List<Cliente> buscarPorNombre(String nombre) {
        return clienteRepository.findByNombre(nombre);
    }

    public Cliente crearCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente editarCliente(Long idCliente, Cliente cliente) {
        if(clienteRepository.existsById(idCliente)) {
            cliente.setIdCliente(idCliente);
           // cliente.s
            return clienteRepository.save(cliente);
        } else {
            throw new RuntimeException("Cliente no encontrado");
        }
    }

    public void eliminarCliente(Long idCliente) {
        clienteRepository.deleteById(idCliente);
    }
}
