package com.ipartek.formacion.dbms.dao;

import java.util.List;

import com.ipartek.formacion.pojo.Modulo;

public interface ModuloDAO {
	
	public Modulo getById(int codigo);
	
	public Modulo update(Modulo modulo);
	
	public Modulo create(Modulo modulo);
	
	public void delete(int codigo);
	
	public List<Modulo> getAll();

}
