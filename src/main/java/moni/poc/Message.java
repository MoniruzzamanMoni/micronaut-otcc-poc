package moni.poc;

import io.micronaut.serde.annotation.Serdeable;

/**
 * @author: Md. Moniruzzaman <moni.return@gmail.com>
 * @since: 5/1/2023
 */
@Serdeable
public class Message {
    private String message;
    private String status;

    public Message() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
