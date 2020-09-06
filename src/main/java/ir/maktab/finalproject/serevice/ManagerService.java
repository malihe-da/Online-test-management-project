package ir.maktab.finalproject.serevice;

import ir.maktab.finalproject.model.dao.CourseDao;
import ir.maktab.finalproject.model.dao.ManagerDao;
import ir.maktab.finalproject.model.dao.UserDao;
import ir.maktab.finalproject.model.entity.Course;
import ir.maktab.finalproject.model.entity.Manager;
import ir.maktab.finalproject.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManagerService {

    UserDao userDao;
    CourseDao courseDao;
    ManagerDao managerDao;


    public Manager checkOldManager(String email, String password){
        Optional<Manager> found = managerDao.findByEmailAddressAndPassword(email, password);
        if (found.isPresent()) {
            return found.get();
        }

        return null;
    }


    public List<User> getAllUserInOneCourse(Integer courseId){
        Course course= courseDao.getCourseById(courseId);
        return course.getUsers();
    }

    public void addUserToCourse(User user, Course course){
        List<Course> courses = user.getCourses();
        if(!courses.contains(course)) {
            courses.add(course);
            user.setCourses(courses);
            userDao.save(user);
        }
            List<User> userList = course.getUsers();
        if(!userList.contains(user)){
            userList.add(user);
            course.setUsers(userList);
            courseDao.save(course);
        }
    }



    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setCourseDao(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Autowired
    public void setManagerDao(ManagerDao managerDao) {
        this.managerDao = managerDao;
    }
}
