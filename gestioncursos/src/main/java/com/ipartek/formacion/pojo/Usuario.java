package com.ipartek.formacion.pojo;

public class Usuario {
  private String userName;
  private String userPassword;
  private String nickname;
  private String sessionId;
  private Idioma idioma;

  public Usuario() {
    setUserName("");
    setUserPassword("");
    setNickname("");
    setSessionId("");
    setIdioma(Idioma.CASTELLANO);
  }

  public Idioma getIdioma() {
    return idioma;
  }

  public void setIdioma(Idioma idioma) {
    this.idioma = idioma;
  }

  public String getSessionId() {
    return sessionId;
  }

  public void setSessionId(String sessionId) {
    this.sessionId = sessionId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserPassword() {
    return userPassword;
  }

  public void setUserPassword(String userPassword) {
    this.userPassword = userPassword;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }
}
