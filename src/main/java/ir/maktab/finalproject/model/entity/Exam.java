package ir.maktab.finalproject.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ir.maktab.finalproject.model.enums.Status;
import ir.maktab.finalproject.model.enums.Type;
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
    Status status;
    @ManyToOne
    User teacher;
    @ManyToOne
    Course course;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    Date startDate;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    Date endDate;
    int duration;
    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL)
    @JsonIgnore
    List<AnswerSheet> answerSheetList;
    @ManyToMany(cascade = CascadeType.ALL)
    List<Question> questions = new ArrayList<>();
    String examCourseTitle;
    Double examMaxScore;
    @ElementCollection
    @MapKeyJoinColumn(name = "question_id")
    Map<Question, Double> questionScoresMap = new HashMap<>();
    Type examType;

    @Transient
    List<Double> questionScores;
    @Transient
    int questionCount;
    @Transient
    int listQueueNumber;
    @Transient
    private Date start;
    @Transient
    private Date finish;

    @Transient
    private Map<String, String> questionScoreAssign;

    public void addQuestion(Question question) {
        if (!this.questions.contains(question)) {
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
                ", user=" + teacher +
                ", duration=" + duration +
                ", questions=" + questions +
                ", examCourseTitle='" + examCourseTitle + '\'' +
                ", examMAxScore=" + examMaxScore +
                ", questionScoresMap=" + questionScoresMap +
                ", questionScores=" + questionScores +
                ", questionCount=" + questionCount +
                ", start=" + start +
                ", finish=" + finish +
                ", questionScoreAssign=" + questionScoreAssign +
                '}';
    }
}
