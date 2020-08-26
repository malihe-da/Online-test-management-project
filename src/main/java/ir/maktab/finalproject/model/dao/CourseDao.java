package ir.maktab.finalproject.model.dao;


import ir.maktab.finalproject.model.entity.Course;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface CourseDao extends CrudRepository<Course, Integer> {

    Optional<Course> findCourseById(Integer id);


    List<Course> findAll();

    Course getCourseById(Integer id);
    Optional<Course> getCourseByCourseTitle(String title);

    @Modifying
    @Query("update Course set courseTitle=:newTitle where id=:id")
    void updateTitle(@Param("id") Integer id, @Param("newTitle") String courseTitle);

    @Modifying
    @Query("update Course set courseClassification=:newClassification where id=:id")
    void updateClassification(@Param("id") Integer id, @Param("newClassification") String courseClassification);

    List<Course> findAll(Specification<Course> courseMaxMatch);

    static Specification<Course> findCourseMaxMatch(Integer id,
                                                    String courseTitle,
                                                    String courseClassification) {
        return (Specification<Course>) (root, criteriaQuery, builder) -> {
            CriteriaQuery<Course> resultCriteria = builder.createQuery(Course.class);

            List<Predicate> predicates = new ArrayList<>();
            if (id != null && id > 0) {
                predicates.add(builder.equal(root.get("id"), id));
            }
            if (!StringUtils.isEmpty(courseTitle) && courseTitle != null) {
                predicates.add(builder.equal(root.get("courseTitle"), courseTitle));
            }
            if (!StringUtils.isEmpty(courseClassification) && courseClassification != null) {
                predicates.add(builder.in(root.get("courseClassification")).value(courseClassification));
            }

            resultCriteria.select(root).where(predicates.toArray(new Predicate[0]));
            return resultCriteria.getRestriction();
        };
    }


}
