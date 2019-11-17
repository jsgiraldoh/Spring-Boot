package com.test.principal.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.test.principal.entities.Usuario;
import com.test.principal.repositories.IUserService;

@RestController
@RequestMapping("/user")
public class UserServiceController {

	@Autowired
	private IUserService userService;

	@RequestMapping(method = RequestMethod.POST)
	public Usuario saveUser(@RequestBody Usuario usuario) {
		return userService.crearUsuario(usuario);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public Usuario updateUser(@RequestBody Usuario usuario) {
		return userService.actualizarUsuario(usuario);
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Usuario> findAll() {
		return userService.getUsuarios();
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public void deleteUser(@RequestBody Usuario usuario) {
		userService.eliminarPorId(usuario.getId());
	}
	
	@RequestMapping(value = "/buscarPorId", method = RequestMethod.GET)
	public Usuario findById(@RequestParam Integer id){
		return userService.buscarPorId(id);
	}

	
}
