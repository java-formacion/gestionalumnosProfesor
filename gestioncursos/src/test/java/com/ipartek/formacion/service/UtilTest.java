/**
 * 
 */
package com.ipartek.formacion.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.Assert;

/**
 * @author Curso
 *
 */
public class UtilTest {

	private List <String> listaDni;
	private String dni = null;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		dni = "16087431N";
		listaDni = new ArrayList<String>();
		listaDni.add(dni);
		for(int i=0;i<6;i++){
			dni = i + "6087431N";
			listaDni.add(dni);
		}
		
	}

	
	@After
	public void tearDown() throws Exception {
		
		dni = null;
	}

	
	@Test
	public void testValidarDni() {
		
		for(String aux: listaDni){
		Assert.assertEquals(Util.validarDni(aux), true);
		}
		
		
		/*
		if(!Util.validarDni(dni)){
			fail("Error DNI");
		}*/
		
	}

}
