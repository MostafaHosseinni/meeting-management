package ir.mine.project.service.dto.extra;

import java.io.Serializable;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BaseSpecification<T, PK extends Serializable> implements Specification<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SearchCriteria criteria;

	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

		if (criteria != null) {
			
			switch (criteria.getOperation()) {
			case EQUAL:
				return builder.equal(root.get(criteria.getKey()), criteria.getValue());
			case LIKE:
				return builder.like(root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
			case NOT_NULL:
				return builder.isNotNull(root.get(criteria.getKey()));
			case GRATE_THAN:
				return builder.greaterThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString());
			case LESS_THAN:
				return builder.greaterThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString());
			default:
				return null;
			}

		}

		return null;
	}
}