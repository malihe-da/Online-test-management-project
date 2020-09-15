package ir.maktab.finalproject.model.dao;

import ir.maktab.finalproject.model.entity.Exam;
import ir.maktab.finalproject.model.entity.Question;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Repository
public interface QuestionDao extends CrudRepository<Question, Integer> {
    List<Question> findAll();

    Question getQuestionById(Integer id);
    Question getFirstQuestionByQuestionFaceAndAndQuestionClassification(String face, String classification);

    List<Question> findAll(Specification<Question> questionMaxMatch);

    static Specification<Question> findQuestionMaxMatch(Integer id,
                                                        String type,
                                                        String questionDescription,
                                                        String questionClassification,
                                                        Integer authorId,
                                                        String questionFace) {
        return (Specification<Question>) (root, criteriaQuery, builder) -> {
            CriteriaQuery<Question> resultCriteria = builder.createQuery(Question.class);

            List<Predicate> predicates = new ArrayList<>();
            if (id != null && id > 0) {
                predicates.add(builder.equal(root.get("id"), id));
            }
            if (!StringUtils.isEmpty(type) && type != null) {
                predicates.add(builder.equal(root.get("type"), type));
            }
            if (!StringUtils.isEmpty(questionDescription) && questionDescription != null) {
                predicates.add(builder.in(root.get("questionDescription")).value(questionDescription));
            }
            if (!StringUtils.isEmpty(questionClassification) && questionClassification != null) {
                predicates.add(builder.in(root.get("questionClassification")).value(questionClassification));
            }
            if (authorId != null && authorId > 0) {
                predicates.add(builder.equal(root.get("authorId"), authorId));
            }
            if (!StringUtils.isEmpty(questionFace) && questionFace != null) {
                predicates.add(builder.in(root.get("questionFace")).value(questionFace));
            }
            resultCriteria.select(root).where(predicates.toArray(new Predicate[0]));
            return resultCriteria.getRestriction();
        };
    }

}
