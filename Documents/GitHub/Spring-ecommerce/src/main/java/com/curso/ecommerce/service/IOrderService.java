package com.curso.ecommerce.service;

import java.util.List;

import com.curso.ecommerce.model.Orden;
import com.curso.ecommerce.model.Usuario;

public interface IOrderService {

	List<Orden> findAll();//llista de orden
	Orden save(Orden orden);
	String generarNumeroOrden();
	List<Orden> findByUsuario(Usuario usuario);

}
