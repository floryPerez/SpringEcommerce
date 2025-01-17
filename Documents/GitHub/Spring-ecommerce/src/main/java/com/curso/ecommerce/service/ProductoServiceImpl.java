package com.curso.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.ecommerce.model.Producto;
import com.curso.ecommerce.repository.ProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService {
//se implementan los metodos que estan el el servicio
	// delarar un obj de tipo repositorio, obtiene los metodos crud
	// indica que se esta inyectando un objeto

	@Autowired
	private ProductoRepository productoRepository;

	@Override
	public Producto save(Producto producto) {
		// TODO Auto-generated method stub
		return productoRepository.save(producto);
	}

	@Override
	public Optional<Producto> get(Integer id) {
		return productoRepository.findById(id);

	}

	@Override
	public void update(Producto producto) {
		 productoRepository.save(producto);

	}

	@Override
	public void delete(Integer id) {
		 productoRepository.deleteById(id);

	}

	@Override
	public List<Producto> findAll() {
		return productoRepository.findAll();
	}

}
