package ir.maktab.finalproject.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String examTitle;
    String examDescription;
    Integer examAuthorId;
    String  examAuthorName;
    String examAuthorFamily;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm")
    Date examStart;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm")
    Date examEnd;
    int duration;
    @OneToMany(cascade = CascadeType.ALL)
    List<Question> questions;
    @ManyToOne
    @JoinColumn(name = "course")
    Course course;

    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", examTitle='" + examTitle + '\'' +
                ", examDescription='" + examDescription + '\'' +
                ", examAuthorId=" + examAuthorId +
                ", examAuthorName='" + examAuthorName + '\'' +
                ", examAuthorFamily='" + examAuthorFamily + '\'' +
                ", examStart=" + examStart +
                ", examEnd=" + examEnd +
                ", duration=" + duration +
                ", questions=" + questions +
                ", course=" + course +
                '}';
    }
}
