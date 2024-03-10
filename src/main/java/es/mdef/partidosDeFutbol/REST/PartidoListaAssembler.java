package es.mdef.partidosDeFutbol.REST;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import es.mdef.partidosDeFutbol.entidades.Partido;


@Component
public class PartidoListaAssembler implements RepresentationModelAssembler<Partido, PartidoListaModel>{

	@Override
	public PartidoListaModel toModel(Partido entity) {
		PartidoListaModel model = new PartidoListaModel();
		model.setIdLocal(entity.getIdLocal());
		model.setIdVisitante(entity.getIdVisitante());
		model.setGolesLocal(entity.getGolesLocal());
		model.setGolesVisitante(entity.getGolesVisitante());
		model.add(
				linkTo(methodOn(PartidoController.class).one(entity.getId())).withSelfRel()
				);
		return model;
	}
	
	public CollectionModel<PartidoListaModel> toCollection(List<Partido> lista) {
		CollectionModel<PartidoListaModel> collection = CollectionModel.of(
				lista.stream().map(this::toModel).collect(Collectors.toList())
				);
		collection.add(
				linkTo(methodOn(PartidoController.class).all()).withRel("partidos")
				);
		return collection;
	}
}
