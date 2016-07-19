package com.ipartek.formacion.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ipartek.formacion.pojo.excepciones.CandidatoException;
import com.ipartek.formacion.service.Genero;
import com.ipartek.formacion.service.Idiomas;
import com.ipartek.formacion.service.Util;

public class Candidato {

	//para heredar los atributos desde otra clase, pueden ser private o protected. La diferencia es como acceder a ellos. si son private se accede con los getters y setters,si son protected con this.
	public static final int CODIGO_ALUMNO = -1;
	protected int codigo;
	
	protected String nombre;
	protected String apellidos;
	protected Date fNacimiento;
	protected String dni;
	protected String email;
	protected String telefono;
	protected Double nota;
	protected Genero genero;
	protected List <Idiomas> idiomas;
	/**
	 * @throws CandidatoException 
	 * 
	 */
	public Candidato(){
		super();
		this.setCodigo(-1);
		this.setNombre("");
		this.setApellidos("");
		this.dni="";
		this.email="";
		this.telefono="";
		this.fNacimiento=new Date();
		this.setNota(0.0);
		this.setGenero(Genero.MASCULINO);
		List<Idiomas>auxIdiomas = new ArrayList<Idiomas>();
		auxIdiomas.add(Idiomas.CASTELLANO);
		this.setIdiomas(auxIdiomas);
		
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
	
	public void setfNacimiento(Date fNacimiento) {
		this.fNacimiento = fNacimiento;
	}
	/*
	public void setfNacimiento(Date fNacimiento) throws CandidatoException {
		
		if(fNacimiento.compareTo(new Date())>0){
			throw new CandidatoException(CandidatoException.CODIGO_ERROR_FECHA_NACIMIENTO,CandidatoException.MSG_ERROR_FECHA_NACIMIENTO);
			
			
		}else{
	
		this.fNacimiento = fNacimiento;
		}
	}*/
	public String getDni() {
		
		return dni;
	}
	public void setDni(String dni){
		
		if(!Util.validarDni(dni)){
			
			//throw new CandidatoException(CandidatoException.CODIGO_ERROR_DNI,CandidatoException.MSG_ERROR_DNI);
			
		}
		
		this.dni = dni;
	}
	public Double getNota() {
		return nota;
	}
	public void setNota(Double nota) {
		this.nota = nota;
	}
	
	
	public Genero getGenero() {
		return genero;
	}
	public void setGenero(Genero genero) {
		this.genero = genero;
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
	public List<Idiomas> getIdiomas() {
		return idiomas;
	}
	public void setIdiomas(List<Idiomas> idiomas) {
		this.idiomas = idiomas;
	}
	protected String mostrarDatos(){
		return this.apellidos + ", " + this.nombre; //no se hace un system.out.println, se devuelven los datos
	}
	
	
	
	
	
	
}
