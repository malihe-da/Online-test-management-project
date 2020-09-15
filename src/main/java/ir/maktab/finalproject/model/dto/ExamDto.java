package ir.maktab.finalproject.model.dto;

import ir.maktab.finalproject.model.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExamDto {
    String examTitle;
    String examDescription;
    String examClassification;
    String examAuthorName;
    String examAuthorFamily;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    Date examStart;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    Date examEnd;
    int duration;
    List<Question> questions = new ArrayList<>();
    String examCourseTitle;
    Double examMAxScore;
    Map<Question, Double> questionScoresMap;
    List<Double> questionScores;
    int questionSize;
}
