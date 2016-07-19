package com.ipartek.formacion.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ipartek.formacion.pojo.exception.CandidatoException;

/**
 * 
 * @author va00
 *
 */
public class Candidato {
	public static final int CODIGO_ALUMNO = -1;
	Integer numero;
	
	int n1;

	protected int codigo;
	protected String nombre;
	protected String apellidos;
	protected Date fNacimiento;
	protected String dni;
	protected String email;
	protected String telefono;
	
	protected Double nota;
	protected Genero genero;
	protected List<Idioma> idiomas;
	
	/**
	 * @throws CandidatoException 
	 * 
	 */
	public Candidato() {
		super();
		setCodigo(CODIGO_ALUMNO);
		setNombre("");
		setApellidos("");
		this.dni ="";
		this.fNacimiento = new Date();
		setNota(0.0);
		setEmail("");
		setTelefono("");
		setGenero(Genero.MASCULINO);
		
		List<Idioma>auxIdiomas = new ArrayList<Idioma>();
		auxIdiomas.add(Idioma.CASTELLANO);
		setIdiomas(auxIdiomas);
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public List<Idioma> getIdiomas() {
		return idiomas;
	}

	public void setIdiomas(List<Idioma> idiomas) {
		this.idiomas = idiomas;
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
		//!Util.validarDni(dni)
		if(false){
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
}
