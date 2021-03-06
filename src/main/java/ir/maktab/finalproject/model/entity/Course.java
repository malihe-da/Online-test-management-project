package ir.maktab.finalproject.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.QueryHints;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String courseTitle;
    String courseClassification;
    @JsonBackReference
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Fetch(value = FetchMode.SUBSELECT)
    List<User> users = new ArrayList<>();
    @OneToMany(cascade=CascadeType.ALL)
    List<Exam> exams;


    @Override
    public String toString() {
        String userNames = "";
        for (User user:
             users) {
            userNames= userNames + user.getEmailAddress() + ", ";
        }
        return "Course{" +
                "id=" + id +
                ", courseTitle='" + courseTitle + '\'' +
                ", courseClassification='" + courseClassification + '\'' +
                ", users=" + userNames+
                '}';
    }
}
