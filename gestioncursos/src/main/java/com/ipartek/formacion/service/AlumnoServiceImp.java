package com.ipartek.formacion.service;

import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ipartek.formacion.pojo.Alumno;
import com.ipartek.formacion.pojo.Curso;
import com.ipartek.formacion.pojo.excepciones.CandidatoException;
import com.ipartek.formacion.service.excepciones.AlumnoException;
import com.ipartek.formacion.service.excepciones.CursoException;

public class AlumnoServiceImp implements AlumnoService {
	private static AlumnoServiceImp INSTANCE = null;
	private List<Alumno> alumnos;
	private static int i = 1; //para contar los objetos que añadimos
	
	
	private void init(){
		Alumno alumno;
		try {
			alumno = new Alumno();
		
		alumno.setCodigo(i);
		alumno.setNombre("Marta");
		alumno.setApellidos("Rivera del Amo");
		alumno.setDni("16087431N");
		alumno.setfNacimiento(new Date());
		alumnos.add(alumno);
		i++;
		} catch (CandidatoException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			alumno = new Alumno();
		
		alumno.setCodigo(i);
		alumno.setNombre("Imanol");
		alumno.setApellidos("Jimenez Lopez");
		alumno.setDni("45751880G");
		alumnos.add(alumno);
		i++;
		} catch (CandidatoException e) {
			System.out.println(e.getMessage());
		}
		/*
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
		}*/
		
	}
	public AlumnoServiceImp(){
		this.alumnos = new ArrayList<Alumno>();
		init();
	}
	
	public AlumnoServiceImp getInstance(){
		if(INSTANCE == null){
			createInstance();
		}
		return INSTANCE;
	}
	
	private synchronized static void createInstance() {
		if(INSTANCE == null){
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
		alumno.setCodigo(i);
		this.alumnos.add(alumno);
		i++;
		return alumno;
	}

	@Override
	public Alumno getById(int codigo) {
		
		Alumno alumno = null;
		
		try {
			alumno = this.alumnos.get(getIndex(codigo));
		} catch (AlumnoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return alumno;
	}

	@Override
	public void deleteAlumno(int codigo) {
		
		try {
			this.alumnos.remove(getIndex(codigo));
		} catch (AlumnoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private int getIndex(int codigo) throws AlumnoException{
		int index = -1;
		int i = 0;
		boolean encontrado = false;
		
		while(i < this.alumnos.size() && encontrado ==false){
			Alumno aux = this.alumnos.get(i); //alumnos[i]
			if(aux.getCodigo()==codigo){
				encontrado=true;
				index=i;
			}
				
			i++;
		}
		
		if(index==-1){
			throw new AlumnoException(AlumnoException.CODIGO_ERROR_ALUMNO_INDEX,AlumnoException.MSG_ERROR_ALUMNO_INDEX);	
		}
		
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
			this.alumnos.set(index,alumno);
		} catch (AlumnoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return alumno;
	}
	
	//Entonces, se debería impedir la clonación sobreescribiendo el método "clone" de la siguiente manera:
	@Override
	protected Object clone() throws CloneNotSupportedException {
		
		throw new CloneNotSupportedException(); 
	}
	
	

}
