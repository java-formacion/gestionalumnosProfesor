/**
 * 
 */
package com.ipartek.formacion.service;

import java.util.ArrayList;
import java.util.List;

import com.ipartek.formacion.pojo.Curso;
import com.ipartek.formacion.pojo.Modulo;
import com.ipartek.formacion.pojo.exception.ModuloException;

/**
 * @author Curso
 *
 */
public class ModuloServiceImp implements ModuloService {

	private static ModuloServiceImp INSTANCE=null;
	private List<Modulo>modulos;
	private static int aCounter=1;
	
	private ModuloServiceImp(){
		this.modulos=new ArrayList<Modulo>();
		init();
	}
	
	public static ModuloServiceImp getInstance(){
		if (INSTANCE==null) {
			INSTANCE=new ModuloServiceImp();
		}
		return INSTANCE;
	}
	
	private synchronized static void createInstance(){
		if (INSTANCE==null) {
			INSTANCE=new ModuloServiceImp();
		}
	}
	
	
	
	public void init(){
		Modulo m1=null;
		try {
			m1=new Modulo();
			m1.setNombre("modulo mnbv");
			m1.setCodigo(1);
			createModulo(m1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Modulo m2=null;
		try {
			m2=new Modulo();
			m2.setNombre("modulo fghj");
			m2.setCodigo(2);
			createModulo(m2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Modulo m3=null;
		try {
			m3=new Modulo();
			m3.setNombre("modulo rtyu");
			m3.setCodigo(3);
			createModulo(m3);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	@Override
	public Modulo createModulo(Modulo modulo) {
		modulo.setCodigo(aCounter);
		this.modulos.add(modulo);
		aCounter++;
		
		return modulo;
	}

	@Override
	public Modulo getById(int codigo) throws ModuloException {
		Modulo aux=null;
		int index;
		try {
			index=this.getIndex(codigo);
			if (index <0) {
				throw new ModuloException(ModuloException.CODIGO_ERROR_INDEX_MODULO,ModuloException.MSG_ERROR_INDEX_MODULO);
			}
			
			aux=this.modulos.get(index);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return aux;
	}

	@Override
	public void deleteModulo(int codigo) {
		int index=this.getIndex(codigo);
		this.modulos.remove(index);
		
	}

	@Override
	public List<Modulo> getAll() {
		
		return this.modulos;
	}

	@Override
	public Modulo updateModulo(Modulo modulo) {
		int index=this.getIndex(modulo.getCodigo());
		this.modulos.set(index, modulo);
		
		return modulo;
	}

	private int getIndex(int codigo) {
		int index = -1;
		int i = 0;
		int len = this.modulos.size();
		boolean found = false;
		while (i < len && found == false) {
			Modulo aux = this.modulos.get(i);

			if (aux.getCodigo() == codigo) {
				found = true;
				index = i;
			}
			i++;
		}

		return index;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
	
	
	
}
