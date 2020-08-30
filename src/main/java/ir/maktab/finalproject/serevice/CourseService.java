package ir.maktab.finalproject.serevice;


import ir.maktab.finalproject.model.dao.CourseDao;
import ir.maktab.finalproject.model.dao.UserDao;
import ir.maktab.finalproject.model.entity.Course;
import ir.maktab.finalproject.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Transient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CourseService {

    CourseDao courseDao;
    UserDao userDao;
@Autowired
    public CourseService(CourseDao courseDao, UserDao userDao) {
        this.courseDao = courseDao;
        this.userDao = userDao;
    }

    public void addNewCourse(Course course){
    courseDao.save(course);
    }
    public List<Course>  findCourseMaxMatch(Integer id,
                                            String courseTitle,
                                            String courseClassification){

        return courseDao.findAll(CourseDao.findCourseMaxMatch(id, courseTitle, courseClassification));
    }

    public List<Course> getAllCourses(){
        return courseDao.findAll();
    }

    public Course getCoursesById(Integer id){
        return courseDao.getCourseById(id);
    }

    public Course getCoursesByTitle(String title){
        Optional<Course> found = courseDao.getCourseByCourseTitle(title);
        if(found.isPresent()){
            return found.get();
        }
        return null;
    }

    public void updateCourseTitle(Course course, String newTitle) {

        courseDao.updateTitle(course.getId(), newTitle);
    }

    public boolean checkCourseTitleIsUnique(String title){
        Optional<Course> found = courseDao.getCourseByCourseTitle(title);
        return (!found.isPresent());
    }

    public Course getCourseByClassification(String classification) {
        return courseDao.getCourseByCourseClassification(classification);
    }

    public void deleteCourse(Course course){
        List<User> userList = course.getUsers();
        for (User user:
                userList) {
            List<Course> courseList = user.getCourses();
            courseList.remove(course);
            user.setCourses(courseList);
            userDao.save(user);
        }
        courseDao.deleteById(course.getId());
    }

}
