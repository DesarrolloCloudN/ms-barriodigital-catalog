package cl.duoc.barriodigital.catalog.service;

import cl.duoc.barriodigital.catalog.dto.TipoTramiteActualizarRequest;
import cl.duoc.barriodigital.catalog.dto.TipoTramiteCrearRequest;
import cl.duoc.barriodigital.catalog.entity.TipoTramite;
import cl.duoc.barriodigital.catalog.exception.CupoAgotadoException;
import cl.duoc.barriodigital.catalog.exception.TipoTramiteNoEncontradoException;
import cl.duoc.barriodigital.catalog.repository.TipoTramiteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// Lógica de negocio de tipos de trámite; el controller solo delega aquí.
@Service
public class TipoTramiteService {

	private final TipoTramiteRepository tipoTramiteRepository;

	public TipoTramiteService(TipoTramiteRepository tipoTramiteRepository) {
		this.tipoTramiteRepository = tipoTramiteRepository;
	}

	public List<TipoTramite> listar() {
		return tipoTramiteRepository.findAll();
	}

	public TipoTramite obtener(Long id) {
		return tipoTramiteRepository.findById(id)
				.orElseThrow(() -> new TipoTramiteNoEncontradoException("No existe un tipo de trámite con id " + id));
	}

	public TipoTramite crear(TipoTramiteCrearRequest request) {
		TipoTramite tipoTramite = new TipoTramite(
				request.nombre(),
				request.descripcion(),
				request.requisitos(),
				request.cupoDiario()
		);
		return tipoTramiteRepository.save(tipoTramite);
	}

	// Actualiza cupoDiario pero no toca cupoDisponibleHoy (se maneja aparte).
	public TipoTramite actualizar(Long id, TipoTramiteActualizarRequest request) {
		TipoTramite tipoTramite = obtener(id);
		tipoTramite.setNombre(request.nombre());
		tipoTramite.setDescripcion(request.descripcion());
		tipoTramite.setRequisitos(request.requisitos());
		tipoTramite.setCupoDiario(request.cupoDiario());
		tipoTramite.setActivo(request.activo());
		return tipoTramiteRepository.save(tipoTramite);
	}

	// Descuenta un cupo; si ya no queda (<=0) lanza CupoAgotadoException.
	public TipoTramite decrementarCupo(Long id) {
		TipoTramite tipoTramite = obtener(id);
		Integer cupoDisponibleHoy = tipoTramite.getCupoDisponibleHoy();
		if (cupoDisponibleHoy == null || cupoDisponibleHoy <= 0) {
			throw new CupoAgotadoException("No queda cupo disponible hoy para el tipo de trámite " + id);
		}
		tipoTramite.setCupoDisponibleHoy(cupoDisponibleHoy - 1);
		return tipoTramiteRepository.save(tipoTramite);
	}

	// Resetea cupoDisponibleHoy al máximo (cupoDiario), no suma de a uno.
	public TipoTramite reponerCupo(Long id) {
		TipoTramite tipoTramite = obtener(id);
		tipoTramite.setCupoDisponibleHoy(tipoTramite.getCupoDiario());
		return tipoTramiteRepository.save(tipoTramite);
	}

	public void eliminar(Long id) {
		TipoTramite tipoTramite = obtener(id);
		tipoTramiteRepository.delete(tipoTramite);
	}

}
