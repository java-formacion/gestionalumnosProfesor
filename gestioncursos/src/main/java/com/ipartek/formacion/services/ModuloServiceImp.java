package com.ipartek.formacion.services;

import java.util.ArrayList;
import java.util.List;

import com.ipartek.formacion.pojo.Modulo;
import com.ipartek.formacion.pojo.excepciones.ModuloServiceException;

public final class ModuloServiceImp implements ModuloService {
	private static ModuloServiceImp INSTANCE = null;
	private List<Modulo> modulos;
	private static int numero = 1;

	private void init() {
		modulos = new ArrayList<Modulo>();
		Modulo modulo = new Modulo();
		modulo.setNombre("Modulo programación");
		createModulo(modulo);
	}

	private ModuloServiceImp() {
		init();
	}

	public static ModuloServiceImp getInstance() {
		if (INSTANCE == null) {
			createInstance();
		}
		return INSTANCE;
	}

	private synchronized static void createInstance() {
		if (INSTANCE == null)
			INSTANCE = new ModuloServiceImp();

	}

	@Override
	public Modulo createModulo(Modulo modulo) {

		modulo.setCodigo(numero);
		modulos.add(modulo);
		numero++;
		return modulo;
	}

	@Override
	public Modulo getByID(int codigo) {
		Modulo modulo = null;
		int index;
		try {
			index = getIndex(codigo);
			modulo = modulos.get(index);
		} catch (ModuloServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return modulo;
	}

	@Override
	public void delete(int codigo) {
		try {
			int index = getIndex(codigo);
			this.modulos.remove(index); // Es mejor no ahorrar lineas para que
										// sea más claro a la hora de debugear
			// si no hariamos asi this.alumnos.remove(getIndex(codigo));
		} catch (ModuloServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<Modulo> getAll() {
		return this.modulos;
	}

	@Override
	public Modulo update(Modulo modulo) {
		int index;
		try {
			index = getIndex(modulo.getCodigo());
			this.modulos.set(index, modulo);
		} catch (ModuloServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return modulo;
	}

	private int getIndex(int codigo) throws ModuloServiceException {

		int index = -1;
		int i = 0, len = this.modulos.size();
		boolean encontrado = false;
		while (i < len && encontrado == false) {

			Modulo aux = this.modulos.get(i); // Modulo [i]
			if (aux.getCodigo() == codigo) {
				encontrado = true;
				index = i;
			}
			i++;
			if (index < 0) {
				throw new ModuloServiceException(ModuloServiceException.CODIGO_ERROR_INDEX,
						ModuloServiceException.MSG_ERROR_INDEX);
			}
		}
		return index;

	}

	public static Modulo getModulo() {
		Modulo mod = null;

		mod = new Modulo();
		mod.setCodigo(numero);
		mod.setNombre("Desarrollo de Aplicaciones de Tecnologias Web");
		numero++;
		return mod;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

}
