package com.ipartek.formacion;

import com.ipartek.formacion.pojo.Candidato;
import com.ipartek.formacion.pojo.Curso;
import com.ipartek.formacion.services.CursoService;
import com.ipartek.formacion.services.CursoServiceImp;

public class Main {

	public static void main(String[] args) {

		// AlumnoService aService = AlumnoServiceImp.getInstance();
		CursoService cService = CursoServiceImp.getInstance();
		Curso curso = new Curso();

		curso.setNombre("Desarrollo de aplicaciones con tecnologías web Java / ASP.NET");

		cService.create(curso);

	}

	public static String saludarAlumno(Candidato candidato) {
		String saludo = ""; // &
		saludo += "Hola " + candidato.getNombre();
		return saludo;
	}

	public static void objetosIguales() {

	}
}
