package ir.maktab.finalproject.service;

import ir.maktab.finalproject.model.dao.AnswerSheetDao;
import ir.maktab.finalproject.model.entity.AnswerSheet;
import ir.maktab.finalproject.model.entity.Exam;
import ir.maktab.finalproject.model.entity.Question;
import ir.maktab.finalproject.model.entity.User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AnswerSheetService {

    AnswerSheetDao answerSheetDao;
    UserService userService;
    ExamService examService;

    public AnswerSheetService(AnswerSheetDao answerSheetDao, UserService userService, ExamService examService) {
        this.answerSheetDao = answerSheetDao;
        this.userService = userService;
        this.examService = examService;
    }

    public AnswerSheet getAnswerSheetById(int id) {
        return answerSheetDao.getById(id);
    }

    public List<AnswerSheet> getAllAnswerSheetOfExam(Exam exam) {
        return answerSheetDao.getAnswerSheetByExam(exam);
    }


    public List<AnswerSheet> selectUncorrectedAnswerSheet(Exam exam) {
        List<AnswerSheet> answerSheets = getAllAnswerSheetOfExam(exam);
        System.out.println(answerSheets.toString());
        List<AnswerSheet> uncorrected = new ArrayList<>();
        for (AnswerSheet ans :
                answerSheets) {

            if (ans.getUserQuestionScore().size()<exam.getQuestions().size())
                uncorrected.add(ans);
        }
        return uncorrected;
    }

    public AnswerSheet saveAnswerSheet(AnswerSheet answerSheet) {
        answerSheet.setUser(userService.getUserById(answerSheet.getUserId()));
        answerSheet.setExam(examService.getExamById(answerSheet.getExamId()));
        return answerSheetDao.save(answerSheet);
    }

    public void putNewAnswerInList(AnswerSheet answerSheet, Question question, String answer) {
        answerSheet.addAnswerSheet(question, answer);
        answerSheetDao.save(answerSheet);
    }

    public void setUserAndExamIdForAnswerSheet(AnswerSheet answerSheet) {
        answerSheet.setUserId(answerSheet.getUser().getId());
        answerSheet.setExamId(answerSheet.getExam().getId());
    }

    public boolean isFirstTime(User user, Exam exam) {
        Optional<AnswerSheet> found = answerSheetDao.getAnswerSheetByUserAndExam(user, exam);
        return (found.isPresent());
    }



    public AnswerSheet concludeStudentExamScore(AnswerSheet answerSheet) {
        Map<Question, String> userAnswerSheet = answerSheet.getUserAnswerSheet();
        Map<Question, Double> userQuestionScore = new HashMap<>();
        Map<Question, Double> questionScoresMap = answerSheet.getExam().getQuestionScoresMap();
        int correctCounter = 0;

        Double totalScore = 0.0;
        for (Question question :
                userAnswerSheet.keySet()) {
            if (userAnswerSheet.get(question) != null) {

                if (question.getType().equals("multipleChoice")) {
                    if (question.getKeyAnswer().equals(userAnswerSheet.get(question))) {
                        userQuestionScore.put(question, questionScoresMap.get(question));
                        totalScore += questionScoresMap.get(question);
                        correctCounter += 1;
                    } else {
                        userQuestionScore.put(question, 0.0);
                    }
                } else {
                    userQuestionScore.put(question, null);
                }
            } else {
                userQuestionScore.put(question, 0.0);
            }
        }
        answerSheet.setTotalExamScore(totalScore);
        answerSheet.setUserQuestionScore(userQuestionScore);
        answerSheet.setCorrectCounter(correctCounter);
        userService.saveExamScoreToMap(answerSheet);
        answerSheetDao.save(answerSheet);
        return answerSheet;
    }


    public void putAnswerGradeInList(AnswerSheet answerSheet, Question question, Double grade) {
        answerSheet.addAnswerGrade(question, grade);
        answerSheetDao.save(answerSheet);
    }

    public void concludeTotalTeacherScoring(AnswerSheet answerSheet) {
        Map<Question, Double> userQuestionScore = answerSheet.getUserQuestionScore();
        Double totalScore = 0.0;
        for (Question question :
                userQuestionScore.keySet()) {
            totalScore += userQuestionScore.get(question);
        }
        answerSheet.setTotalExamScore(totalScore);
        answerSheetDao.save(answerSheet);
    }
}
