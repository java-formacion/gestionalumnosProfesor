package com.ipartek.formacion.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.controller.exception.AlumnoError;
import com.ipartek.formacion.pojo.Alumno;
import com.ipartek.formacion.pojo.Horas;
import com.ipartek.formacion.pojo.Modulo;
import com.ipartek.formacion.pojo.excepciones.CandidatoException;
import com.ipartek.formacion.service.ModuloService;
import com.ipartek.formacion.service.ModuloServiceImp;
import com.ipartek.formacion.service.Util;

/**
 * Servlet implementation class ModuloServlet
 */
public class ModuloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ModuloService mService = new ModuloServiceImp();  
	private List<Modulo> modulos = null;
	private Modulo modulo = null;
	private RequestDispatcher rwd = null;
	private int id = -1;   
	private int operacion = -1;   
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
  			recogerId(request); //recibe como paremetro el id, porque en listado.jsp le hemos llamado id.Getparameter recibe siempre un string, por eso hay que pasarlo a integer
  			request.setAttribute(Constantes.ATT_LISTADO_MODULOS, mService.getAll()); 
  			if(id<0){ //REDIRIGIMOS PARA UN CREATE
  				rwd = request.getRequestDispatcher(Constantes.JSP_MODULO); //a jsp_alumno no le paoso el alumno, xq en jsp_alumno si no recibe alumno lo que hace es create
  			}else{ //REDIRIGIMOS PARA UNA UPDATE
  				getById(request); //si recibe un parametro (un id)hace getById
  			}
  			
  		}
  		catch (Exception e){
  			getAll(request);//si lo anterior falla xq no recibe parametro (un id), entra aqui y hace getAll
  		}
  		rwd.forward(request,response);
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
  	
  	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Procesaremos el DELETE , UPDATE Y CREATE
			//1º recoger datos del objeto curso
			String op = request.getParameter(Constantes.PAR_OPERACION);
			
				//System.out.println(op);
			
				operacion = Integer.parseInt(op);
				
				switch (operacion) {
				
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
						mService.deleteModulo(id);
				break;
	
				default:
					break;
			}
				
		    getAll(request);		
			rwd.forward(request, response);
	}
	
	private void recogerId(HttpServletRequest request) {
		id = Integer.parseInt(request.getParameter(Constantes.PAR_CODIGO));
	}


	private void recogerDatos(HttpServletRequest request){
		modulo = new Modulo();
		
		recogerId(request);
		modulo.setCodigo(id);
		String nombre = request.getParameter(Constantes.PAR_NOMBRE);
		modulo.setNombre(nombre);
		String referencia = request.getParameter(Constantes.PAR_REFERENCIA);
		modulo.setReferencia(referencia);
		String horas = request.getParameter(Constantes.PAR_HORAS);
		modulo.setHoras(Util.parseHoras(horas));
		
		
	}

}

