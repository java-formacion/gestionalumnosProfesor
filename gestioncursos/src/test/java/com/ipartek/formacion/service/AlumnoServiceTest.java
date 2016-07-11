package com.ipartek.formacion.service;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ipartek.formacion.pojo.Alumno;

public class AlumnoServiceTest {
	private Alumno alumno = null;
	private static AlumnoService aService = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		aService = AlumnoServiceImp.getInstance();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		aService = null;
	}

	@Before
	public void setUp() throws Exception {
		alumno = new Alumno();
		alumno.setCodigo(1);

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreateAlumno() {
		aService.createAlumno(alumno);
		Assert.assertEquals(aService.getById(alumno.getCodigo()), alumno);
	}

	@Test
	public void testGetById() {
		aService.createAlumno(alumno);
		int id = alumno.getCodigo();

	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

}
