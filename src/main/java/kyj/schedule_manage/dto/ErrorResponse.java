package kyj.schedule_manage.dto;

import kyj.schedule_manage.exception.ErrorType;

public record ErrorResponse(ErrorType errorType, String errorMessage) {

}
