package com.example.ComprasOnline.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ComprasOnline.entities.Rubro;

@Repository("rubroRepository")
public interface IRubroRepository extends 
						JpaRepository<Rubro, Serializable> {
	

	public abstract Rubro findByDescripcion(String nombre);
	
	public abstract Rubro findById(int id);
	

}