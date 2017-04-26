package controller.exception;

/**
 * Created by alarreine on 13/04/2017.
 */
public class KeyNotFoundException extends RuntimeException {

    public KeyNotFoundException(String key) {
        super("Il n'exist pas la cle " + key);
    }
}
