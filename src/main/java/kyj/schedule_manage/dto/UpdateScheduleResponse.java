package kyj.schedule_manage.dto;

import java.time.LocalDateTime;

public record UpdateScheduleResponse(
        long id
        , String title
        , String content
        , String author
        , LocalDateTime createAt
        , LocalDateTime updateAt
) {
}
