package cl.duoc.barriodigital.catalog.controller;

import cl.duoc.barriodigital.catalog.dto.TipoTramiteActualizarRequest;
import cl.duoc.barriodigital.catalog.dto.TipoTramiteCrearRequest;
import cl.duoc.barriodigital.catalog.dto.TipoTramiteResponse;
import cl.duoc.barriodigital.catalog.service.TipoTramiteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Endpoints internos (sin seguridad propia, ver sección 3 del contrato) de catálogo de tipos de trámite y cupos.
 * Consumidos únicamente por ms-barriodigital-bff.
 */
// Controller: es la puerta de entrada HTTP. Recibe las peticiones y le pasa
// el trabajo al TipoTramiteService, que tiene la lógica de verdad.
@RestController
@RequestMapping("/api/catalog")
public class TipoTramiteController {

	private final TipoTramiteService tipoTramiteService;

	// Spring inyecta automáticamente el service por el constructor.
	public TipoTramiteController(TipoTramiteService tipoTramiteService) {
		this.tipoTramiteService = tipoTramiteService;
	}

	// GET /api/catalog -> lista todos los tipos de trámite existentes.
	@GetMapping
	public List<TipoTramiteResponse> listar() {
		return tipoTramiteService.listar().stream()
				.map(TipoTramiteResponse::from)
				.toList();
	}

	// GET /api/catalog/{id} -> devuelve un solo tipo de trámite por su id.
	@GetMapping("/{id}")
	public TipoTramiteResponse obtener(@PathVariable Long id) {
		return TipoTramiteResponse.from(tipoTramiteService.obtener(id));
	}

	// POST /api/catalog -> crea un tipo de trámite nuevo. Devuelve HTTP 201
	// (CREATED) cuando sale bien.
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public TipoTramiteResponse crear(@RequestBody TipoTramiteCrearRequest request) {
		return TipoTramiteResponse.from(tipoTramiteService.crear(request));
	}

	// PUT /api/catalog/{id} -> actualiza los datos de un tipo de trámite
	// existente (nombre, descripción, cupo diario, si está activo, etc.).
	@PutMapping("/{id}")
	public TipoTramiteResponse actualizar(@PathVariable Long id, @RequestBody TipoTramiteActualizarRequest request) {
		return TipoTramiteResponse.from(tipoTramiteService.actualizar(id, request));
	}

	// PATCH /api/catalog/{id}/decrementar-cupo -> se llama cada vez que se
	// crea un trámite de este tipo, para descontar un cupo disponible hoy.
	@PatchMapping("/{id}/decrementar-cupo")
	public TipoTramiteResponse decrementarCupo(@PathVariable Long id) {
		return TipoTramiteResponse.from(tipoTramiteService.decrementarCupo(id));
	}

	// PATCH /api/catalog/{id}/reponer-cupo -> vuelve a dejar el cupo
	// disponible de hoy en el máximo (cupoDiario), por ejemplo si se
	// cancela un trámite o al empezar un nuevo día.
	@PatchMapping("/{id}/reponer-cupo")
	public TipoTramiteResponse reponerCupo(@PathVariable Long id) {
		return TipoTramiteResponse.from(tipoTramiteService.reponerCupo(id));
	}

	// DELETE /api/catalog/{id} -> elimina un tipo de trámite. Devuelve HTTP
	// 204 (NO_CONTENT) porque no hay nada que devolver en el cuerpo.
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
		tipoTramiteService.eliminar(id);
	}

}
