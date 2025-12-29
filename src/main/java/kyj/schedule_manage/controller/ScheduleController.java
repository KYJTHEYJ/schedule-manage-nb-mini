package kyj.schedule_manage.controller;

import kyj.schedule_manage.dto.*;
import kyj.schedule_manage.service.CommentService;
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
    private final CommentService commentService;

    //region 일정 관련
    @PostMapping("/schedules")
    public ResponseEntity<CreateScheduleResponse> createSchedule(@RequestBody CreateScheduleRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.createSchedule(request));
    }

    @GetMapping("/schedules/{id}")
    public ResponseEntity<GetScheduleResponse> getSchedule(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.getSchedule(id));
    }

    @GetMapping("/schedules")
    public ResponseEntity<List<GetScheduleResponse>> getAllSchedule(@RequestParam(required = false) String author) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.getAllSchedule(author));
    }

    // 수정 부는 record 클래스 사용
    @PutMapping("/schedules/{id}")
    public ResponseEntity<UpdateScheduleResponse> updateSchedule(@PathVariable Long id, @RequestBody UpdateScheduleRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.updateSchedule(id, request));
    }

    @PatchMapping("/schedules/{id}")
    public ResponseEntity<UpdateScheduleResponse> updatePortionSchedule(@PathVariable Long id, @RequestBody UpdateScheduleRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.patchSchedule(id, request));
    }

    // 삭제 부는 record 클래스 사용
    @DeleteMapping("/schedules/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id, @RequestBody DeleteScheduleRequest request) {
        scheduleService.deleteSchedule(id, request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    //endregion

    //region 댓글 관련
    @PostMapping("/schedules/{id}/comments")
    public ResponseEntity<CreateCommentResponse> createComment(@PathVariable Long id, @RequestBody CreateCommentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.createComment(id, request));
    }
    //endregion
}
