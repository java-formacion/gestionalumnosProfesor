package com.ipartek.formacion.pojo;

import com.ipartek.formacion.pojo.excepciones.CandidatoException;

public class Alumno extends Candidato implements Comparable<Alumno>{
	
	private Curso curso;
	
	
	//constructor
	public Alumno() throws CandidatoException {
		super();
		this.curso=new Curso();
	}


	//getters y setters
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	
	protected String mostrarDatos(){
		return super.mostrarDatos() + ", " +this.curso.getNombre();
	}


	@Override
	public int compareTo(Alumno o) {
		int igual=-1;
		if(o.getCodigo()==this.getCodigo()){
		igual=0;	
		}else{
			if(o.getApellidos().compareToIgnoreCase(this.getApellidos())>0){
				igual=1;
			}else{
				if(o.getApellidos().compareToIgnoreCase(this.getApellidos())==0){
					igual=0;
				}else{
					igual=-1;
				}
			}
		}
		return igual;
	}
	

}
