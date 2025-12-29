package kyj.schedule_manage.service;

import kyj.schedule_manage.dto.*;
import kyj.schedule_manage.entity.Comment;
import kyj.schedule_manage.entity.Schedule;
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

    public Schedule checkPwd(long id, String pwd) {
        Schedule getSchedule = scheduleRepository.findById(id).orElseThrow(() -> new IllegalStateException("존재하지 않는 일정입니다"));

        if (!pwd.equals(getSchedule.getPwd())) {
            throw new IllegalStateException("비밀번호가 틀립니다");
        }

        return getSchedule;
    }

    @Transactional
    public CreateScheduleResponse createSchedule(CreateScheduleRequest request) {
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
        Schedule getSchedule = scheduleRepository.findById(id).orElseThrow(() -> new IllegalStateException("존재하지 않는 일정입니다"));
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
    public List<GetScheduleResponse> getAllSchedule() {
        return scheduleRepository.findAll().stream()
                .map(schedule -> new GetScheduleResponse(
                        schedule.getId()
                        , schedule.getTitle()
                        , schedule.getContent()
                        , schedule.getAuthor()
                        , schedule.getCreateAt()
                        , schedule.getUpdateAt()
                        , commentRepository.findByScheduleId(schedule.getId()).stream()
                        .map(comment -> new GetCommentResponse(
                                comment.getId()
                                , comment.getScheduleId()
                                , comment.getContent()
                                , comment.getAuthor()
                                , comment.getCreateAt()
                                , comment.getUpdateAt())
                        ).toList())
                ).toList();
    }

    @Transactional
    public UpdateScheduleResponse updateSchedule(long id, UpdateScheduleRequest request) {
        Schedule getSchedule = checkPwd(id, request.pwd());
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
        Schedule getSchedule = checkPwd(id, request.pwd());
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
        Schedule getSchedule = checkPwd(id, request.pwd());
        scheduleRepository.delete(getSchedule);
    }
}
