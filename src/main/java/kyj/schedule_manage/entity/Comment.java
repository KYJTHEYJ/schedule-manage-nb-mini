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
    private long id;
    @Column(nullable = false)
    private long scheduleId;
    @Column(nullable = false, length = 300)
    private String content;
    @Column(nullable = false)
    private String author;
    @Column(nullable = false)
    private String pwd;

    public Comment(Long scheduleId, String content, String author, String pwd) {
        this.scheduleId = scheduleId;
        this.content = content;
        this.author = author;
        this.pwd = pwd;
    }
}
