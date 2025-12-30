package kyj.schedule_manage.service;

import kyj.schedule_manage.dto.*;
import kyj.schedule_manage.entity.Comment;
import kyj.schedule_manage.entity.Schedule;
import kyj.schedule_manage.exception.NotFoundDataException;
import kyj.schedule_manage.exception.NotMatchedException;
import kyj.schedule_manage.repository.CommentRepository;
import kyj.schedule_manage.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final CommentRepository commentRepository;

    //region 유효성 검사
    private Schedule checkSchedulePwd(long id, String pwd) {
        Schedule getSchedule = scheduleRepository.findById(id).orElseThrow(() -> new NotFoundDataException("존재하지 않는 일정입니다"));

        if (!pwd.equals(getSchedule.getPwd())) {
            throw new NotMatchedException("등록한 일정의 비밀번호와 일치하지 않습니다");
        }

        return getSchedule;
    }

    private void checkEmptyToCreateSchedule(String title, String content, String author, String pwd) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("제목은 필수입니다");
        }

        if (content == null || content.isEmpty()) {
            throw new IllegalArgumentException("내용은 필수입니다");
        }

        if (author == null || author.isEmpty()) {
            throw new IllegalArgumentException("작성자는 필수입니다");
        }

        if (pwd == null || pwd.isEmpty()) {
            throw new IllegalArgumentException("비밀번호는 필수입니다");
        }
    }

    private void checkScheduleTitleLength(String title) {
        if (title.length() > 30) {
            throw new IllegalArgumentException("제목은 30자 까지 작성 가능합니다");
        }
    }

    private void checkScheduleContentLength(String content) {
        if (content.length() > 200) {
            throw new IllegalArgumentException("내용은 200자 까지 작성 가능합니다");
        }
    }
    //endregion

    @Transactional
    public CreateScheduleResponse createSchedule(CreateScheduleRequest request) {
        checkEmptyToCreateSchedule(request.getTitle(), request.getContent(), request.getAuthor(), request.getPwd());
        checkScheduleTitleLength(request.getTitle());
        checkScheduleContentLength(request.getContent());

        Schedule schedule = new Schedule(request.getTitle(), request.getContent(), request.getAuthor(), request.getPwd());
        Schedule createdSchedule = scheduleRepository.save(schedule);

        return new CreateScheduleResponse(
                createdSchedule.getId()
                , createdSchedule.getTitle()
                , createdSchedule.getContent()
                , createdSchedule.getAuthor()
                , createdSchedule.getCreateAt()
                , createdSchedule.getUpdateAt());
    }

    @Transactional(readOnly = true)
    public GetScheduleResponse getSchedule(long id) {
        Schedule getSchedule = scheduleRepository.findById(id).orElseThrow(() -> new NotFoundDataException("존재하지 않는 일정입니다"));
        List<GetCommentResponse> commentResponseList = commentRepository.findByScheduleId(getSchedule.getId()).stream()
                .map(comment -> new GetCommentResponse(
                        comment.getId()
                        , comment.getScheduleId()
                        , comment.getContent()
                        , comment.getAuthor()
                        , comment.getCreateAt()
                        , comment.getUpdateAt())
                ).toList();

        return new GetScheduleResponse(
                getSchedule.getId()
                , getSchedule.getTitle()
                , getSchedule.getContent()
                , getSchedule.getAuthor()
                , getSchedule.getCreateAt()
                , getSchedule.getUpdateAt()
                , commentResponseList);
    }

    @Transactional(readOnly = true)
    public List<GetScheduleResponse> getAllSchedule(String author) {
        List<Schedule> scheduleList;

        if (author != null) {
            scheduleList = scheduleRepository.findAll().stream().filter(schedule -> schedule.getAuthor().equals(author)).toList();
        } else {
            scheduleList = scheduleRepository.findAll();
        }

        return scheduleList.stream().map(schedule -> new GetScheduleResponse(
                schedule.getId()
                , schedule.getTitle()
                , schedule.getContent()
                , schedule.getAuthor()
                , schedule.getCreateAt()
                , schedule.getUpdateAt()
                , commentRepository.findByScheduleId(schedule.getId()).stream().map(comment -> new GetCommentResponse(
                comment.getId()
                , comment.getScheduleId()
                , comment.getContent()
                , comment.getAuthor()
                , comment.getCreateAt()
                , comment.getUpdateAt())).toList())).toList();
    }

    @Transactional
    public UpdateScheduleResponse updateSchedule(long id, UpdateScheduleRequest request) {
        checkScheduleTitleLength(request.title());
        checkScheduleContentLength(request.content());

        Schedule getSchedule = checkSchedulePwd(id, request.pwd());
        getSchedule.update(request.title(), request.content());

        return new UpdateScheduleResponse(
                getSchedule.getId()
                , getSchedule.getTitle()
                , getSchedule.getContent()
                , getSchedule.getAuthor()
                , getSchedule.getCreateAt()
                , getSchedule.getUpdateAt());
    }

    @Transactional
    public UpdateScheduleResponse patchSchedule(long id, UpdateScheduleRequest request) {
        if (request.title() != null) {
            checkScheduleTitleLength(request.title());
        }

        if (request.content() != null) {
            checkScheduleContentLength(request.content());
        }

        Schedule getSchedule = checkSchedulePwd(id, request.pwd());
        getSchedule.patch(request.title(), request.content());

        return new UpdateScheduleResponse(
                getSchedule.getId()
                , getSchedule.getTitle()
                , getSchedule.getContent()
                , getSchedule.getAuthor()
                , getSchedule.getCreateAt()
                , getSchedule.getUpdateAt());
    }

    @Transactional
    public void deleteSchedule(long id, DeleteScheduleRequest request) {
        Schedule getSchedule = checkSchedulePwd(id, request.pwd());

        List<Comment> getCommentList = commentRepository.findByScheduleId(getSchedule.getId());
        commentRepository.deleteAll(getCommentList);

        scheduleRepository.delete(getSchedule);
    }
}
