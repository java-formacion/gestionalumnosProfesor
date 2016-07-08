/**
 * 
 */
package com.ipartek.formacion.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ipartek.formacion.pojo.Candidato;
import com.ipartek.formacion.pojo.exception.CandidatoException;

/**
 * @author va00
 *
 */
public class UtilTest {

	private List<String> listaDni;
	private String dni = null;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	 dni = "78889321x";
	 listaDni = new ArrayList<String>();
	 listaDni.add(dni);
	 for(int i = 0; i < 6; i++){
		 dni = i+"8889321x";
		 listaDni.add(dni);
	 }
	 
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		dni = null;
	}

	/**
	 * Test method for {@link com.ipartek.formacion.service.Util#validarDni(java.lang.String)}.
	 * @throws CandidatoException 
	 */
	
	@Test
	public void testValidarDni() throws CandidatoException {
		for(String aux: listaDni){
			Assert.assertEquals("",Util.validarDni(aux), false);
		}
		Candidato candidato = null;
		candidato = new Candidato();
		
	}

}
