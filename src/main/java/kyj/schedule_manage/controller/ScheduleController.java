package kyj.schedule_manage.controller;

import kyj.schedule_manage.dto.CreateScheduleRequest;
import kyj.schedule_manage.dto.CreateScheduleResponse;
import kyj.schedule_manage.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping("/schedules")
    public ResponseEntity<CreateScheduleResponse> createSchedule(@RequestBody CreateScheduleRequest createScheduleRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.createSchedule(createScheduleRequest));
    }
}
