package com.curso.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.ecommerce.model.Usuario;
import com.curso.ecommerce.repository.IUsuarioRepository;
@Service
public class UsuarioServiceImpl implements IUsuarioService {

	
	//inyectar a tarves de autowired
	@Autowired
	private IUsuarioRepository usuarioRepository ;

	@Override
	public Optional<Usuario> findById(Integer id) {
		// TODO Auto-generated method stub
		return usuarioRepository.findById(id);//retiorna un obj como opcional
	}

	@Override
	public Usuario save(Usuario usuario) {
		// TODO Auto-generated method stub
		return usuarioRepository.save(usuario);//guardar el obj usuario
	}

	@Override
	public Optional<Usuario> findByEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}

	@Override
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		return usuarioRepository.findAll();//trae todos los usuarios
	}
	

	


}
