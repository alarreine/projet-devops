package controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by alarreine on 16/04/2017.
 */
@ResponseStatus(value = HttpStatus.CONFLICT)
public class IncreaseKeyException extends RuntimeException {
    public IncreaseKeyException(String m) {
        super(m);
    }
}
