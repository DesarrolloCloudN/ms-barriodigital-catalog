package cl.duoc.barriodigital.catalog.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// Tipo de trámite (ej. Certificado de residencia) con cupos por día.
@Entity
@Table(name = "TIPOS_TRAMITE")
public class TipoTramite {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "NOMBRE", nullable = false)
	private String nombre;

	@Column(name = "DESCRIPCION", length = 2000, nullable = false)
	private String descripcion;

	@Column(name = "REQUISITOS", length = 2000)
	private String requisitos;

	// Cupo máximo por día; solo cambia mediante un PUT explícito.
	@Column(name = "CUPO_DIARIO", nullable = false)
	private Integer cupoDiario;

	// Cupo que baja con cada trámite y se resetea aparte de cupoDiario.
	@Column(name = "CUPO_DISPONIBLE_HOY", nullable = false)
	private Integer cupoDisponibleHoy;

	@Column(name = "ACTIVO", nullable = false)
	private Boolean activo;

	// Constructor vacío que exige JPA; no se usa directamente en el código.
	protected TipoTramite() {
	}

	// Al crear, cupoDisponibleHoy arranca igual a cupoDiario y activo=true.
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
