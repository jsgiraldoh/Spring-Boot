package com.test.principal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.test.principal.entities.Usuario;
import com.test.principal.repositories.IUserRepository;
import com.test.principal.repositories.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private IUserRepository userRepository;

	@Override
	public Usuario crearUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return userRepository.save(usuario);
	}

	@Override
	public Usuario actualizarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return userRepository.save(usuario);
	}

	@Override
	public Usuario buscarPorId(Integer userId) {
		// TODO Auto-generated method stub
		Optional<Usuario> optionalUsuario = userRepository.findById(userId);
		if(optionalUsuario.isPresent()){
			return optionalUsuario.get();
		}
	    throw new RuntimeException("Usuario no existe"); 
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List getUsuarios() {
		// TODO Auto-generated method stub
		List listUsers = new ArrayList<>();
		userRepository.findAll().forEach(p-> listUsers.add(p));
		return listUsers;
	}

	@Override
	public void eliminarPorId(Integer userId) {
		// TODO Auto-generated method stub
		userRepository.deleteById(userId);
		
	}

}
