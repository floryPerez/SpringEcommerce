package com.curso.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.curso.ecommerce.model.Usuario;
import java.util.List;

//mapeo a la bd
@Repository
public interface IUsuarioRepository  extends JpaRepository<Usuario, Integer>{
//metdoo que permita obtener el registro email
	//encontrar por find by y lo que se queire buscar
	
	Optional<Usuario>  findByEmail(String email);
}
