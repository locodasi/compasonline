package com.example.ComprasOnline.services;

import java.util.List;

import com.example.ComprasOnline.entities.User;
import com.example.ComprasOnline.models.UsuarioModelo;

public interface IUserServices {

	public List<User> getAll();
	
	public UsuarioModelo insert(UsuarioModelo usuarioModelo);
	
	public UsuarioModelo update(UsuarioModelo usuarioModelo);
	
	public boolean remove(int id);
	
	public User buscarUsuario(String usuario);
	
	public UsuarioModelo buscarUsuarioModelo(String usuario);
}
