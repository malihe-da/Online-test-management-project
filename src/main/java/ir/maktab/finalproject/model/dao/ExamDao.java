package ir.maktab.finalproject.model.dao;

import ir.maktab.finalproject.model.entity.Exam;
import ir.maktab.finalproject.model.entity.Question;
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

@org.springframework.stereotype.Repository
public interface ExamDao extends CrudRepository<Exam, Integer> {

    Exam getExamById(Integer id);

    List<Exam> findAll();
    List<Exam> findAll(Specification<Exam> courseMaxMatch);

    @Modifying
    @Query("update Exam set questions=:questions where id=:id")
    void updateQuestionList(@Param("id") Integer id, @Param("questions") List<Question> questions);

    static Specification<Exam> findCourseMaxMatch(Integer id,
                                                  String examTitle,
                                                  String examClassification,
                                                  String examDescription,
                                                  Integer authorId) {
        return (Specification<Exam>) (root, criteriaQuery, builder) -> {
            CriteriaQuery<Exam> resultCriteria = builder.createQuery(Exam.class);

            List<Predicate> predicates = new ArrayList<>();
            if (id != null && id > 0) {
                predicates.add(builder.equal(root.get("id"), id));
            }
            if (!StringUtils.isEmpty(examTitle) && examTitle != null) {
                predicates.add(builder.equal(root.get("examTitle"), examTitle));
            }
            if (!StringUtils.isEmpty(examClassification) && examClassification != null && !examClassification.equals("NONE")) {
                predicates.add(builder.in(root.get("examClassification")).value(examClassification));
            }
            if (!StringUtils.isEmpty(examDescription) && examDescription != null) {
                predicates.add(builder.in(root.get("examDescription")).value(examDescription));
            }
            if (authorId != null && authorId > 0) {
                predicates.add(builder.equal(root.get("authorId"), authorId));
            }
            resultCriteria.select(root).where(predicates.toArray(new Predicate[0]));
            return resultCriteria.getRestriction();
        };
    }

}
