package es.mdef.partidosDeFutbol.REST;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import es.mdef.partidosDeFutbol.entidades.Partido;

@Component
public class PartidoAssembler implements RepresentationModelAssembler<Partido, PartidoModel>{

	@Override
	public PartidoModel toModel(Partido entity) {
		PartidoModel model = new PartidoModel();
		model.setIdLocal(entity.getIdLocal());
		model.setIdVisitante(entity.getIdVisitante());
		model.setGolesLocal(entity.getGolesLocal());
		model.setGolesVisitante(entity.getGolesVisitante());
		model.add(
				linkTo(methodOn(PartidoController.class).one(entity.getId())).withSelfRel(),
				linkTo(methodOn(PartidoController.class).temporadasPartido(entity.getId())).withRel("temporadasPartidodesdePartidoAssembler")
				);
		return model;
	}
	
	public Partido toEntity(PartidoModel model) {
		Partido partido = new Partido();
		partido.setIdLocal(model.getIdLocal());
		partido.setIdVisitante(model.getIdVisitante());
		partido.setGolesLocal(model.getGolesLocal());
		partido.setGolesVisitante(model.getGolesVisitante());
		
		return partido;
	}
}
