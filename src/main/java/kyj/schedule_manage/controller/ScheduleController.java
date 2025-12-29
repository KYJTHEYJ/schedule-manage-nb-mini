package kyj.schedule_manage.controller;

import kyj.schedule_manage.dto.CreateScheduleRequest;
import kyj.schedule_manage.dto.CreateScheduleResponse;
import kyj.schedule_manage.dto.GetScheduleResponse;
import kyj.schedule_manage.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping("/schedules")
    public ResponseEntity<CreateScheduleResponse> createSchedule(@RequestBody CreateScheduleRequest createScheduleRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.createSchedule(createScheduleRequest));
    }

    @GetMapping("/schedules/{id}")
    public ResponseEntity<GetScheduleResponse> getSchedule(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.getSchedule(id));
    }

    @GetMapping("/schedules")
    public ResponseEntity<List<GetScheduleResponse>> getAllSchedule() {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.getAllSchedule());
    }
}
