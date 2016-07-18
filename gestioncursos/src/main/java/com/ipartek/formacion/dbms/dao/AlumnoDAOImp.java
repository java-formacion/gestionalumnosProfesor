package com.ipartek.formacion.dbms.dao;

import com.ipartek.formacion.pojo.Alumno;

public class AlumnoDAOImp implements AlumnoDAO {

	@Override
	public Alumno getById(int codigo) {
		
		// TODO es singleton: va a tener el atributo de conexion
		String sql = "SELECT codAlumno, a.nombre, apellidos, dni-nie, fNacimiento, email, telefono, codGenero, g.nombre as 'nGenero"
				+ "		FROM alumno a "
				+ "		INNERJOIN genero g ON g.codGenero=a.codGenero"
				+ "		WHERE codAlumno = " + codigo;
		return null;
	}

	@Override
	public Alumno update(Alumno alumno) {
		
		return null;
	}

	@Override
	public Alumno insert(Alumno alumno) {
		
		return null;
	}

	@Override
	public void delete(int codigo) {

	}
}
