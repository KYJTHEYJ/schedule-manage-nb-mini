package kyj.schedule_manage.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateCommentResponse {
    private final Long scheduleId;
    private final String content;
    private final String author;
    private final String pwd;
    private final LocalDateTime createAt;
    private final LocalDateTime updateAt;

    public CreateCommentResponse(Long scheduleId, String content, String author, String pwd, LocalDateTime createAt, LocalDateTime updateAt) {
        this.scheduleId = scheduleId;
        this.content = content;
        this.author = author;
        this.pwd = pwd;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }
}
