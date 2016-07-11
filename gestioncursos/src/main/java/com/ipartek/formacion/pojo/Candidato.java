package com.ipartek.formacion.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ipartek.formacion.pojo.excepciones.CandidatoException;
import com.ipartek.formacion.service.Genero;
import com.ipartek.formacion.service.Util;

public class Candidato {

	public static final int CODIGO_ALUMNO = -1;
	protected int codigo;
	protected String nombre;
	protected String apellidos;
	protected Date fNacimiento;
	protected String dni;
	protected Double nota;
	protected Genero genero;
	protected List<Idioma> idiomas;

	/**
	 * @throws CandidatoException
	 * 
	 */
	public Candidato() throws CandidatoException {
		super();
		setCodigo(CODIGO_ALUMNO);
		setNombre("");
		setApellidos("");
		this.dni = "";
		this.fNacimiento = new Date();
		setGenero(Genero.OTROS);
		setNota(0.0);
		List<Idioma> aux = new ArrayList<Idioma>();
		aux.add(Idioma.CASTELLANO);
		setIdiomas(aux);

	}

	public List<Idioma> getIdiomas() {
		return idiomas;
	}

	public void setIdiomas(List<Idioma> idiomas) {
		this.idiomas = idiomas;
	}

	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
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
		if (fNacimiento.compareTo(new Date()) > 0) {
			throw new CandidatoException(
					CandidatoException.CODIGO_ERROR_FECHA_NACIMIENTO,
					CandidatoException.MSG_ERROR_FECHA_NACIMIENTO);
		} else {
			this.fNacimiento = fNacimiento;
		}
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) throws CandidatoException {
		if (!Util.validarDni(dni)) {
			throw new CandidatoException(CandidatoException.CODIGO_ERROR_DNI,
					CandidatoException.MSG_ERROR_DNI);
		}
		this.dni = dni;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	protected String mostrarDatos() {
		return this.apellidos + ", " + this.nombre;
	}

}
