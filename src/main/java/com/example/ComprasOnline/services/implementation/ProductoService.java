package com.example.ComprasOnline.services.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.ComprasOnline.converters.ProductoConverter;
import com.example.ComprasOnline.entities.Producto;
import com.example.ComprasOnline.models.ProductoModelo;
import com.example.ComprasOnline.repositories.IProductoRepository;
import com.example.ComprasOnline.services.IProductoServices;


@Service("productoService")
public class ProductoService implements IProductoServices{

	@Autowired
	@Qualifier("productoRepository")
	private IProductoRepository productoRepository;
	
	@Autowired
	@Qualifier("productoConverter")
	private ProductoConverter productoConverter;
	
	@Override
	public List<Producto> getAll(){	
			
			
		return productoRepository.findAll();	
	}
	
	
	public ProductoModelo traerPorId(int id) {
		
		
		return productoConverter.entityToModel(productoRepository.findById(id));
		
	};
	
	
	
	
	public List<ProductoModelo> getAllModel(){
		
		List<ProductoModelo> listaDeProductos = new ArrayList<ProductoModelo>();
		
		for (Producto p:getAll() ) {
			
			listaDeProductos.add(productoConverter.entityToModel(p));
			
			
		}
	
		
		return listaDeProductos;
	}
	
	@Override
	public ProductoModelo insert(ProductoModelo productoModelo) {
		
	
		Producto p =productoConverter.modelToEntity(productoModelo);
		//System.out.println("dd: " + p);
		p.setId(-1);
		Producto producto = productoRepository.save(p);
		
		//System.out.println("bbbb");
		return productoConverter.entityToModel(producto);
	}
	
	@Override
	public ProductoModelo update(ProductoModelo productoModelo) {
		
	
		Producto p =productoConverter.modelToEntity(productoModelo);
		//System.out.println("dd: " + p);
		//p.setId(-1);
		Producto producto = productoRepository.save(p);
		
		//System.out.println("bbbb");
		return productoConverter.entityToModel(producto);
	}
	
	@Override
	public boolean remove(int id) {
		
		try {
			productoRepository.deleteById(id);
			return true;
		}catch (Exception e) {
			return false;
		}
		
	}
}
