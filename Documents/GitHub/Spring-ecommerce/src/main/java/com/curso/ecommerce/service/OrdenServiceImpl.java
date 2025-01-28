package com.curso.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.ecommerce.model.Orden;
import com.curso.ecommerce.repository.IOrdenRepository;

//clase de servicio para la orden 
@Service
public class OrdenServiceImpl implements IOrderService{

	//inyectar este obj a este servicio
	@Autowired 
	private IOrdenRepository ordenRepository;
	
	@Override
	public Orden save(Orden orden) {
		// TODO Auto-generated method stub
		return ordenRepository.save(orden);
	}

}
