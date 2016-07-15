package com.ipartek.formacion.pojo;

import java.util.Date;

public class Usuario {
  private String sessionId;
  private String userName;
  private String userPassword;
  private String nickname;
  private Idioma idioma;
  private Date fConexion;

  public Usuario() {
    this.setUserName("");
    this.setUserPassword("");
    this.setNickname("");
    this.fConexion = new Date();
  }

  public Idioma getIdioma() {
    return this.idioma;
  }

  public void setIdioma(Idioma idioma) {
    this.idioma = idioma;
  }

  public String getSessionId() {
    return this.sessionId;
  }

  public void setSessionId(String sessionId) {
    this.sessionId = sessionId;
  }

  public Date getfConexion() {
    return this.fConexion;
  }

  public void setfConexion(Date fConexion) {
    this.fConexion = fConexion;
  }

  public String getUserName() {
    return this.userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserPassword() {
    return this.userPassword;
  }

  public void setUserPassword(String userPassword) {
    this.userPassword = userPassword;
  }

  public String getNickname() {
    return this.nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }
}
