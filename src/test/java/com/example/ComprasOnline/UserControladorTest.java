package com.example.ComprasOnline;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.example.ComprasOnline.controllers.ProductosController;
import com.example.ComprasOnline.models.ProductoModelo;
import com.example.ComprasOnline.models.UsuarioModelo;
import com.example.ComprasOnline.services.IProductoServices;
import com.example.ComprasOnline.services.IRubroService;
import com.example.ComprasOnline.services.IUserServices;

public class UserControladorTest {

	@Autowired
	@Qualifier("userService")
	private IUserServices userService;
	
	@Autowired
	private IUserServices userServiceMock = Mockito.mock(IUserServices.class);
	
	
	@BeforeEach
	void setUp() {
		
		Mockito.when(userServiceMock.insert(null)).thenReturn(new UsuarioModelo(1, null, null, null));
		Mockito.when(userServiceMock.update(null)).thenReturn(new UsuarioModelo(2, null, null, null));
		Mockito.when(userServiceMock.remove(1)).thenReturn(true);

	}
	
	@Test
	void InsertService() {
		
		
		UsuarioModelo p=userServiceMock.insert(null);
		Assertions.assertEquals(1, p.getId());

	}
	
	@Test
	void UpdateService() {
		
		
		UsuarioModelo p=userServiceMock.update(null);		
		Assertions.assertEquals(2, p.getId());

	}

	@Test
	void borrarService() {
	
		Assertions.assertEquals(true,userServiceMock.remove(1));

	}

}
