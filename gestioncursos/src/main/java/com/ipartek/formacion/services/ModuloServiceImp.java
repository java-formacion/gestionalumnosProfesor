package com.ipartek.formacion.services;

import java.util.List;

import com.ipartek.formacion.dbms.dao.ModuloDAO;
import com.ipartek.formacion.dbms.dao.ModuloDAOImp;
import com.ipartek.formacion.pojo.Modulo;

public final class ModuloServiceImp implements ModuloService {
	private static ModuloServiceImp INSTANCE = null;

	private ModuloDAO modDAO;

	private ModuloServiceImp() {

		modDAO = ModuloDAOImp.getInstance();

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

		Modulo mod = modDAO.insert(modulo);
		return mod;
	}

	@Override
	public Modulo getByID(int codigo) {
		Modulo mod = modDAO.getByID(codigo);
		return mod;
	}

	@Override
	public void delete(int codigo) {
		modDAO.delete(codigo);

	}

	@Override
	public List<Modulo> getAll() {
		return modDAO.getAll();
	}

	@Override
	public Modulo update(Modulo modulo) {
		Modulo mod = modDAO.update(modulo);
		return mod;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

}
