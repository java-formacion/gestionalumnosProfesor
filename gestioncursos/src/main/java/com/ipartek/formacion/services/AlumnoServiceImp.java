package com.ipartek.formacion.services;

import java.util.List;

import org.apache.log4j.Logger;

import com.ipartek.formacion.dbms.dao.AlumnoDAO;
import com.ipartek.formacion.dbms.dao.AlumnoDAOImp;
import com.ipartek.formacion.pojo.Alumno;
import com.ipartek.formacion.pojo.Curso;

public final class AlumnoServiceImp implements AlumnoService {
	private static AlumnoServiceImp INSTANCE = null;
	private final static Logger LOG = Logger.getLogger(AlumnoService.class);

	private AlumnoDAO alumDAO;

	private AlumnoServiceImp() {

		alumDAO = AlumnoDAOImp.getInstance();

	}

	public static AlumnoServiceImp getInstance() {
		if (INSTANCE == null) {
			createInstance();
		}
		return INSTANCE;
	}

	private synchronized static void createInstance() {
		if (INSTANCE == null)
			INSTANCE = new AlumnoServiceImp();

	}

	private static Curso crearCursoAlumno() {
		Curso curso = new Curso();
		curso.setCodigo(1);
		curso.setNombre("Desarrollo de Aplicaciones con Tecnologias Web");
		return curso;
	}

	@Override
	public Alumno createAlumno(Alumno alumno) {
		Alumno alum = alumDAO.insert(alumno);
		return alum;
	}

	@Override
	public Alumno getById(int codigo) {

		Alumno alum = alumDAO.getByID(codigo);
		return alum;
	}

	@Override
	public void delete(int codigo) {

		alumDAO.delete(codigo);

	}

	@Override
	public List<Alumno> getAll() {
		return alumDAO.getAll();
	}

	@Override
	public Alumno update(Alumno alumno) {

		Alumno alum = alumDAO.update(alumno);
		return alum;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

}
