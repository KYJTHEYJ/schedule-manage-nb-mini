package kyj.schedule_manage;

import kyj.schedule_manage.controller.ScheduleController;
import kyj.schedule_manage.dto.GetScheduleResponse;
import kyj.schedule_manage.service.CommentService;
import kyj.schedule_manage.service.ScheduleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest(ScheduleController.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ScheduleService scheduleService;

    @MockitoBean
    private CommentService commentService;

    @Test
    @DisplayName("GET /schedules/{id} - 일정 조회 성공 테스트")
    void getSchedule_Success() throws Exception {
        long testId = 1L;
        LocalDateTime now = LocalDateTime.now();

        GetScheduleResponse mockResponse = new GetScheduleResponse(
                testId,
                "테스트 제목",
                "테스트 내용",
                "작성자",
                now,
                now,
                new ArrayList<>()
        );

        given(scheduleService.getSchedule(testId))
            .willReturn(mockResponse);

        mockMvc.perform(get("/schedules/{id}", testId))
                .andDo(print())  // 요청/응답 정보 출력
                .andExpect(status().isOk())  // HTTP 200 확인
                .andExpect(jsonPath("$.id").value(testId))  // JSON의 id 필드 확인
                .andExpect(jsonPath("$.title").value("테스트 제목"))
                .andExpect(jsonPath("$.content").value("테스트 내용"))
                .andExpect(jsonPath("$.author").value("작성자"));
    }
}
