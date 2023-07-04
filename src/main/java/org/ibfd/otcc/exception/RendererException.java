package org.ibfd.otcc.exception;

public class RendererException extends Exception {

    private static final long serialVersionUID = 1L;

    public RendererException() {
        super();
    }

    public RendererException(String message, Throwable cause) {
        super(message, cause);
    }

    public RendererException(String message) {
        super(message);
    }

    public RendererException(Throwable cause) {
        super(cause);
    }
}
