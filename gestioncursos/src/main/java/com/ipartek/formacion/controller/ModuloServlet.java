package com.ipartek.formacion.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.pojo.Modulo;
import com.ipartek.formacion.service.ModuloService;
import com.ipartek.formacion.service.ModuloServiceImp;
import com.ipartek.formacion.service.Util;

/**
 * Servlet implementation class ModuloServlet
 */
public class ModuloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ModuloService mService = ModuloServiceImp.getInstance();
	private Modulo modulo = null;
	private List<Modulo> modulos = null;
	private int id = -1;
	private int operacion = -1;
	private RequestDispatcher rwd;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModuloServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			recogerId(request);
			if (this.id < 0) {
				rwd = request.getRequestDispatcher(Constantes.JSP_MODULO);
			} else {
				getById(request);
			}

		} catch (Exception e) {
			getAll(request);
		}
		rwd.forward(request, response);
	}

	private void getById(HttpServletRequest request) {
		modulo = mService.getById(id);
		request.setAttribute(Constantes.ATT_MODULO, modulo);
		rwd = request.getRequestDispatcher(Constantes.JSP_MODULO);
	}

	private void getAll(HttpServletRequest request) {
		modulos = mService.getAll();
		request.setAttribute(Constantes.ATT_LISTADO_MODULOS, modulos);
		rwd = request.getRequestDispatcher(Constantes.JSP_LISTADO_MODULOS);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter(Constantes.PAR_OPERACION);
		try {
			this.operacion = Integer.parseInt(op);

			switch (this.operacion) {
			case Constantes.OP_CREATE:
				recogerDatos(request);
				mService.createModulo(modulo);
				break;
			case Constantes.OP_UPDATE:
				recogerDatos(request);
				mService.update(modulo);
				break;
			case Constantes.OP_DELETE:
				recogerId(request);
				mService.delete(this.id);
				break;
			default:
				break;
			}
			getAll(request);
		} catch (NumberFormatException e) {

		} catch (Exception e) {

		}

		rwd.forward(request, response);
	}

	private void recogerId(HttpServletRequest request) {
		this.id = Integer.parseInt(request.getParameter(Constantes.PAR_CODIGO));

	}

	private void recogerDatos(HttpServletRequest request) {
		modulo = new Modulo();
		recogerId(request);
		modulo.setCodigo(this.id);
		String referencia = request.getParameter(Constantes.PAR_REFERENCIA);
		String nombre = request.getParameter(Constantes.PAR_NOMBRE);
		String duracion = request.getParameter(Constantes.PAR_DURACION);
		modulo.setNombre(nombre);		
		modulo.setDuracion(Util.parseDuracion(duracion));
		modulo.setReferencia(referencia);

	}

}
