package cl.duoc.barriodigital.catalog.exception;

// No existe un tipo de trámite con el id buscado; el handler responde HTTP 404.
public class TipoTramiteNoEncontradoException extends RuntimeException {

	public TipoTramiteNoEncontradoException(String message) {
		super(message);
	}

}
