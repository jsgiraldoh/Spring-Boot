package com.test.principal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.test.principal.entities.Usuario;
import com.test.principal.repositories.IHelloWorldService;
import com.test.principal.repositories.IUserRepository;

@RestController
public class HelloWorldController {
	
	@Autowired
	private IHelloWorldService helloWorldServiceImpl;
	
	@Autowired
	private IUserRepository userRepository;
	
	@RequestMapping(value="/hello-world", method= RequestMethod.GET)
	public String helloWorld(@RequestParam String username, @RequestParam String email) {
		crearUsuario(username, email);
		return helloWorldServiceImpl.getHelloWorld();
	}
	
	private void crearUsuario(String username, String email){
		Usuario usuario = new Usuario();
		usuario.setName(username);
		usuario.setEmail(email);
		userRepository.save(usuario);
	}

}
