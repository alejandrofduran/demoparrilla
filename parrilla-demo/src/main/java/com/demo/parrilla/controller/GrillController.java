package com.demo.parrilla.controller;

//import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.parrilla.exceptions.GrillBaseException;
import com.demo.parrilla.service.ProcesProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@Api(tags = "Grill Controller", value = "GrillController")
@RestController
@RequestMapping("/v1.0")
public class GrillController {

	@Autowired
	private ProcesProductService procesProductService;

	@ApiOperation(value = "Devuelve la lista de productos para pintar en la parrilla de los los frontales web.", response = String.class, produces = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Operacion correcta"),
			@ApiResponse(code = 204, message = "No se encontraron registros"),
			@ApiResponse(code = 400, message = "El servidor no puede procesar la solicitud por un error en el request"),
			@ApiResponse(code = 404, message = "Recurso no encontrado"),
			@ApiResponse(code = 500, message = "Errores internos de servidor o que no se pudo conectar con un servicio externo") })
	@GetMapping(value = "/parrilla")
	public ResponseEntity<String> getAllProductToSell(HttpServletRequest request,
			@RequestParam(value = "Directorio con los archivos a procesar", required = true) String directory)
			throws GrillBaseException {

		// List<Integer> products = procesProductService.getProductToSell(directory);
		// return (products != null && !products.isEmpty()) ?
		// ResponseEntity.ok(products) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();

		String products = procesProductService.getProductToSell(directory);
		return (StringUtils.isNotBlank(products)) ? ResponseEntity.ok(products)
				: ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
