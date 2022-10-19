package com.example.ComprasOnline.services.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.ComprasOnline.converters.MensajeConverter;
import com.example.ComprasOnline.entities.Mensaje;
import com.example.ComprasOnline.entities.User;
import com.example.ComprasOnline.models.MensajeModelo;
import com.example.ComprasOnline.repositories.IMensajeRepository;
import com.example.ComprasOnline.services.IMensajeService;

@Service("mensajeService")
public class MensajeService implements  IMensajeService {


		@Autowired
		@Qualifier("mensajeRepository")
		private IMensajeRepository mensajeRepository;
		
		@Autowired
		@Qualifier("mensajeConverter")
		private MensajeConverter mensajeConverter;
		
		@Override
		public List<Mensaje> getAll(){	
				
				
			return mensajeRepository.findAll();	
		}
		
		
		public MensajeModelo traerPorId(int id) {
			
			
			return mensajeConverter.entityToModel(mensajeRepository.findById(id));
			
		};
		
		
		
		
		public List<MensajeModelo> getAllModel(){
			
			List<MensajeModelo> listaDeMensajes = new ArrayList<MensajeModelo>();
			
			for (Mensaje p:getAll() ) {
				
				listaDeMensajes.add(mensajeConverter.entityToModel(p));
				
				
			}
		
			
			return listaDeMensajes;
		}
		
		@Override
		public MensajeModelo insertOrUpdate(MensajeModelo mensajeModelo) {
			
		
			Mensaje mensaje = mensajeRepository.save(mensajeConverter.modelToEntity(mensajeModelo));
			
			return mensajeConverter.entityToModel(mensaje);
		}
		
		@Override
		public boolean remove(int id) {
			
			try {
				mensajeRepository.deleteById(id);
				return true;
			}catch (Exception e) {
				return false;
			}
		}
			
			@Override
			public List<Mensaje> getMensajes(int id) {
				// TODO Auto-generated method stub
				//return mensajeRepository.findByUsuario_emisorOrUsuario_receptor(id);
				return null;
			}


			@Override
			public List<User> getUsuarios() {
				// TODO Auto-generated method stub
				return null;
			}
		
}

