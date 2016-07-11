package com.ipartek.formacion.service;

import java.util.ArrayList;
import java.util.List;

import com.ipartek.formacion.pojo.Alumno;
import com.ipartek.formacion.pojo.Curso;
import com.ipartek.formacion.service.excepciones.CursoServiceImpException;

public class CursoServiceImp implements CursoService {
	private static CursoServiceImp INSTANCE = null;
	private List<Curso> cursos;
	private static int i = 1;

	private void init() {
		Curso curso = new Curso();
		curso.setCodigo(i);
		curso.setNombre("Programacion");
		cursos.add(curso);
		i++;

		curso = new Curso();
		curso.setCodigo(i);
		curso.setNombre("Diseño");
		cursos.add(curso);
		i++;

		curso = new Curso();
		curso.setCodigo(i);
		curso.setNombre("Base de Datos");
		cursos.add(curso);
		i++;
	}

	private CursoServiceImp() {
		this.cursos = new ArrayList<Curso>();
		init();
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

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		throw new CloneNotSupportedException();
	}

	@Override
	public Curso createCurso(Curso curso) {
		curso.setCodigo(i);
		cursos.add(curso);
		i++;
		return curso;
	}

	private int getIndex(int codigo) throws CursoServiceImpException {
		int index = -1;
		int i = 0;
		boolean encontrado = false;
		while (i < this.cursos.size() && !encontrado) {
			Curso aux = cursos.get(i);
			if (aux.getCodigo() == codigo) {
				index = i;
				encontrado = true;
			}
			i++;
		}
		if (index == -1)
			throw new CursoServiceImpException(
					CursoServiceImpException.CODIGO_ERROR_INDEX_INVALIDO,
					CursoServiceImpException.MSG_ERROR_INDEX_INVALIDO);
		return index;
	}

	@Override
	public Curso getById(int codigo) {
		Curso curso = null;
		try {
			curso = this.cursos.get(getIndex(codigo));
		} catch (CursoServiceImpException e) {
			System.out.println(e.getMessage());
		}
		return curso;
	}

	@Override
	public void delete(int codigo) {
		int index;
		try {
			index = getIndex(codigo);
			this.cursos.remove(index);
		} catch (CursoServiceImpException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public List<Curso> getAll() {
		return this.cursos;
	}

	@Override
	public Curso update(Curso curso) {
		int index;
		try {
			index = getIndex(curso.getCodigo());
			this.cursos.set(index, curso);
		} catch (CursoServiceImpException e) {
			System.out.println(e.getMessage());
		}

		return curso;
	}

	@Override
	public void darDeAlta(Alumno alumno, int codigo) {
		Curso curso = getById(codigo);
		curso.getAlumnos().put(alumno.getDni(), alumno);
		update(curso);

	}

	@Override
	public void darDeBaja(String dni, int codigo) {
		Curso curso = getById(codigo);
		curso.getAlumnos().remove(dni);
		update(curso);

	}

	@Override
	public void darDeAlta(Alumno alumno) {
		Curso curso = getById(alumno.getCurso().getCodigo());
		curso.getAlumnos().put(alumno.getDni(), alumno);
		update(curso);

	}

	@Override
	public void darDeBaja(Alumno alumno) {
		Curso curso = getById(alumno.getCurso().getCodigo());
		curso.getAlumnos().remove(alumno.getDni());
		update(curso);

	}

}
