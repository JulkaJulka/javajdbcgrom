package hibernate.lesson4.exception;

/**
 * Created by user on 06.09.2017.
 */
public class BadRequestException extends Exception {
    public BadRequestException(String message) {
        super(message);
    }
}
