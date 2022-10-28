package com.example.ComprasOnline.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.example.ComprasOnline.entities.Comentario;
import com.example.ComprasOnline.entities.Mensaje;
import com.example.ComprasOnline.models.ComentarioModelo;
import com.example.ComprasOnline.models.MensajeModelo;

@Component("comentarioConverter")
public class ComentarioConverter {

	@Autowired
	@Qualifier("userConverter")
	private UserConverter userConverter;
	
	@Autowired
	@Qualifier("productoConverter")
	private ProductoConverter productoConverter;
	
	public ComentarioModelo entityToModel(Comentario comentario) {
		
		return new ComentarioModelo(comentario.getId(),userConverter.entityToModel(comentario.getUsuario()), productoConverter.entityToModel(comentario.getProducto()), comentario.getComentario());
		
	}
	
	
	
	public Comentario modelToEntity(ComentarioModelo comentarioModelo) {
		
		return new Comentario(comentarioModelo.getId(),userConverter.modelToEntity(comentarioModelo.getUsuario()), productoConverter.modelToEntity(comentarioModelo.getProducto()) ,comentarioModelo.getComentario());
		
	}
}
