package com.curso.ecommerce.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.curso.ecommerce.model.DetalleOrden;
import com.curso.ecommerce.model.Orden;
import com.curso.ecommerce.model.Producto;
import com.curso.ecommerce.model.Usuario;
import com.curso.ecommerce.repository.IUsuarioRepository;
import com.curso.ecommerce.service.IUsuarioService;
import com.curso.ecommerce.service.ProductoService;

import ch.qos.logback.classic.Logger;

@Controller
@RequestMapping("/")
public class HomeController {
	// crear un logge
	private final Logger log = (Logger) LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private ProductoService productoService;

	
	//declarar un obj de tipo usuario
	@Autowired
	private IUsuarioService usuarioService;
	
	// crear dos variables
	// lista de detalles de la orde
	List<DetalleOrden> detalles = new ArrayList<DetalleOrden>();

	// detalles de la orden
	Orden orden = new Orden();

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
		Producto producto = new Producto();
		Optional<Producto> productoOptional = productoService.get(id);
		producto = productoOptional.get();
		model.addAttribute("producto", producto);
		return "usuario/productohome";
	}

	@PostMapping("/cart")
	public String addCart(@RequestParam Integer id, @RequestParam Integer cantidad, Model model) {
		DetalleOrden detalleOrden = new DetalleOrden();
		Producto producto = new Producto();
		double sumaTotal = 0;// sumar el total de tooso los productos

		// buscar el producto

		Optional<Producto> optionalProducto = productoService.get(id);
		log.info("Producto añadido: {}", optionalProducto.get());
		log.info("Cantidad:{}", cantidad);
		producto = optionalProducto.get();

		detalleOrden.setCantidad(cantidad);
		detalleOrden.setPrecio(producto.getPrecio());
		detalleOrden.setNombre(producto.getNombre());
		detalleOrden.setTotal(producto.getPrecio() * cantidad);
		detalleOrden.setProducto(producto);

		// validacion para que el produc no se agrege mas de una vez

		Integer idProducto = producto.getId();
		boolean ingresado = detalles.stream().anyMatch(p -> p.getProducto().getId() == idProducto);

		if (!ingresado) {// si no es true
			// añadir cada detalle a la lista
			detalles.add(detalleOrden);
		}

		sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();

		orden.setTotal(sumaTotal);
		// llevar a la vista

		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);// recibir en la vista esas variables y mostrar información
		return "usuario/carrito";
	}

	// quitar un producto del carrito
	@GetMapping("delete/cart/id")
	public String deleteProductoCart(@PathVariable Integer id, Model model) {

		// crear lista nueva de productos
		List<DetalleOrden> ordenesNueva = new ArrayList<DetalleOrden>();

		for (DetalleOrden detalleOrden : detalles) {
			if (detalleOrden.getProducto().getId() != id) {
				ordenesNueva.add(detalleOrden);
			}
		}
//poner la nueva lista con los prod restantes

		detalles = ordenesNueva;

		double sumaTotal = 0;

		sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();
//se pone el total de la orden

		orden.setTotal(sumaTotal);
		// se manda el total las ordenes
		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);// recibir en la vista esas variables y mostrar información
		return "usuario/carrito";

	}

	// getmapin

	@GetMapping("/getCar")
	public String getCart(Model model) {

		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);// recibir en la vista esas variables y mostrar información
		return "/usuario/carrito";
	}

	// ver resumen de la orden

	@GetMapping("/order")
	public String order(Model model) {
		//obteermos un usuario y lo pasamos a la vista
		Usuario usuario=usuarioService.findById(1).get();//por cuestiones de seg 
		
		model.addAttribute("cart", detalles);
		model.addAttribute("orden", orden);
		
		model.addAttribute("usuario", usuario);//cambiar esto por sesiones login
		return "/usuario/resumenorden";
	}
	
	//mostrar la informacion del producto
	

}
