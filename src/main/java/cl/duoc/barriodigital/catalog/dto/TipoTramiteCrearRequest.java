package cl.duoc.barriodigital.catalog.dto;

// Solo trae cupoDiario; cupoDisponibleHoy se inicializa en la entidad.
public record TipoTramiteCrearRequest(
		String nombre,
		String descripcion,
		String requisitos,
		Integer cupoDiario
) {
}
