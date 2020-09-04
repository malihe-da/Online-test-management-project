package ir.maktab.finalproject.model.dao;

import ir.maktab.finalproject.model.entity.QuestionsBank;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@org.springframework.stereotype.Repository
public interface QuestionBankDao extends CrudRepository<QuestionsBank, Integer> {

    List<QuestionsBank> findAll();
    List<QuestionsBank> findAllByQuestionClassification(String classification);
}
