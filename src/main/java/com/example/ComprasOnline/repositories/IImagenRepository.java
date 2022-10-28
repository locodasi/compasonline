package com.example.ComprasOnline.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ComprasOnline.entities.Imagen;
import com.example.ComprasOnline.entities.Mensaje;

@Repository("imagenRepository")
public interface IImagenRepository extends JpaRepository<Imagen, Serializable> {
	
	public abstract Imagen findById(int id);

}
