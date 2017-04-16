package controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by alarreine on 16/04/2017.
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class ConflicException extends RuntimeException {
    public ConflicException(String key) {
        super("Imposible de ajouter la cle "+key);
    }
}
