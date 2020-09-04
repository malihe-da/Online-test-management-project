package ir.maktab.finalproject.serevice;

import ir.maktab.finalproject.model.dao.QuestionDao;
import ir.maktab.finalproject.model.entity.Question;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class QuestionService {
    QuestionDao questionDao;

    public QuestionService(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    public List<Question> getAllQuestions(){
        return questionDao.findAll();
    }

    public void saveNewQuestion (Question question){
        questionDao.save(question);
    }

    public Question getQuestionById(Integer id){
        return questionDao.getQuestionById(id);
    }

    public Question getQuestionByQuestionFace(String face){
        return questionDao.getQuestionByQuestionFace(face);
    }

    public List<Question> getAllTeacherDescriptiveQuestions(Integer authorId){
        return questionDao
                .findAll(QuestionDao.findQuestionMaxMatch(0, "descriptive", null,
                        null, authorId, null));

    }
    public List<Question> getAllTeacherMultipleChoiceQuestions(Integer authorId){
        return questionDao
                .findAll(QuestionDao.findQuestionMaxMatch(0, "multipleChoice", null,
                        null, authorId, null));

    }
    public List<Question> getAllTeacherQuestions(Integer authorId){
        return questionDao
                .findAll(QuestionDao.findQuestionMaxMatch(0, null, null,
                        null, authorId, null));

    }
}
