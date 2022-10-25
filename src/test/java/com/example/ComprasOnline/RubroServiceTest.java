package com.example.ComprasOnline;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.example.ComprasOnline.entities.Rubro;
import com.example.ComprasOnline.models.RubroModelo;
import com.example.ComprasOnline.services.IRubroService;

public class RubroServiceTest {

	@Autowired
	@Qualifier("rurboService")
	private IRubroService rurbojeService;
	
	@Autowired
	private IRubroService rubrojeServiceMock = Mockito.mock(IRubroService.class);
	
	@BeforeEach
	void setUp() {
		
		List<Rubro> lista = new ArrayList<>();
		List<RubroModelo> lista2 = new ArrayList<>();
		
		lista.add(new Rubro());
		lista2.add(new RubroModelo());
		
		Mockito.when(rubrojeServiceMock.getAll()).thenReturn(lista);
		
		Mockito.when(rubrojeServiceMock.insertOrUpdate(null)).thenReturn(null);
		
		Mockito.when(rubrojeServiceMock.remove(1)).thenReturn(true);
		
		Mockito.when(rubrojeServiceMock.traerPorId(1)).thenReturn(null);
		
		Mockito.when(rubrojeServiceMock.getAllModel()).thenReturn(lista2);
	}
	
	@Test
	void GetAll() {
		
		
		Assertions.assertEquals(1, rubrojeServiceMock.getAll().size());

	}
	
	@Test
	void InsertOrUpdate() {
		
		
		Assertions.assertEquals(null, rubrojeServiceMock.insertOrUpdate(null));

	}
	
	@Test
	void Remove() {
		
		
		Assertions.assertEquals(true, rubrojeServiceMock.remove(1));

	}
	
	@Test
	void TraerPorId() {
		
		
		Assertions.assertEquals(null, rubrojeServiceMock.traerPorId(1));

	}
	
	@Test
	void GetAllModel() {
		
		
		Assertions.assertEquals(1, rubrojeServiceMock.getAllModel().size());

	}
	
}
