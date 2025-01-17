package com.curso.ecommerce.service;

import java.util.List;
import java.util.Optional;
import com.curso.ecommerce.model.Producto;

public interface ProductoService {
//solo se definen los metodos

	public Producto save(Producto producto);

	// permite que el objeto que se obtiene existe o no
	public Optional<Producto> get(Integer id);

	public void update(Producto producto);

	public void delete(Integer id);
	public List<Producto>findAll();
}
