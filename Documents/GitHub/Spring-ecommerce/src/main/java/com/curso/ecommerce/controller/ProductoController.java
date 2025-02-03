package com.curso.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.curso.ecommerce.model.Producto;
import com.curso.ecommerce.model.Usuario;
import com.curso.ecommerce.service.IUsuarioService;
import com.curso.ecommerce.service.ProductoService;
import com.curso.ecommerce.service.UploadFileService;
import com.curso.ecommerce.service.UsuarioServiceImpl;

import jakarta.servlet.http.HttpSession;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import org.slf4j.*;

@Controller
@RequestMapping("/productos")
public class ProductoController {
	// para no usar sout
	private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);

	@Autowired
	private ProductoService productoService;

	@Autowired
	private UploadFileService upload;
	// redicreccionar hacia la vista
@Autowired
	private IUsuarioService usuarioService;
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
	public String save(Producto producto, @RequestParam("img") MultipartFile file, HttpSession session) throws IOException {
		// test
		LOGGER.info("ESTE ES EL OBJETO PRODUCTO DE LA VISTA{}", producto);

	// crear un usuario
		Usuario u = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
		// se pasa el usuario u
		producto.setUsuario(u);
		// crer la log para subir la imgen al servidor y guardar el nombre en la bd
		// ingresa la ig por primera vez
		if (producto.getId() == null) {// cuando se crea un producto
			String nombreImagen = upload.saveImage(file);
			producto.setImagen(nombreImagen);// se guarda enn el campo img el nombre de la img
		} else {//
				// cuando se modifique un producto y se cargue la misma imagen
			
		}
		productoService.save(producto);

		return "redirect:/productos";
	}

//editar un producto,
	// se pasa el id y se bsuca en la bd para ese id y se pasa hacia la vista de
	// editar y se edita y luego se actualiza
//
	// buscar la isntancia del registro para el id que se esta pasando
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, Model model) {
		// se crea un onj d etipo producto
		Producto producto = new Producto();
		Optional<Producto> optionalProducto = productoService.get(id);

		producto = optionalProducto.get();
		LOGGER.info("Producto buscado: {}", producto);
		model.addAttribute("producto", producto);
		return "productos/edit";
	}

	@PostMapping("/update")
	public String update(Producto producto , @RequestParam("img") MultipartFile file)throws IOException  {
		if (file.isEmpty()) {
			// obtener un obj de tipo producto
			Producto p = new Producto();
			// obtenemos la img
			p = productoService.get(producto.getId()).get();
			producto.setImagen(p.getImagen());
		} else {//cuando se edita la imagen 
			
			Producto p=new Producto();
			p=productoService.get(producto.getId()).get();//obtenemos el prod y se elimina la img
			if (!p.getImagen().equals("default.jpg")) {
				upload.deleteImage(p.getImagen());
			}
			
			// se obtiene la img nueva y luego se le pasa al producto
			String nombreImagen = upload.saveImage(file);
			producto.setImagen(nombreImagen);// se guarda enn el campo img el nombre de la img
		}
		productoService.update(producto);
		return "redirect:/productos";
	}

	// eliminar un registro
	@GetMapping("/delete/{id}")

	public String delete(@PathVariable Integer id) {
		//eliminar la imagen cuando no sea la imagen por defecto
		Producto p=new Producto();
		p=productoService.get(id).get();
		if (!p.getImagen().equals("default.jpg")) {
			upload.deleteImage(p.getImagen());
		}
		productoService.delete(id);
		// regresa a la lista de todoos los productos
		return "redirect:/productos";

	}

	// logica para crera y subir la im

}
