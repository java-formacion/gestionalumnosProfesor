package com.ipartek.formacion.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.pojo.Modulo;
import com.ipartek.formacion.pojo.exception.CandidatoException;
import com.ipartek.formacion.service.ModuloService;
import com.ipartek.formacion.service.ModuloServiceImp;

public class ModuloServlet extends HttpServlet{
	
	 private int id = -1, operacion = -1;
	 private RequestDispatcher rd = null;
	 private ModuloService mService = new ModuloServiceImp();
	 private List<Modulo> modulos = null;
	 private Modulo modulo = null;  

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ModuloServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			recogerId(request);
			if(id<0)
			{//redireccion a un create
				rd = request.getRequestDispatcher(Constantes.JSP_MODULO);
			}
			else
			{//Redireccion a una update
				getById(request);
			}
			
		}catch(Exception e){
			getAll(request);
		}
		rd.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String op = request.getParameter(Constantes.PAR_OPERACION);
		recogerId(request);
		try {
			operacion = Integer.parseInt(op);
			recogerDatosModulo(request);
			switch (operacion) {
			case Constantes.OP_CREATE:
				mService.create(modulo);
				break;
			case Constantes.OP_DELETE:
				mService.delete(id);
				break;
			case Constantes.OP_READ:
				
				break;
			case Constantes.OP_UPDATE:
				mService.update(modulo);
				break;
			default:
				break;
			}
		}catch (Exception e) {
				// TODO: handle exception
			}
		rd.forward(request, response);
		
	}
	
	private void getById(HttpServletRequest request) {
		modulo = mService.getById(id);
		request.setAttribute(Constantes.ATT_MODULO, modulo);
		rd = request.getRequestDispatcher(Constantes.JSP_MODULO);
	}
	
	private void getAll(HttpServletRequest request) {
		modulos = mService.getAll();
		request.setAttribute(Constantes.ATT_LISTADO_MODULOS, modulos);
		rd = request.getRequestDispatcher(Constantes.JSP_LISTADO_MODULOS);
	}

	private void recogerId(HttpServletRequest request) {
		id = Integer.parseInt(request.getParameter(Constantes.PAR_CODIGO));
		
	}
	
	private void recogerDatosModulo(HttpServletRequest request) throws CandidatoException {
		// TODO Auto-generated method stub
		modulo = new Modulo();
		modulo.setNombre(request.getParameter(Constantes.PAR_NOMBRE));
		modulo.setCodigo(id);
	}
}
