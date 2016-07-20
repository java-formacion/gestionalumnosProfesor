package com.ipartek.formacion.service;

import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ipartek.formacion.dbms.dao.AlumnoDAO;
import com.ipartek.formacion.dbms.dao.AlumnoDAOImp;
import com.ipartek.formacion.pojo.Alumno;
import com.ipartek.formacion.pojo.Curso;

public class AlumnoServiceImp implements AlumnoService {
	private static AlumnoServiceImp INSTANCE = null;
	private List<Alumno> alumnos;
	private static int i = 1; //para contar los objetos que añadimos
	private AlumnoDAO alumDAO;
	
	/*
	private void init(){
		Alumno alumno;
		
			alumno = new Alumno();
		
		alumno.setCodigo(i);
		alumno.setNombre("Marta");
		alumno.setApellidos("Rivera del Amo");
		alumno.setDni("16087431N");
		//alumno.fNacimiento=new Date();
		alumnos.add(alumno);
		i++;
		
		
		
			alumno = new Alumno();
		
		alumno.setCodigo(i);
		alumno.setNombre("Imanol");
		alumno.setApellidos("Jimenez Lopez");
		alumno.setDni("45751880G");
		alumnos.add(alumno);
		i++;
		
		
		try {
		alumno = new Alumno();
		alumno.setCodigo(i);
		alumno.setNombre("Josu");
		alumno.setApellidos("Asua Gallego");
		alumno.setDni("");
		alumnos.add(alumno);
		i++;
		} catch (CandidatoException e) {
			System.out.println(e.getMessage());
		}
		
	}*/
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

	
	/*
	public static  Alumno getAlumno(){
		Alumno alum = null;
		try {
			alum=new Alumno();
		} catch (CandidatoException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		alum.setNombre("Marta");
		alum.setApellidos("Rivera del amo");
		alum.setNota(0.0);
		alum.setCurso(crearCursoAlumno());
		
		
		
		return alum;
	}*/
	
	
	private static Curso crearCursoAlumno(){
		
		Curso curso=null;
		curso=new Curso();
		curso.setCodigo(1);
		curso.setNombre("Desarrollo de Aplicaciones con Tecnologías Web");
		
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
	public void deleteAlumno(int codigo) {
		
		alumDAO.delete(codigo);
		
	}
	
	@Override
	public Alumno update(Alumno alumno) {
		Alumno alum = alumDAO.update(alumno);
		
		return alum;
	}

	@Override
	public List<Alumno> getAll() {
		
		return alumDAO.getAll();
	}
	
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		
		throw new CloneNotSupportedException(); 
	}
	
	

}
