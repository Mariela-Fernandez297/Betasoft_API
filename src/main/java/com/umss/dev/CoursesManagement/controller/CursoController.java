package com.umss.dev.CoursesManagement.controller;

import java.util.List;


import java.util.Optional;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonView;
import com.umss.dev.CoursesManagement.model.Views;
import com.umss.dev.CoursesManagement.repository.CursoRepository;
import com.umss.dev.CoursesManagement.repository.InstructorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.umss.dev.CoursesManagement.exception.ResourceNotFoundException;
import com.umss.dev.CoursesManagement.model.Curso;
import com.umss.dev.CoursesManagement.service.CursoService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class CursoController {

	@Autowired
	InstructorRepository instructorRepository;
	@Autowired
	CursoRepository cursoRepository;
	@Autowired
	private CursoService cursoService;

	@GetMapping("/curso")
	@JsonView(Views.cursoViews.class)
	
	public List<Curso> getCursos(@RequestParam(required = false) String search) {
		if (search == null || search.isEmpty()) {
			return cursoService.findAll();
		} else {
			return cursoService.Search(search);
		}
	}

	@GetMapping("/curso/{id}")
	@JsonView(Views.cursoViews.class)
	public Optional<Curso> obtenerCurso(@PathVariable Long id) {
		return cursoService.findById(id);
	}

	@PostMapping("/CrearCurso")
	public Curso createCurso(@Valid @RequestBody Curso curso) {
		return cursoRepository.save(curso);
	}

	@PutMapping("/curso/{id}")
	public Curso updateCurso(@PathVariable(value = "id") long cursoId, @Valid @RequestBody Curso cursoDetails) {
		Curso curso = cursoRepository.findById(cursoId)
				.orElseThrow(() -> new ResourceNotFoundException("Curso", "id", cursoId));
		curso.setNombre(cursoDetails.getNombre());
		curso.setDescripcion(cursoDetails.getDescripcion());
		curso.setUbicacion_img(cursoDetails.getUbicacion_img());
		curso.setUbicacion_vid(cursoDetails.getUbicacion_vid());

		Curso updateCurso = cursoRepository.save(curso);
		return updateCurso;

	}
	
	//@PostMapping("/eliminar")
	//comentario random

	@DeleteMapping("/curso/{id}")
	
	public ResponseEntity<?> deleteCurso(@PathVariable(value = "id") long cursoId) {
		Curso curso = cursoRepository.findById(cursoId)
				.orElseThrow(() -> new ResourceNotFoundException("Curso", "id", cursoId));

		cursoRepository.delete(curso);

		return ResponseEntity.ok().build();
	}
	
	// FIRST VERSION(NO EXCEPTION CLASS)
	// @PostMapping("/CrearCurso")
	/**
	 * public ResponseEntity<?> CrearNewCurso(@RequestBody CrearRequest
	 * crearRequest) {
	 * 
	 * 
	 * Curso curso = new Curso(crearRequest.getNombre(),
	 * crearRequest.getDescripcion(), crearRequest.getUbicacion_img(),
	 * crearRequest.getUbicacion_vid(), crearRequest.getInstructor());
	 * 
	 * cursoRepository.save(curso); return ResponseEntity.ok("Curso creado");
	 * 
	 * } //@PutMapping("/curso/{id}") /**public Curso update(@RequestBody Curso
	 * curso, @PathVariable long id) {
	 * 
	 * Curso cursoActual = cursoRepository.findById(id).get();
	 * 
	 * cursoActual.setNombre(curso.getNombre());
	 * cursoActual.setDescripcion(curso.getDescripcion());
	 * cursoActual.setUbicacion_img(curso.getUbicacion_img());
	 * cursoActual.setUbicacion_vid(curso.getUbicacion_vid()); return
	 * cursoRepository.save(cursoActual);
	 * 
	 * }
	 * 
	 * @DeleteMapping("/curso/{id}") public void delete(@PathVariable long id) {
	 * cursoRepository.deleteById(id);
	 * 
	 * 
	 * }
	 */
}
