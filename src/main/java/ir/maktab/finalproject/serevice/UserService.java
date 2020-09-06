package ir.maktab.finalproject.serevice;


import ir.maktab.finalproject.model.dao.UserDao;
import ir.maktab.finalproject.model.dao.UserSpecifications;
import ir.maktab.finalproject.model.entity.Course;
import ir.maktab.finalproject.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class UserService {
    UserDao userDao;


    public void userRegister(User user) {
        userDao.save(user);
    }

    public User checkOldUser(String email, String password) {
        Optional<User> found = userDao.findByEmailAddressAndPassword(email, password);
        if (found.isPresent()) {
            return found.get();
        }

        return null;
    }

    public List<User> getAllUserByTest() {

        return userDao.findAll();
    }

    public void changeUserRole(Integer id, String newRole) {
        userDao.updateUserRole(id, newRole);
    }

    public void changeUserStatus(Integer id, String newStatus) {
        userDao.updateUserStatus(id, newStatus);
    }
    public User getUserByEmailAddress(String email){
        return userDao.findByEmailAddressIgnoreCase(email);
    }
    public User getUserById(Integer id){
        Optional<User> found= userDao.findById(id);
        if(found.isPresent()){
            return found.get();
        }
        return null;
    }

    public List<User> findMaxMatch(Integer id,
                                            String userRole,
                                            String name,
                                            String family,
                                            String status) {

        return userDao.findAll(UserSpecifications.findMaxMatch(id, userRole, name, family, status));
    }

    public List<User> getAllUserInCourses(List<Course> courses) {
        List<User> userList = new ArrayList<>();
        for (Course course:
             courses) {
            userList.addAll(userDao.findAllByCourses(course));
        }
        return userList;
    }
    public List<User> getAllStudents() {
        //return userDao.findAllByUserRole("student");
        return userDao.findAll(UserSpecifications.findMaxMatch(null,"student", null,
                null, null));
    }

    public List<User> getAllTeachers() {
        //return userDao.findAllByUserRole("teacher");
        return userDao.findAll(UserSpecifications.findMaxMatch(null,"teacher", null,
                null, null));
    }

    public boolean isValidPassword(String password) {
        if (password.length() < 8)
            return false;
        boolean containDigit = false;
        boolean containAlpha = false;

        for (int i = 0; i < password.length(); i++) {
            if (Character.isDigit(password.charAt(i)))
                containDigit = true;
            if (Character.isAlphabetic(password.charAt(i)))
                containAlpha = true;

            if (containAlpha && containDigit)
                return true;
        }
        return false;
    }

    public boolean duplicateMailAddress(String mail) {
        Optional<User> found = userDao.findByEmailAddress(mail);
        if (found.isPresent())
            return true;

        return false;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
