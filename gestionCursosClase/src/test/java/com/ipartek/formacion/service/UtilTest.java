package com.ipartek.formacion.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.Assert;

public class UtilTest {
	
	private String dni = "45622967P";
	private List<String> listaDni = null; 

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		listaDni = new ArrayList<String>();
		listaDni.add(dni);
		listaDni.add("45622967Y");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testValidarDni() {

		/*if(!Util.validarDni(dni))
		{
			fail("Error DNI");
		}*/
		Assert.assertEquals(Util.validarDni(listaDni.get(0)), false);
		Assert.assertEquals(Util.validarDni(listaDni.get(1)), true);
		for(String aux : listaDni){
			
		}
	
	}

}
