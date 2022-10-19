package com.example.ComprasOnline.services;

import java.util.List;

import com.example.ComprasOnline.entities.Mensaje;
import com.example.ComprasOnline.entities.User;
import com.example.ComprasOnline.models.MensajeModelo;



public interface IMensajeService {

	public List<Mensaje> getAll();
	
	public List<Mensaje> getMensajes(int id);
	
	public List<User> getUsuarios();
	
	public MensajeModelo insertOrUpdate(MensajeModelo mensajeModelo);
		
	public boolean remove(int id);
		
	public MensajeModelo traerPorId(int id);
		
	public List<MensajeModelo> getAllModel();
}
