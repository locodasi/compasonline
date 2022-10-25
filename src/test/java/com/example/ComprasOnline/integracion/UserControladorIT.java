package com.example.ComprasOnline.integracion;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.example.ComprasOnline.controllers.UserController;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserControladorIT {

	@LocalServerPort
	private int port ;

	
	@Autowired
	private UserController controlador;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	

	
	@Test
	public void cargarEntorno() throws Exception{
		assertTrue(controlador!=null);
	}
	
	@Test
	public void greetingShouldReturnDefaultMessage() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/crearUsuario",
				String.class)).contains("Sign Up");
	}
	
	@Test
	public void greetingShouldReturnDefaultMessage2() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/login",
				String.class)).contains("Forgot Password?");
	}
}
