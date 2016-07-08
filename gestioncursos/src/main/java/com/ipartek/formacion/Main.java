package com.ipartek.formacion;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.ipartek.formacion.pojo.Alumno;
import com.ipartek.formacion.pojo.Candidato;
import com.ipartek.formacion.pojo.Curso;
import com.ipartek.formacion.pojo.Modulo;
import com.ipartek.formacion.pojo.excepciones.AlumnoServiceException;
import com.ipartek.formacion.services.AlumnoService;
import com.ipartek.formacion.services.AlumnoServiceImp;
import com.ipartek.formacion.services.CursoService;
import com.ipartek.formacion.services.CursoServiceImp;
import com.ipartek.formacion.services.ModuloService;
import com.ipartek.formacion.services.ModuloServiceImp;

public class Main {

	public static void main(String[] args) {
		
		AlumnoService aService = AlumnoServiceImp.getInstance();
		CursoService cService = CursoServiceImp.getInstance();
		Curso curso = new Curso();
		
		curso.setNombre("Desarrollo de aplicaciones con tecnologías web Java / ASP.NET");
		
		cService.create(curso);
		
		System.out.println(curso.getNombre()+" "+curso.getCodigo());
			
	}
	public static String saludarAlumno(Candidato candidato){
		String saludo = ""; //&
		saludo += "Hola "+ candidato.getNombre(); 
		return saludo;
	}
	private static void hasAprobado(Candidato candidato){
		String mensaje = "";
		if (candidato.getNota() > 5 && candidato.getNota()>=5){
			mensaje = "Has aprobado";
		}else{
			mensaje = "Has suspendido";
		}
		
		System.out.println(mensaje);
	}
	private static void evaluar(Candidato alumno){
		int nota = (int) Math.floor(alumno.getNota());
		
		switch(nota){
		case 5:{
			String mensaje = "Es un candidato normal";
			System.out.println(mensaje);
			break;
		}
		case 6:{
			String mensaje = "Es un candidato bueno";
			System.out.println(mensaje);
			break;
		}
		case 7:{
			String mensaje = "Es un candidato muy bueno";
			System.out.println(mensaje);
			break;
		}
		case 8:{
			String mensaje = "Es un candidato muy avanzado";
			System.out.println(mensaje);
			break;
		}
		case 9://if (nota == 9 || nota ==10)
		case 10:{
			String mensaje = "Es un candidato perfecto";
			System.out.println(mensaje);
			break;
		}
		default:{
			String mensaje = "Es un candidato no apto";
			System.out.println(mensaje);
			break;
		}
		}
	}
	public static void objetosIguales( ){
	
	}
	private void gestionarMap(){
		Curso curso = new Curso();
		ModuloService mService = ModuloServiceImp.getInstance();
		Modulo modulo = mService.getByID(0);
		Map<Integer,Modulo>modulos = curso.getModulos();
		modulos.put(modulo.getCodigo(), modulo);
		modulo = modulos.get(modulo.getCodigo());
		
		for(Modulo aux: modulos.values()){
			System.out.println(aux.getNombre());
		}
		for(Entry<Integer, Modulo> aux: modulos.entrySet()){
			System.out.println(aux.getKey());
			System.out.println(aux.getValue().getNombre());
		}
		
		
	}
	private void matricularAlumnos(Alumno alumno) throws AlumnoServiceException{
		CursoService cService = CursoServiceImp.getInstance();
		AlumnoService aService = AlumnoServiceImp.getInstance();
		Alumno alum = aService.getById(1);
		alum.setCurso(cService.getById(1));
		cService.darDeAlta(alum);
	}
	private void eliminarAlumnos(Alumno alumno) throws AlumnoServiceException{
		CursoService cService = CursoServiceImp.getInstance();
		AlumnoService aService = AlumnoServiceImp.getInstance();
		Alumno alum = aService.getById(1);
		alum.setCurso(cService.getById(1));
		cService.darDeBaja(alumno);
	}
}
