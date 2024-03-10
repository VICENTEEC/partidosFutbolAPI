package es.mdef.partidosDeFutbol.REST;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import es.mdef.partidosDeFutbol.entidades.Temporada;


@Component
public class TemporadaListaAssembler implements RepresentationModelAssembler<Temporada, TemporadaListaModel>{

	private final PartidoAssembler partidoAssembler;

	public TemporadaListaAssembler(PartidoAssembler partidoAssembler) {
		this.partidoAssembler = partidoAssembler;
	}
	@Override
	public TemporadaListaModel toModel(Temporada entity) {
		TemporadaListaModel model = new TemporadaListaModel();
		model.setNombre(entity.getNombre());
		model.setFecha(entity.getFecha());
		model.setHora(entity.getHora());
		model.setPartido(partidoAssembler.toModel(entity.getPartido()));
		model.add(
				linkTo(methodOn(TemporadaController.class).one(entity.getId())).withSelfRel(),
				linkTo(methodOn(PartidoController.class).one(entity.getPartido().getId())).withRel("partidodesdeTemporadaListAssembler")
				);
		return model;
	}
	
	public CollectionModel<TemporadaListaModel> toCollection(List<Temporada> lista) {
		CollectionModel<TemporadaListaModel> collection = CollectionModel.of(
				lista.stream().map(this::toModel).collect(Collectors.toList())
				);
		collection.add(
				linkTo(methodOn(TemporadaController.class).all()).withRel("temporadas")
				);
		return collection;
	}
}
