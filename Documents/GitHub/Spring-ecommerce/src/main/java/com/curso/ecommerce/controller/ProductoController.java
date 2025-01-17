package com.curso.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.curso.ecommerce.model.Producto;
import com.curso.ecommerce.model.Usuario;
import com.curso.ecommerce.service.ProductoService;

import java.util.Optional;

import org.slf4j.*;

@Controller
@RequestMapping("/productos")
public class ProductoController {
	// para no usar sout
	private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);

	@Autowired
	private ProductoService productoService;

	// redicreccionar hacia la vista

	@GetMapping("")
	public String show(Model model) {
		// estemodel obj lleva la lista d eprod hacia la vista
		// la variable,y la lista que s emanda a la vista
		model.addAttribute("productos", productoService.findAll());
		return "productos/show";
	}

	@GetMapping("/create")
	public String create() {
		return "productos/create";
	}

	@PostMapping("/save")
	public String save(Producto producto) {
		// test
		LOGGER.info("ESTE ES EL OBJETO PRODUCTO DE LA VISTA{}", producto);
		// crear un usuario
		Usuario u = new Usuario(1, "", "", "", "", "", "", "");
		// se pasa el usuario u
		producto.setUsuario(u);
		productoService.save(producto);

		return "redirect:/productos";
	}
//editar un producto,
	// se pasa el id y se bsuca en la bd para ese id y se pasa hacia la vista de
	// editar y se edita y luego se actualiza
//
	//buscar la isntancia del registro para el id que se esta pasando
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, Model model) {
		//se crea un onj d etipo producto
		Producto producto=new Producto();
		Optional<Producto> optionalProducto=productoService.get(id);
		
		producto=optionalProducto.get();
		LOGGER.info("Producto buscado: {}",producto);
		model.addAttribute("producto", producto);
		return "productos/edit";
	}
	@PostMapping("/update")
	public String update(Producto producto) {
		productoService.update(producto);
		return "redirect:/productos";
	}
	
	//eliminar un registro
	
}
