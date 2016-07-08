package com.ipartek.formacion.controller;

import com.ipartek.formacion.pojo.Curso;
import com.ipartek.formacion.pojo.Duracion;
import com.ipartek.formacion.pojo.Modulo;
import com.ipartek.formacion.pojo.exception.ModuloException;
import com.ipartek.formacion.service.CursoService;
import com.ipartek.formacion.service.CursoServiceImp;
import com.ipartek.formacion.service.ModuloService;
import com.ipartek.formacion.service.ModuloServiceImp;
import com.ipartek.formacion.service.Util;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ModuloServlet
 */
public class ModuloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    private int id = -1;
    private int operacion = -1;
    private RequestDispatcher rd = null;
    private ModuloService mService = ModuloServiceImp.getInstance();
    private List<Modulo> modulos = null;
    private Modulo modulo = null;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			recogerId(request);
			request.setAttribute(Constantes.ATT_LISTA_DURACION_MODULO, Constantes.LISTA_DURACION);
			if(id < 0){
				rd = request.getRequestDispatcher(Constantes.JSP_MODULO);	
			}else{                       
				
				getById(request);
			}
			
		} catch(Exception e){
			getAll(request);
		}
		rd.forward(request, response);
	}
	private void getById(HttpServletRequest request) throws ModuloException {
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

	private void recogerDatos(HttpServletRequest request) {
		modulo = new Modulo();
		recogerId(request);
		modulo.setCodigo(id);
		String nombre = request.getParameter(Constantes.PAR_NOMBRE);
		modulo.setNombre(nombre);
		String referencia=request.getParameter(Constantes.PAR_REFERENCIA);
		modulo.setRefModulo(referencia);
		Duracion duraModulo=Util.parseDuracion(request.getParameter(Constantes.PAR_DURACION));
		modulo.setDurModulo(duraModulo);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter(Constantes.PAR_OPERACION);
		try{
			
			operacion = Integer.parseInt(op);
		
			switch(operacion){
				case Constantes.OP_CREATE:
					recogerDatos(request);
					mService.createModulo(modulo);
				break;
				case Constantes.OP_DELETE:
					recogerId(request);
					mService.deleteModulo(id);
				break;
				case Constantes.OP_UPDATE:
					recogerDatos(request);
					mService.updateModulo(modulo);
				break;
			}
		} catch (NumberFormatException e){
			//TODO alguien nos toquetea los argumentos del form
		}
		
		getAll(request);
		rd.forward(request, response);
	}

}