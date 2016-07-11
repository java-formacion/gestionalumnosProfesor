package com.ipartek.formacion.service;

import java.util.ArrayList;
import java.util.List;

import com.ipartek.formacion.pojo.Alumno;
import com.ipartek.formacion.pojo.Curso;
import com.ipartek.formacion.service.excepciones.CursoException;

public class CursoServiceImp implements CursoService{
	private static CursoServiceImp INSTANCE = null;
	private List<Curso> cursos;
	private static int i = 1; //para contar los objetos que añadimos
	
	private void init(){
		Curso curso = new Curso();
		curso.setCodigo(i);
		curso.setNombre("Desarrollo de Java");
		cursos.add(curso);
		i++;
		
		curso=new Curso();
		curso.setCodigo(i);
		curso.setNombre("Desarrollo de C");
		cursos.add(curso);
		i++;
		
	}
	
	public CursoServiceImp(){
		this.cursos = new ArrayList<Curso>();
		init();
	}
	
	public CursoServiceImp getInstance(){
		if(INSTANCE == null){
			createInstance();
		}
		return INSTANCE;
	}
	
	private synchronized static void createInstance() {
		if(INSTANCE == null){
			INSTANCE = new CursoServiceImp();
		}
	
	}

	@Override
	public Curso createCurso(Curso curso) {
		curso.setCodigo(i);
		this.cursos.add(curso);
		i++;
		return curso;
	}

	@Override
	public Curso getById(int codigo) {
		Curso curso = null;
		
		try {
			curso = this.cursos.get(getIndex(codigo));
		} catch (CursoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return curso;
	}

	@Override
	public void deleteCurso(int codigo) {
		try {
			this.cursos.remove(getIndex(codigo));
		} catch (CursoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private int getIndex(int codigo) throws CursoException{
		int index = -1;
		int i = 0;
		boolean encontrado = false;
		
		while(i < this.cursos.size() && encontrado ==false){
			Curso aux = this.cursos.get(i); //cursos[i]
			if(aux.getCodigo()==codigo){
				encontrado=true;
				index=i;
			}
				
			
			i++;
		}
		if(index==-1){
			throw new CursoException(CursoException.CODIGO_ERROR_CURSO_INDEX,CursoException.MSG_ERROR_CURSO_INDEX);	
		}
		
			return index;
		
	}

	@Override
	public List<Curso> getAll() {
		
		return this.cursos;
	}

	@Override
	public Curso update(Curso curso) {
		int index;
		try {
			index = getIndex(curso.getCodigo());
			this.cursos.set(index,curso);
			
		} catch (CursoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return curso;
	}

	@Override
	public void darDeAlta(int codigo, Alumno alumno) {
		
		//1-Obtener el curso
		Curso aux = getById(codigo); //en aux me guardo el objeto que me devuelve el método getbById de esta clase (curso)
		//2-Obtener el Map//3-Meter el alumno en el Map
		aux.getAlumnos().put(alumno.getDni(), alumno); //con aux.getAlumnos() accedo a los alumnos de ese curso
		//4-Actualizar
		update(aux); //actualizo ese curso
		
		
	}

	
	@Override
	public void darDeBaja(int codigo, Alumno alumno) {
		
		Curso aux = getById(codigo); //en aux me guardo el objeto que me devuelve getbById
		aux.getAlumnos().remove(alumno.getDni(), alumno); 
		update(aux);  //actualizo ese curso
		
	}

	
	
	
	//Entonces, se debería impedir la clonación sobreescribiendo el método "clone" de la siguiente manera:
	@Override
	protected Object clone() throws CloneNotSupportedException {
		
		throw new CloneNotSupportedException(); 
	}
	


}
