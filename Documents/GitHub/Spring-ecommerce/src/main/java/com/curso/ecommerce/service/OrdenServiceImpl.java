package com.curso.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

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

	@Override
	public List<Orden> findAll() {
		// TODO Auto-generated method stub
		return ordenRepository.findAll();
	}
	//crear metodo que muestra la secuencial de orden
	
	//OOOO10
	public String generarNumeroOrden() {
		
		int numero =0;
		
		//incrementar el numero de la orden 
		String numeroConcatenado="";
		//crear lista
		List<Orden> ordenes =findAll();
		
		//lista de enteros
		List<Integer> numeros=new ArrayList<Integer>();
		//poner el num de orden
		
		
		ordenes.stream().forEach(o->numeros.add(Integer.parseInt(o.getNumero()
				)));
		
		if(ordenes.isEmpty()) {
			numero=1;
			
		}else {
			numero=numeros.stream().max(Integer::compare).get();
		numero++;
		
		}
		
		//pasar esto a cadena
		
		if(numero<10) {//0000000001
			numeroConcatenado="000000000"+String.valueOf(numero);
			
		}
		else if(numero<100) {
			numeroConcatenado="00000000"+String.valueOf(numero);
			
		}
		else if(numero<1000) {
			numeroConcatenado="0000000"+String.valueOf(numero);
			
		}
		else if(numero<10000) {
			numeroConcatenado="0000000"+String.valueOf(numero);
			
		}
		return numeroConcatenado;
		
		
	}

}
