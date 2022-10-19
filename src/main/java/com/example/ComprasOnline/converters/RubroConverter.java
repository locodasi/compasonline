package com.example.ComprasOnline.converters;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.example.ComprasOnline.entities.Rubro;
import com.example.ComprasOnline.models.RubroModelo;

@Component("rubroConverter")
public class RubroConverter {
	
	public RubroModelo entityToModel(Rubro rubro) {
		
		return new RubroModelo(rubro.getId(), rubro.getDescripcion());
		
	}
	
	public Set<RubroModelo> entityToModelSet(Set<Rubro> rubros) {
		
		Set<RubroModelo>  lista = new HashSet<>();
		
		for(Rubro r: rubros) {
			
			lista.add(entityToModel(r));
			
			
		}
		
		
		return lista;
		
	}
	
	
	
	public Rubro modelToEntity(RubroModelo rubroModelo) {
		
		return new Rubro(rubroModelo.getId(), rubroModelo.getDescripcion());
			
	}
	
	public Set<Rubro> modeloAEntidadSet(Set<RubroModelo> rubros){
		
		//System.out.println("rubros Modelo:" + rubros);
		Set<Rubro>  lista = new HashSet<>();
		
		for(RubroModelo r: rubros) {
			
			lista.add(modelToEntity(r));
			
			
		}
		
		//System.out.println("rubros Entidad:" + lista);
		
		return lista;
	}
}
