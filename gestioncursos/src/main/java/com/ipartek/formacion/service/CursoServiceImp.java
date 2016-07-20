package com.ipartek.formacion.service;

import java.util.List;


import com.ipartek.formacion.dbms.dao.CursoDAO;
import com.ipartek.formacion.dbms.dao.CursoDAOImp;
import com.ipartek.formacion.pojo.Alumno;
import com.ipartek.formacion.pojo.Curso;

public class CursoServiceImp implements CursoService {
	private static CursoServiceImp INSTANCE = null;
	private CursoDAO cursoDAO;

	private CursoServiceImp() {
		
		cursoDAO =new CursoDAOImp();
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
	public Curso create(Curso curso) {
		Curso cur = cursoDAO.create(curso);
		return cur;
	}

	@Override
	public Curso getById(int codigo) {

		Curso curso = cursoDAO.getById(codigo);

		return curso;
	}

	@Override
	public void delete(int codigo) {
		cursoDAO.delete(codigo);
	}

	
	@Override
	public List<Curso> getAll() {
		List<Curso> cursos = cursoDAO.getAll();
		return cursos;
	}

	@Override
	public Curso update(Curso curso) {

		return cursoDAO.update(curso);
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