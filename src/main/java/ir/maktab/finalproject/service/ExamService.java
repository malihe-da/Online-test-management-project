package ir.maktab.finalproject.service;

import ir.maktab.finalproject.model.dao.CourseDao;
import ir.maktab.finalproject.model.dao.ExamDao;
import ir.maktab.finalproject.model.dao.QuestionDao;
import ir.maktab.finalproject.model.entity.User;
import ir.maktab.finalproject.model.enums.Type;
import ir.maktab.finalproject.model.entity.Course;
import ir.maktab.finalproject.model.entity.Exam;
import ir.maktab.finalproject.model.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class ExamService {
    ExamDao examDao;
    CourseDao courseDao;
    QuestionDao questionDao;
    UserService userService;
    CourseService courseService;
    List<Question> questionList = new ArrayList<>();

    @Autowired
    public ExamService(ExamDao examDao) {
        this.examDao = examDao;
    }


    public List<Exam> getExamsByClassification(String classification) {
        List<Exam> examList = examDao.findAll(ExamDao.findCourseMaxMatch(0, null,
                classification, null, null, null));
        examList = determinedListQueue(examList);
        return examList;
    }

    public List<Exam> getAllExamsOfATeacher(User teacher) {
        List<Exam> examList = examDao.findAll(ExamDao.findCourseMaxMatch(0, null,
                null, null, teacher, null));
        examList = determinedListQueue(examList);
        return examList;
    }

    private List<Exam> determinedListQueue(List<Exam> examList) {
        for (int i = 0; i < examList.size(); i++) {
            Exam exam = examList.get(i);
            exam.setListQueueNumber(i + 1);
        }
        return examList;
    }

    public Exam getExamByTitle(String title) {
        return examDao.getExamByExamTitle(title);
    }

    public List<Exam> getAllDescriptiveExamOfTeacher(User teacher) {
        List<Exam> examList1 = examDao.findAll(ExamDao.findCourseMaxMatch(0, null,
                null, null, teacher, Type.descriptive));
        List<Exam> examList2 = examDao.findAll(ExamDao.findCourseMaxMatch(0, null,
                null, null, teacher, Type.mixed));
        List<Exam> examList = new ArrayList<>();
        if (!examList1.isEmpty()) {
            examList.addAll(examList1);
        }
        if (!examList1.isEmpty()) {
            examList.addAll(examList2);
        }
        examList = determinedListQueue(examList);
        return examList;
    }

    public void saveExam(Exam exam) {
        concludeDuration(exam);

        String courseTitle = exam.getExamCourseTitle();
        Optional<Course> found = courseDao.getCourseByCourseTitle(courseTitle);

        if (found.isPresent()) {
            Course course = found.get();
            exam.setExamClassification(course.getCourseClassification());
            exam.setCourse(course);
        }
        examDao.save(exam);
        Exam exam1 = examDao.getExamByExamTitle(exam.getExamTitle());
        courseService.addExamToList(exam1);
    }

    private void concludeDuration(Exam exam) {
        long duration = (exam.getEndDate().getTime() - exam.getStartDate().getTime());
        exam.setDuration((int) duration / 60000);
    }

    public void updateExam(Exam exam) {
        examDao.save(exam);
    }

    public void addQuestionToExam(Question question, Exam exam) {
        exam.addQuestion(question);
        examDao.save(exam);
    }

    public Exam preparedToDetermineScore(Exam exam) {
        List<Question> questionList = exam.getQuestions();
        Map<Question, Double> questionScoresMap = new HashMap<>();
        determineExamType(exam);
        for (Question question :
                questionList) {
            questionScoresMap.put(question, 0.0);
        }
        exam.setQuestionScoresMap(questionScoresMap);

        return exam;
    }

    private void determineExamType(Exam exam) {
        List<Question> questionList = exam.getQuestions();
        boolean isOptional = false;
        boolean isDescriptive = false;

        for (Question question :
                questionList) {
            if (question.getType().equals("descriptive")) {
                isDescriptive = true;
            }
            if (question.getType().equals("multipleChoice")) {
                isOptional = true;
            }
        }
        if (isDescriptive == true && isOptional == true) {
            exam.setExamType(Type.mixed);
            return;
        }
        if (isDescriptive == true) {
            exam.setExamType(Type.descriptive);
            return;
        }
        exam.setExamType(Type.multipleChoice);

    }

    public List<String> getExamTitlesFromList(List<Exam> examList) {
        /*List<String> examTitle = new ArrayList<>();
        for (Exam exam : examList) {
            examTitle.add(exam.getExamTitle());
        }
        return examTitle;
        */

        return examList.stream().map(Exam::getExamTitle).collect(Collectors.toList());

    }


    public Exam getExamById(Integer id) {
        return examDao.getExamById(id);
    }

    public Exam saveQuestionsScoreFromArray(Exam exam, String[] values) {
        questionList = exam.getQuestions();
        Double maxScore = 0.0;
        Map<Question, Double> questionScores = exam.getQuestionScoresMap();
        for (int i = 0; i < exam.getQuestions().size(); i++) {
            if(values.length>=i+1) {
                Double score = Double.parseDouble(values[i]);
                maxScore = maxScore + score;

                Question question = questionList.get(i);
                question.setQuestionScore(score);
                questionScores.put(question, score);
            }
        }
        exam.setExamMaxScore(maxScore);
        exam.setQuestionScoresMap(questionScores);
        examDao.save(exam);
        exam.setQuestionCount(questionScores.size());
        return exam;
    }

    @Autowired
    public void setCourseDao(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Autowired
    public void setQuestionDao(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }
}
