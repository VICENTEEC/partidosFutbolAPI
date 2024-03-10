package es.mdef.partidosDeFutbol.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.mdef.partidosDeFutbol.entidades.Temporada;

@Repository
public interface TemporadaRepositorio extends JpaRepository <Temporada, Long>{

}