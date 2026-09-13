package cl.duoc.barriodigital.catalog.exception;

/**
 * Se lanza al intentar decrementar el cupo diario de un tipo de trámite cuyo {@code cupoDisponibleHoy} ya es 0.
 * El {@code @RestControllerAdvice} la traduce a HTTP 409.
 */
// Excepción propia (custom) para avisar que ya no queda cupo disponible
// hoy para un tipo de trámite. El GlobalExceptionHandler la atrapa y
// responde con un HTTP 409 (Conflict).
public class CupoAgotadoException extends RuntimeException {

	public CupoAgotadoException(String message) {
		super(message);
	}

}
