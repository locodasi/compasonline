package com.example.ComprasOnline.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ComprasOnline.entities.Comentario;

@Repository("comentarioRepository")
public interface IComentarioRepository extends JpaRepository<Comentario, Serializable>  {

	
	public abstract Comentario findById(int id);
	
	//public abstract List<Comentario> findAllById_producto(int id);
}
