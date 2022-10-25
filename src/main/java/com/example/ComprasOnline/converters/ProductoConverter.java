package com.example.ComprasOnline.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.example.ComprasOnline.entities.Producto;
import com.example.ComprasOnline.entities.Rubro;
import com.example.ComprasOnline.models.ProductoModelo;
import com.example.ComprasOnline.models.RubroModelo;

@Component("productoConverter")
public class ProductoConverter {

	@Autowired
	@Qualifier("rubroConverter")
	private RubroConverter rubroConverter;
	
	public ProductoModelo entityToModel(Producto producto) {
		
		System.out.println("hola");
		return new ProductoModelo(producto.getId(), producto.getNombre(), producto.getCosto(), producto.getDescripcion(), rubroConverter.entityToModelSet(producto.getRubros()), null);
		
	}
	
	
	
	public Producto modelToEntity(ProductoModelo productoModelo) {
		
		return new Producto(productoModelo.getId(), productoModelo.getNombre(), productoModelo.getCosto(), productoModelo.getDescripcion(), rubroConverter.modeloAEntidadSet(productoModelo.getRubros()), null);
	}
}
