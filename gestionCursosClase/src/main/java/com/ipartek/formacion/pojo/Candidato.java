package com.ipartek.formacion.pojo;

import java.util.Date;

import com.ipartek.formacion.pojo.exception.CandidatoException;
import com.ipartek.formacion.service.Util;

/**
 * 
 * @author va00
 *
 */
public class Candidato {
	public static final int CODIGO_ALUMNO = -1;
	Integer numero;
	
	int n1;
	/**
	 * 
	 */
	protected int codigo;
	/**
	 * 
	 */
	protected String nombre;
	protected String apellidos;
	protected Date fNacimiento;
	protected String dni;
	protected Double nota;
	/**
	 * @throws CandidatoException 
	 * 
	 */
	public Candidato() throws CandidatoException {
		super();
		setCodigo(CODIGO_ALUMNO);
		setNombre("");
		setApellidos("");
		this.dni ="";
		setfNacimiento(new Date());
		setNota(0.0);

	}
	

	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public Date getfNacimiento() {
		return fNacimiento;
	}
	public void setfNacimiento(Date fNacimiento) throws CandidatoException {
		if(fNacimiento.compareTo(new Date())>0){
			throw new CandidatoException(CandidatoException.CODIGO_ERROR_FECHA_NACIMIENTO,CandidatoException.MSG_ERROR_FECHA_NACIMIENTO);
		}else{
			this.fNacimiento = fNacimiento;
		}
		
		
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) throws CandidatoException {
		if(!Util.validarDni(dni)){
		 throw new CandidatoException(CandidatoException.CODIGO_ERROR_DNI_INCORRECTO, CandidatoException.MSG_ERROR_DNI_INCORRECTO);	
		}else{
			this.dni = dni;
		}
		
	}


	public Double getNota() {
		return nota;
	}


	public void setNota(Double nota) {
		this.nota = nota;
	}

	protected String mostrarDatos(){
		return this.apellidos +", "+ this.nombre;
	}
	
	
	
	
	
	
	
}
