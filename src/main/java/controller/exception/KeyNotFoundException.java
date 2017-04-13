package controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by alarreine on 13/04/2017.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class KeyNotFoundException extends RuntimeException {

    public KeyNotFoundException(String key) {
        super("Il n'exist pas la cle "+key);
    }
}
