package cl.duoc.barriodigital.catalog.service;

import cl.duoc.barriodigital.catalog.dto.TipoTramiteActualizarRequest;
import cl.duoc.barriodigital.catalog.dto.TipoTramiteCrearRequest;
import cl.duoc.barriodigital.catalog.entity.TipoTramite;
import cl.duoc.barriodigital.catalog.exception.CupoAgotadoException;
import cl.duoc.barriodigital.catalog.exception.TipoTramiteNoEncontradoException;
import cl.duoc.barriodigital.catalog.repository.TipoTramiteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// Service: acá vive la lógica de negocio de los tipos de trámite (crear,
// listar, actualizar y manejar los cupos diarios). El controller solo
// delega el trabajo a esta clase.
@Service
public class TipoTramiteService {

	private final TipoTramiteRepository tipoTramiteRepository;

	public TipoTramiteService(TipoTramiteRepository tipoTramiteRepository) {
		this.tipoTramiteRepository = tipoTramiteRepository;
	}

	// Devuelve todos los tipos de trámite guardados.
	public List<TipoTramite> listar() {
		return tipoTramiteRepository.findAll();
	}

	// Busca un tipo de trámite por id. Si no existe, lanza una excepción
	// que más adelante el GlobalExceptionHandler convierte en HTTP 404.
	public TipoTramite obtener(Long id) {
		return tipoTramiteRepository.findById(id)
				.orElseThrow(() -> new TipoTramiteNoEncontradoException("No existe un tipo de trámite con id " + id));
	}

	// Crea un tipo de trámite nuevo. El constructor de TipoTramite ya se
	// encarga de dejar el cupo disponible de hoy igual al cupo diario.
	public TipoTramite crear(TipoTramiteCrearRequest request) {
		TipoTramite tipoTramite = new TipoTramite(
				request.nombre(),
				request.descripcion(),
				request.requisitos(),
				request.cupoDiario()
		);
		return tipoTramiteRepository.save(tipoTramite);
	}

	// Actualiza los datos generales de un tipo de trámite existente. Ojo
	// que esto reemplaza el cupoDiario, pero no toca el cupoDisponibleHoy
	// directamente (ese se maneja aparte con decrementarCupo/reponerCupo).
	public TipoTramite actualizar(Long id, TipoTramiteActualizarRequest request) {
		TipoTramite tipoTramite = obtener(id);
		tipoTramite.setNombre(request.nombre());
		tipoTramite.setDescripcion(request.descripcion());
		tipoTramite.setRequisitos(request.requisitos());
		tipoTramite.setCupoDiario(request.cupoDiario());
		tipoTramite.setActivo(request.activo());
		return tipoTramiteRepository.save(tipoTramite);
	}

	// Descuenta un cupo disponible de hoy (se usa cada vez que se crea un
	// trámite de este tipo). Si ya no queda cupo (0 o menos), no se deja
	// descontar más y se lanza CupoAgotadoException.
	public TipoTramite decrementarCupo(Long id) {
		TipoTramite tipoTramite = obtener(id);
		Integer cupoDisponibleHoy = tipoTramite.getCupoDisponibleHoy();
		if (cupoDisponibleHoy == null || cupoDisponibleHoy <= 0) {
			throw new CupoAgotadoException("No queda cupo disponible hoy para el tipo de trámite " + id);
		}
		tipoTramite.setCupoDisponibleHoy(cupoDisponibleHoy - 1);
		return tipoTramiteRepository.save(tipoTramite);
	}

	// Vuelve a dejar el cupo disponible de hoy en el máximo configurado
	// (cupoDiario). No suma de a uno: directamente resetea el contador,
	// por ejemplo para cuando empieza un nuevo día.
	public TipoTramite reponerCupo(Long id) {
		TipoTramite tipoTramite = obtener(id);
		tipoTramite.setCupoDisponibleHoy(tipoTramite.getCupoDiario());
		return tipoTramiteRepository.save(tipoTramite);
	}

	// Elimina un tipo de trámite de la base de datos.
	public void eliminar(Long id) {
		TipoTramite tipoTramite = obtener(id);
		tipoTramiteRepository.delete(tipoTramite);
	}

}
