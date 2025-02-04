package com.curso.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.curso.ecommerce.model.Orden;
import com.curso.ecommerce.model.Usuario;
import com.curso.ecommerce.service.IOrderService;
import com.curso.ecommerce.service.IUsuarioService;

import ch.qos.logback.classic.Logger;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	// para no usar sout
	private final Logger logger = (Logger) LoggerFactory.getLogger(ProductoController.class);

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private IOrderService ordenService;

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

	// mostrar la vista para que el usuario pueda logearse
	@GetMapping("/login")
	public String login() {

		return "usuario/login";
	}

	//
	@PostMapping("/acceder") // terminacion de url ACCEDER
	// parametro obj de tipo usuario
	public String acceder(Usuario usuario, HttpSession sesion) {
		logger.info("Accesos: {}", usuario);
		// validar el correo se encuentre en la bd

		Optional<Usuario> user = usuarioService.findByEmail(usuario.getEmail());

		// logger.info("Usuario obtenido de bd : {}", user.get());

		if (user.isPresent()) {// so el usuario esta presnete
			// guardamos la sesion del usuario
			sesion.setAttribute("idusuario", user.get().getId());// guradar el id del usuario
			if (user.get().getTipo().equals("ADMIN")) {
				return "redirect:/administrador";

			} else {
				return "redirect:/";
			}

		} else {
			logger.info("USUARIO NO EXISTE");
		}
		return "redirect:/";
	}

	// obtener copras
	@GetMapping("/compras")
	public String obtenerCompras(Model model, HttpSession session) {
		// enviar id de la sesion a la vista
		model.addAttribute("sesion", session.getAttribute("idusuario"));
		Usuario usuario = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
		List<Orden> ordenes = ordenService.findByUsuario(usuario);

		// pasamos hacia la vista compras
		model.addAttribute("ordenes", ordenes);

		return "usuario/compras";
	}
}
