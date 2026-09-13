package cl.duoc.barriodigital.catalog.dto;

import cl.duoc.barriodigital.catalog.entity.TipoTramite;

// DTO que se devuelve al cliente (el bff) cuando se consulta o se modifica
// un tipo de trámite. Incluye tanto el cupo máximo diario (cupoDiario)
// como el cupo que queda disponible hoy (cupoDisponibleHoy).
public record TipoTramiteResponse(
		Long id,
		String nombre,
		String descripcion,
		String requisitos,
		Integer cupoDiario,
		Integer cupoDisponibleHoy,
		Boolean activo
) {

	// Convierte una entidad TipoTramite (la que se guarda en la base de
	// datos) en un TipoTramiteResponse (lo que se manda como respuesta HTTP).
	public static TipoTramiteResponse from(TipoTramite tipoTramite) {
		return new TipoTramiteResponse(
				tipoTramite.getId(),
				tipoTramite.getNombre(),
				tipoTramite.getDescripcion(),
				tipoTramite.getRequisitos(),
				tipoTramite.getCupoDiario(),
				tipoTramite.getCupoDisponibleHoy(),
				tipoTramite.getActivo()
		);
	}

}
