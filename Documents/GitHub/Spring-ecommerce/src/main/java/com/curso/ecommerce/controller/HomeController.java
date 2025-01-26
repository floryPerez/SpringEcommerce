package com.curso.ecommerce.controller;

import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.curso.ecommerce.model.Producto;
import com.curso.ecommerce.service.ProductoService;

import ch.qos.logback.classic.Logger;

@Controller
@RequestMapping("/")
public class HomeController {
	// crear un logge
	private final Logger log = (Logger) LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private ProductoService productoService;

	@GetMapping("")
	public String home(Model model) {
//model para que lleve los produ a la vista
		model.addAttribute("productos", productoService.findAll());
		return "usuario/home";
	}

	// crear nuevo metodo que lleva ver producuto hacia la vista productohome
	@GetMapping("productohome/{id}")
	public String productoHome(@PathVariable Integer id, Model model) {
		log.info("Id producto enviado como parámetro {}", id);
		Producto producto=new Producto();
		Optional<Producto> productoOptional=productoService.get(id);
		producto=productoOptional.get();
		model.addAttribute("producto",producto);
		return "usuario/productohome";
	}
}
