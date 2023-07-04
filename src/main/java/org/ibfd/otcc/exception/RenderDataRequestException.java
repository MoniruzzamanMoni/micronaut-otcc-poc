package org.ibfd.otcc.exception;

public class RenderDataRequestException extends Exception {

    private static final long serialVersionUID = 1L;

    public RenderDataRequestException() {
        super();
    }

    public RenderDataRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public RenderDataRequestException(String message) {
        super(message);
    }

    public RenderDataRequestException(Throwable cause) {
        super(cause);
    }
}
