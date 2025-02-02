package com.curso.ecommerce.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.curso.ecommerce.model.Usuario;
import com.curso.ecommerce.service.IUsuarioService;

import ch.qos.logback.classic.Logger;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	// para no usar sout
	private final Logger logger = (Logger) LoggerFactory.getLogger(ProductoController.class);

	@Autowired
	private IUsuarioService usuarioService;

	// metodo para mostar la pag de registro

	// usuario/registro
	@GetMapping("/registro")
	public String create() {

		return "usuario/registro";
	}

	//

	@PostMapping("/save")
	public String save(Usuario usuario) {
		// impresion de los datos del usuario

		logger.info("usuario Registrado: {}", usuario);
		// poner la propiedad

		usuario.setTipo("USER");
		usuarioService.save(usuario);// guardar el usuario

		return "redirect:/";// para que lance al home una vez registrado
	}

}
