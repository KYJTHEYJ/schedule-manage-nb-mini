package kyj.schedule_manage.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "schedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, length = 90)
    private String title;
    @Column(nullable = false, length = 600)
    private String content;
    @Column(nullable = false)
    private String author;
    @Column(nullable = false)
    private String pwd;
    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

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
