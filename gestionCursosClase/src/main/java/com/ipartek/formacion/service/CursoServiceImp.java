package com.ipartek.formacion.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ipartek.formacion.pojo.Alumno;
import com.ipartek.formacion.pojo.Curso;
import com.ipartek.formacion.service.exceptions.CursoServiceException;

public class CursoServiceImp implements CursoService {

	private List<Curso> cursos;
	private static int i = 1;
	public CursoServiceImp(){
		init();
	}
	private void init() {
		cursos = new ArrayList<Curso>();
		Curso curso = new Curso();
		curso.setNombre("Desarrollo de aplicaciones con tecnologías web Java / ASP.NET");
		create(curso);
		
		
		curso = new Curso();
		curso.setNombre("Curso numero 2");
		create(curso);
		
		
		curso = new Curso();
		curso.setNombre("Curso numero 3");
		create(curso);
		
	}
	@Override
	public Curso create(Curso curso) {
		curso.setCodigo(i);
		this.cursos.add(curso);
		i++;
		return curso;
	}

	@Override
	public Curso getById(int codigo) {
		 Curso curso = null;
		try {
			curso = this.cursos.get(getIndex(codigo));
		} catch (CursoServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return curso;
	}

	@Override
	public void delete(int codigo) {
		try {
			this.cursos.remove(getIndex(codigo));
		} catch (CursoServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Curso> getAll() {
		
		return this.cursos;
	}

	@Override
	public Curso update(Curso curso) {
		try {
			this.cursos.set(getIndex(curso.getCodigo()), curso);
		} catch (CursoServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return curso;
	}
	private int getIndex(int codigo) throws CursoServiceException{
		int i = 0,index = -1,len = cursos.size();
		boolean econtrado = false;
		while (i < len && econtrado == false){
			if(cursos.get(i).getCodigo()==codigo){
				econtrado = true;
				index = i;
			}
			i++;
		}
		if(i == -1){
			throw new CursoServiceException(CursoServiceException.CODIGO_CURSO_NO_ECONTRADO, CursoServiceException.MSG_CURSO_NO_ENCONTRADO);
		}
			
		return index;
	}
	@Override
	public void darDeAlta(Alumno alumno) {
		//1. obtener el curso
		int codigo = alumno.getCurso().getCodigo();
		Curso curso = getById(codigo);
		//2.obtener el Map
		Map<String,Alumno> alumnos = curso.getAlumnos();
		//3.meter el alumno en el Mapa
		alumnos.put(alumno.getDni(), alumno);
		//4.guardar/actualizar el curso 
		curso.setAlumnos(alumnos);
		update(curso);
	}

	@Override
	public void darDeBaja(Alumno alumno) {
		int codigo  = alumno.getCurso().getCodigo();
		Curso curso1 = alumno.getCurso();

		Curso curso = getById(codigo);
		Map<String, Alumno> alumnos = curso.getAlumnos();
		alumnos.remove(alumno.getDni());
		curso.setAlumnos(alumnos);
		update(curso);
		
		
		
	}

}
