package com.example.ComprasOnline;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.example.ComprasOnline.converters.ProductoConverter;
import com.example.ComprasOnline.converters.RubroConverter;
import com.example.ComprasOnline.entities.Rubro;
import com.example.ComprasOnline.models.RubroModelo;
import com.example.ComprasOnline.services.IRubroService;

public class ConverterTest {

	@Autowired
	@Qualifier("rubroConverter")
	private RubroConverter rubroConverter;
	
	@Autowired
	@Qualifier("productoConverter")
	private ProductoConverter productoConverter;
	
	@Autowired
	private ProductoConverter productoConvMock = Mockito.mock(ProductoConverter.class);
	
	@Autowired
	private RubroConverter rubroConvMock = Mockito.mock(RubroConverter.class);
	
	@BeforeEach
	void setUp() {

		Mockito.when(rubroConvMock.entityToModel(null)).thenReturn(null);
		
		
		
		Mockito.when(rubroConvMock.modelToEntity(null)).thenReturn(null);
		

	}
	
	@Test
	void rubroEntMod() {

		RubroModelo r= new RubroModelo();
		 r = rubroConvMock.entityToModel(null);

		Assertions.assertTrue(r==null);

	}
	
	@Test
	void rubroModEnt() {
		
		
		Rubro r = new Rubro();
		r=rubroConvMock.modelToEntity(null);
		Assertions.assertTrue(r==null);

	}
}
