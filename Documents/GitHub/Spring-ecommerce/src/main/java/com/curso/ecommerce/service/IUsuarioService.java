package com.curso.ecommerce.service;

import java.util.List;
import java.util.Optional;

import com.curso.ecommerce.model.Usuario;

public interface IUsuarioService  {

	//metdoo para obtner todos los usuarios
	List<Usuario> findAll();
	Optional<Usuario> findById(Integer id);
	//guardar un uusario
	
	Usuario save (Usuario usuario);
	
	//
	Optional<Usuario> findByEmail(String email);
}
