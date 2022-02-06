package com.demo.parrilla.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.demo.parrilla.ParrillaDemoApplication;
import com.demo.parrilla.service.ProcesProductService;

@WebAppConfiguration
@ContextConfiguration(classes = { ParrillaDemoApplication.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class GrillControllerTest {

	@InjectMocks
	private GrillController grillController;

	@Mock
	private ProcesProductService procesProductService;

	private MockMvc mvc;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(grillController).build();
	}

	@Test
	public void testGetAllProductToSell() throws Exception {
		String uri = "/v1.0/parrilla?Directorio con los archivos a procesar=directory";

		//List<Integer> list = new ArrayList<Integer>();
		//list.add(1);
		//list.add(2);
		//list.add(3);
		String products = "5,1,3";
		when(procesProductService.getProductToSell("directory")).thenReturn(products);

		mvc.perform(get(uri).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void testGetAllProductToSellEmpty() throws Exception {
		String uri = "/v1.0/parrilla?Directorio con los archivos a procesar=directory";

		//List<Integer> list = new ArrayList<Integer>();
		String products = "";
		when(procesProductService.getProductToSell("directory")).thenReturn(products);

		mvc.perform(get(uri).accept(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());
	}
	
	@Test
	public void testGetAllProductToSellIsNull() throws Exception {
		String uri = "/v1.0/parrilla?Directorio con los archivos a procesar=directory";

		when(procesProductService.getProductToSell("directory")).thenReturn(null);

		mvc.perform(get(uri).accept(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());
	}
	
	@Test
	public void testGetAllProductToSellIsBlank() throws Exception {
		String uri = "/v1.0/parrilla?Directorio con los archivos a procesar=directory";

		String products = "   ";
		when(procesProductService.getProductToSell("directory")).thenReturn(products);

		mvc.perform(get(uri).accept(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());
	}
	
}
