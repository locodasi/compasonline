package com.example.ComprasOnline.services.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.ComprasOnline.converters.RubroConverter;
import com.example.ComprasOnline.entities.Rubro;
import com.example.ComprasOnline.models.RubroModelo;
import com.example.ComprasOnline.repositories.IRubroRepository;
import com.example.ComprasOnline.services.IRubroService;

@Service("rubroService")
public class RubroService implements  IRubroService{

	@Autowired
	@Qualifier("rubroRepository")
	private IRubroRepository rubroRepository;
	
	@Autowired
	@Qualifier("rubroConverter")
	private RubroConverter rubroConverter;
	
	@Override
	public List<Rubro> getAll(){	
			
			
		return rubroRepository.findAll();	
	}
	
	
	public RubroModelo traerPorId(int id) {
		
		
		return rubroConverter.entityToModel(rubroRepository.findById(id));
		
	};
	
	
	
	
	public List<RubroModelo> getAllModel(){
		
		List<RubroModelo> listaDeRubros = new ArrayList<RubroModelo>();
		
		for (Rubro p:getAll() ) {
			
			listaDeRubros.add(rubroConverter.entityToModel(p));
			
			
		}
	
		
		return listaDeRubros;
	}
	
	@Override
	public RubroModelo insertOrUpdate(RubroModelo rubroModelo) {
		
	
		
		Rubro rubro = rubroRepository.save(rubroConverter.modelToEntity(rubroModelo));
		
		return rubroConverter.entityToModel(rubro);
	}
	
	@Override
	public boolean remove(int id) {
		
		try {
			rubroRepository.deleteById(id);
			return true;
		}catch (Exception e) {
			return false;
		}
		
	}
}
