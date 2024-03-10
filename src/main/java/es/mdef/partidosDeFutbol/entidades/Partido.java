package es.mdef.partidosDeFutbol.entidades;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity                      
@Table(name="PARTIDOS") 
public class Partido {
	
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    private String idLocal;
    private String idVisitante;
    private int golesLocal;
    private int golesVisitante;
    
    @OneToMany(mappedBy = "partido")                //RELACIONES
    List<Temporada> temporadas;

	public Long getId() {
		return id;
	}

	public String getIdLocal() {
		return idLocal;
	}

	public String getIdVisitante() {
		return idVisitante;
	}

	public int getGolesLocal() {
		return golesLocal;
	}

	public int getGolesVisitante() {
		return golesVisitante;
	}

	public List<Temporada> getTemporadas() {
		return temporadas;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setIdLocal(String idLocal) {
		this.idLocal = idLocal;
	}

	public void setIdVisitante(String idVisitante) {
		this.idVisitante = idVisitante;
	}

	public void setGolesLocal(int golesLocal) {
		this.golesLocal = golesLocal;
	}

	public void setGolesVisitante(int golesVisitante) {
		this.golesVisitante = golesVisitante;
	}

	public void setTemporadas(List<Temporada> temporadas) {
		this.temporadas = temporadas;
	}

	@Override
	public String toString() {
		return "Partido [id=" + id + ", idLocal=" + idLocal + ", idVisitante=" + idVisitante + ", golesLocal="
				+ golesLocal + ", golesVisitante=" + golesVisitante +"]";
	}

	
}
