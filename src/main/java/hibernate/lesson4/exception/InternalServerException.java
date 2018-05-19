package hibernate.lesson4.exception;

/**
 * Created by user on 06.09.2017.
 */
public class InternalServerException extends Exception {
    public InternalServerException(String message) {
        super(message);
    }
}
