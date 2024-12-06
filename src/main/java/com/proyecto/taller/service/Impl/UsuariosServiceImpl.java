package com.proyecto.taller.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.taller.model.UsuariosModel;
import com.proyecto.taller.model.DTO.UsuariosDTO;
import com.proyecto.taller.repository.UsuariosRepo;
import com.proyecto.taller.service.UsuariosService;

@Service
public class UsuariosServiceImpl implements UsuariosService {
	@Autowired
	public UsuariosRepo repositorio;

	@Override
	public List<UsuariosDTO> findByUsuarios() {
		return repositorio.findBy();
	}
	
		@Override
    public Optional<UsuariosModel> obtenerUsuarioPorLogin(String login) {
        return repositorio.findByLogin(login);
}


}