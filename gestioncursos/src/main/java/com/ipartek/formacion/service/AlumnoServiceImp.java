package com.ipartek.formacion.service;

import java.util.ArrayList;
import java.util.List;

import com.ipartek.formacion.pojo.Alumno;
import com.ipartek.formacion.pojo.excepciones.CandidatoException;
import com.ipartek.formacion.service.excepciones.AlumnoServiceImpException;

public class AlumnoServiceImp implements AlumnoService {
	private static AlumnoServiceImp INSTANCE = null;
	private List<Alumno> alumnos;
	private static int i = 1;

	private void init() {
		Alumno alumno;
		try {
			alumno = new Alumno();
			alumno.setCodigo(1);
			alumno.setNombre("Imanol");
			alumno.setDni("45751880G");
			alumno.setApellidos("Jimenez Lopez");
			this.alumnos.add(alumno);
			i++;
		} catch (CandidatoException e) {
			System.out.println(e.getMessage());
		}
		try {
			alumno = new Alumno();
			alumno.setCodigo(2);
			alumno.setNombre("Josu");
			alumno.setDni("45751881M");
			alumno.setApellidos("Asua Gallego");
			this.alumnos.add(alumno);
			i++;
		} catch (CandidatoException e) {
			System.out.println(e.getMessage());
		}
		try {
			alumno = new Alumno();
			alumno.setCodigo(3);
			alumno.setNombre("Alvaro");
			alumno.setDni("15360981V");
			alumno.setApellidos("Rodriguez Miguel");
			this.alumnos.add(alumno);
			i++;
		} catch (CandidatoException e) {
			System.out.println(e.getMessage());
		}
	}

	private AlumnoServiceImp() {
		this.alumnos = new ArrayList<Alumno>();
		init();
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

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		throw new CloneNotSupportedException();
	}

//	public static Alumno getAlumno() {
//		Alumno alum = null;
//		try {
//			alum = new Alumno();
//			alum.setCodigo(1);
//			alum.setNombre("Imanol");
//			alum.setApellidos("Jimenez Lopez");
//			alum.setNota(0.0);
//			alum.setCurso(crearCurso());
//		} catch (CandidatoException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return alum;
//
//	}

//	private static Curso crearCurso() {
//		Curso curso = null;
//		curso = new Curso();
//		curso.setCodigo(1);
//		curso.setNombre("Desarrollo de Aplicaciones con Tecnologias Web");
//		return curso;
//	}

	@Override
	public Alumno createAlumno(Alumno alum) {
		alum.setCodigo(i);
		alumnos.add(alum);
		i++;
		return alum;
	}

	@Override
	public Alumno getById(int codigo) {
		Alumno alum = null;
		try {
			alum = this.alumnos.get(getIndex(codigo));
		} catch (AlumnoServiceImpException e) {
			System.out.println(e.getMessage());
		}
		return alum;
	}

	@Override
	public void delete(int codigo) {
		int index;
		try {
			index = getIndex(codigo);
			this.alumnos.remove(index);
		} catch (AlumnoServiceImpException e) {
			System.out.println(e.getMessage());
		}

	}

	/*
	 * excepcion i = -1 (alumno, curso, modulo)
	 */
	private int getIndex(int codigo) throws AlumnoServiceImpException {
		int index = -1;
		int ix = 0;
		boolean encontrado = false;
		while (ix < this.alumnos.size() && !encontrado) {
			Alumno aux = alumnos.get(ix);
			if (aux.getCodigo() == codigo) {
				index = ix;
				encontrado = true;
			}
			ix++;
		}
		if (index == -1)
			throw new AlumnoServiceImpException(
					AlumnoServiceImpException.CODIGO_ERROR_INDEX_INVALIDO,
					AlumnoServiceImpException.MSG_ERROR_INDEX_INVALIDO);
		return index;
	}

	@Override
	public List<Alumno> getAll() {
		return this.alumnos;
	}

	@Override
	public Alumno update(Alumno alumno) {
		int index;
		try {
			index = getIndex(alumno.getCodigo());
			this.alumnos.set(index, alumno);
		} catch (AlumnoServiceImpException e) {
			System.out.println(e.getMessage());
		}
		return alumno;
	}

}
