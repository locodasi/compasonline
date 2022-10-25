package com.example.ComprasOnline.integracion;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.ComprasOnline.entities.Mensaje;
import com.example.ComprasOnline.entities.Rubro;
import com.example.ComprasOnline.entities.User;
import com.example.ComprasOnline.services.IMensajeService;
import com.example.ComprasOnline.services.IRubroService;
import com.example.ComprasOnline.services.IUserServices;



@SpringBootTest
public class GettersIT {

	@Autowired
	@Qualifier("userService")
	private IUserServices userService;
	
	@Autowired
	@Qualifier("rubroService")
	private IRubroService rubroService;
	
	@Autowired
	@Qualifier("mensajeService")
	private IMensajeService mensajeService;
	
	@Test
	void traerUsuarios() {
		
		List<User> lista = userService.getAll();
		

		Assertions.assertTrue(lista.size()>=2);
		Assertions.assertTrue(lista.get(0).getId()==1);

		
		//fail();
		
	}
	
	@Test
	void traerRubros() {
		
		List<Rubro> lista = rubroService.getAll();
		
		Assertions.assertTrue(lista.size()>=3);
		Assertions.assertTrue(lista.get(1).getId()==2);

		
		//fail();
		
	}
	

	

}
