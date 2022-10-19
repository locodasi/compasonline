package com.example.ComprasOnline.services;

import java.util.List;

import com.example.ComprasOnline.entities.User;
import com.example.ComprasOnline.models.UsuarioModelo;

public interface IUserServices {

	public List<User> getAll();
	
	public UsuarioModelo insertOrUpdate(UsuarioModelo usuarioModelo);
	
	public boolean remove(int id);
	
	public User buscarUsuario(String usuario);
}
