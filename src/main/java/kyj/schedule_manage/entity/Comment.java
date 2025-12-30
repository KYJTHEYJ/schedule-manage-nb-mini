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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Schedule schedule;
    @Column(nullable = false, length = 300)
    private String content;
    @Column(nullable = false)
    private String author;
    @Column(nullable = false)
    private String pwd;

    public Comment(Schedule schedule, String content, String author, String pwd) {
        this.schedule = schedule;
        this.content = content;
        this.author = author;
        this.pwd = pwd;
    }
}
