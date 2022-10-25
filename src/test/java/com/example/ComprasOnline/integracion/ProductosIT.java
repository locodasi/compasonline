package com.example.ComprasOnline.integracion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.ComprasOnline.entities.Producto;
import com.example.ComprasOnline.models.ProductoModelo;
import com.example.ComprasOnline.models.RubroModelo;
import com.example.ComprasOnline.services.IProductoServices;
import com.example.ComprasOnline.services.IRubroService;


@SpringBootTest
public class ProductosIT {

	@Autowired
	@Qualifier("productoService")
	private IProductoServices productoService;
	
	@Autowired
	@Qualifier("rubroService")
	private IRubroService rubroService;
	
	@Test
	void InsertProducto() {
		
		Set<RubroModelo> l = new HashSet<>();
		l.addAll(rubroService.getAllModel());
		ProductoModelo pro = new ProductoModelo(0,"casa",200,null,l,null);
		ProductoModelo pro2 = new ProductoModelo();
		
		pro2= productoService.insert(pro);
		
		Assertions.assertTrue(pro.getDescripcion()== pro2.getDescripcion());	
		//fail();
		
	}
	

}
