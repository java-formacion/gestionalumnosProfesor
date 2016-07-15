package com.ipartek.formacion.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ipartek.formacion.pojo.Idioma;
import com.ipartek.formacion.pojo.Mensaje;
import com.ipartek.formacion.pojo.Usuario;
import com.ipartek.formacion.service.Util;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private RequestDispatcher rd = null;
  private HttpSession session = null;
  private static final Logger log = Logger.getLogger(LoginServlet.class);
  private Usuario user = null;
  private String nUsuario = "";
  private String passWord = "";
  Cookie cookieNombre = null;
  Cookie cookiePass = null;

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    this.doProcess(request, response);
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    this.doProcess(request, response);
  }

  private void doProcess(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    if (this.cargarCookies(request)) {
      this.cargarDatosCookies();

    } else {
      if (request.getParameter(Constantes.PAR_USERNAME) != null) {
        this.cargarParametros(request);

      }
    }

    if (this.user != null && "anabel".equals(this.user.getUserName())
        && "anabel".equals(this.user.getUserPassword())) {
      this.generarCookies(response);

      String[] checkboxes = request.getParameterValues(Constantes.PAR_REMEMBER);
      if (checkboxes != null && checkboxes.length == 1) {
        this.cookieNombre.setMaxAge(60 * 60 * 24);
        this.cookiePass.setMaxAge(24 * 60 * 60);
      } else {
        cookieNombre.setMaxAge(0);
        cookiePass.setMaxAge(0);
      }
      response.addCookie(cookieNombre);
      response.addCookie(cookiePass);
      procesarLogin(request);
      session.setAttribute(Constantes.ATT_USUARIO, user);
      rd.forward(request, response);
    } else {
      createSession(request);
      Mensaje mensaje = new Mensaje();
      mensaje.setMsg("Usuario y/o contraseña incorrectos");
      mensaje.setType(Mensaje.MSG_TYPE_DANGER);
      session.setAttribute(Constantes.ATT_MENSAJE, mensaje);

      response.sendRedirect(Constantes.JSP_INDEX);
    }

  }

  private void generarCookies(HttpServletResponse response) {
    cookieNombre = new Cookie("usuario", user.getUserName());
    cookiePass = new Cookie("password", user.getUserPassword());

  }

  private void procesarLogin(HttpServletRequest request) {
    createSession(request);
    rd = request.getRequestDispatcher(Constantes.SERVLET_CURSOS);

    // request.setAttribute(Constantes.ATT_MENSAJE, mensaje);
  }

  private void cargarDatosCookies() {
    log.trace(nUsuario + " " + passWord);
    user = new Usuario();
    user.setUserName(nUsuario);
    user.setUserPassword(passWord);
    user.setNickname("Alumno");
  }

  private void cargarParametros(HttpServletRequest request) {
    this.user = new Usuario();
    String int1 = request.getParameter(Constantes.PAR_IDIOMA);
    Idioma id = Util.parseIdioma(int1);
    this.user.setUserName(request.getParameter(Constantes.PAR_USERNAME));
    this.user.setUserPassword(request.getParameter(Constantes.PAR_PASSWORD));
    this.user.setNickname("Alumno");
    this.user.setIdioma(id);
    // session.setAttribute(Constantes.ATT_USUARIO, usuario);
    // rd = request.getRequestDispatcher(Constantes.SERVLET_CURSOS);
  }

  private boolean cargarCookies(HttpServletRequest request) {
    boolean cargado = false;

    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (Cookie cookie : cookies) {

        if (cookie.getName().equals("usuario")) {
          this.nUsuario = cookie.getValue();
        } else {
          if (cookie.getName().equals("password")) {
            this.passWord = cookie.getValue();
          }
        }
      }
      if (!"".equals(this.nUsuario) && !"".equals(this.passWord)) {
        cargado = true;
      }
    }
    return cargado;
  }

  private void createSession(HttpServletRequest request) {
    this.session = request.getSession(true);
    /*
     * getSession(true) ---> Si la session no existe te la crea getSession(false) --> Te coge la
     * session activa si no existe es null
     */
    session.setMaxInactiveInterval(60 * 60 * 15);
  }
}
