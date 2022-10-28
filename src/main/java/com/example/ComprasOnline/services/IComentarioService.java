package com.example.ComprasOnline.services;

import java.util.List;

import com.example.ComprasOnline.entities.Comentario;

import com.example.ComprasOnline.entities.User;
import com.example.ComprasOnline.models.ComentarioModelo;


public interface IComentarioService {

	public List<Comentario> getAll();
	
	
	public ComentarioModelo insertOrUpdate(ComentarioModelo comentarioModelo);
		
	public boolean remove(int id);
		
	public ComentarioModelo traerPorId(int id);
	
	public List<ComentarioModelo> traerPorProductoModelo(int id);
	
	public List<Comentario> traerPorProductoEntidad(int id);
		
	public List<ComentarioModelo> getAllModel();
}
