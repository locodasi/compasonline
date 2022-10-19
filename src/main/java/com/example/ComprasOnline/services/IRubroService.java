package com.example.ComprasOnline.services;

import java.util.List;

import com.example.ComprasOnline.entities.Rubro;
import com.example.ComprasOnline.models.RubroModelo;

public interface IRubroService {
				
	public List<Rubro> getAll();
		
	public RubroModelo insertOrUpdate(RubroModelo rubroModelo);
		
	public boolean remove(int id);
		
	public RubroModelo traerPorId(int id);
		
	public List<RubroModelo> getAllModel();

}
