package ir.maktab.finalproject.service;

import ir.maktab.finalproject.model.dao.QuestionDao;
import ir.maktab.finalproject.model.entity.Question;
import ir.maktab.finalproject.model.enums.Type;
import org.springframework.stereotype.Service;

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

    public boolean fixQuestionAnswerKey(Question question) {
        if(question.getType().equals("multipleChoice")){
            List<String> answerList = question.getAnswerOptions();
            String answerKey = question.getKeyAnswer().toLowerCase();
            if (answerKey.matches("[0-9]+")){
                int index= Integer.parseInt(answerKey)-1;
                if(index<question.getAnswerOptions().size()) {
                    question.setKeyAnswer(answerList.get(index));
                    return true;
                }
                else
                    return false;
            }
            if(answerKey.length()==1){
                char k=answerKey.charAt(0);
                int index= (int)k-(int)'a';
                if (answerKey.matches("[0-9]+")) {
                    question.setKeyAnswer(answerList.get(index));
                    return true;
                }
                else
                    return false;
            }
        }
        return false;
    }

    public Question getQuestionById(Integer id){
        return questionDao.getQuestionById(id);
    }

    public Question getQuestionByQuestionFace(String face, String classifications){
        return questionDao.getFirstQuestionByQuestionFaceAndAndQuestionClassification(face, classifications);
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

    public int giveNextDescriptiveQuestion(int num, List<Question> questions) {

        for(int i=num; i<questions.size(); i++){
            if(questions.get(i).getType().equals(Type.descriptive.name()))
                return i;
        }

        return -1;
    }

    public int givePreviousDescriptiveQuestion(int num, List<Question> questions) {
        for(int i=num-1; i>=0; i--){
            if(questions.get(i).getType().equals(Type.descriptive.name()))
                return i;
        }

        return -1;
    }

    public boolean teacherScoringValidate(String grade, Double aDouble) {
        if (grade == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(grade);
            if (d>aDouble)
                return false;
        } catch (NumberFormatException nfe) {
            return false;
        }

        return true;
    }

}
