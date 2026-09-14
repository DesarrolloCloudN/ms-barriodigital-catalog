package cl.duoc.barriodigital.catalog.exception;

// Ya no queda cupo disponible hoy; el GlobalExceptionHandler responde HTTP 409.
public class CupoAgotadoException extends RuntimeException {

	public CupoAgotadoException(String message) {
		super(message);
	}

}
