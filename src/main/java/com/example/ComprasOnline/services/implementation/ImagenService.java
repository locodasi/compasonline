package com.example.ComprasOnline.services.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.ComprasOnline.converters.ImagenConverter;
import com.example.ComprasOnline.entities.Imagen;
import com.example.ComprasOnline.models.ImagenModelo;
import com.example.ComprasOnline.repositories.IImagenRepository;
import com.example.ComprasOnline.services.IImagenService;

@Service("imagenService")
public class ImagenService implements IImagenService{

	@Autowired
	@Qualifier("imagenRepository")
	private IImagenRepository imagenRepository;
	
	@Autowired
	@Qualifier("imagenConverter")
	private ImagenConverter imagenConverter;
	
	@Override
	public List<Imagen> getAll(){	
			
			
		return imagenRepository.findAll();	
	}
	
	
	public ImagenModelo traerPorId(int id) {
		
		
		return imagenConverter.entityToModel(imagenRepository.findById(id));
		
	};
	
	
	
	
	public List<ImagenModelo> getAllModel(){
		
		List<ImagenModelo> listaDeImagenes = new ArrayList<ImagenModelo>();
		
		for (Imagen p:getAll() ) {
			
			listaDeImagenes.add(imagenConverter.entityToModel(p));
			
			
		}
	
		
		return listaDeImagenes;
	}
	
	@Override
	public ImagenModelo insertOrUpdate(ImagenModelo imagenModelo) {
		
	
		Imagen imagen = imagenRepository.save(imagenConverter.modelToEntity(imagenModelo));
		
		return imagenConverter.entityToModel(imagen);
	}
	
	@Override
	public boolean remove(int id) {
		
		try {
			imagenRepository.deleteById(id);
			return true;
		}catch (Exception e) {
			return false;
		}
	}
		
}
