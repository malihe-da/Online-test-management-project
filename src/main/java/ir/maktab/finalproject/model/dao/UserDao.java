package ir.maktab.finalproject.model.dao;

import ir.maktab.finalproject.model.entity.Course;
import ir.maktab.finalproject.model.entity.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface UserDao extends CrudRepository<User, Integer> {

    User save(User user);

    User findUserById(Integer id);
    List<User> findAll(Specification<User> maxMatch);
    Optional<User> findByEmailAddress(String email);
    User findByEmailAddressIgnoreCase(String emailId);
    Optional<User> findById(Integer id);
    Optional<User> findByEmailAddressAndPassword(String email, String password);

    List<User> findAll();
    List<User> findAllByCourses(Course courses);

    @Modifying
    @Query("update User set userRole=:newRole where id=:id")
    void updateUserRole(@Param("id") Integer id, @Param("newRole") String userRole);

    @Modifying
    @Query("update User set status=:newStatus where id=:id")
    void updateUserStatus(@Param("id") Integer id, @Param("newStatus") String status);
}
