package com.example.ComprasOnline.converters;

import org.springframework.stereotype.Component;

import com.example.ComprasOnline.entities.User;
import com.example.ComprasOnline.models.UsuarioModelo;

@Component("userConverter")
public class UserConverter {

	public UsuarioModelo entityToModel(User user) {
			
		return new UsuarioModelo(user.getId(), user.getUsuario(), user.getContra(), user.getEmail());		
	}
	
	
	
	public User modelToEntity(UsuarioModelo usuarioModelo) {

		return new User(usuarioModelo.getId(), usuarioModelo.getUsuario(), usuarioModelo.getContra(), usuarioModelo.getEmail(),true);
	}
}
