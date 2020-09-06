package ir.maktab.finalproject.serevice;

import ir.maktab.finalproject.model.dao.CourseDao;
import ir.maktab.finalproject.model.dao.ExamDao;
import ir.maktab.finalproject.model.dao.QuestionDao;
import ir.maktab.finalproject.model.entity.Course;
import ir.maktab.finalproject.model.entity.Exam;
import ir.maktab.finalproject.model.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class ExamService {
    ExamDao examDao;
    CourseDao courseDao;
    QuestionDao questionDao;
    List<Question> questionList = new ArrayList<>();

    @Autowired
    public ExamService(ExamDao examDao, CourseDao courseDao, QuestionDao questionDao) {
        this.examDao = examDao;
        this.courseDao = courseDao;
        this.questionDao = questionDao;
    }

    public List<Exam> getATeachersExam(Integer teacherId) {
        return examDao.findAll(ExamDao.findCourseMaxMatch(0, null, null, null, teacherId));
    }

    public List<Exam> getExamsByClassification(String classification) {
        return examDao.findAll(ExamDao.findCourseMaxMatch(0, null, classification, null, null));
    }

    public void saveExam(Exam exam) {
        String courseTitle = exam.getExamCourseTitle();
        Optional<Course> found = courseDao.getCourseByCourseTitle(courseTitle);
        if (found.isPresent()) {
            Course course = found.get();
            List<Exam> examList = course.getExams();
            examList.add(exam);
            courseDao.save(course);
            exam.setExamClassification(course.getCourseClassification());
        }
        long duration = (exam.getExamEnd().getTime() - exam.getExamStart().getTime());
        exam.setDuration((int) duration / 60000);
        examDao.save(exam);
    }

    public void addQuestionToExam(Question question, Integer examId) {
        Exam exam = examDao.getExamById(examId);
        List<Question> questionList = exam.getQuestions();
        if (!questionList.contains(question)) {
            questionList.add(question);
            exam.setQuestions(questionList);
        }
        examDao.save(exam);
    }


    public void addQuestionToExam(Question question, Exam exam) {
       exam.addQuestion(question);
       examDao.save(exam);
    }

    public Exam saveQuestionsScore(Exam exam, List<Double> questionsScore){
        List<Double> questionScoreList = new ArrayList<>();
        questionList= exam.getQuestions();
        Double maxScore=0.0;

        for(int i=0; i<exam.getQuestions().size(); i++){
            Double score = questionsScore.get(i);
            maxScore = maxScore+score;
            questionScoreList.add(score);
            Question question= questionList.get(i);
            question.setQuestionScore(score);
        }
        exam.setExamMAxScore(maxScore);
        exam.setQuestionScores(questionScoreList);
        examDao.save(exam);
        exam.setQuestionSize(questionScoreList.size());
        return exam;
    }

    public List<Exam> getAllExam() {
        return examDao.findAll();
    }

    public Exam getExamById(Integer id) {
        return examDao.getExamById(id);
    }
}
