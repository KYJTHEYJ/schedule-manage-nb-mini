package kyj.schedule_manage.service;

import kyj.schedule_manage.dto.CreateCommentRequest;
import kyj.schedule_manage.dto.CreateCommentResponse;
import kyj.schedule_manage.entity.Comment;
import kyj.schedule_manage.repository.CommentRepository;
import kyj.schedule_manage.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    private void checkCommentContentLength(String content) {
        content = "a".repeat(300);
        if(content.length() > 100) {
            throw new IllegalArgumentException("내용은 100자 까지 작성 가능합니다");
        }
    }

    private void checkEmptyToCreateComment(String content, String author, String pwd) {
        if(content.isEmpty()) {
            throw new IllegalArgumentException("내용은 필수입니다");
        }

        if(author.isEmpty()) {
            throw new IllegalArgumentException("작성자는 필수입니다");
        }

        if(pwd.isEmpty()) {
            throw new IllegalArgumentException("비밀번호는 필수입니다");
        }
    }

    private void checkCommentCount(long scheduleId) {
        if(commentRepository.findByScheduleId(scheduleId).stream().count() >= 10) {
            throw new IllegalArgumentException("일정엔 10개까지의 댓글만 작성 가능합니다");
        }
    }

    public CreateCommentResponse createComment(long scheduleId, CreateCommentRequest request) {
        if(!scheduleRepository.existsById(scheduleId)) {
            throw new IllegalArgumentException("댓글을 입력 할 일정이 없습니다");
        }

        checkCommentCount(scheduleId);
        checkCommentContentLength(request.getContent());
        checkEmptyToCreateComment(request.getContent(), request.getAuthor(), request.getPwd());

        Comment comment = new Comment(scheduleId, request.getContent(), request.getAuthor(), request.getPwd());
        Comment createdComment = commentRepository.save(comment);

        return new CreateCommentResponse(
                scheduleId
                , createdComment.getContent()
                , createdComment.getAuthor()
                , createdComment.getPwd()
                , createdComment.getCreateAt()
                , createdComment.getUpdateAt());
    }
}
