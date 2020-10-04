package ir.mine.project.service.dto.extra;

import java.io.Serializable;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
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

		Join<T, ?> join = null;
		String key = null;

		if (criteria != null) {

			if (criteria.getKey().contains(".")) {
				String[] split = criteria.getKey().split("\\.");

				for (int i = 0; i < split.length - 1; i++) {
					if (join == null)
						join = root.join(split[i]);
					else
						join = join.join(split[i]);
					key = split[i + 1];
				}
			}

			if (join == null) {
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
					return builder.lessThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString());
				default:
					return null;
				}
			} else {
				switch (criteria.getOperation()) {
				case EQUAL:
					return builder.equal(join.get(key), criteria.getValue());
				case LIKE:
					return builder.like(join.get(key), "%" + criteria.getValue() + "%");
				case NOT_NULL:
					return builder.isNotNull(join.get(key));
				case GRATE_THAN:
					return builder.greaterThanOrEqualTo(join.get(key), criteria.getValue().toString());
				case LESS_THAN:
					return builder.lessThanOrEqualTo(join.get(key), criteria.getValue().toString());
				default:
					return null;
				}
			}

		}

		return null;
	}
}