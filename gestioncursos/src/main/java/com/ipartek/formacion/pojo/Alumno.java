package com.ipartek.formacion.pojo;

public class Alumno extends Candidato implements Comparable<Alumno> {
	private Curso curso;

	public Alumno() {
		super();
		this.curso = new Curso();
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	@Override
	public String mostrarDatos() {
		return super.mostrarDatos() + " " + this.curso.getNombre();
	}

	@Override
	public int compareTo(Alumno o) {
		int igual = 0;
		if (o.getCodigo() == this.getCodigo()) {
			igual = 0;
		} else {
			if (o.getApellidos().compareToIgnoreCase(this.getApellidos()) > 0) {
				igual = 1;
			} else {
				if (o.getApellidos().compareToIgnoreCase(this.getApellidos()) == 0) {
					igual = 0;
				} else {
					igual = -1;
				}
			}
		}
		return igual;
	}

	@Override
	public String toString() {
		return "Alumno [curso=" + curso + ", codigo=" + codigo + ", nombre="
				+ nombre + ", apellidos=" + apellidos + ", fNacimiento="
				+ fNacimiento + ", dni=" + dni + ", nota=" + nota + ", genero="
				+ genero + ", telefono=" + telefono + ", email=" + email
				+ ", idiomas=" + idiomas + "]";
	}

}
