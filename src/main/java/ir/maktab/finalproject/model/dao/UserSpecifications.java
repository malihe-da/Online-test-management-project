package ir.maktab.finalproject.model.dao;

import ir.maktab.finalproject.model.entity.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public interface UserSpecifications {
    static Specification<User> findMaxMatch(Integer id,
                                               String userRole,
                                               String name,
                                               String family,
                                               String status ) {
        return (Specification<User>) (root, criteriaQuery, builder) -> {
            CriteriaQuery<User> resultCriteria = builder.createQuery(User.class);

            List<Predicate> predicates = new ArrayList<Predicate>();
            if (id!=null && id>0) {
                predicates.add(builder.equal(root.get("userRole"), userRole));
            }
            if (!StringUtils.isEmpty(userRole) && userRole != null) {
                predicates.add(builder.equal(root.get("userRole"), userRole));
            }
            if (!StringUtils.isEmpty(name) && name != null) {
                predicates.add(builder.equal(root.get("name"), name));
            }
            if (!StringUtils.isEmpty(family) && family != null) {
                predicates.add(builder.equal(root.get("family"), family));
            }
            if (!StringUtils.isEmpty(status) && status != null) {
                predicates.add(builder.equal(root.get("status"), status));
            }

            resultCriteria.select(root).where(predicates.toArray(new Predicate[0]));
            return resultCriteria.getRestriction();
        };
    }
}
