package com.demo.parrilla.service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.parrilla.dto.ProductDTO;
import com.demo.parrilla.dto.SizeDTO;
import com.demo.parrilla.dto.StockDTO;
import com.demo.parrilla.exceptions.GrillBaseException;
import com.demo.parrilla.exceptions.GrillFilesException;
import com.demo.parrilla.exceptions.GrillReadFilesException;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

@Service
public class ReadFilesService {

	private static final String FILE_NAME_PORDUCT = "product.csv";
	private static final String FILE_NAME_SIZE = "size.csv";
	private static final String FILE_NAME_STOCK = "stock.csv";

	private List<ProductDTO> porductsList;
	private List<SizeDTO> sizesList;
	private List<StockDTO> stockList;

	@Autowired
	ErrorMessageService errorMessageService;

	public List<ProductDTO> readFilesProduct(String directory) throws GrillBaseException {
		readFiles(directory, FILE_NAME_PORDUCT);
		return porductsList;
	}

	public List<SizeDTO> readFilesSize(String directory) throws GrillBaseException {
		readFiles(directory, FILE_NAME_SIZE);
		return sizesList;
	}

	public List<StockDTO> readFilesStock(String directory) throws GrillBaseException {
		readFiles(directory, FILE_NAME_STOCK);
		return stockList;
	}

	public void validateDirectory(String directory) throws GrillBaseException {
		File file = new File(directory);
		if (!file.exists()) {
			throw new GrillFilesException(errorMessageService.getMessage("isNotDirectoryCode"),
					errorMessageService.getMessage("isNotDirectoryMessage"));
		} else if (file.isFile()) {
			throw new GrillFilesException(errorMessageService.getMessage("isFileCodeMessage"),
					errorMessageService.getMessage("isFileMessage"));
		}

		checkExistsFile(directory, FILE_NAME_PORDUCT);
		checkExistsFile(directory, FILE_NAME_SIZE);
		checkExistsFile(directory, FILE_NAME_STOCK);		
	}

	private void checkExistsFile(String directory, String fileName) throws GrillBaseException {
		File file = new File(directory + "/" + fileName);
		if (!file.exists()) {
			throw new GrillFilesException("FILE_NOT_FOUND",
					"El directorio indicado no contiene el archivo: " + fileName + ".");
		}
	}

	private void readFiles(String directory, String fileName) throws GrillBaseException {

		FileReader archveCSV = null;
		CSVReader csvReader = null;
		try {
			archveCSV = new FileReader(directory + "/" + fileName);
			csvReader = readCSV(archveCSV, ',');
			if (FILE_NAME_PORDUCT.equals(fileName))
				readPorductList(csvReader);
			else if (FILE_NAME_SIZE.equals(fileName))
				readSizeList(csvReader);
			else
				readStockList(csvReader);

		} catch (IOException e) {
			throw new GrillReadFilesException("Error al leer el archivo " + fileName + ". Error: " + e.getMessage());
		} catch (Exception e) {
			throw new GrillReadFilesException(
					"Error al procesar el archivo " + fileName + ". Error: " + e.getMessage());
		} finally {
			try {
				archveCSV.close();
				csvReader.close();
			} catch (IOException e) {
				System.out.println(e);
			}
		}

	}

	private CSVReader readCSV(FileReader archveCSV, char separator) {

		CSVParser csvParser = new CSVParserBuilder().withSeparator(separator).build();
		CSVReader csvReader = new CSVReaderBuilder(archveCSV).withCSVParser(csvParser).build();

		return csvReader;
	}

	private void readPorductList(CSVReader csvReader) throws NumberFormatException, IOException {

		porductsList = new ArrayList<ProductDTO>();
		String[] fila = null;
		while ((fila = csvReader.readNext()) != null) {

			int id = Integer.parseInt(fila[0].trim());
			int sequence = Integer.parseInt(fila[1].trim());
			ProductDTO product = new ProductDTO(id, sequence);
			// System.out.println(product.toString());
			porductsList.add(product);
		}
	}

	private void readSizeList(CSVReader csvReader) throws NumberFormatException, IOException {

		sizesList = new ArrayList<SizeDTO>();
		String[] fila = null;
		while ((fila = csvReader.readNext()) != null) {

			int id = Integer.parseInt(fila[0].trim());
			int productId = Integer.parseInt(fila[1].trim());
			boolean backSoon = Boolean.parseBoolean(fila[2].trim());
			boolean special = Boolean.parseBoolean(fila[3].trim());
			SizeDTO size = new SizeDTO(id, productId, backSoon, special);
			// System.out.println(size.toString());
			sizesList.add(size);
		}
	}

	private void readStockList(CSVReader csvReader) throws NumberFormatException, IOException {

		stockList = new ArrayList<StockDTO>();
		String[] fila = null;
		while ((fila = csvReader.readNext()) != null) {

			int sizeId = Integer.parseInt(fila[0].trim());
			int quantity = Integer.parseInt(fila[1].trim());

			if (quantity > 0) {
				StockDTO stock = new StockDTO(sizeId, quantity);
				// System.out.println(stock.toString());
				stockList.add(stock);
			}
		}
	}

}
