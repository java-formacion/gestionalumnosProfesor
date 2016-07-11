package com.ipartek.formacion.service.exceptions;

public class AlumnoServiceException extends Exception {
    /**
     * 
     */
    private static final long serialVersionUID = 5645309620763577742L;
    public final static int CODIGO_ALUMNO_NO_ECONTRADO = 100;
    public final static String MSG_ALUMNO_NO_ENCONTRADO = "El alumno no se ha encontrado.";
    private int codigo;
    private String mensaje;

    public AlumnoServiceException(final int codigo, final String mensaje) {
        super(mensaje);
        this.codigo = codigo;
        this.mensaje = mensaje;
    }

    public AlumnoServiceException(final String message, final Throwable cause,
            final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }

    public AlumnoServiceException(final String message, final Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    public AlumnoServiceException(final String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    public AlumnoServiceException(final Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    @Override
    public String getMessage() {
        // TODO Auto-generated method stub
        return mensaje;
    }

    public final int getCodigo() {
        return codigo;
    }

    public final void setCodigo(final int codigo) {
        this.codigo = codigo;
    }

}
