package ir.maktab.finalproject.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    String family;
    @JsonIgnore
    String password;
    String userRole;
    String emailAddress;
    String status;
    boolean isEnabled;
    /*@JsonManagedReference*/
    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    @JsonIgnore
    List<Course> courses = new ArrayList<>();
    @ElementCollection(fetch = FetchType.EAGER)
    @MapKeyJoinColumn(name = "exam_id")
    @JsonIgnore
    Map<Exam, Double> finalExamScore;
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    @JsonIgnore
    List<Exam> teacherRegisteredExams;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    @JsonIgnore
    List<AnswerSheet> answerSheetList;
    @Transient
    @JsonIgnore
    List<String> userAnswerSheet;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", family='" + family + '\'' +
                ", userRole='" + userRole + '\'' +
                ", courses=" + courses +
                ", userAnswerSheet=" + userAnswerSheet +
                '}';
    }
}
