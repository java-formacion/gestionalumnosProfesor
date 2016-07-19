package com.ipartek.formacion.service;

import java.util.List;
import java.util.Map;

import com.ipartek.formacion.dbms.dao.CursoDAO;
import com.ipartek.formacion.dbms.dao.CursoDAOImp;
import com.ipartek.formacion.pojo.Alumno;
import com.ipartek.formacion.pojo.Curso;

public class CursoServiceImp implements CursoService {
	private static CursoServiceImp INSTANCE = null;
	private CursoDAO curDAO;

	/*
	 * private List<Curso> cursos; private static int i = 1;
	 */
	private CursoServiceImp() {
		// init();
		curDAO = CursoDAOImp.getInstance();
	}

	public static CursoServiceImp getInstance() {
		if (INSTANCE == null) {
			createInstance();
		}
		return INSTANCE;
	}

	private synchronized static void createInstance() {
		if (INSTANCE == null) {
			INSTANCE = new CursoServiceImp();
		}

	}

	/*
	 * private void init() { cursos = new ArrayList<Curso>(); Curso curso = new
	 * Curso(); curso.setNombre(
	 * "Desarrollo de aplicaciones con tecnologías web Java / ASP.NET");
	 * create(curso); }
	 */
	@Override
	public Curso create(Curso curso) {
		Curso cur = curDAO.create(curso);
		return cur;
	}

	@Override
	public Curso getById(int codigo) {
		Curso curso = curDAO.getById(codigo);
		return curso;
	}

	@Override
	public void delete(int codigo) {
		curDAO.delete(codigo);
	}

	@Override
	public List<Curso> getAll() {
		return curDAO.getAll();
	}

	@Override
	public Curso update(Curso curso) {
		Curso cur = curDAO.update(curso);
		return curso;
	}

	/*
	 * private int getIndex(int codigo) throws CursoServiceException { int i =
	 * 0, index = -1, len = cursos.size(); boolean econtrado = false; while (i <
	 * len && econtrado == false) { if (cursos.get(i).getCodigo() == codigo) {
	 * econtrado = true; index = i; } i++; } if (i == -1) { throw new
	 * CursoServiceException(CursoServiceException.CODIGO_CURSO_NO_ECONTRADO,
	 * CursoServiceException.MSG_CURSO_NO_ENCONTRADO); }
	 * 
	 * return index; }
	 */

	@Override
	public void darDeAlta(Alumno alumno) {
		// 1. obtener el curso
		int codigo = alumno.getCurso().getCodigo();
		Curso curso = getById(codigo);
		// 2.obtener el Map
		Map<String, Alumno> alumnos = curso.getAlumnos();
		// 3.meter el alumno en el Mapa
		alumnos.put(alumno.getDni(), alumno);
		// 4.guardar/actualizar el curso
		curso.setAlumnos(alumnos);
		update(curso);
	}

	@Override
	public void darDeBaja(Alumno alumno) {
		int codigo = alumno.getCurso().getCodigo();
		Curso curso1 = alumno.getCurso();

		Curso curso = getById(codigo);
		Map<String, Alumno> alumnos = curso.getAlumnos();
		alumnos.remove(alumno.getDni());
		curso.setAlumnos(alumnos);
		update(curso);

	}

}
