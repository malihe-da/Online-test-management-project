package ir.maktab.finalproject.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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


    @Override
    public String toString() {
        String coursList="";
        for (Course cr:
             courses) {
            coursList = coursList + cr.getCourseTitle() + " ";
        }
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", family='" + family + '\'' +
                ", userRole='" + userRole + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", status='" + status + '\'' +
                ", isEnabled=" + isEnabled +
                ", courses=" + coursList +
                '}';
    }
}
