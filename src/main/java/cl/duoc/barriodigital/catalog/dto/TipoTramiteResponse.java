package cl.duoc.barriodigital.catalog.dto;

import cl.duoc.barriodigital.catalog.entity.TipoTramite;

// Incluye cupoDiario (máximo) y cupoDisponibleHoy (lo que queda hoy).
public record TipoTramiteResponse(
		Long id,
		String nombre,
		String descripcion,
		String requisitos,
		Integer cupoDiario,
		Integer cupoDisponibleHoy,
		Boolean activo
) {

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
