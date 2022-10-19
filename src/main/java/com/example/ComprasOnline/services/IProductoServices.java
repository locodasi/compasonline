package com.example.ComprasOnline.services;

import java.util.List;

import com.example.ComprasOnline.entities.Producto;
import com.example.ComprasOnline.models.ProductoModelo;



public interface IProductoServices {

	public List<Producto> getAll();
	
	public ProductoModelo insert(ProductoModelo productoModelo);
	
	public ProductoModelo update(ProductoModelo productoModelo);
		
	public boolean remove(int id);
		
	public ProductoModelo traerPorId(int id);
		
	public List<ProductoModelo> getAllModel();
}
