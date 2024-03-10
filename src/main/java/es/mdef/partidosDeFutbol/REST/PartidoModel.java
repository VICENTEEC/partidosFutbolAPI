package es.mdef.partidosDeFutbol.REST;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(itemRelation="partido")
public class PartidoModel extends RepresentationModel<PartidoModel>{

    private String idLocal;
    private String idVisitante;
    private int golesLocal;
    private int golesVisitante;
    
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
   
}
