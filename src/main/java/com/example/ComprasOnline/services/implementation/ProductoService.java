package com.example.ComprasOnline.services.implementation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.ComprasOnline.converters.ProductoConverter;
import com.example.ComprasOnline.entities.Producto;
import com.example.ComprasOnline.entities.Rubro;
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
	
	
	
	@Override
	public List<ProductoModelo> getAllModel(){
		
		List<ProductoModelo> listaDeProductos = new ArrayList<ProductoModelo>();
//		System.out.println("ayuda");
//		listaDeProductos.addAll(productoRepository.findAll());
//		System.out.println("gege");
//		List<Rubro> r = new ArrayList<>();
//		
//			r.addAll(listaDeProductos.get(0).getRubros());
//		System.out.println("hhhhh" + r.get(0).getDescripcion());
		for (Producto p: getAll() ) {
//			System.out.println("hola");
			listaDeProductos.add(productoConverter.entityToModel(p));
			
			
		}
	
//		System.out.println(listaDeProductos.get(0).getClass());
//		System.out.println(listaDeProductos.get(0).getNombre());
//		System.out.println(listaDeProductos);
		
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
