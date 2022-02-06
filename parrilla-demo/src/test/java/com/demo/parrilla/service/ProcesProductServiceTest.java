package com.demo.parrilla.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.parrilla.dto.ProductDTO;
import com.demo.parrilla.dto.SizeDTO;
import com.demo.parrilla.dto.StockDTO;
import com.demo.parrilla.exceptions.GrillBaseException;
import com.demo.parrilla.service.impl.ProcesProductServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProcesProductServiceTest {

	@InjectMocks
	private ProcesProductServiceImpl procesProductService;

	@Mock
	private ReadFilesService readFilesService;

	List<ProductDTO> porductsList;
	List<SizeDTO> sizesList;
	List<StockDTO> stockList;
	String directory = "";

	@Before
	public void before() throws GrillBaseException {

		porductsList = new ArrayList<ProductDTO>();
		sizesList = new ArrayList<SizeDTO>();
		stockList = new ArrayList<StockDTO>();

		when(readFilesService.readFilesProduct(anyString())).thenReturn(porductsList);
		when(readFilesService.readFilesSize(anyString())).thenReturn(sizesList);
		when(readFilesService.readFilesStock(anyString())).thenReturn(stockList);

	}

	@Test
	public void testCaseDemo() throws GrillBaseException {

		porductsList.add(new ProductDTO(1, 10));
		porductsList.add(new ProductDTO(2, 7));
		porductsList.add(new ProductDTO(3, 15));
		porductsList.add(new ProductDTO(4, 13));
		porductsList.add(new ProductDTO(5, 6));

		sizesList.add(new SizeDTO(11, 1, true, false));
		sizesList.add(new SizeDTO(12, 1, false, false));
		sizesList.add(new SizeDTO(13, 1, true, false));
		sizesList.add(new SizeDTO(21, 2, false, false));
		sizesList.add(new SizeDTO(22, 2, false, false));
		sizesList.add(new SizeDTO(23, 2, true, true));
		sizesList.add(new SizeDTO(31, 3, true, false));
		sizesList.add(new SizeDTO(32, 3, true, false));
		sizesList.add(new SizeDTO(33, 3, false, false));
		sizesList.add(new SizeDTO(41, 4, false, false));
		sizesList.add(new SizeDTO(42, 4, false, false));
		sizesList.add(new SizeDTO(43, 4, false, false));
		sizesList.add(new SizeDTO(44, 4, true, true));
		sizesList.add(new SizeDTO(51, 5, true, false));
		sizesList.add(new SizeDTO(52, 5, false, false));
		sizesList.add(new SizeDTO(53, 5, false, false));
		sizesList.add(new SizeDTO(54, 5, true, true));

		stockList.add(new StockDTO(11, 0));
		stockList.add(new StockDTO(12, 0));
		stockList.add(new StockDTO(13, 0));
		stockList.add(new StockDTO(22, 0));
		stockList.add(new StockDTO(31, 10));
		stockList.add(new StockDTO(32, 10));
		stockList.add(new StockDTO(33, 10));
		stockList.add(new StockDTO(41, 0));
		stockList.add(new StockDTO(42, 0));
		stockList.add(new StockDTO(43, 0));
		stockList.add(new StockDTO(44, 10));
		stockList.add(new StockDTO(51, 10));
		stockList.add(new StockDTO(52, 10));
		stockList.add(new StockDTO(53, 10));
		stockList.add(new StockDTO(54, 10));

		/*
		 * List<Integer> result = procesProductService.getProductToSell("directory");
		 * 
		 * assertTrue(result.size() == 3); assertTrue(result.get(0).equals(5));
		 * assertTrue(result.get(1).equals(1)); assertTrue(result.get(2).equals(3));
		 */

		String productToPublish = "5,1,3";
		String result = procesProductService.getProductToSell("directory");
		assertTrue(result.equals(productToPublish));
	}

	@Test
	public void testCaseProductOneAllSpecial() throws GrillBaseException {

		porductsList.add(new ProductDTO(1, 10));
		porductsList.add(new ProductDTO(2, 7));
		porductsList.add(new ProductDTO(3, 15));
		porductsList.add(new ProductDTO(4, 13));
		porductsList.add(new ProductDTO(5, 6));

		sizesList.add(new SizeDTO(11, 1, true, true));
		sizesList.add(new SizeDTO(12, 1, false, true));
		sizesList.add(new SizeDTO(13, 1, true, true));
		sizesList.add(new SizeDTO(21, 2, false, false));
		sizesList.add(new SizeDTO(22, 2, false, false));
		sizesList.add(new SizeDTO(23, 2, true, true));
		sizesList.add(new SizeDTO(31, 3, true, false));
		sizesList.add(new SizeDTO(32, 3, true, false));
		sizesList.add(new SizeDTO(33, 3, false, false));
		sizesList.add(new SizeDTO(41, 4, false, false));
		sizesList.add(new SizeDTO(42, 4, false, false));
		sizesList.add(new SizeDTO(43, 4, false, false));
		sizesList.add(new SizeDTO(44, 4, true, true));
		sizesList.add(new SizeDTO(51, 5, true, false));
		sizesList.add(new SizeDTO(52, 5, false, false));
		sizesList.add(new SizeDTO(53, 5, false, false));
		sizesList.add(new SizeDTO(54, 5, true, true));

		stockList.add(new StockDTO(11, 0));
		stockList.add(new StockDTO(12, 0));
		stockList.add(new StockDTO(13, 0));
		stockList.add(new StockDTO(22, 0));
		stockList.add(new StockDTO(31, 10));
		stockList.add(new StockDTO(32, 10));
		stockList.add(new StockDTO(33, 10));
		stockList.add(new StockDTO(41, 0));
		stockList.add(new StockDTO(42, 0));
		stockList.add(new StockDTO(43, 5));
		stockList.add(new StockDTO(44, 10));
		stockList.add(new StockDTO(51, 10));
		stockList.add(new StockDTO(52, 10));
		stockList.add(new StockDTO(53, 10));
		stockList.add(new StockDTO(54, 10));

		/*
		 * List<Integer> result = procesProductService.getProductToSell("directory");
		 * 
		 * assertTrue(result.size() == 3); assertTrue(result.get(0).equals(5));
		 * assertTrue(result.get(1).equals(4)); assertTrue(result.get(2).equals(3));
		 */

		String productToPublish = "5,4,3";
		String result = procesProductService.getProductToSell("directory");
		assertTrue(result.equals(productToPublish));

	}

	@Test
	public void testCaseNoStock() throws GrillBaseException {

		porductsList.add(new ProductDTO(1, 10));
		porductsList.add(new ProductDTO(2, 7));
		porductsList.add(new ProductDTO(3, 15));
		porductsList.add(new ProductDTO(4, 13));
		porductsList.add(new ProductDTO(5, 6));

		sizesList.add(new SizeDTO(11, 1, true, true));
		sizesList.add(new SizeDTO(12, 1, false, true));
		sizesList.add(new SizeDTO(13, 1, true, true));
		sizesList.add(new SizeDTO(21, 2, false, false));
		sizesList.add(new SizeDTO(22, 2, false, false));
		sizesList.add(new SizeDTO(23, 2, true, true));
		sizesList.add(new SizeDTO(31, 3, true, false));
		sizesList.add(new SizeDTO(32, 3, true, false));
		sizesList.add(new SizeDTO(33, 3, false, false));
		sizesList.add(new SizeDTO(41, 4, false, false));
		sizesList.add(new SizeDTO(42, 4, false, false));
		sizesList.add(new SizeDTO(43, 4, false, false));
		sizesList.add(new SizeDTO(44, 4, true, true));
		sizesList.add(new SizeDTO(51, 5, true, false));
		sizesList.add(new SizeDTO(52, 5, false, false));
		sizesList.add(new SizeDTO(53, 5, false, false));
		sizesList.add(new SizeDTO(54, 5, true, true));

		/*
		 * List<Integer> result = procesProductService.getProductToSell("directory");
		 * 
		 * assertTrue(result.size() == 2); assertTrue(result.get(0).equals(5));
		 * assertTrue(result.get(1).equals(3));
		 */

		String productToPublish = "5,3";
		String result = procesProductService.getProductToSell("directory");
		assertTrue(result.equals(productToPublish));

	}

	@Test
	public void testOrder() throws GrillBaseException {

		porductsList.add(new ProductDTO(1, 1));
		porductsList.add(new ProductDTO(2, 2));
		porductsList.add(new ProductDTO(3, 3));
		porductsList.add(new ProductDTO(4, 4));
		porductsList.add(new ProductDTO(5, 5));

		sizesList.add(new SizeDTO(11, 1, true, false));
		sizesList.add(new SizeDTO(12, 1, false, false));
		sizesList.add(new SizeDTO(13, 1, true, false));
		sizesList.add(new SizeDTO(21, 2, false, false));
		sizesList.add(new SizeDTO(22, 2, false, false));
		sizesList.add(new SizeDTO(23, 2, true, true));
		sizesList.add(new SizeDTO(31, 3, true, false));
		sizesList.add(new SizeDTO(32, 3, true, false));
		sizesList.add(new SizeDTO(33, 3, false, false));
		sizesList.add(new SizeDTO(41, 4, false, false));
		sizesList.add(new SizeDTO(42, 4, false, false));
		sizesList.add(new SizeDTO(43, 4, false, false));
		sizesList.add(new SizeDTO(44, 4, true, true));
		sizesList.add(new SizeDTO(51, 5, true, false));
		sizesList.add(new SizeDTO(52, 5, false, false));
		sizesList.add(new SizeDTO(53, 5, false, false));
		sizesList.add(new SizeDTO(54, 5, true, true));

		stockList.add(new StockDTO(11, 0));
		stockList.add(new StockDTO(12, 0));
		stockList.add(new StockDTO(13, 0));
		stockList.add(new StockDTO(22, 0));
		stockList.add(new StockDTO(31, 10));
		stockList.add(new StockDTO(32, 10));
		stockList.add(new StockDTO(33, 10));
		stockList.add(new StockDTO(41, 0));
		stockList.add(new StockDTO(42, 0));
		stockList.add(new StockDTO(43, 0));
		stockList.add(new StockDTO(44, 10));
		stockList.add(new StockDTO(51, 10));
		stockList.add(new StockDTO(52, 10));
		stockList.add(new StockDTO(53, 10));
		stockList.add(new StockDTO(54, 10));

		/*
		 * List<Integer> result = procesProductService.getProductToSell("directory");
		 * 
		 * assertTrue(result.size() == 3); assertTrue(result.get(0).equals(1));
		 * assertTrue(result.get(1).equals(3)); assertTrue(result.get(2).equals(5));
		 */

		String productToPublish = "1,3,5";
		String result = procesProductService.getProductToSell("directory");
		assertTrue(result.equals(productToPublish));
	}

	@Test
	public void testAllBackSoon() throws GrillBaseException {

		porductsList.add(new ProductDTO(1, 10));
		porductsList.add(new ProductDTO(2, 7));
		porductsList.add(new ProductDTO(3, 15));
		porductsList.add(new ProductDTO(4, 13));
		porductsList.add(new ProductDTO(5, 6));

		sizesList.add(new SizeDTO(11, 1, true, false));
		sizesList.add(new SizeDTO(12, 1, true, false));
		sizesList.add(new SizeDTO(13, 1, true, false));
		sizesList.add(new SizeDTO(21, 2, true, false));
		sizesList.add(new SizeDTO(22, 2, true, false));
		sizesList.add(new SizeDTO(23, 2, true, true));
		sizesList.add(new SizeDTO(31, 3, true, false));
		sizesList.add(new SizeDTO(32, 3, true, false));
		sizesList.add(new SizeDTO(33, 3, true, false));
		sizesList.add(new SizeDTO(41, 4, true, false));
		sizesList.add(new SizeDTO(42, 4, true, false));
		sizesList.add(new SizeDTO(43, 4, true, false));
		sizesList.add(new SizeDTO(44, 4, true, true));
		sizesList.add(new SizeDTO(51, 5, true, false));
		sizesList.add(new SizeDTO(52, 5, true, false));
		sizesList.add(new SizeDTO(53, 5, true, false));
		sizesList.add(new SizeDTO(54, 5, true, true));

		stockList.add(new StockDTO(11, 0));
		stockList.add(new StockDTO(12, 0));
		stockList.add(new StockDTO(13, 0));
		stockList.add(new StockDTO(22, 0));
		stockList.add(new StockDTO(31, 10));
		stockList.add(new StockDTO(32, 10));
		stockList.add(new StockDTO(33, 10));
		stockList.add(new StockDTO(41, 0));
		stockList.add(new StockDTO(42, 0));
		stockList.add(new StockDTO(43, 0));
		stockList.add(new StockDTO(44, 10));
		stockList.add(new StockDTO(51, 10));
		stockList.add(new StockDTO(52, 10));
		stockList.add(new StockDTO(53, 10));
		stockList.add(new StockDTO(54, 10));

		/*
		 * List<Integer> result = procesProductService.getProductToSell("directory");
		 * 
		 * assertTrue(result.size() == 5); assertTrue(result.get(0).equals(5));
		 * assertTrue(result.get(1).equals(2)); assertTrue(result.get(2).equals(1));
		 * assertTrue(result.get(3).equals(4)); assertTrue(result.get(4).equals(3));
		 */

		String productToPublish = "5,2,1,4,3";
		String result = procesProductService.getProductToSell("directory");
		assertTrue(result.equals(productToPublish));
	}

	@Test
	public void testAllBackSoonAndSpecial() throws GrillBaseException {

		porductsList.add(new ProductDTO(1, 10));
		porductsList.add(new ProductDTO(2, 7));
		porductsList.add(new ProductDTO(3, 15));
		porductsList.add(new ProductDTO(4, 13));
		porductsList.add(new ProductDTO(5, 6));

		sizesList.add(new SizeDTO(11, 1, true, true));
		sizesList.add(new SizeDTO(12, 1, true, true));
		sizesList.add(new SizeDTO(13, 1, true, true));
		sizesList.add(new SizeDTO(21, 2, true, true));
		sizesList.add(new SizeDTO(22, 2, true, true));
		sizesList.add(new SizeDTO(23, 2, true, true));
		sizesList.add(new SizeDTO(31, 3, true, true));
		sizesList.add(new SizeDTO(32, 3, true, true));
		sizesList.add(new SizeDTO(33, 3, true, true));
		sizesList.add(new SizeDTO(41, 4, true, true));
		sizesList.add(new SizeDTO(42, 4, true, true));
		sizesList.add(new SizeDTO(43, 4, true, true));
		sizesList.add(new SizeDTO(44, 4, true, true));
		sizesList.add(new SizeDTO(51, 5, true, true));
		sizesList.add(new SizeDTO(52, 5, true, true));
		sizesList.add(new SizeDTO(53, 5, true, true));
		sizesList.add(new SizeDTO(54, 5, true, true));

		stockList.add(new StockDTO(11, 0));
		stockList.add(new StockDTO(12, 0));
		stockList.add(new StockDTO(13, 0));
		stockList.add(new StockDTO(22, 0));
		stockList.add(new StockDTO(31, 10));
		stockList.add(new StockDTO(32, 10));
		stockList.add(new StockDTO(33, 10));
		stockList.add(new StockDTO(41, 0));
		stockList.add(new StockDTO(42, 0));
		stockList.add(new StockDTO(43, 0));
		stockList.add(new StockDTO(44, 10));
		stockList.add(new StockDTO(51, 10));
		stockList.add(new StockDTO(52, 10));
		stockList.add(new StockDTO(53, 10));
		stockList.add(new StockDTO(54, 10));

		/*
		 * List<Integer> result = procesProductService.getProductToSell("directory");
		 * 
		 * assertTrue(result.size() == 0);
		 */

		String productToPublish = "";
		String result = procesProductService.getProductToSell("directory");
		assertTrue(result.equals(productToPublish));
	}

	@Test
	public void testNoBackSoonAndNoStock() throws GrillBaseException {

		porductsList.add(new ProductDTO(1, 10));
		porductsList.add(new ProductDTO(2, 7));
		porductsList.add(new ProductDTO(3, 15));
		porductsList.add(new ProductDTO(4, 13));
		porductsList.add(new ProductDTO(5, 6));

		sizesList.add(new SizeDTO(11, 1, false, true));
		sizesList.add(new SizeDTO(12, 1, false, true));
		sizesList.add(new SizeDTO(13, 1, false, true));
		sizesList.add(new SizeDTO(21, 2, false, false));
		sizesList.add(new SizeDTO(22, 2, false, false));
		sizesList.add(new SizeDTO(23, 2, false, true));
		sizesList.add(new SizeDTO(31, 3, false, false));
		sizesList.add(new SizeDTO(32, 3, false, false));
		sizesList.add(new SizeDTO(33, 3, false, false));
		sizesList.add(new SizeDTO(41, 4, false, false));
		sizesList.add(new SizeDTO(42, 4, false, false));
		sizesList.add(new SizeDTO(43, 4, false, false));
		sizesList.add(new SizeDTO(44, 4, false, true));
		sizesList.add(new SizeDTO(51, 5, false, false));
		sizesList.add(new SizeDTO(52, 5, false, false));
		sizesList.add(new SizeDTO(53, 5, false, false));
		sizesList.add(new SizeDTO(54, 5, false, true));

		stockList.add(new StockDTO(11, 0));
		stockList.add(new StockDTO(12, 0));
		stockList.add(new StockDTO(13, 0));
		stockList.add(new StockDTO(22, 0));
		stockList.add(new StockDTO(31, 0));
		stockList.add(new StockDTO(32, 0));
		stockList.add(new StockDTO(33, 0));
		stockList.add(new StockDTO(41, 0));
		stockList.add(new StockDTO(42, 0));
		stockList.add(new StockDTO(43, 0));
		stockList.add(new StockDTO(44, 0));
		stockList.add(new StockDTO(51, 0));
		stockList.add(new StockDTO(52, 0));
		stockList.add(new StockDTO(53, 0));
		stockList.add(new StockDTO(54, 0));

		/*
		 * List<Integer> result = procesProductService.getProductToSell("directory");
		 * 
		 * assertTrue(result.size() == 0);
		 */

		String productToPublish = "";
		String result = procesProductService.getProductToSell("directory");
		assertTrue(result.equals(productToPublish));
	}

	@Test
	public void testNoBackSoonAndAllStock() throws GrillBaseException {

		porductsList.add(new ProductDTO(1, 10));
		porductsList.add(new ProductDTO(2, 7));
		porductsList.add(new ProductDTO(3, 15));
		porductsList.add(new ProductDTO(4, 13));
		porductsList.add(new ProductDTO(5, 6));

		sizesList.add(new SizeDTO(11, 1, false, false));
		sizesList.add(new SizeDTO(12, 1, false, false));
		sizesList.add(new SizeDTO(13, 1, false, false));
		sizesList.add(new SizeDTO(21, 2, false, false));
		sizesList.add(new SizeDTO(22, 2, false, false));
		sizesList.add(new SizeDTO(23, 2, false, true));
		sizesList.add(new SizeDTO(31, 3, false, false));
		sizesList.add(new SizeDTO(32, 3, false, false));
		sizesList.add(new SizeDTO(33, 3, false, false));
		sizesList.add(new SizeDTO(41, 4, false, false));
		sizesList.add(new SizeDTO(42, 4, false, false));
		sizesList.add(new SizeDTO(43, 4, false, false));
		sizesList.add(new SizeDTO(44, 4, false, true));
		sizesList.add(new SizeDTO(51, 5, false, false));
		sizesList.add(new SizeDTO(52, 5, false, false));
		sizesList.add(new SizeDTO(53, 5, false, false));
		sizesList.add(new SizeDTO(54, 5, false, true));

		stockList.add(new StockDTO(11, 10));
		stockList.add(new StockDTO(12, 10));
		stockList.add(new StockDTO(13, 10));
		stockList.add(new StockDTO(22, 10));
		stockList.add(new StockDTO(31, 10));
		stockList.add(new StockDTO(32, 10));
		stockList.add(new StockDTO(33, 10));
		stockList.add(new StockDTO(41, 10));
		stockList.add(new StockDTO(42, 10));
		stockList.add(new StockDTO(43, 10));
		stockList.add(new StockDTO(44, 10));
		stockList.add(new StockDTO(51, 10));
		stockList.add(new StockDTO(52, 10));
		stockList.add(new StockDTO(53, 10));
		stockList.add(new StockDTO(54, 10));

		/*
		 * List<Integer> result = procesProductService.getProductToSell("directory");
		 * 
		 * assertTrue(result.size() == 4); assertTrue(result.get(0).equals(5));
		 * assertTrue(result.get(1).equals(1)); assertTrue(result.get(2).equals(4));
		 * assertTrue(result.get(3).equals(3));
		 */

		String productToPublish = "5,1,4,3";
		String result = procesProductService.getProductToSell("directory");
		assertTrue(result.equals(productToPublish));
	}

}
