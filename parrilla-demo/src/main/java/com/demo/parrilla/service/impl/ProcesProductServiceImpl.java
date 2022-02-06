package com.demo.parrilla.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.parrilla.dto.ProductDTO;
import com.demo.parrilla.dto.ProductDataDTO;
import com.demo.parrilla.dto.SizeDTO;
import com.demo.parrilla.dto.StockDTO;
import com.demo.parrilla.exceptions.GrillBaseException;
import com.demo.parrilla.service.ProcesProductService;
import com.demo.parrilla.service.ReadFilesService;

@Service
public class ProcesProductServiceImpl implements ProcesProductService {

	private List<ProductDTO> porductsList;
	private List<SizeDTO> sizesList;
	private List<StockDTO> stockList;
	private Set<Integer> stockSet;

	@Autowired
	private ReadFilesService readFilesService;

	@Override
	public String getProductToSell(String directory) throws GrillBaseException {

		readFilesService.validateDirectory(directory);

		porductsList = readFilesService.readFilesProduct(directory);
		sizesList = readFilesService.readFilesSize(directory);
		stockList = readFilesService.readFilesStock(directory);

		stockSet = new HashSet<Integer>();
		for (StockDTO stock : stockList) {
			if (stock.getQuantity() > 0)
				stockSet.add(stock.getSizeId());
		}

		HashMap<Integer, ProductDataDTO> dicProductData = new HashMap<Integer, ProductDataDTO>();
		for (SizeDTO size : sizesList) {

			ProductDataDTO productData = dicProductData.get(size.getProductId());
			if (productData == null) {
				productData = new ProductDataDTO(false, false, false);
				dicProductData.put(size.getProductId(), productData);
			}
			procesProduct(productData, size);
		}

		Collections.sort(porductsList, new Comparator<ProductDTO>() {
			@Override
			public int compare(ProductDTO p1, ProductDTO p2) {
				return p1.getSequence().compareTo(p2.getSequence());
			}
		});

		//List<Integer> productToPublish = new ArrayList<Integer>();
		List<String> productToPublish = new ArrayList<String>();
		for (ProductDTO product : porductsList) {
			ProductDataDTO productData = dicProductData.get(product.getId());
			if (productData.isNormalSizeOKToPublish()
					&& (productData.isSpecialSizeOkToPublish() || !productData.isHasSpecialSizes()))
				productToPublish.add(product.getId().toString());
		}

		return String.join(",", productToPublish);
	}

	private void procesProduct(ProductDataDTO productData, SizeDTO size) {
		if (size.isSpecial()) {
			productData.setHasSpecialSizes(true);
			if (!productData.isSpecialSizeOkToPublish()) {
				if (size.isBackSoon())
					productData.setSpecialSizeOkToPublish(true);
				else
					productData.setSpecialSizeOkToPublish(stockSet.contains(size.getId()));
			}

		} else {
			if (!productData.isNormalSizeOKToPublish()) {
				if (size.isBackSoon())
					productData.setNormalSizeOKToPublish(true);
				else
					productData.setNormalSizeOKToPublish(stockSet.contains(size.getId()));
			}
		}
	}

	
}
