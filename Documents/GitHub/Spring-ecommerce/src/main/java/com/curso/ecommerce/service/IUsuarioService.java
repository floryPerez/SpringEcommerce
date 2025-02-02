package com.curso.ecommerce.service;

import java.util.Optional;

import com.curso.ecommerce.model.Usuario;

public interface IUsuarioService  {

	Optional<Usuario> findById(Integer id);
	//guardar un uusario
	
	Usuario save (Usuario usuario);
}
