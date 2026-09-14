package cl.duoc.barriodigital.catalog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

// Centraliza el manejo de errores de todos los controllers.
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(TipoTramiteNoEncontradoException.class)
	public ResponseEntity<Map<String, String>> handleTipoTramiteNoEncontrado(TipoTramiteNoEncontradoException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", ex.getMessage()));
	}

	@ExceptionHandler(CupoAgotadoException.class)
	public ResponseEntity<Map<String, String>> handleCupoAgotado(CupoAgotadoException ex) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error", ex.getMessage()));
	}

}
