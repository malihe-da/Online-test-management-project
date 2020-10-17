package ir.maktab.finalproject.model.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AnswerSheet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @ManyToOne(targetEntity=User.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private User student;
    private Double totalExamScore = 0.0;
    @ManyToOne(targetEntity=Exam.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "exam_id")
    private Exam exam;
    @ElementCollection
    @MapKeyJoinColumn(name = "question_id")
    Map<Question, String> userAnswerSheet = new HashMap<>();
    @ElementCollection
    @MapKeyJoinColumn(name = "question_id")
    Map<Question, Double> userQuestionScore = new HashMap<>();

    @Transient
    int studentId;
    @Transient
    int examId;
    @Transient
    String answer;
    @Transient
    int questionsCounter;
    @Transient
    int correctCounter=0;
    @Transient
    String grade;

    public AnswerSheet(int userId, int examId) {
        this.studentId = userId;
        this.examId = examId;
    }

    public void addAnswerSheet(Question question, String answer) {
        this.userAnswerSheet.put(question, answer);
    }

    public void addAnswerGrade(Question question, Double grade) {
        this.userQuestionScore.put(question, grade);
    }

    @Override
    public String toString() {
        return "AnswerSheet{" +
                "id=" + id +
                ", user=" + student +
                ", totalExamScore=" + totalExamScore +
                ", userAnswerSheet=" + userAnswerSheet +
                ", userQuestionScore=" + userQuestionScore +
                ", userId=" + studentId +
                ", examId=" + examId +
                ", answer='" + answer + '\'' +
                ", questionsCounter=" + questionsCounter +
                ", correctCounter=" + correctCounter +
                ", grade='" + grade + '\'' +
                '}';
    }
}
