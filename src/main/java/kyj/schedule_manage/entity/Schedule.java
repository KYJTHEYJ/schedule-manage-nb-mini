package kyj.schedule_manage.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
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
}
