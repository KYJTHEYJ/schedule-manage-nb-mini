package kyj.schedule_manage.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "comments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long scheduleId;
    private String content;
    private String author;
    private String pwd;

    public Comment(Long scheduleId, String content, String author, String pwd) {
        this.scheduleId = scheduleId;
        this.content = content;
        this.author = author;
        this.pwd = pwd;
    }

    public void update(String content) {
        this.content = content;
    }
}
