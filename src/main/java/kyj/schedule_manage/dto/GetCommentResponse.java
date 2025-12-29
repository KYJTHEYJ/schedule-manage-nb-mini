package kyj.schedule_manage.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetCommentResponse {
    private final long id;
    private final long scheduleId;
    private final String content;
    private final String author;
    private final LocalDateTime createAt;
    private final LocalDateTime updateAt;

    public GetCommentResponse(long id, long scheduleId, String content, String author, LocalDateTime createAt, LocalDateTime updateAt) {
        this.id = id;
        this.scheduleId = scheduleId;
        this.content = content;
        this.author = author;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }
}
