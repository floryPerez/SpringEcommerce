package com.curso.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.curso.ecommerce.model.Producto;
import com.curso.ecommerce.model.Usuario;
import com.curso.ecommerce.service.ProductoService;

import org.slf4j.*;

@Controller
@RequestMapping("/productos")
public class ProductoController {
	//para no usar sout
	private final Logger LOGGER=LoggerFactory.getLogger(ProductoController.class);
	
	@Autowired
	private ProductoService productoService;
	
	//redicreccionar hacia la vista
	
	@GetMapping("")
	public String show() {
		return "productos/show";
	}
	
	@GetMapping("/create")
	public String create() {
		return "productos/create";
	}
	@PostMapping("/save")
	public String save(Producto producto) {
		//test
		LOGGER.info("ESTE ES EL OBJETO PRODUCTO DE LA VISTA{}",producto);
	//crear un usuario
		Usuario u=new Usuario(1,"","","","","","","");
		//se pasa el usuario u
		producto.setUsuario(u);
		productoService.save(producto);
		
		return "redirect:/productos";
	}


}
