package kyj.schedule_manage.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "schedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, length = 150)
    private String title;
    @Column(length = 600)
    private String content;
    @Column(nullable = false, length = 30)
    private String author;
    @Column(nullable = false, length = 30)
    private String pwd;

    public Schedule(String title, String content, String author, String pwd) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.pwd = pwd;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void patch(String title, String content) {
        if(title != null) {
            this.title = title;
        }

        if(content != null) {
            this.content = content;
        }
    }
}
