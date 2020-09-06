package ir.maktab.finalproject.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class QuestionsBank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    int questionId;
    String questionClassification;

    public QuestionsBank(int questionId, String questionClassification) {
        this.questionId = questionId;
        this.questionClassification = questionClassification;
    }
}
