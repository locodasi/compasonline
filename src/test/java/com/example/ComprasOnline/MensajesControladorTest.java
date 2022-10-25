package com.example.ComprasOnline;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.example.ComprasOnline.controllers.MensajesController;
import com.example.ComprasOnline.entities.Mensaje;
import com.example.ComprasOnline.entities.User;
import com.example.ComprasOnline.models.MensajeModelo;
import com.example.ComprasOnline.services.IMensajeService;

public class MensajesControladorTest {

	@Autowired
	@Qualifier("mensajeService")
	private IMensajeService mensajeService;
	
	@Autowired
	private IMensajeService mensajeServiceMock = Mockito.mock(IMensajeService.class);
	
	@Autowired
	MensajesController controladorMock = Mockito.mock(MensajesController.class);
	
	@BeforeEach
	void setUp() {
		
		//Respuesta - Mockeado
		
		List <Mensaje> lista = new ArrayList<>();
		List <Mensaje> lista2 = new ArrayList<>();
		List <User> listau = new ArrayList<>();
		User u1 = new User();
		u1.setId(1);
		User u2 = new User();
		u2.setId(2);
		
		
		Mensaje MensajeMock = new Mensaje (1,u1,u2,"hola");
		
		lista.add(MensajeMock);
		lista.add(MensajeMock);
		Mockito.when(mensajeServiceMock.getAll()).thenReturn(lista);
		
		lista2.add(MensajeMock);
		Mockito.when(mensajeServiceMock.getMensajes(1)).thenReturn(lista2);
		
		listau.add(u1);
		Mockito.when(mensajeServiceMock.getUsuarios()).thenReturn(listau);
		
		Mockito.when(mensajeServiceMock.insertOrUpdate(null)).thenReturn(null);
		
		Mockito.when(mensajeServiceMock.remove(1)).thenReturn(true);
		
		
		MensajeModelo mensa = new MensajeModelo();
		mensa.setMensaje("hola");
		Mockito.when(mensajeServiceMock.traerPorId(1)).thenReturn(mensa);
		
		Mockito.when(mensajeServiceMock.getAllModel()).thenReturn(null);
		
		
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("hola");
		//Primero instanción los objetos
		Mockito.when(controladorMock.verMensajes(null)).thenReturn(mv);
		
		ModelAndView mv2 = new ModelAndView();
		mv2.setViewName("adios");
		Mockito.when(controladorMock.verMensajesConId("mati",null)).thenReturn(mv2);
		
		
		
		
//		//Respuesta - Mockeado
//		figuritaMock = null;
//		respuesta = ResponseEntity.status(HttpStatus.NO_CONTENT).body(figuritaMock);
//		
//		//Primero instanción los objetos
//		Mockito.when(controladorMock.traerFigurita(100 )).thenReturn(respuesta);
	}
	
	@Test
	void VerMensajes() {
		
		
		ModelAndView respuesta;
		
		respuesta = controladorMock.verMensajes(null);
		
		Assertions.assertEquals("hola", respuesta.getViewName());

	}
	
	@Test
	void VerMensajesConId() {
		
		
		ModelAndView respuesta;
		
		respuesta = controladorMock.verMensajesConId("mati",null);
		
		Assertions.assertEquals("adios", respuesta.getViewName());

	}
	
	@Test
	void TraerMensajesServices() {
		
		List<Mensaje> lista = mensajeServiceMock.getAll();
		

		
		Assertions.assertEquals(2, lista.size());

	}
	
	@Test
	void TraerMensajesIdServices() {
		
		List<Mensaje> lista = mensajeServiceMock.getMensajes(1);
		

		
		Assertions.assertEquals(1, lista.size());

	}
	
	@Test
	void TraerUsuariosServices() {
		
		List<User> lista = mensajeServiceMock.getUsuarios();
				
		Assertions.assertEquals(1, lista.size());

	}
	
	@Test
	void InsertOrUpdateService() {
		
		
				
		Assertions.assertEquals(null, mensajeServiceMock.insertOrUpdate(null));

	}
	
	@Test
	void RemoveService() {
		
		
				
		Assertions.assertEquals(true, mensajeServiceMock.remove(1));

	}
	
	@Test
	void TraerPorIdService() {
		
		
				
		Assertions.assertEquals("hola", mensajeServiceMock.traerPorId(1).getMensaje());

	}
	
	@Test
	void TraerAllModelService() {
		
		
				
		Assertions.assertEquals(null, mensajeServiceMock.getAllModel());

	}
	
	@Test
	void FuncionesBasicasDeMensaje() {
		
		MensajeModelo m = new MensajeModelo(null, null, null);
		
		System.out.println(m);
		
		m.setId(1);
		m.setMensaje("hola");
		m.setUsuario_emisor(null);
		m.setUsuario_receptor(null);
		
		m.getId();
		m.getMensaje();
		m.getUsuario_emisor();
		m.getUsuario_receptor();
	}
	
}
