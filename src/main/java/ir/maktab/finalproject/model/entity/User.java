package ir.maktab.finalproject.model.entity;

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
    String password;
    String userRole;
    String emailAddress;
    String status;
    boolean isEnabled;
    @JsonManagedReference
    @ManyToMany(mappedBy = "users")
    List<Course> courses = new ArrayList<>();
    @ElementCollection
    @MapKeyJoinColumn(name = "exam_id")
    Map<Exam, Double> finalExamScore;
    @OneToMany(mappedBy = "teacher", cascade=CascadeType.ALL)
    List<Exam> teacherRegisteredExams;
    @Transient
    List<String > userAnswerSheet;


    @Override
    public String toString() {
        String courseList="";
        for (Course cr:
             courses) {
            courseList = courseList + cr.getCourseTitle() + " ";
        }
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", family='" + family + '\'' +
                ", userRole='" + userRole + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", status='" + status + '\'' +
                ", isEnabled=" + isEnabled +
                ", courses=" + courseList +
                '}';
    }
}
