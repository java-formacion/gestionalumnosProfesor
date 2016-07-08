/**
 * 
 */
package com.ipartek.formacion.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ipartek.formacion.pojo.Alumno;
import com.ipartek.formacion.pojo.Curso;
import com.ipartek.formacion.pojo.Modulo;
import com.ipartek.formacion.pojo.exception.AlumnoException;
import com.ipartek.formacion.pojo.exception.CursoException;

/**
 * @author Curso
 *
 */
public class CursoServiceImp implements CursoService {

	private static CursoServiceImp INSTANCE=null;
	private List<Curso> cursos;
	private static int aCounter=0;
	
	private CursoServiceImp(){
		this.cursos=new ArrayList<Curso>();
		init();
	}
	
	public static CursoServiceImp getInstance(){
		if (INSTANCE==null) {
			INSTANCE=new CursoServiceImp();
		}
		return INSTANCE;
	}
	
	private synchronized static void createInstance(){
		if (INSTANCE==null) {
			INSTANCE=new CursoServiceImp();
		}
	}
	
	
	public void init(){
		Curso c1=new Curso();
		Curso c2=new Curso();
		Curso c3=new Curso();
		Curso c4=new Curso();
		
		
		c1.setNombre("Curso qwer");
		
		c2.setNombre("Curso asdf");
		
		c3.setNombre("Curso zxcv");
		
		c4.setNombre("Curso uiop");
		
		
		createCurso(c1);
		createCurso(c2);
		createCurso(c3);
		createCurso(c4);
		
	}

	@Override
	public Curso createCurso(Curso curso) {
		curso.setCodigo(aCounter);
		aCounter++;
		this.cursos.add(curso);
		
		return curso;
	}

	@Override
	public void deleteCurso(int codigo) {
		int index=this.getIndex(codigo);
		this.cursos.remove(index);
		
	}

	@Override
	public Curso updateCurso(Curso curso) {
		int index=this.getIndex(curso.getCodigo());
		this.cursos.set(index, curso);
		return curso;
	}

	@Override
	public Curso getById(int codigo) throws CursoException {
		Curso aux=null;
		int index=this.getIndex(codigo);
		if (index <0) {
			throw new CursoException(CursoException.CODIGO_ERROR_INDEX_CURSO, CursoException.MSG_ERROR_INDEX_CURSO);
		}
		aux=this.cursos.get(index);
		
		return aux;
	}

	@Override
	public List<Curso> getAll() {
		
		return this.cursos;
	}
	
	private int getIndex(int codigo) {
		int index = -1;
		int i = 0;
		int len = this.cursos.size();
		boolean found = false;
		while (i < len && found == false) {
			Curso aux = this.cursos.get(i);

			if (aux.getCodigo() == codigo) {
				found = true;
				index = i;
			}
			i++;
		}

		return index;
	}
	
	@Override
	public void darDeAlta(Alumno alumno, int codCurso) throws CursoException {
		Curso aux= null;
		aux=this.getById(codCurso);
		
		Map <String,Alumno>alumnado=null;
		alumnado=aux.getAlumnos();
		//alumnado=curso.getAlumnos();
		alumno.setCursoMat(aux);
		alumnado.put(alumno.getDni(), alumno);
		aux.setAlumnos(alumnado);
		this.updateCurso(aux);
		//this.updateCurso(curso);
		
		
	}

	@Override
	public void darDeBaja(String dni, int codCurso ) throws CursoException {
		Curso aux= null;
		aux=this.getById(codCurso);
		
		Map <String,Alumno>alumnado=null;
		alumnado=aux.getAlumnos();
		//alumnado=curso.getAlumnos();
		
		alumnado.remove(dni);
		aux.setAlumnos(alumnado);
		this.updateCurso(aux);
		//this.updateCurso(curso);
		
	}
	

}
