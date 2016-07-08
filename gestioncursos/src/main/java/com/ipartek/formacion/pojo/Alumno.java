/**
 * 
 */
package com.ipartek.formacion.pojo;

import com.ipartek.formacion.pojo.exception.CandidatoException;

/**
 * @author Curso
 *
 */
public class Alumno extends Candidato implements Comparable<Alumno> {

	private Curso cursoMat;

	/**
	 * @throws CandidatoException 
	 * 
	 */
	public Alumno() throws CandidatoException {
		super();
		this.cursoMat=new Curso();
	}

	/**
	 * @return the cursoMat
	 */
	public Curso getCursoMat() {
		return cursoMat;
	}

	/**
	 * @param cursoMat the cursoMat to set
	 */
	public void setCursoMat(Curso cursoMat) {
		this.cursoMat = cursoMat;
	}
	
	public String mostrarDatos(){
		
		return super.mostrarDatos()+" "+this.getDni();
	}

	@Override
	public int compareTo(Alumno o) {
		int igual=-1;
		if(o.getCodigo()==this.getCodigo()){
			igual= 0;
		}else{
			if(o.getApellidos().compareToIgnoreCase(this.getApellidos())>0){
				igual = 1;
			}else{
				if(o.getApellidos().compareToIgnoreCase(this.getApellidos())==0)
				{
					igual = 0;
				}else{
					igual = -1;
				}
			}
		}
		
		return igual;
	}
	
	
	
	
	
	
}
