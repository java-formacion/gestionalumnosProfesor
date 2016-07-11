package com.ipartek.formacion.service;

import java.util.ArrayList;
import java.util.List;

import com.ipartek.formacion.pojo.Duracion;
import com.ipartek.formacion.pojo.Modulo;
import com.ipartek.formacion.service.excepciones.ModuloServiceImpException;

public class ModuloServiceImp implements ModuloService {
	private static ModuloServiceImp INSTANCE = null;
	private List<Modulo> modulos;
	private static int i = 1;

	private void init() {
		Modulo modulo = new Modulo();
		modulo.setCodigo(1);
		modulo.setReferencia("MF0491_3");
		modulo.setNombre("Programación web en el entorno cliente");
		modulo.setDuracion(Duracion.H80);
		modulos.add(modulo);
		i++;

		modulo = new Modulo();
		modulo.setCodigo(2);
		modulo.setReferencia("MF0492_3");
		modulo.setNombre("Programación web en el entorno servidor");
		modulo.setDuracion(Duracion.H45);
		modulos.add(modulo);
		i++;

		modulo = new Modulo();
		modulo.setCodigo(3);
		modulo.setReferencia("MF0493_3");
		modulo.setNombre("Implantación de aplicaciones web en entornos internet, intranet y extranet");
		modulo.setDuracion(Duracion.H90);
		modulos.add(modulo);
		i++;
	}

	private ModuloServiceImp() {
		this.modulos = new ArrayList<Modulo>();
		init();
	}

	public static ModuloServiceImp getInstance() {
		if (INSTANCE == null) {
			createInstance();
		}
		return INSTANCE;
	}

	private synchronized static void createInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ModuloServiceImp();
		}
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		throw new CloneNotSupportedException();
	}
	@Override
	public Modulo createModulo(Modulo modulo) {
		modulo.setCodigo(i);
		modulos.add(modulo);
		i++;
		return modulo;
	}

	private int getIndex(int codigo) throws ModuloServiceImpException {
		int index = -1;
		int i = 0;
		boolean encontrado = false;
		while (i < this.modulos.size() && !encontrado) {
			Modulo aux = modulos.get(i);
			if (aux.getCodigo() == codigo) {
				index = i;
				encontrado = true;
			}
			i++;
		}
		if(index == -1){
			throw new ModuloServiceImpException(ModuloServiceImpException.CODIGO_ERROR_INDEX_INVALIDO, ModuloServiceImpException.MSG_ERROR_INDEX_INVALIDO);
		}
		return index;
	}

	@Override
	public Modulo getById(int codigo) {
		Modulo modulo = null;
		try {
			modulo = this.modulos.get(getIndex(codigo));
		} catch (ModuloServiceImpException e) {
			System.out.println(e.getMessage());
		}
		
		return modulo;
	}

	@Override
	public void delete(int codigo) {
		int index;
		try {
			index = getIndex(codigo);
			this.modulos.remove(index);
		} catch (ModuloServiceImpException e) {
			System.out.println(e.getMessage());
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
		} catch (ModuloServiceImpException e) {
			System.out.println(e.getMessage());
		}
		return modulo;
	}
}
