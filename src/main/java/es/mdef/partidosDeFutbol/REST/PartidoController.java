package es.mdef.partidosDeFutbol.REST;

import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;

import es.mdef.partidosDeFutbol.entidades.Temporada;
import es.mdef.partidosDeFutbol.entidades.Partido;
import es.mdef.partidosDeFutbol.PartidosDeFutbolApplication;
import es.mdef.partidosDeFutbol.repositorios.PartidoRepositorio;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/partidos")
public class PartidoController {
	private final PartidoRepositorio repositorio;
	private final PartidoAssembler assembler;
	private final PartidoListaAssembler listaAssembler;
	private final TemporadaListaAssembler temporadaListaAssembler;
	private final Logger log;
	
	public PartidoController(PartidoRepositorio repositorio, PartidoAssembler assembler, PartidoListaAssembler listaAssembler, TemporadaListaAssembler temporadaListaAssembler) {
		this.repositorio = repositorio;
		this.assembler = assembler;
		this.listaAssembler = listaAssembler;
		this.temporadaListaAssembler = temporadaListaAssembler;
		log = PartidosDeFutbolApplication.log;
	}
	
	@GetMapping("{id}")
	public PartidoModel one(@PathVariable Long id) {
		Partido partido = repositorio.findById(id)
				.orElseThrow(() -> new RegisterNotFoundException(id, "partido"));
		log.info("Recuperado " + partido);
		return assembler.toModel(partido);
	}
	
	@GetMapping
	public CollectionModel<PartidoListaModel> all() {
		log.info("Recuperada lista de partidos");
		return listaAssembler.toCollection(repositorio.findAll());
	}
	
	@GetMapping("{id}/temporadas")
	public CollectionModel<TemporadaListaModel> temporadasPartido(@PathVariable Long id) {
		List<Temporada> temporadas = repositorio.findById(id)
				.orElseThrow(() -> new RegisterNotFoundException(id, "partido"))
				.getTemporadas();
		return CollectionModel.of(
				temporadas.stream().map(temporada -> temporadaListaAssembler.toModel(temporada)).collect(Collectors.toList()),
				linkTo(methodOn(PartidoController.class).one(id)).slash("temporadas").withSelfRel()
				);
	}
	
	@PostMapping
	public PartidoModel add(@RequestBody PartidoModel model) {
		Partido partido = repositorio.save(assembler.toEntity(model));
		log.info("Añadido " + partido);
		return assembler.toModel(partido);
	}
	
	@PutMapping("{id}")
	public PartidoModel edit(@PathVariable Long id, @RequestBody PartidoModel model) {
		Partido partido = repositorio.findById(id).map(part -> {
			part.setIdLocal(model.getIdLocal());
			part.setIdVisitante(model.getIdVisitante());
			part.setGolesLocal(model.getGolesLocal());
			part.setGolesVisitante(model.getGolesVisitante());
			return repositorio.save(part);
		})
		.orElseThrow(() -> new RegisterNotFoundException(id, "partido"));
		log.info("Actualizado " + partido);
		return assembler.toModel(partido);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
		log.info("Borrado partido " + id);
		repositorio.deleteById(id);
	}
}
