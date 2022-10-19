package com.example.ComprasOnline.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.ComprasOnline.entities.Mensaje;

@Repository("mensajeRepository")
public interface IMensajeRepository extends JpaRepository<Mensaje, Serializable>  {

	
	public abstract Mensaje findById(int id);
	
//	@Query("SELECT m FROM mensaje m JOIN FETCH m.usuario_emisor, m.usuario_receptor WHERE m.id = (:id)")
//	public abstract List<Mensaje> findByUsuario_emisorOrUsuario_receptor(@Param("id") int id);
}
