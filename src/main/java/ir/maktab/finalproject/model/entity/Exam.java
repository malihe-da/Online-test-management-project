package ir.maktab.finalproject.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.*;

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
    String examClassification;
    Integer examAuthorId;
    String examAuthorName;
    String examAuthorFamily;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    Date examStart;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    Date examEnd;
    int duration;
    @ManyToMany(cascade = CascadeType.ALL)
    List<Question> questions = new ArrayList<>();
    String examCourseTitle;
    Double examMAxScore;
    @ElementCollection
    List<Double> questionScores;
    @Transient
    int questionSize;

    public void addQuestion(Question question){
        if(!this.questions.contains(question)) {
            this.questions.add(question);
        }
        question.setExamId(this.id);
    }

    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", examTitle='" + examTitle + '\'' +
                ", examDescription='" + examDescription + '\'' +
                ", examClassification='" + examClassification + '\'' +
                ", examAuthorId=" + examAuthorId +
                ", examAuthorName='" + examAuthorName + '\'' +
                ", examAuthorFamily='" + examAuthorFamily + '\'' +
                ", examStart=" + examStart +
                ", examEnd=" + examEnd +
                ", duration=" + duration +
                ", examCourseTitle='" + examCourseTitle + '\'' +
                ", examMAxScore=" + examMAxScore +
                '}';
    }
}
