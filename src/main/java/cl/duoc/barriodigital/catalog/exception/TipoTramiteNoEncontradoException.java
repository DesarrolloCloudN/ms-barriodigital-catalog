package cl.duoc.barriodigital.catalog.exception;

/**
 * Se lanza cuando se busca un {@link cl.duoc.barriodigital.catalog.entity.TipoTramite} por id y no existe.
 * El {@code @RestControllerAdvice} la traduce a HTTP 404.
 */
// Excepción propia para cuando buscamos un tipo de trámite por id y no lo
// encontramos en la base de datos.
public class TipoTramiteNoEncontradoException extends RuntimeException {

	public TipoTramiteNoEncontradoException(String message) {
		super(message);
	}

}
