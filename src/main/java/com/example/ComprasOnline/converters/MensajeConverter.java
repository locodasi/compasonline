package com.example.ComprasOnline.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.example.ComprasOnline.entities.Mensaje;
import com.example.ComprasOnline.models.MensajeModelo;

@Component("mensajeConverter")
public class MensajeConverter {

	@Autowired
	@Qualifier("userConverter")
	private UserConverter userConverter;
	
	public MensajeModelo entityToModel(Mensaje mensaje) {
		
		return new MensajeModelo(userConverter.entityToModel(mensaje.getUsuario_emisor()), userConverter.entityToModel(mensaje.getUsuario_receptor()), mensaje.getMensaje());
		
	}
	
	
	
	public Mensaje modelToEntity(MensajeModelo mensajeModelo) {
		
		return new Mensaje(mensajeModelo.getId(),userConverter.modelToEntity(mensajeModelo.getUsuario_emisor()), userConverter.modelToEntity(mensajeModelo.getUsuario_receptor()) ,mensajeModelo.getMensaje());
		
	}
}
