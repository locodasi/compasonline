package com.example.ComprasOnline.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ComprasOnline.entities.Producto;

@Repository("productoRepository")
public interface IProductoRepository extends 
									JpaRepository<Producto, Serializable>  {
		
	public abstract Producto findById(int id);

}
