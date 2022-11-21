package com.example.ComprasOnline;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.example.ComprasOnline.controllers.ProductosController;
import com.example.ComprasOnline.models.ProductoModelo;
import com.example.ComprasOnline.services.IProductoServices;
import com.example.ComprasOnline.services.IRubroService;

public class ProductosControladorTest {

	@Autowired
	@Qualifier("productoService")
	private IProductoServices productoService;
	
	@Autowired
	@Qualifier("rurboService")
	private IRubroService rubroService;
	
	@Autowired
	private IRubroService rubroServiceMock = Mockito.mock(IRubroService.class);
	
	@Autowired
	private IProductoServices productoServiceMock = Mockito.mock(IProductoServices.class);
	
	@Autowired
	ProductosController controladorMock = Mockito.mock(ProductosController.class);
	
	@BeforeEach
	void setUp() {
		
		Mockito.when(productoServiceMock.insert(null)).thenReturn(new ProductoModelo(1, null, 0, null, null, null));
		Mockito.when(productoServiceMock.update(null)).thenReturn(new ProductoModelo(2, null, 0, null, null, null));
		Mockito.when(rubroServiceMock.getAll()).thenReturn(null);
		Mockito.when(rubroServiceMock.insertOrUpdate(null)).thenReturn(null);

	}
	
	@Test
	void InsertService() {
		
		
		ProductoModelo p=productoServiceMock.insert(null);
		Assertions.assertEquals(1, p.getId());

	}
	
	@Test
	void UpdateService() {
		
		
		ProductoModelo p=productoServiceMock.update(null);		
		Assertions.assertEquals(2, p.getId());

	}
	
	@Test
	void getRubrosService() {
		
		
				
		Assertions.assertEquals(null, rubroServiceMock.getAll());

	}
	
	@Test
	void RubrosInsertOrUpdateService() {
		
		
				
		Assertions.assertEquals(null, rubroServiceMock.insertOrUpdate(null));

	}
}
