package kyj.schedule_manage.service;

import kyj.schedule_manage.dto.CreateCommentRequest;
import kyj.schedule_manage.dto.CreateCommentResponse;
import kyj.schedule_manage.entity.Comment;
import kyj.schedule_manage.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public CreateCommentResponse createComment(long scheduleId, CreateCommentRequest request) {
        Comment comment = new Comment(scheduleId, request.getContent(), request.getAuthor(), request.getPwd());

        if(commentRepository.findByScheduleId(scheduleId).stream().count() >= 10) {
            throw new IllegalStateException("일정엔 10개까지의 댓글만 작성 가능합니다");
        }

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
