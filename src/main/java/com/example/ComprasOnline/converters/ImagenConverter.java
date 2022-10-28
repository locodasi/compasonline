package com.example.ComprasOnline.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.example.ComprasOnline.entities.Imagen;
import com.example.ComprasOnline.models.ImagenModelo;


@Component("imagenConverter")
public class ImagenConverter {

	
	public ImagenModelo entityToModel(Imagen imagen) {
		
		return new ImagenModelo(imagen.getId(),imagen.getUrl(),imagen.getFormato());
		
	}
	
	
	
	public Imagen modelToEntity(ImagenModelo imagenModelo) {
		
		return new Imagen(imagenModelo.getId(),imagenModelo.getUrl(),imagenModelo.getFormato());
		
	}
}
