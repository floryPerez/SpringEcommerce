package com.curso.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.curso.ecommerce.model.Producto;
import com.curso.ecommerce.service.IOrderService;
import com.curso.ecommerce.service.IUsuarioService;
import com.curso.ecommerce.service.ProductoService;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/administrador")
public class AdministradorController {
	@Autowired
	private ProductoService productoService;
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private IOrderService ordenService;
	@GetMapping("")
	public String home(Model model) {
	
		List<Producto> productos=productoService.findAll();
		model.addAttribute("productos",productos);
		
		return "administrador/home";
	}
	
	@GetMapping("/usuario")
	public String usuarios(Model model) {
		model.addAttribute("usuarios",usuarioService.findAll());
		
		return "administrador/usuarios";
	}
	
	@GetMapping("/ordenes")
	public String ordenes(Model model) {
		model.addAttribute("ordenes",ordenService.findAll());
		return "administrador/ordenes";
	}
	
}
