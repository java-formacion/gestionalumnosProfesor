package com.ipartek.formacion.service;

import java.util.ArrayList;
import java.util.List;

import com.ipartek.formacion.dbms.dao.CursoDAO;
import com.ipartek.formacion.dbms.dao.CursoDAOImp;
import com.ipartek.formacion.dbms.dao.ModuloDAO;
import com.ipartek.formacion.dbms.dao.ModuloDAOImp;
import com.ipartek.formacion.pojo.Curso;
import com.ipartek.formacion.pojo.Horas;
import com.ipartek.formacion.pojo.Modulo;
import com.ipartek.formacion.service.excepciones.ModuloException;

public class ModuloServiceImp implements ModuloService{
	private static ModuloServiceImp INSTANCE = null;
	private List<Modulo> modulos;
	private static int i = 1; //para contar los objetos que añadimos y que cada objeto tenga un codigo distinto
	private ModuloDAO modDAO;
	
	/*
	private void init(){
		Modulo modulo = new Modulo();
		modulo.setNombre("Java");
		createModulo(modulo);
		
		modulo = new Modulo();
		modulo.setNombre("PHP");
		createModulo(modulo);
		
		modulo = new Modulo();
		modulo.setNombre("ASP.NET");
		createModulo(modulo);
		
		modulo.setCodigo(i);
		modulo.setNombre("Java");
		modulos.add(modulo);
		i++;
		
		modulo = new Modulo();
		modulo.setCodigo(i);
		modulo.setNombre("ASP");
		modulos.add(modulo);
		i++;
		
		
	}*/
	
	//constructor
	public ModuloServiceImp(){
		modDAO = ModuloDAOImp.getInstance();
		//init();
	}
	
	public ModuloServiceImp getInstance(){
		if(INSTANCE == null){
			createInstance();
		}
		return INSTANCE;
	}
	
	private synchronized static void createInstance() {
		if(INSTANCE == null){
			INSTANCE = new ModuloServiceImp();
		}
	
	}

	@Override
	public Modulo createModulo(Modulo modulo) {
		
		Modulo mod = modDAO.create(modulo);
		
		return mod;
	}

	@Override
	public Modulo getById(int codigo) {
		
		Modulo modulo = modDAO.getById(codigo);
		
		return modulo;
	}

	
	/*
	private int getIndex(int codigo) throws ModuloException{
		int index = -1;
		int i = 0;
		boolean encontrado = false;
		
		while(i < this.modulos.size() && encontrado ==false){
			Modulo aux = this.modulos.get(i); //modulos[i]
			if(aux.getCodigo()==codigo){
				encontrado=true;
				index=i;
			}
				
			i++;
		}
		
		if(index==-1){
			throw new ModuloException(ModuloException.CODIGO_ERROR_INDEX,ModuloException.MSG_ERROR_INDEX);
		}
			
		return index;		
	}

	@Override
	public List<Modulo> getAll() {
		
		return this.modulos;
	}
	*/
	

	@Override
	public Modulo update(Modulo modulo) {
		Modulo mod = modDAO.update(modulo);
		
		return mod;
	}
	
	@Override
	public void deleteModulo(int codigo) {
		modDAO.delete(codigo);
		
	}
	
	//Entonces, se debería impedir la clonación sobreescribiendo el método "clone" de la siguiente manera:
		@Override
		protected Object clone() throws CloneNotSupportedException {
			
			throw new CloneNotSupportedException(); 
		}

		@Override
		public List<Modulo> getAll() {
			return modDAO.getAll();
		}

		

		
	
}
