package es.mdef.partidosDeFutbol.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.mdef.partidosDeFutbol.entidades.Partido;

@Repository
public interface PartidoRepositorio extends JpaRepository<Partido, Long>{

}
