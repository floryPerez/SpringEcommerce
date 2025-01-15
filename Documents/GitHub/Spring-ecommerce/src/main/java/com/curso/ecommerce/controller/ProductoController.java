package com.curso.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/productos")
public class ProductoController {
	//redicreccionar hacia la vista
	
	@GetMapping("")
	public String show() {
		return "productos/show";
	}

}
