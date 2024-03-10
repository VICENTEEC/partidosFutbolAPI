package es.mdef.partidosDeFutbol.REST;

import org.slf4j.Logger;
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

import es.mdef.partidosDeFutbol.PartidosDeFutbolApplication;
import es.mdef.partidosDeFutbol.entidades.Temporada;
import es.mdef.partidosDeFutbol.repositorios.TemporadaRepositorio;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/temporadas")
public class TemporadaController {
	private final TemporadaRepositorio repositorio;
	private final TemporadaAssembler assembler;
	private final TemporadaListaAssembler listaAssembler;
	private final Logger log;
	
	public TemporadaController(TemporadaRepositorio repositorio, TemporadaAssembler assembler, TemporadaListaAssembler listaAssembler) {
		this.repositorio = repositorio;
		this.assembler = assembler;
		this.listaAssembler = listaAssembler;
		log = PartidosDeFutbolApplication.log;
	}
	
	@GetMapping("{id}")
	public TemporadaModel one(@PathVariable Long id) {
		Temporada temporada = repositorio.findById(id)
				.orElseThrow(() -> new RegisterNotFoundException(id, "temporada"));
		log.info("Recuperada " + temporada);
		return assembler.toModel(temporada);
	}
	
	@GetMapping
	public CollectionModel<TemporadaListaModel> all() {
		log.info("Recuperada lista de temporadas");
		return listaAssembler.toCollection(repositorio.findAll());
	}
	
	@PostMapping
	public TemporadaModel add(@RequestBody TemporadaModel model) {
		Temporada temporada = repositorio.save(assembler.toEntity(model));
		log.info("Añadida " + temporada);
		return assembler.toModel(temporada);
	}
	
	@PutMapping("{id}")
	public TemporadaModel edit(@PathVariable Long id, @RequestBody TemporadaModel model) {
		Temporada temporada = repositorio.findById(id).map(temp -> {
			temp.setNombre(model.getNombre());
			temp.setFecha(model.getFecha());
			temp.setHora(model.getHora());
			temp.setPartido(model.getPartido());
			return repositorio.save(temp);
		})
		.orElseThrow(() -> new RegisterNotFoundException(id, "temporada"));
		log.info("Actualizada " + temporada);
		return assembler.toModel(temporada);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
		log.info("Borrada temporada " + id);
		repositorio.deleteById(id);
	}
}
