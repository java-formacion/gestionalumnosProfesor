package com.ipartek.formacion.service;

import java.util.List;

import com.ipartek.formacion.pojo.Modulo;

public interface ModuloService {

	public Modulo createModulo(Modulo modulo);

	public Modulo getById(int codigo);

	public void delete(int codigo);

	public List<Modulo> getAll();

	public Modulo update(Modulo modulo);
}
