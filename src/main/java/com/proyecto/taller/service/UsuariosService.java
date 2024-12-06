package com.proyecto.taller.service;

import java.util.List;
import java.util.Optional;

import com.proyecto.taller.model.UsuariosModel;
import com.proyecto.taller.model.DTO.UsuariosDTO;

public interface UsuariosService {
	List<UsuariosDTO> findByUsuarios();
		Optional<UsuariosModel> obtenerUsuarioPorLogin(String login);
    
}
