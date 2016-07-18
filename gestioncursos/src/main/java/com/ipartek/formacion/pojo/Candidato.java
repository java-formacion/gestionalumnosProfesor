package com.ipartek.formacion.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ipartek.formacion.pojo.excepciones.CandidatoException;
import com.ipartek.formacion.service.Genero;
import com.ipartek.formacion.service.Util;

/**
 * 
 * @author Curso
 *
 */
public class Candidato {

  public static final int CODIGO_ALUMNO = -1;
  private int codigo;
  private String nombre;
  private String apellidos;
  protected Date fNacimiento;
  protected String dni;
  private Double nota;
  private Genero genero;
  private List<Idioma> idiomas;

  /**
   * @throws CandidatoException
   *           excepcion al crear candidato
   */
  public Candidato() {
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

  /**
   * 
   * @return idioma
   */
  public List<Idioma> getIdiomas() {
    return idiomas;
  }

  /**
   * 
   * @param idiomas
   *          lista de idiomas
   */
  public void setIdiomas(List<Idioma> idiomas) {
    this.idiomas = idiomas;
  }

  /**
   * 
   * @return Double nota
   * 
   */
  public Double getNota() {
    return nota;
  }

  /**
   * 
   * @param nota
   *          nota
   * 
   */
  public void setNota(Double nota) {
    this.nota = nota;
  }

  /**
   * 
   * @return int codigo candidato
   */
  public int getCodigo() {
    return codigo;
  }

  /**
   * 
   * @param codigo
   *          codigo
   */
  public void setCodigo(int codigo) {
    this.codigo = codigo;
  }

  /**
   * 
   * @return nombre candidato
   */
  public String getNombre() {
    return nombre;
  }

  /**
   * 
   * @param nombre
   *          nombre del candidato
   */
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  /**
   * 
   * @return apellidos del candidato
   */
  public String getApellidos() {
    return apellidos;
  }

  /**
   * 
   * @param apellidos
   *          apellidos del candidato
   */
  public void setApellidos(String apellidos) {
    this.apellidos = apellidos;
  }

  /**
   * 
   * @return fecha de nacimiento
   */
  public Date getfNacimiento() {
    return fNacimiento;
  }

  /**
   * 
   * @param fNacimiento
   *          Date fecha de nacimiento
   * @throws CandidatoException
   *           fecha no valida
   */
  public void setfNacimiento(Date fNacimiento) throws CandidatoException {
    if (fNacimiento.compareTo(new Date()) > 0) {
      throw new CandidatoException(CandidatoException.CODIGO_ERROR_FECHA_NACIMIENTO,
          CandidatoException.MSG_ERROR_FECHA_NACIMIENTO);
    } else {
      this.fNacimiento = fNacimiento;
    }
  }

  /**
   * 
   * @return DNI
   */
  public String getDni() {
    return dni;
  }

  /**
   * 
   * @param dni
   *          String DNI
   * @throws CandidatoException
   *           DNI no valido
   */
  public void setDni(String dni) throws CandidatoException {
    if (!Util.validarDni(dni)) {
      throw new CandidatoException(CandidatoException.CODIGO_ERROR_DNI,
          CandidatoException.MSG_ERROR_DNI);
    }
    this.dni = dni;
  }

  /**
   * 
   * @return genero
   */
  public Genero getGenero() {
    return genero;
  }

  /**
   * 
   * @param genero
   *          genero del candidato
   */
  public void setGenero(Genero genero) {
    this.genero = genero;
  }

  /**
   * 
   * @return datos
   */
  protected String mostrarDatos() {
    return this.apellidos + ", " + this.nombre;
  }

}
