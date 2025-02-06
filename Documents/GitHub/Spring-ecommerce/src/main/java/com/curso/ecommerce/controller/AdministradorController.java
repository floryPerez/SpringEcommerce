package com.curso.ecommerce.controller;

import java.util.List;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.curso.ecommerce.model.Orden;
import com.curso.ecommerce.model.Producto;
import com.curso.ecommerce.service.IOrderService;
import com.curso.ecommerce.service.IUsuarioService;
import com.curso.ecommerce.service.ProductoService;

import ch.qos.logback.classic.Logger;

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
	
	// crear un logge
		private final Logger log = (Logger) LoggerFactory.getLogger(HomeController.class);
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
	
	//para ver los detalles de las ordenes
	@GetMapping("/detalle/{id}")
	public String detalle(Model model, @PathVariable Integer id ) {
		
		log.info("id de la orden {}",id);
		Orden orden=ordenService.findById(id).get();
		
		//lista de atributos
		
		model.addAttribute("detalles",orden.getDetalle());
		
	//	model.addAttribute("ordenes",ordenService.findAll());
		return "administrador/detalleorden";
	}
	
}
