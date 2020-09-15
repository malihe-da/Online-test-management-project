package ir.maktab.finalproject.model.dao;

import ir.maktab.finalproject.model.entity.AnswerSheet;
import ir.maktab.finalproject.model.entity.AnswerSheet_;
import ir.maktab.finalproject.model.entity.Exam;
import ir.maktab.finalproject.model.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface AnswerSheetDao extends CrudRepository<AnswerSheet, Integer> {
    AnswerSheet getById(Integer id);

    AnswerSheet save(AnswerSheet answerSheet);

    Optional<AnswerSheet> getAnswerSheetByUserAndExam(User user, Exam exam);

    List<AnswerSheet> getAnswerSheetByExam(Exam exam);
}
