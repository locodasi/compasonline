package com.example.ComprasOnline.integracion;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.ComprasOnline.models.ImagenModelo;
import com.example.ComprasOnline.services.IImagenService;

@SpringBootTest
public class ImagenIT {

	@Autowired
	@Qualifier("imagenService")
	private IImagenService imagenService;
	
	@Test
	void InsertImagen() {
		
		
		ImagenModelo i= new ImagenModelo(1, "casa.jpg", "jpg");
		ImagenModelo i2 = new ImagenModelo(); 
		i2= imagenService.insertOrUpdate(i);
		
		Assertions.assertTrue(i.getFormato()== i2.getFormato());	
		//fail();
		
	}
	
	@Test
	void TraerImagen() {
		
		
		ImagenModelo i2 = new ImagenModelo(); 
		i2= imagenService.traerPorId(1);
		
		Assertions.assertTrue(i2.getId()==1);	
		//fail();
		
	}
	
	@Test
	void ModificarImagen() {
		
		ImagenModelo i= new ImagenModelo(1, "seno.jpg", "jpg");
		ImagenModelo i2 = new ImagenModelo(); 
		i2= imagenService.insertOrUpdate(i);
		
		Assertions.assertTrue(i2.getUrl()==i.getUrl());	
		//fail();
		
	}
	
	@Test
	void EliminarImagen() {
		
		imagenService.remove(1);
		//fail();
		
	}
}
