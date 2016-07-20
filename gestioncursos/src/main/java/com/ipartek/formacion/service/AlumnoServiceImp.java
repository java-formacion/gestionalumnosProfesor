package com.ipartek.formacion.service;

import java.util.List;

import com.ipartek.formacion.dbms.dao.AlumnoDAO;
import com.ipartek.formacion.dbms.dao.AlumnoDAOImp;
import com.ipartek.formacion.pojo.Alumno;
import com.ipartek.formacion.pojo.Curso;

public class AlumnoServiceImp implements AlumnoService {
	private static AlumnoServiceImp INSTANCE = null;
	private AlumnoDAO alumDAO;

	// private List<Alumno> alumnos;
	// private static int i = 1;
	/*
	 * private void init() { Alumno alumno = null;
	 *
	 * alumno = new Alumno(); alumno.setCodigo(1);
	 *
	 * alumno.setNombre("Imanol"); alumno.setApellidos("Jimenez Lopez");
	 * alumnos.add(alumno); i++;
	 *
	 * alumno = new Alumno(); alumno.setCodigo(2); alumno.setNombre("Josu");
	 *
	 * alumno.setApellidos("Asua Gallego"); alumnos.add(alumno); i++;
	 *
	 * alumno = new Alumno(); alumno.setCodigo(3); alumno.setNombre("Alvaro");
	 * alumno.setApellidos("Rodriguez Miguel"); alumnos.add(alumno); i++;
	 *
	 * }
	 */
	private AlumnoServiceImp() {
		// this.alumnos = new ArrayList<Alumno>();
		// init();
		alumDAO = AlumnoDAOImp.getInstance();
	}

	public static AlumnoServiceImp getInstance() {
		if (INSTANCE == null) {
			createInstance();
		}
		return INSTANCE;
	}

	private synchronized static void createInstance() {
		if (INSTANCE == null) {
			INSTANCE = new AlumnoServiceImp();
		}

	}

	private static Curso crearCursoAlumno() {
		Curso curso = null;
		curso = new Curso();
		curso.setCodigo(1);
		curso.setNombre("Desarrollo de Aplicaciones con Tecnologias Web");
		return curso;
	}

	@Override
	public Alumno createAlumno(Alumno alumno) {
		Alumno alum = alumDAO.create(alumno);
		return alum;
	}

	@Override
	public Alumno getById(int codigo) {

		Alumno alumno = alumDAO.getById(codigo);

		return alumno;
	}

	@Override
	public void delete(int codigo) {
		alumDAO.delete(codigo);
	}

	/*
	 * private int getIndex(int codigo) throws AlumnoServiceException { int
	 * index = -1; int i = 0, len = this.alumnos.size(); boolean encontrado =
	 * false; while (i < len && encontrado == false) { Alumno aux =
	 * this.alumnos.get(i);// alumnos[i]; if (aux.getCodigo() == codigo) {
	 * encontrado = true; index = i; } i++; } if (i == -1) { throw new
	 * AlumnoServiceException(
	 * AlumnoServiceException.CODIGO_ALUMNO_NO_ECONTRADO,
	 * AlumnoServiceException.MSG_ALUMNO_NO_ENCONTRADO); } return index; }
	 */
	@Override
	public List<Alumno> getAll() {
		List<Alumno> alumnos = alumDAO.getAll();
		return alumnos;
	}

	@Override
	public Alumno update(Alumno alumno) {

		return alumDAO.update(alumno);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {

		throw new CloneNotSupportedException();
	}

}
