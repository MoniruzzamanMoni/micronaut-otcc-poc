package org.ibfd.otcc.exception;

public class ExternalGatewayException extends Exception {

    private static final long serialVersionUID = 1L;

    public ExternalGatewayException() {
        super();
    }

    public ExternalGatewayException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExternalGatewayException(String message) {
        super(message);
    }

    public ExternalGatewayException(Throwable cause) {
        super(cause);
    }
}
