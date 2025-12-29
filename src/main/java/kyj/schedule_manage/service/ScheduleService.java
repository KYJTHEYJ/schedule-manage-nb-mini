package kyj.schedule_manage.service;

import kyj.schedule_manage.dto.CreateScheduleRequest;
import kyj.schedule_manage.dto.CreateScheduleResponse;
import kyj.schedule_manage.entity.Schedule;
import kyj.schedule_manage.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
