package es.mdef.partidosDeFutbol.REST;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import es.mdef.partidosDeFutbol.entidades.Temporada;

@Component
public class TemporadaAssembler implements RepresentationModelAssembler<Temporada, TemporadaModel>{

	public TemporadaModel toModel (Temporada entity) {
		TemporadaModel model = new TemporadaModel();
		model.setNombre(entity.getNombre());
		model.setFecha(entity.getFecha());
		model.setHora(entity.getHora());
		model.setPartido(entity.getPartido());
		model.add(
				linkTo(methodOn(TemporadaController.class).one(entity.getId())).withSelfRel(),
				linkTo(methodOn(PartidoController.class).one(entity.getPartido().getId())).withRel("partidodesdeTemporadaAsem")
				);
		return model;
	}
	
	public Temporada toEntity(TemporadaModel model) {
		Temporada temporada = new Temporada();
		temporada.setNombre(model.getNombre());
		temporada.setFecha(model.getFecha());
		temporada.setHora(model.getHora());
		temporada.setPartido(model.getPartido());
		return temporada;
	}
}
