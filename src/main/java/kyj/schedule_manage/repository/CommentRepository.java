package kyj.schedule_manage.repository;

import kyj.schedule_manage.entity.Comment;
import kyj.schedule_manage.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByScheduleId(long scheduleId);
}
