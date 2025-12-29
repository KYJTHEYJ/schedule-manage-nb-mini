package kyj.schedule_manage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ScheduleManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScheduleManageApplication.class, args);
    }

}
