package com.ipartek.formacion.control;

import java.util.GregorianCalendar;

import com.ipartek.formacion.services.Horas;
import com.ipartek.formacion.services.TipoCurso;

public class Constantes {
	private Constantes() {}
	
	
	public final static String ATT_LISTADO_CURSOS= "listado_cursos";
	public final static String ATT_CURSO = "curso";
	
	public final static String ATT_LISTADO_ALUMNOS ="listado_alumnos";
	public final static String ATT_ALUMNO ="alumno";
	
	public final static String ATT_LISTADO_MODULOS = "listado_modulos";
	public final static String ATT_MODULO = "modulo";
	
	public final static String ATT_MENSAJE = "mensaje";
	
	public final static String ATT_LISTA_DURACION_MODULO="listado_duracion";
	public final static String ATT_LISTA_TIPO_CURSO="listado_tipocurso";
	
	public final static String PAR_CODIGO= "codigo";
	public final static String PAR_NOMBRE ="nombre";
	
	public final static String PAR_LISTADO_ALUMNOS="codigo alumnos";
	public final static String PAR_LISTADO_MODULOS="codigo modulos";
	
	public final static String PAR_DNI = "dni";
	public final static String PAR_APELLIDOS = "apellidos";
	public final static String PAR_OPERACION ="operacion";
	public final static String PAR_MES ="mes";
	public final static String PAR_DIA ="dia";
	public final static String PAR_ANYO ="anyo";
	public final static String PAR_GENERO="genero";
	public final static String PAR_IDIOMA ="idioma";
	public final static String PAR_CURSO ="curso";
	public final static String PAR_MODULO ="modulo";
	public final static String PAR_ALUMNO ="alumno";
	public final static String PAR_DURACION ="duracion";
	public final static String PAR_TIPOCURSO ="tipo_curso";
	public final static String PAR_REFERENCIA="referencia";
	public final static String PAR_USER="user";
	public final static String PAR_PASSWORD="password";
	
	public final static String SERVLET_CURSOS ="curso.do";
	public final static String SERVLET_ALUMNOS ="alumno.do";
	public final static String SERVLET_MODULOS ="modulo.do";
	public final static String SERVLET_LOGIN ="login.do";
	public final static String SERVLET_LOGOUT ="logout.do";
	
	
	public final static Horas[] LISTA_DURACION = {Horas.QUINCE,Horas.VEINTE,Horas.CUARENTAYCINCO,Horas.OCHENTA,Horas.NOVENTA};
	public final static TipoCurso[] LISTA_TIPOCURSO = {TipoCurso.LANBIDE,TipoCurso.HOBETUZ,TipoCurso.TRIPARTITO};

	public final static String JSP_LISTADO_CURSOS ="/cursos/listado.jsp";
	public final static String JSP_CURSO ="/cursos/curso.jsp";
	
	public final static String JSP_LISTADO_ALUMNOS ="/alumnos/listadoalumno.jsp";
	public final static String JSP_ALUMNO ="/alumnos/alumno.jsp";
	
	public final static String JSP_LISTADO_MODULOS ="/modulos/listadomodulo.jsp";
	public final static String JSP_MODULO ="/modulos/modulo.jsp";
	
	public final static int OP_CREATE = 0;
	public final static int OP_READ = 1;
	public final static int OP_UPDATE = 2;
	public final static int OP_DELETE = 3;
}