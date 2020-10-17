package ir.maktab.finalproject.service;

import ir.maktab.finalproject.model.dao.QuestionBankDao;
import ir.maktab.finalproject.model.dao.QuestionDao;
import ir.maktab.finalproject.model.entity.Question;
import ir.maktab.finalproject.model.entity.QuestionsBank;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class QuestionBankService {

    QuestionBankDao questionBankDao;
    QuestionDao questionDao;
    List<Question> questionList;

    public QuestionBankService(QuestionBankDao questionBankDao, QuestionDao questionDao) {
        this.questionBankDao = questionBankDao;
        this.questionDao = questionDao;
    }

    public void addQuestionIdToBank(Question question){
        QuestionsBank questionsBank = new QuestionsBank(question.getId(), question.getQuestionClassification());
        questionBankDao.save(questionsBank);
    }

    public List<Question> getQuestionsInBank(){
         questionList = new ArrayList<>();
        List<Question> questions = questionDao.findAll();
        List<QuestionsBank> questionsBanks= questionBankDao.findAll();
        for (QuestionsBank qb:
             questionsBanks) {
            int id= qb.getQuestionId();
            for (Question question:
                questions ) {
                if(question.getId()==id){
                    questionList.add(question);
                }
            }
        }
        return questionList;
    }

    public List<String> getClassifiedQuestionsInBank( String classification){
        List<String> questionFaceList = new ArrayList<>();
        List<Question> questions = questionDao.findAll(QuestionDao.findQuestionMaxMatch(0, null, null,
                classification, null, null));
        List<QuestionsBank> questionsBanks= questionBankDao.findAllByQuestionClassification(classification);
        for (QuestionsBank qb:
             questionsBanks) {
            int id= qb.getQuestionId();
            for (Question question:
                questions ) {
                if(question.getId()==id){
                    questionFaceList.add(question.getQuestionFace());
                }
            }
        }
        return questionFaceList;
    }

}
