package com.umss.dev.CoursesManagement.controller;

import java.util.List;



import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.umss.dev.CoursesManagement.model.Inscripcion;
import com.umss.dev.CoursesManagement.model.Views;
import com.umss.dev.CoursesManagement.payload.request.InscripcionRequest;
import com.umss.dev.CoursesManagement.repository.CursoRepository;
import com.umss.dev.CoursesManagement.repository.EstudianteRepository;
import com.umss.dev.CoursesManagement.repository.InscripcionRepository;
import com.umss.dev.CoursesManagement.service.InscripcionService;


@RestController
@RequestMapping("/api")
public class InscritoController {
	@Autowired
	InscripcionRepository inscripcionRepository;
	@Autowired
	EstudianteRepository estudianteRepository;
	@Autowired
	CursoRepository cursoRepository;
	@Autowired
	private InscripcionService inscripcionService;
	@JsonView(Views.inscritosViews.class)
	@GetMapping("/inscritos")
	public List<Inscripcion> getInscritos(){
		return inscripcionService.findAll();	
	}


	@GetMapping("/inscritos/{id}")
	@JsonView(Views.inscritosViews.class)
	public Optional<Inscripcion> obtenerInscrito(@PathVariable Long id){
	return inscripcionService.findById(id);
	}
	@PostMapping("inscribirse")
	public ResponseEntity<?> InscribirseCurso(@RequestBody InscripcionRequest inscripcionRequest) {
		//inscripcionService.findByEstudianteCurso(inscripcionRequest.getCurso(), inscripcionRequest.getEstudiante());
		//Inscripcion inscripcionCurso = inscripcionService.findByEstudianteCurso(inscripcionRequest.getCurso(), inscripcionRequest.getEstudiante()).get(0);
		//System.out.print(inscripcionCurso);
		//comentario para que se vuelva deployar
	Inscripcion inscripcion = new Inscripcion(inscripcionRequest.getEstudiante(),
			inscripcionRequest.getCurso());
	inscripcionRepository.save(inscripcion);
	return ResponseEntity.ok("ir al curso");
	
	}
	/**@PostMapping("/inscribirse")
	public Inscripcion inscribirseACurso(@Valid @RequestBody Inscripcion inscripcion) {
		return inscripcionRepository.save(inscripcion);
	}*/
	

}
