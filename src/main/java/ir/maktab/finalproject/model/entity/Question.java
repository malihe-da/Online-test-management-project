package ir.maktab.finalproject.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String type;
    String questionTitle;
    String questionDescription;
    Integer authorId;
    String questionFace;
    String keyAnswer;
    int examId;
    @ElementCollection
    List<String> answerOptions;
    @Transient
    String questionBankAdded;


    @Override
    public String toString() {
        String answerList="";
        for(int i=0; i< answerOptions.size(); i++) {
            answerList= answerList + " " + i + ") " + answerOptions.get(i);
        }
        return "Question{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", questionTitle='" + questionTitle + '\'' +
                ", questionDescription='" + questionDescription + '\'' +
                ", authorId=" + authorId +
                ", examId= " + examId+
                ", questionFace='" + questionFace + '\'' +
                ", keyAnswer='" + keyAnswer + '\'' +
                ", answerOptions=" + answerList +
                '}';
    }
}
