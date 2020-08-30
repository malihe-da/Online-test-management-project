package ir.maktab.finalproject.serevice;

import ir.maktab.finalproject.model.dao.QuestionBankDao;
import ir.maktab.finalproject.model.dao.QuestionDao;
import ir.maktab.finalproject.model.entity.Question;
import ir.maktab.finalproject.model.entity.QuestionsBank;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionBankService {

    QuestionBankDao questionBankDao;
    QuestionDao questionDao;

    public QuestionBankService(QuestionBankDao questionBankDao, QuestionDao questionDao) {
        this.questionBankDao = questionBankDao;
        this.questionDao = questionDao;
    }

    public void addQuestionIdToBank(Integer questionId){
        QuestionsBank questionsBank = new QuestionsBank(questionId);
        questionBankDao.save(questionsBank);
    }

    public List<Question> getQuestionsInBank(){
        List<Question> questionList = new ArrayList<>();
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

}
