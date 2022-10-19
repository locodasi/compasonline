package com.example.ComprasOnline;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class ComprasOnlineApplicationTests {

	@Test
	void contextLoads() {
		
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
		String passEncriptado = "Admin"; //El pass que quieras para el usuario //Admin
		System.out.println("PALABRA: " +passEncriptado +" "+pe.encode(passEncriptado));
	}
}
