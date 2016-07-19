package com.ipartek.formacion.service;

import java.util.List;

import com.ipartek.formacion.dbms.dao.AlumnoDAO;
import com.ipartek.formacion.dbms.dao.AlumnoDAOImp;
import com.ipartek.formacion.pojo.Alumno;

public class AlumnoServiceImp implements AlumnoService{
	//private List<Alumno> alumnos;
	//private static int i = 1;
	private static AlumnoServiceImp INSTANCE = null;
	private AlumnoDAO alumDAO;
	
	public AlumnoServiceImp(){
		alumDAO = AlumnoDAOImp.getInstance();
	}
	
	public AlumnoServiceImp getInstance(){
		if(INSTANCE == null){
			createInstance();
		}
		
		return INSTANCE;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	private synchronized static void createInstance() {
		if(INSTANCE == null){
			INSTANCE = new AlumnoServiceImp();
		}
	}

	/*
	private static Curso crearCursoAlumno(){
		Curso curso = null;
		curso = new Curso();
		curso.setCodigo(1);
		curso.setNombre("HTML + CSS");
		return curso;
	}
	*/

	@Override
	public Alumno createAlumno(Alumno alumno) {
		Alumno alum =  alumDAO.insert(alumno);
		return alum;
	}

	@Override
	public Alumno getById(int codigo) {
		Alumno alumno = null;
		alumno = alumDAO.getById(codigo);
		return alumno;
	}

	@Override
	public void delete(int codigo) {
		alumDAO.delete(codigo);
	}
	
	/*
	private int getIndex(int codigo) throws AlumnoServiceException{
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
	}
	*/
	
	@Override
	public List<Alumno> getAll() {
		return alumDAO.getAll();
	}
	
	@Override
	public Alumno update(Alumno alumno) {
		return alumDAO.update(alumno);
	}
}