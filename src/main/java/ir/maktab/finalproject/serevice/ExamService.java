package ir.maktab.finalproject.serevice;

import ir.maktab.finalproject.model.dao.ExamDao;
import ir.maktab.finalproject.model.entity.Course;
import ir.maktab.finalproject.model.entity.Exam;
import ir.maktab.finalproject.model.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service

public class ExamService {
    ExamDao examDao;

    @Autowired
    public ExamService(ExamDao examDao) {
        this.examDao = examDao;
    }

    public List<Exam> getATeachersExam(Integer teacherId) {
        return examDao.findAll(ExamDao.findCourseMaxMatch(0, null, null, teacherId));
    }

    public void saveExam(Exam exam) {
        long duration= (exam.getExamEnd().getTime()-exam.getExamStart().getTime());
        exam.setDuration((int) duration/60000);
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

    public List<Exam> getAllExamOfACourse(Course course) {
        return examDao.findAllByCourse(course);
    }


    public void addQuestionToExam(Question question, Exam exam) {
        List<Question> questionList = exam.getQuestions();
        if (!questionList.contains(question)) {
            questionList.add(question);
            exam.setQuestions(questionList);
            examDao.save(exam);
        }
    }

    public void addQuestionListToExam(List<Question> questions, Integer examId) {
        Exam exam = examDao.getExamById(examId);
        List<Question> questionList = exam.getQuestions();
        for (Question question :
                questions) {
            if (!questionList.contains(question))
                questionList.add(question);
        }
        exam.setQuestions(questionList);
        examDao.save(exam);
    }

    public List<Exam> getAllExam() {
        return examDao.findAll();
    }

    public Exam getExamById(Integer id) {
        return examDao.getExamById(id);
    }
}
