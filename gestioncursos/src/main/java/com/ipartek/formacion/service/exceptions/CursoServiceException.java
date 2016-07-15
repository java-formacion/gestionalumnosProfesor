package com.ipartek.formacion.service.exceptions;

public class CursoServiceException extends Exception {
	public final static int CODIGO_CURSO_NO_ECONTRADO = 100;
	public final static String MSG_CURSO_NO_ENCONTRADO = "El curso no se ha encontrado.";
	private int codigo;
	private String mensaje;

	public CursoServiceException(final int codigo, final String mensaje) {
		super(mensaje);
		this.codigo = codigo;
		this.mensaje = mensaje;
	}

	/**
	 * <strong>CursoServiceException</strong>
	 * 
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public CursoServiceException(final String message, final Throwable cause,
			final boolean enableSuppression, final boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CursoServiceException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public CursoServiceException(final String message) {
		super(message);
	}

	public CursoServiceException(final Throwable cause) {
		super(cause);
	}

	@Override
	public String getMessage() {
		return mensaje;
	}

}
