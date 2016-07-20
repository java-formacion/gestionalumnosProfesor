package com.ipartek.formacion.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ipartek.formacion.dbms.dao.AlumnoDAO;
import com.ipartek.formacion.dbms.dao.AlumnoDAOImp;
import com.ipartek.formacion.pojo.Alumno;
import com.ipartek.formacion.pojo.Curso;
import com.ipartek.formacion.pojo.exception.CandidatoException;
import com.ipartek.formacion.service.exceptions.AlumnoServiceException;

public class AlumnoServiceImp implements AlumnoService{
	/*SINGLETON:Patrón para controlar q sólo existe una instancia de esa clase, en toda la ejecución.
	 generalmente sin atributos. Ej:tener una sola conex de bbdd y que todo el mundo tire de ella.*/
	private static AlumnoServiceImp INSTANCE =null;
	private AlumnoDAO alumDAO;
	/*private List<Alumno> alumnos;
	private static int i = 1;
	private void init() {
		Alumno alumno = null;
		try {
			alumno = new Alumno();
			alumno.setCodigo(1);
			alumno.setDni("68925141y");
			alumno.setNombre("Imanol");
			alumno.setApellidos("Jimenez Lopez");
			alumnos.add(alumno);
			i++;
		} catch (CandidatoException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		


		try {
			alumno = new Alumno();
			alumno.setCodigo(2);
			alumno.setNombre("Josu");
			alumno.setDni("68925142z");
			alumno.setApellidos("Asua Gallego");
			alumnos.add(alumno);
			i++;
		} catch (CandidatoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		try {
			alumno = new Alumno();
			alumno.setCodigo(3);
			alumno.setNombre("Alvaro");
			alumno.setApellidos("Rodriguez Miguel");
			alumnos.add(alumno);
			i++;
		} catch (CandidatoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}*/
	//SINGLETON
		private AlumnoServiceImp(){
			/*this.alumnos = new ArrayList<Alumno>();
			init();*/
			alumDAO = AlumnoDAOImp.getInstance();
		}
		public static AlumnoServiceImp getInstance(){
			if(INSTANCE==null){
				createInstance();
			}
			return INSTANCE;//objeto de tipo alumnoServiceImp
		}
		private synchronized static void createInstance(){
			if(INSTANCE==null){
				INSTANCE=new AlumnoServiceImp();
			}
		}
		/*public AlumnoServiceImp(){
			this.alumnos = new ArrayList<Alumno>();
			init();
		}*/
	/*public AlumnoServiceImp(){
		this.alumnos = new ArrayList<Alumno>();
		init();
	}*/
	
	private static Curso crearCursoAlumno(){
		Curso curso = null;
		curso = new Curso();
		curso.setCodigo(1);
		curso.setNombre("Desarrollo de Aplicaciones con Tecnologias Web");
		return curso;
	}

	@Override
	public Alumno createAlumno(Alumno alumno) {
		/*alumno.setCodigo(i);
		alumnos.add(alumno);
		i++;
		return alumno;*/
		Alumno alum = alumDAO.create(alumno);
		return alum;
	}

	@Override
	public Alumno getById(int codigo) {
		/*Alumno alumno = null;
		int index;
		try {
			index = getIndex(codigo);
			alumno = alumnos.get(index);
		} catch (AlumnoServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return alumno;*/
		Alumno alumno = alumDAO.getById(codigo);

		return alumno;
	}

	@Override
	public void delete(int codigo) {
		/*int index;
		try {
			index = getIndex(codigo);
			this.alumnos.remove(index);
		} catch (AlumnoServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		alumDAO.delete(codigo);
		
	}
	/*private int getIndex(int codigo) throws AlumnoServiceException{
		int index = -1;
		int i = 0,len= this.alumnos.size();
		boolean encontrado = false;
		while(i< len && encontrado ==false){
			Alumno aux = this.alumnos.get(i);//alumnos[i];
			if(aux.getCodigo()==codigo){
				encontrado = true;
				index = i;
			}
			i++;
		}
		if(i == -1){
			throw new AlumnoServiceException(AlumnoServiceException.CODIGO_ALUMNO_NO_ECONTRADO,AlumnoServiceException.MSG_ALUMNO_NO_ENCONTRADO);
		}
		return index;
	}*/
	
	@Override
	public List<Alumno> getAll() {
		//return this.alumnos;
		List<Alumno> alumnos = alumDAO.getAll();
		return alumnos;
	}
	@Override
	public Alumno update(Alumno alumno) {
		/*int index;
		try {
			index = getIndex(alumno.getCodigo());
			this.alumnos.set(index, alumno);
		} catch (AlumnoServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return alumno*/
		return alumDAO.update(alumno);
	}
	//para no dejar clonar cosas
		@Override
		protected Object clone() throws CloneNotSupportedException {
			
			throw new CloneNotSupportedException();
		}
}










