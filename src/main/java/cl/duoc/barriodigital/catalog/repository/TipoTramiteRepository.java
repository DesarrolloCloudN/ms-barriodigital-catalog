package cl.duoc.barriodigital.catalog.repository;

import cl.duoc.barriodigital.catalog.entity.TipoTramite;
import org.springframework.data.jpa.repository.JpaRepository;

// Repository: es la capa que habla con la base de datos. Al extender de
// JpaRepository ya vienen gratis los métodos típicos (guardar, buscar por
// id, buscar todos, eliminar, etc.), sin tener que escribir SQL a mano.
public interface TipoTramiteRepository extends JpaRepository<TipoTramite, Long> {
}
