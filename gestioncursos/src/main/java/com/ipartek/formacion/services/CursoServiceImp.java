package com.ipartek.formacion.services;

import java.util.List;

import com.ipartek.formacion.dbms.dao.CursoDAO;
import com.ipartek.formacion.pojo.Alumno;
import com.ipartek.formacion.pojo.Curso;

public final class CursoServiceImp implements CursoService {
	private static CursoServiceImp INSTANCE = null;
	private CursoDAO curDAO;

	private CursoServiceImp() {
		init();
	}

	public static CursoServiceImp getInstance() {
		if (INSTANCE == null) {
			createInstance();
		}
		return INSTANCE;
	}

	private synchronized static void createInstance() {
		if (INSTANCE == null)
			INSTANCE = new CursoServiceImp();

	}

	private void init() {

	}

	@Override
	public Curso create(Curso curso) {
		Curso cur = curDAO.insert(curso);

		return cur;
	}

	@Override
	public Curso getById(int codigo) {
		Curso curso = curDAO.getByID(codigo);
		return curso;
	}

	@Override
	public void delete(int codigo) {
		curDAO.delete(codigo);
	}

	@Override
	public List<Curso> getAll() {
		List<Curso> cursos = null;
		cursos = curDAO.getAll();
		return cursos;
	}

	@Override
	public Curso update(Curso curso) {

		Curso cur = curDAO.update(curso);
		return cur;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	@Override
	public void darDeAlta(Alumno alumno) {
		// TODO Auto-generated method stub

	}

	@Override
	public void darDeBaja(Alumno alumno) {
		// TODO Auto-generated method stub

	}

}
