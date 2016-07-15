package com.ipartek.formacion.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.pojo.DuracionModulo;
import com.ipartek.formacion.pojo.Modulo;
import com.ipartek.formacion.service.ModuloService;
import com.ipartek.formacion.service.ModuloServiceImp;
import com.ipartek.formacion.service.Util;

/**
 * Servlet implementation class ModuloServlet
 */
public class ModuloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static ModuloService mService = new ModuloServiceImp();
	private RequestDispatcher rwd = null;
	private List<Modulo> modulos = null;
	private Modulo modulo = null;
	private int id = -1;
	private int operacion = -1;

	public ModuloServlet() {
		super();
	    // TODO Auto-generated constructor stub
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			//id = Integer.parseInt(request.getParameter("id"));
			request.setAttribute(Constantes.ATT_LISTA_DURACION_MODULO, Constantes.LISTA_DURACION);
			recogerId(request);
			
			if(id < 0){
				// Se redirige para realizar un CREATE
				rwd = request.getRequestDispatcher(Constantes.JSP_MODULO);
			} else{
				// Se redirige para realizar un UPDATE
				getById(request);
			}
		} 		
		catch(Exception e){
			getAll(request);
		}

		rwd.forward(request, response);
	}
	
	private void getAll(HttpServletRequest request) {
		modulos = mService.getAll();
		request.setAttribute("listado_modulos", modulos);
		rwd = request.getRequestDispatcher("/modulos/listadoModulos.jsp");
	}

	private void getById(HttpServletRequest request) {
		modulo = mService.getById(id);
		request.setAttribute(Constantes.ATT_MODULO, modulo);
		rwd = request.getRequestDispatcher(Constantes.JSP_MODULO);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter(Constantes.PAR_OPERACION);
		
		try {
			if(Util.tryParseInt(op)){
				operacion = Integer.parseInt(op);
			}
			
			// 1- recoger los datos del objeto curso
			recogerId(request);
			
			// 2- diferencias la create de las demas
			switch(operacion){
				case Constantes.OP_CREATE:
					// CREATE
					recogerDatosModulo(request);
					mService.create(modulo);
					break;
				
				case Constantes.OP_DELETE:
					// DELETE
					mService.delete(id);
					break;
					
				case Constantes.OP_UPDATE:
					// UPDATE
					recogerDatosModulo(request);
					mService.update(modulo);
					break;
					
				case Constantes.OP_READ:
				
					break;
				
				default:
					break;
			
			}
		} catch (NumberFormatException e){
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		getAll(request);
		rwd.forward(request, response);
	}
	
	private void recogerId(HttpServletRequest request) {
		id = Integer.parseInt(request.getParameter(Constantes.PAR_CODIGO));
	}

	private void recogerDatosModulo(HttpServletRequest request) {	
		modulo = new Modulo();
		
		modulo.setCodigo(id);
		
		String nombre = request.getParameter(Constantes.PAR_NOMBRE);
		modulo.setNombre(nombre);
		
		String codDuracion = request.getParameter(Constantes.PAR_DURACION);
		DuracionModulo duracion = Util.parseDuracion(codDuracion);
		modulo.setDuracion(duracion);
		
		String referencia = request.getParameter(Constantes.PAR_REFERENCIA);
		modulo.setReferencia(referencia);
		
		String tipoCurso = request.getParameter(Constantes.PAR_TIPO_CURSO);
		modulo.setTipoCurso(tipoCurso);
	}

}
