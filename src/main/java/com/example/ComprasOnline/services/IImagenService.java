package com.example.ComprasOnline.services;

import java.util.List;

import com.example.ComprasOnline.entities.Imagen;
import com.example.ComprasOnline.models.ImagenModelo;

public interface IImagenService {

	public List<Imagen> getAll();
	
	
	public ImagenModelo insertOrUpdate(ImagenModelo imagenModelo);
		
	public boolean remove(int id);
		
	public ImagenModelo traerPorId(int id);
		
	public List<ImagenModelo> getAllModel();
}
