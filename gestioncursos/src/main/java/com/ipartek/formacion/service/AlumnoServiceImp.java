package com.ipartek.formacion.service;


import java.util.List;

import com.ipartek.formacion.dbms.dao.AlumnoDAO;
import com.ipartek.formacion.dbms.dao.AlumnoDAOImp;
import com.ipartek.formacion.pojo.Alumno;
import com.ipartek.formacion.pojo.Curso;

public class AlumnoServiceImp implements AlumnoService
	{
		private static AlumnoServiceImp INSTANCE = null;
		private AlumnoDAO alumDAO;
		
		private AlumnoServiceImp()
			{
				alumDAO = AlumnoDAOImp.getInstance();
			}
		
		public static AlumnoServiceImp getInstance()
			{
				if (INSTANCE == null)
					{
						createInstance();
					}
				return INSTANCE;
			}
		
		private synchronized static void createInstance()
			{
				if (INSTANCE == null)
					{
						INSTANCE = new AlumnoServiceImp();
					}
				
			}
		
		private static Curso crearCursoAlumno()
			{
				Curso curso = null;
				curso = new Curso();
				curso.setCodigo(1);
				curso.setNombre("Desarrollo de Aplicaciones con Tecnologias Web");
				return curso;
			}
		
		@Override
		public Alumno createAlumno(Alumno alumno)
			{
				Alumno alum = alumDAO.create(alumno);
				return alum;
			}
		
		@Override
		public Alumno getById(int codigo)
			{
				Alumno alum = null;
				alum = alumDAO.getById(codigo);
				return alum;
			}
		
		@Override
		public void delete(int codigo)
			{
				alumDAO.delete(codigo);
			}
		
		@Override
		public List<Alumno> getAll()
			{
				return alumDAO.getAll();
			}
		
		@Override
		public Alumno update(Alumno alumno)
			{
				return alumDAO.update(alumno);
			}
		
		@Override
		protected Object clone() throws CloneNotSupportedException
			{
				throw new CloneNotSupportedException();
			}
		
	}
