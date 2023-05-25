package example.poc.exception;

public class RegionalXmlTransformerException extends Exception {

    private static final long serialVersionUID = 1L;

    public RegionalXmlTransformerException() {
        super();
    }

    public RegionalXmlTransformerException(String message, Throwable cause) {
        super(message, cause);
    }

    public RegionalXmlTransformerException(String message) {
        super(message);
    }

    public RegionalXmlTransformerException(Throwable cause) {
        super(cause);
    }
}
