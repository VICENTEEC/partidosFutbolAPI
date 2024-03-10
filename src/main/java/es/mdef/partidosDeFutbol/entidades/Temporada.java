package es.mdef.partidosDeFutbol.entidades;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity                      
@Table(name="TEMPORADAS") 
public class Temporada {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    private String nombre;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate fecha;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime hora;
    
	@ManyToOne(fetch=FetchType.LAZY)            //RELACIONES
	@JoinColumn(name="Partido")               //RELACIONES. ESTO CREA UNA NUEVA COLUMNA EN LA BASE DE DATOS
	private Partido partido;
    
	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public LocalTime getHora() {
		return hora;
	}

	public Partido getPartido() {
		return partido;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public void setPartido(Partido partido) {
		this.partido = partido;
	}

	@Override
	public String toString() {
		return "Temporada [id=" + id + ", nombre=" + nombre + ", fecha=" + fecha + ", hora=" + hora +"]";
	}	
}
