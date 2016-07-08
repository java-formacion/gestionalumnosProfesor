/**
 * 
 */
package com.ipartek.formacion.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ipartek.formacion.pojo.exception.CandidatoException;
import com.ipartek.formacion.service.Util;

/**
 * 
 * @author Adrian York
 *
 */
public class Candidato {
	/**
	 * Codigo identificador del alumno
	 */
	//private int codigo;
	public static final int CODIGO_ALUMNO=-1;
	protected int codigo;
	/**
	 * Nombre del alumno
	 */
	protected String nombre;
	/**
	 * Apellido del alumno
	 */
	protected String apellidos;
	/**
	 * Fecha de nacimiento del alumno
	 */
	protected Date fNacimiento;
	/**
	 * DNI del alumno
	 */
	protected String dni;
	
	protected double nota;
	/**
	 * @return the nota
	 */
	protected Genero genero;
	protected List<Idioma> idiomas;
	
	public double getNota() {
		return nota;
	}
	/**
	 * @param nota the nota to set
	 */
	public void setNota(double nota) {
		this.nota = nota;
	}
	/**
	 * @throws CandidatoException 
	 * 
	 */
	public Candidato() throws CandidatoException {
		super();
		setCodigo(CODIGO_ALUMNO);
		setNombre("");
		setApellidos("");
		setfNacimiento(new Date());
		setDni("");
		setNota(0.0);
		setGenero(Genero.MASCULINO);
		List<Idioma>auxIdiomas = new ArrayList<Idioma>();
		auxIdiomas.add(Idioma.CASTELLANO);
		setIdiomas(auxIdiomas);
	}
	/**
	 * @return the codigo
	 */
	
	public List<Idioma> getIdiomas() {
		return idiomas;
	}


	public void setIdiomas(List<Idioma> idiomas) {
		this.idiomas = idiomas;
	}


	public Genero getGenero() {
		return genero;
	}


	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	public int getCodigo() {
		return codigo;
	}
	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the apellidos
	 */
	public String getApellidos() {
		return apellidos;
	}
	/**
	 * @param apellidos the apellidos to set
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	/**
	 * @return the fNacimiento
	 */
	public Date getfNacimiento() {
		return fNacimiento;
	}
	/**
	 * @param fNacimiento the fNacimiento to set
	 * @throws CandidatoException 
	 */
	public void setfNacimiento(Date fNacimiento) throws CandidatoException {
		if (fNacimiento.compareTo(new Date())>0) {
			throw new CandidatoException(CandidatoException.CODIGO_ERROR_FECHA_NACIMIENTO,CandidatoException.MSG_ERROR_FECHA_NACIMIENTO);
		}else {
			this.fNacimiento = fNacimiento;
		}
		
	}
	/**
	 * @return the dni
	 */
	public String getDni() {
		return dni;
	}
	/**
	 * @param dni the dni to set
	 */
	public void setDni(String dni) throws CandidatoException{
		//Util.validarDni(dni)==false
		if (false) {
			throw new CandidatoException(CandidatoException.CODIGO_ERROR_DNI_INCORRECTO, CandidatoException.MSG_ERROR_DNI_INCORRECTO);		
		}else{
			this.dni = dni;
		}
		
	}
	
	protected String mostrarDatos(){
		return this.getApellidos()+", "+this.getNombre();
	}
	
	
}
