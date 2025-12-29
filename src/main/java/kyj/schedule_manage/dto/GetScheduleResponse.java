package kyj.schedule_manage.dto;

import kyj.schedule_manage.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class GetScheduleResponse {
    private final long id;
    private final String title;
    private final String content;
    private final String author;
    private final LocalDateTime createAt;
    private final LocalDateTime updateAt;
    private final List<GetCommentResponse> commentResponseList;

    public GetScheduleResponse(long id, String title, String content, String author, LocalDateTime createAt, LocalDateTime updateAt,  List<GetCommentResponse> commentResponseList) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.commentResponseList = commentResponseList;
    }
}
