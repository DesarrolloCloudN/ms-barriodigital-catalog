package cl.duoc.barriodigital.catalog.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// Representa un tipo de trámite (por ejemplo "Certificado de residencia"),
// junto con cuántos cupos tiene disponibles por día. @Entity + @Table le
// dicen a JPA que esta clase se guarda en la tabla TIPOS_TRAMITE.
@Entity
@Table(name = "TIPOS_TRAMITE")
public class TipoTramite {

	// Id autogenerado por la base de datos (autoincremental).
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "NOMBRE", nullable = false)
	private String nombre;

	@Column(name = "DESCRIPCION", length = 2000, nullable = false)
	private String descripcion;

	@Column(name = "REQUISITOS", length = 2000)
	private String requisitos;

	// Cupo máximo de trámites de este tipo que se pueden atender por día.
	// Este número es fijo hasta que alguien lo cambie con un PUT.
	@Column(name = "CUPO_DIARIO", nullable = false)
	private Integer cupoDiario;

	// Cupo que va quedando disponible durante el día de hoy. Se guarda
	// aparte de cupoDiario porque cupoDiario es el máximo configurado (no
	// cambia solo), mientras que cupoDisponibleHoy va bajando cada vez que
	// se crea un trámite (decrementarCupo) y se puede volver a llenar
	// (reponerCupo), por ejemplo al empezar un nuevo día o si se cancela
	// un trámite.
	@Column(name = "CUPO_DISPONIBLE_HOY", nullable = false)
	private Integer cupoDisponibleHoy;

	// Indica si este tipo de trámite se puede seguir usando o si está dado
	// de baja.
	@Column(name = "ACTIVO", nullable = false)
	private Boolean activo;

	// Constructor vacío que pide JPA internamente para poder crear los
	// objetos cuando lee desde la base de datos. No se usa directamente.
	protected TipoTramite() {
		// requerido por JPA
	}

	// Constructor que se usa al crear un tipo de trámite nuevo. Al crearlo,
	// el cupo disponible de hoy arranca igual al cupo diario configurado,
	// y queda activo por defecto.
	public TipoTramite(String nombre, String descripcion, String requisitos, Integer cupoDiario) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.requisitos = requisitos;
		this.cupoDiario = cupoDiario;
		this.cupoDisponibleHoy = cupoDiario;
		this.activo = true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getRequisitos() {
		return requisitos;
	}

	public void setRequisitos(String requisitos) {
		this.requisitos = requisitos;
	}

	public Integer getCupoDiario() {
		return cupoDiario;
	}

	public void setCupoDiario(Integer cupoDiario) {
		this.cupoDiario = cupoDiario;
	}

	public Integer getCupoDisponibleHoy() {
		return cupoDisponibleHoy;
	}

	public void setCupoDisponibleHoy(Integer cupoDisponibleHoy) {
		this.cupoDisponibleHoy = cupoDisponibleHoy;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

}
