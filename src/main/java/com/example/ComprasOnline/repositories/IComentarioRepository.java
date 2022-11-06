package com.example.ComprasOnline.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.ComprasOnline.entities.Comentario;

@Repository("comentarioRepository")
public interface IComentarioRepository extends JpaRepository<Comentario, Serializable>  {

	
	public abstract Comentario findById(int id);
	
//	@Query("SELECT c FROM Comentario c JOIN FETCH c.id_producto WHERE c.id = (:id)")
//	public abstract List<Comentario> findAllById_productoAndFetchProductoEagerly(@Param("id") int id);
}
