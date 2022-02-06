package com.demo.parrilla.service;

//import java.util.List;

import org.springframework.stereotype.Service;

import com.demo.parrilla.exceptions.GrillBaseException;

@Service
public interface ProcesProductService {

	//public List<Integer> getProductToSell(String directory) throws GrillBaseException;
	public String getProductToSell(String directory) throws GrillBaseException;
}
