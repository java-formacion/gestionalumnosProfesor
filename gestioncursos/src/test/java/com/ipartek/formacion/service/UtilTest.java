/**
 * 
 */
package com.ipartek.formacion.service;

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
	private List<String> listaDni = null;
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
		dni = "45751880A";
		listaDni = new ArrayList<String>();
		listaDni.add(dni);
		for (int i = 0; i < 6; i++) {
			dni = i + "234567T";
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
	 * Test method for
	 * {@link com.ipartek.formacion.service.Util#validarDni(java.lang.String)}.
	 */
	@Test
	public void testValidarDni() {
		for (String aux : listaDni) {
			Assert.assertEquals(Util.validarDni(aux), false);
		}

	}

}
