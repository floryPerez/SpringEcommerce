package com.curso.ecommerce.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

//para subir y eliminar una imagen ya cargada 

@Service
public class UploadFileService {
	public String folder = "images//";

	public String saveImage(MultipartFile file) throws IOException {
		/* Si el usuario sube un archivo entra al if */
		// si no es vacio se pasa la img a bits para que se pueda enviar desde el
		// cliente al serv
		if (!file.isEmpty()) {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(folder + file.getOriginalFilename());
			Files.write(path, bytes); // la ruta y la variable bytes que ha transgoramdo la img
			return file.getOriginalFilename();
		}
		return "default.jpg";// si el usuario no sube ningun archivo
	}

	public void deleteImage(String nombre) {
		String ruta = "images//";
		// crear variable d etipo file
		File file = new File(ruta + nombre);
		file.delete();
	}
}
