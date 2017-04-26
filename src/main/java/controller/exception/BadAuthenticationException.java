package controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by alarreine on 16/04/2017.
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class BadAuthenticationException extends RuntimeException {
    public BadAuthenticationException(String m) {
        super(m);
    }
}
