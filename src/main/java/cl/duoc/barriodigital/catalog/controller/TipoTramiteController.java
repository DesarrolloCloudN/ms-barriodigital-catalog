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

// Endpoints internos de catálogo (tipos de trámite y cupos), usados por el bff.
@RestController
@RequestMapping("/api/catalog")
public class TipoTramiteController {

	private final TipoTramiteService tipoTramiteService;

	public TipoTramiteController(TipoTramiteService tipoTramiteService) {
		this.tipoTramiteService = tipoTramiteService;
	}

	@GetMapping
	public List<TipoTramiteResponse> listar() {
		return tipoTramiteService.listar().stream()
				.map(TipoTramiteResponse::from)
				.toList();
	}

	@GetMapping("/{id}")
	public TipoTramiteResponse obtener(@PathVariable Long id) {
		return TipoTramiteResponse.from(tipoTramiteService.obtener(id));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public TipoTramiteResponse crear(@RequestBody TipoTramiteCrearRequest request) {
		return TipoTramiteResponse.from(tipoTramiteService.crear(request));
	}

	@PutMapping("/{id}")
	public TipoTramiteResponse actualizar(@PathVariable Long id, @RequestBody TipoTramiteActualizarRequest request) {
		return TipoTramiteResponse.from(tipoTramiteService.actualizar(id, request));
	}

	// Descuenta un cupo disponible del tipo de trámite indicado.
	@PatchMapping("/{id}/decrementar-cupo")
	public TipoTramiteResponse decrementarCupo(@PathVariable Long id) {
		return TipoTramiteResponse.from(tipoTramiteService.decrementarCupo(id));
	}

	// Restablece el cupo disponible de hoy al máximo (cupoDiario).
	@PatchMapping("/{id}/reponer-cupo")
	public TipoTramiteResponse reponerCupo(@PathVariable Long id) {
		return TipoTramiteResponse.from(tipoTramiteService.reponerCupo(id));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
		tipoTramiteService.eliminar(id);
	}

}
