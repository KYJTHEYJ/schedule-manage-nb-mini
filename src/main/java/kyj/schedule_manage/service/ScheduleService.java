package kyj.schedule_manage.service;

import kyj.schedule_manage.dto.CreateScheduleRequest;
import kyj.schedule_manage.dto.CreateScheduleResponse;
import kyj.schedule_manage.dto.GetScheduleResponse;
import kyj.schedule_manage.entity.Schedule;
import kyj.schedule_manage.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

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
        return new GetScheduleResponse(
                getSchedule.getId()
                , getSchedule.getTitle()
                , getSchedule.getContent()
                , getSchedule.getAuthor()
                , getSchedule.getCreateAt()
                , getSchedule.getUpdateAt());
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
                        , schedule.getUpdateAt()))
                .toList();
    }
}
