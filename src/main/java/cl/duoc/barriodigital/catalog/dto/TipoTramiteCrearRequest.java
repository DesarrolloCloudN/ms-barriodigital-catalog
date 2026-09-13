package cl.duoc.barriodigital.catalog.dto;

/**
 * Body de {@code POST /api/catalog}.
 */
// DTO con los datos que llegan al crear un tipo de trámite nuevo. Notar
// que acá solo se define cupoDiario: el cupo disponible de hoy se calcula
// solo dentro de la entidad (ver TipoTramite).
public record TipoTramiteCrearRequest(
		String nombre,
		String descripcion,
		String requisitos,
		Integer cupoDiario
) {
}
