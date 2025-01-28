package com.curso.ecommerce.service;

import java.util.List;

import com.curso.ecommerce.model.Orden;

public interface IOrderService {

	List<Orden> findAll();
	Orden save(Orden orden);
	
}
