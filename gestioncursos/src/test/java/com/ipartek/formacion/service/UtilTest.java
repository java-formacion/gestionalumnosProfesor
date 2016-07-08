package com.ipartek.formacion.service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ipartek.formacion.services.Util;

import junit.framework.Assert;

public class UtilTest {
	private String dni=null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		dni="45628477L";
		
	}

	@After
	public void tearDown() throws Exception {
		
		dni=null;
		
	}

	@Test
	public void testValidarDni() {
		Assert.assertEquals(!Util.validarDni(dni), false);
	}

}
