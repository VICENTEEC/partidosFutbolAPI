package es.mdef.partidosDeFutbol.REST;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonFormat;


@Relation(collectionRelation = "temporadas")
public class TemporadaListaModel extends RepresentationModel<TemporadaListaModel>{
	private String nombre;
	
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate fecha;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime hora;

	private PartidoModel partido;
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public LocalDate getFecha() {
		return fecha;
	}
	
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
	public LocalTime getHora() {
		return hora;
	}
	
	public void setHora(LocalTime hora) {
		this.hora = hora;
	}
	
	public PartidoModel getPartido() {
		return partido;
	}
	public void setPartido(PartidoModel partido) {
		this.partido = partido;
	}
}
