package com.ipartek.formacion.service;

import java.util.ArrayList;
import java.util.List;

import com.ipartek.formacion.pojo.Alumno;
import com.ipartek.formacion.pojo.Curso;
import com.ipartek.formacion.pojo.exception.AlumnoException;
import com.ipartek.formacion.pojo.exception.CandidatoException;
import com.ipartek.formacion.service.exceptions.AlumnoServiceException;

public class AlumnoServiceImp implements AlumnoService {

	private static AlumnoServiceImp INSTANCE=null;
	
	private List<Alumno> alumnos;
	private static int aCounter = 1;
	

	private void init()  {
		
		Alumno alumno1 = null;
		try {
			alumno1 = new Alumno();
			alumno1.setCodigo(1);
			alumno1.setNombre("Angus");
			alumno1.setApellidos("Young");
			alumno1.setDni(Util.generaDni());
			createAlumno(alumno1);
			
		} catch (CandidatoException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		Alumno alumno2 = null;
		try {
			alumno2 = new Alumno();
			alumno2.setCodigo(2);
			alumno2.setNombre("Zakk");
			alumno2.setApellidos("Wylde");
			alumno2.setDni(Util.generaDni());
			createAlumno(alumno2);
			
		} catch (CandidatoException e) {
			// TODO: handle exception
		}
		
		Alumno alumno3 = null;
		
		try {
			alumno3 = new Alumno();
			alumno3.setCodigo(3);
			alumno3.setNombre("Robert");
			alumno3.setApellidos("Johnson");
			alumno3.setDni(Util.generaDni());
			createAlumno(alumno3);
			
		} catch (CandidatoException e) {
			// TODO: handle exception
		}
		
		Alumno alumno4 = null;

		try {
			
			alumno4 = new Alumno();
			alumno4.setCodigo(4);
			alumno4.setNombre("Keith");
			alumno4.setApellidos("Richards");
			alumno4.setDni(Util.generaDni());
			createAlumno(alumno4);
		} catch (CandidatoException e) {
			// TODO: handle exception
		}	
		

	}

	private AlumnoServiceImp(){
		this.alumnos = new ArrayList<Alumno>();
		init();
	}
	public static AlumnoServiceImp getInstance(){
		if (INSTANCE==null) { 
			INSTANCE=new AlumnoServiceImp();
		}
		return INSTANCE;
	}
	
	private synchronized static void createInstance(){
		if (INSTANCE==null) {
			INSTANCE=new AlumnoServiceImp();
		}
	}

//	public static Alumno getAlumno(){
//		Alumno a = null;
//		a = new Alumno();
//		a.setNombre("Adrian");
//		a.setApellidos("York Celaya");
//		a.setDni("78993157W");
//		a.setNota(9.3);
//		a.setCursoMat(crearCursoAlumno());
//
//		return a;
//	}

	private static Curso crearCursoAlumno() {
		Curso c = null;
		c = new Curso();
		c.setCodigo(1);
		c.setNombre("Desarrollo de Aplicaciones con tecnologias web");
		return c;
	}

	@Override
	public Alumno createAlumno(Alumno alumno) {
		alumno.setCodigo(aCounter);
		this.alumnos.add(alumno);
		aCounter++;
		return alumno;

	}

	@Override
	public Alumno getById(int codigo){
		Alumno aux = null;	
		int index;
					
		try {
			index = getIndex(codigo);
			aux = this.alumnos.get(index);
		} catch (AlumnoServiceException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		
		return aux;
	}

	@Override
	public void deleteAlumno(int codigo) {
		int index;
		try {
			index=getIndex(codigo);
			this.alumnos.remove(index);
		} catch (AlumnoServiceException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}

	@Override
	public List<Alumno> getAll() {
		// TODO Auto-generated method stub
		return this.alumnos;
	}

	private int getIndex(int codigo) throws AlumnoServiceException {
		int index = -1;
		int i = 0;
		int len = this.alumnos.size();
		boolean found = false;
		while (i < len && found == false) {
			Alumno aux = this.alumnos.get(i);

			if (aux.getCodigo() == codigo) {
				found = true;
				index = i;
			}
			i++;
		}
		if(i == -1){
			throw new AlumnoServiceException(AlumnoServiceException.CODIGO_ALUMNO_NO_ECONTRADO,AlumnoServiceException.MSG_ALUMNO_NO_ENCONTRADO);
		}
		return index;
	}

	@Override
	public Alumno updateAlumno(Alumno alumno) {
		int index ;
		
		try {
			index = getIndex(alumno.getCodigo());
			this.alumnos.set(index, alumno);
		} catch (AlumnoServiceException e) {
			// TODO: handle exception
			e.printStackTrace();
		}


		return alumno;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	
	

}
