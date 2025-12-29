package kyj.schedule_manage.dto;

public record UpdateScheduleRequest(
        String title
        , String content
        , String pwd
) {
}
