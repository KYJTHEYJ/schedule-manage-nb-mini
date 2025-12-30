package kyj.schedule_manage.exception.handler;

import kyj.schedule_manage.dto.ErrorResponse;
import kyj.schedule_manage.exception.ErrorType;
import kyj.schedule_manage.exception.NotFoundDataException;
import kyj.schedule_manage.exception.NotMatchedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class exceptionHandler {

    @ExceptionHandler(NotFoundDataException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFoundException(NotFoundDataException e) {
        return new ErrorResponse(ErrorType.NOT_FOUND_DATA, e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleIllegalArgumentException(IllegalArgumentException e) {
        return new ErrorResponse(ErrorType.INPUT_DATA_WRONG, e.getMessage());
    }

    @ExceptionHandler(NotMatchedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleNotMatchedException(NotMatchedException e) {
        return new ErrorResponse(ErrorType.NOT_MATCH, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleException(Exception e) {
        return new ErrorResponse(ErrorType.UNCONTROLLED_ERROR, "서버 오류가 발생하였습니다");
    }
}
