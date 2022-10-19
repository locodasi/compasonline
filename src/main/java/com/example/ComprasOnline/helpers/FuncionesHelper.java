package com.example.ComprasOnline.helpers;

import java.util.ArrayList;
import java.util.List;

import com.example.ComprasOnline.entities.User;
import com.example.ComprasOnline.models.MensajeModelo;

public class FuncionesHelper {

	public List<MensajeModelo> getMensajes(List<MensajeModelo> mensajes, User usuario){
		
		List<MensajeModelo> mensajesCorrectos = new ArrayList<>();
		
		for (MensajeModelo mensaje : mensajes) {
			
			if(mensaje.getUsuario_emisor().getId() == usuario.getId() || mensaje.getUsuario_receptor().getId() == usuario.getId()) {
				mensajesCorrectos.add(mensaje);
			}
		}
		
		return mensajesCorrectos;
	}
}
