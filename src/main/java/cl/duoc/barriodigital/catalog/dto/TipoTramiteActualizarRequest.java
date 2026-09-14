package cl.duoc.barriodigital.catalog.dto;

public record TipoTramiteActualizarRequest(
		String nombre,
		String descripcion,
		String requisitos,
		Integer cupoDiario,
		Boolean activo
) {
}
