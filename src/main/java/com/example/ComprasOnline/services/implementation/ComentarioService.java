package com.example.ComprasOnline.services.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.ComprasOnline.converters.ComentarioConverter;
import com.example.ComprasOnline.converters.MensajeConverter;
import com.example.ComprasOnline.entities.Comentario;
import com.example.ComprasOnline.entities.Mensaje;
import com.example.ComprasOnline.entities.User;
import com.example.ComprasOnline.models.ComentarioModelo;
import com.example.ComprasOnline.models.MensajeModelo;
import com.example.ComprasOnline.repositories.IComentarioRepository;
import com.example.ComprasOnline.repositories.IMensajeRepository;
import com.example.ComprasOnline.services.IComentarioService;

@Service("comentarioService")
public class ComentarioService implements IComentarioService{

	@Autowired
	@Qualifier("comentarioRepository")
	private IComentarioRepository comentarioRepository;
	
	@Autowired
	@Qualifier("comentarioConverter")
	private ComentarioConverter comentarioConverter;
	
	@Override
	public List<Comentario> getAll(){	
			
			
		return comentarioRepository.findAll();	
	}
	
	@Override
	public ComentarioModelo traerPorId(int id) {
		
		
		return comentarioConverter.entityToModel(comentarioRepository.findById(id));
		
	};
	
	
	
	
	public List<ComentarioModelo> getAllModel(){
		
		List<ComentarioModelo> listaDecomentarios = new ArrayList<ComentarioModelo>();
		
		for (Comentario p:getAll() ) {
			
			listaDecomentarios.add(comentarioConverter.entityToModel(p));
			
			
		}
	
		
		return listaDecomentarios;
	}
	
	@Override
	public ComentarioModelo insertOrUpdate(ComentarioModelo comentarioModelo) {
		
	
		Comentario comentario = comentarioRepository.save(comentarioConverter.modelToEntity(comentarioModelo));
		
		return comentarioConverter.entityToModel(comentario);
	}
	
	@Override
	public boolean remove(int id) {
		
		try {
			comentarioRepository.deleteById(id);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

		@Override
		public List<ComentarioModelo> traerPorProductoModelo(int id) {
			// TODO Auto-generated method stub
			
//			List<ComentarioModelo> listaComentarioModelos = new ArrayList<>();
//			
//			for (Comentario c : comentarioRepository.findById_producto(id)) {
//				listaComentarioModelos.add(comentarioConverter.entityToModel(c));
//			}
//
//			return listaComentarioModelos;
			return null;
		}


		@Override
		public List<Comentario> traerPorProductoEntidad(int id) {
			// TODO Auto-generated method stub
//			List<Comentario> listaComentario = new ArrayList<>();
//			listaComentario.addAll(comentarioRepository.findById_producto(id));
//			return listaComentario;
			
			return null;
		}

}
