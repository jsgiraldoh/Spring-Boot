package com.test.principal.repositories;

import java.util.List;

import com.test.principal.entities.Usuario;

public interface IUserService {

	Usuario crearUsuario(Usuario usuario);
	
	Usuario actualizarUsuario(Usuario usuario);
	
	Usuario buscarPorId(Integer userId);
	
	List<Usuario> getUsuarios();
	
	void eliminarPorId(Integer userId);
	
}
