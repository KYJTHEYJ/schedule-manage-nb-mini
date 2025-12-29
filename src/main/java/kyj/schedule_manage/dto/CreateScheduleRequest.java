package kyj.schedule_manage.dto;

import lombok.Getter;

@Getter
public class CreateScheduleRequest {
    private long id;
    private String title;
    private String content;
    private String author;
    private String pwd;
}
