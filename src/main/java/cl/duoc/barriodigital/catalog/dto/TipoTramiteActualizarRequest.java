package cl.duoc.barriodigital.catalog.dto;

/**
 * Body de {@code PUT /api/catalog/{id}}.
 */
// DTO (record) con los datos que llegan al actualizar un tipo de trámite.
// Un record es solo una forma corta de escribir una clase que únicamente
// guarda datos (Java genera constructor y getters solo).
public record TipoTramiteActualizarRequest(
		String nombre,
		String descripcion,
		String requisitos,
		Integer cupoDiario,
		Boolean activo
) {
}
