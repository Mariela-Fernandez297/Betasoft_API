package com.umss.dev.CoursesManagement.model;


import com.fasterxml.jackson.annotation.JsonView;


import java.util.List;
import javax.persistence.*;


@Entity
@Table(name="Curso")
public class Curso {
	
	@Id
	@JsonView({Views.cursoViews.class,Views.instructorViews.class, Views.usuarioViews.class, 
		Views.instructorCursoViews.class,Views.estudianteViews.class,Views.inscritosViews.class})
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_curso;
	@JsonView({Views.cursoViews.class, Views.instructorViews.class, Views.usuarioViews.class, 
		Views.instructorCursoViews.class,Views.estudianteViews.class,Views.inscritosViews.class,
		Views.SliderViews.class})
	private String nombre;
	@JsonView({Views.cursoViews.class, Views.instructorViews.class, Views.usuarioViews.class,
		Views.estudianteViews.class,Views.inscritosViews.class,Views.SliderViews.class})
	private String descripcion;
	@JsonView({Views.cursoViews.class, Views.instructorViews.class, Views.instructorCursoViews.class,
		Views.estudianteViews.class,Views.inscritosViews.class,Views.SliderViews.class})
	private String ubicacion_img;
	@JsonView({Views.cursoViews.class, Views.instructorViews.class, Views.instructorCursoViews.class,
		Views.estudianteViews.class,Views.inscritosViews.class,Views.SliderViews.class})
	private String ubicacion_vid;
	
	@ManyToOne
	@JoinColumn(name="id_instructor")
	@JsonView({Views.cursoViews.class})
	private Instructor instructor;
	
	
	@OneToMany(cascade = { CascadeType.ALL })
	@JoinColumn(name = "id_curso")
	private List<Inscripcion> inscripciones;
	
	public Curso() {
		super();
	}

	public Curso(String nombre, String descripcion, String ubicacion_img, String ubicacion_vid, Instructor instructor) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.ubicacion_img = ubicacion_img;
		this.ubicacion_vid = ubicacion_vid;
		this.instructor = instructor;
	}

	public long getId_curso() {
		return id_curso;
	}

	public void setId_curso(long id_curso) {
		this.id_curso = id_curso;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getUbicacion_img() {
		return ubicacion_img;
	}

	public void setUbicacion_img(String ubicacion_img) {
		this.ubicacion_img = ubicacion_img;
	}
	public String getUbicacion_vid() {
		return ubicacion_vid;
	}

	public void setUbicacion_vid(String ubicacion_vid) {
		this.ubicacion_vid = ubicacion_vid;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	//comentario random :v
}
