package kyj.schedule_manage.exception;

public class NotFoundDataException extends RuntimeException {
    public NotFoundDataException(String errorMessage) {
        super(errorMessage);
    }
}
