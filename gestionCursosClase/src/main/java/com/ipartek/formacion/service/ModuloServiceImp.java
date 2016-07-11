package com.ipartek.formacion.service;

import java.util.ArrayList;


import java.util.List;

import com.ipartek.formacion.pojo.Curso;
import com.ipartek.formacion.pojo.Modulo;
import com.ipartek.formacion.service.exceptions.ModuloServiceException;

public class ModuloServiceImp implements ModuloService{

	private List<Modulo>modulos;
	private static int i  = 1;
	private void init(){
		Modulo modulo = new Modulo();
		modulo.setNombre("Desarrollo Aplicaciones Web");
		create(modulo);
	}
	public ModuloServiceImp(){
		this.modulos = new ArrayList<Modulo>();
		init();
	}
	@Override
	public Modulo create(Modulo modulo) {
		//le asignamos el codigo al modulo
		modulo.setCodigo(ModuloServiceImp.i);
		//lo añadimos a la "BBDD"
		modulos.add(modulo);
		i++;
		return modulo;
	}
	

	@Override
	public Modulo getById(int codigo) {
		Modulo modulo = null;
		try {
			modulo = this.modulos.get(getIndex(codigo));
		} catch (ModuloServiceException e) {
			modulo = new Modulo();
		}
		return modulo;
	}
	private int getIndex(int codigo) throws ModuloServiceException{
		int index = -1;
		int i= 0,len = modulos.size();
		boolean encontrado = false;
		while(i < len && encontrado == false){
			if(this.modulos.get(i).getCodigo()==codigo){
				encontrado = true;
				index = i;
			}
			i++;
		}	
		if(i==-1){
			throw new  ModuloServiceException(ModuloServiceException.CODIGO_MODULO_NO_ECONTRADO,ModuloServiceException.MSG_MODULO_NO_ENCONTRADO);
		}
		return index;
	}
	@Override
	public void delete(int codigo) {
		//DELETE FROM modulo
		//WHERE id = codigo;
		try {
			modulos.remove(getIndex(codigo));
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
		try {
			this.modulos.add(getIndex(modulo.getCodigo()), modulo);
		} catch (ModuloServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return modulo;
	}

}
