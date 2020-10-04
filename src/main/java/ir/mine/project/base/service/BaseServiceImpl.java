package ir.mine.project.base.service;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;

import ir.mine.project.base.domin.BaseEntity;
import ir.mine.project.base.repository.BaseRepository;
import ir.mine.project.service.dto.extra.BaseSpecification;
import ir.mine.project.service.dto.extra.CriteriaOperation;
import ir.mine.project.service.dto.extra.SearchCriteria;

public class BaseServiceImpl<T, PK extends Serializable, Repository extends BaseRepository<T, PK>>
		implements BaseService<T, PK> {

	public static final String CREATE_OPERATION = "create";
	public static final String READ_OPERATION = "read";
	public static final String UPDATE_OPERATION = "update";
	public static final String DELETE_OPERATION = "delete";
	protected final Repository baseRepository;
	protected Class<T> entityClass;

	@SuppressWarnings("unchecked")
	public BaseServiceImpl(Repository baseRepository) {
		this.baseRepository = baseRepository;

		if (getClass().getGenericSuperclass() instanceof ParameterizedType) {
			ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
			if (genericSuperclass != null && genericSuperclass.getActualTypeArguments() != null
					&& genericSuperclass.getActualTypeArguments().length > 0) {
				if (genericSuperclass.getActualTypeArguments()[0] instanceof Class) {
					entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
				}
			}
		}
	}

	public String getEntityName() {
		String simpleName = entityClass.getSimpleName();
//        String substring = simpleName.substring(0, simpleName.length() - "Entity".length());
		return simpleName.toLowerCase();
	}

	@Override
	@PreAuthorize("hasPermission(getThis().getEntityName(), '" + CREATE_OPERATION + "')")
	public T save(T t) {
		return baseRepository.save(t);
	}

	@Override
	@PreAuthorize("hasPermission(getThis().getEntityName(), '" + READ_OPERATION + "')")
	public Page<T> findAll(Pageable pageable) {
		return baseRepository.findAll(pageable);
	}

	@Override
	@PreAuthorize("hasPermission(getThis().getEntityName(), '" + READ_OPERATION + "')")
	public List<T> findAll() {
		return baseRepository.findAll();
	}

	@Override
	@PreAuthorize("hasPermission(getThis().getEntityName(), '" + READ_OPERATION + "')")
	public T findOne(PK id) {
		return baseRepository.getOne(id);
	}

	@Override
	@PreAuthorize("hasPermission(getThis().getEntityName(), '" + DELETE_OPERATION + "')")
	public void delete(PK id) {
		baseRepository.deleteById(id);
	}

	@Override
	@PreAuthorize("hasPermission(getThis().getEntityName(), '" + READ_OPERATION + "')")
	public Long countAll() {
		return baseRepository.count();
	}

	@Override
	public Long countAllNoteSecure() {
		return baseRepository.count();
	}

	@Override
	public T saveNotSecure(T t) {
		return baseRepository.save(t);
	}

	@Override
	public Page<T> findAllNotSecure(Pageable pageable) {
		return baseRepository.findAll(pageable);
	}

	@Override
	public List<T> findAllNotSecure() {
		return baseRepository.findAll();
	}

	@Override
	public T findOneNotSecure(PK id) {
		return baseRepository.getOne(id);
	}

	@Override
	public void deleteNotSecure(PK id) {
		baseRepository.deleteById(id);
	}

	@Override
	public List<T> searchByExample(T t) {
		return baseRepository.findAll(calculateSpecification(t));
	}

	@Override
	public Long countByExample(T t) {
		return baseRepository.count(calculateSpecification(t));
	}

	protected Specification<T> calculateSpecification(T t) {

		BaseSpecification<T, PK> specification = new BaseSpecification<>(
				new SearchCriteria("id", CriteriaOperation.NOT_NULL, null));

		Specification<T> where = Specification.where(specification);

		Field[] fields = t.getClass().getDeclaredFields();

		for (Field field : fields) {

			if ("serialVersionUID".equals(field.getName()))
				continue;

			try {
				String fieldName = field.getName();

				Object value = t.getClass()
						.getMethod("get" + (fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1)))
						.invoke(t);

				if (value != null) {

					// TODO fix me to calculate base entity foreign key and object type
					if (value instanceof BaseEntity) {
						continue;
					} else {
						if (value instanceof String) {
							BaseSpecification<T, PK> spec = new BaseSpecification<>(
									new SearchCriteria(fieldName, CriteriaOperation.LIKE, value));
							where = where.and(spec);
						} else {
							BaseSpecification<T, PK> spec = new BaseSpecification<>(
									new SearchCriteria(fieldName, CriteriaOperation.EQUAL, value));
							where = where.and(spec);
						}
					}
				}

			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
		}

		return where;
	}

	protected Specification<T> calculateSpecificationObject(Object t) {

		BaseSpecification<T, PK> specification = new BaseSpecification<>(
				new SearchCriteria("id", CriteriaOperation.NOT_NULL, null));

		Specification<T> where = Specification.where(specification);

		Field[] fields = t.getClass().getDeclaredFields();

		for (Field field : fields) {

			try {
				String fieldName = field.getName();

				Object value = t.getClass()
						.getMethod("get" + (fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1)))
						.invoke(t);

				if (value != null) {

					// TODO fix me to calculate base entity foreign key and object type
					if (value instanceof BaseEntity) {
						continue;
					} else {
						if (value instanceof String) {
							BaseSpecification<T, PK> spec = new BaseSpecification<>(
									new SearchCriteria(fieldName, CriteriaOperation.LIKE, value));
							where = where.and(spec);
						} else {
							BaseSpecification<T, PK> spec = new BaseSpecification<>(
									new SearchCriteria(fieldName, CriteriaOperation.EQUAL, value));
							where = where.and(spec);
						}
					}
				}

			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
		}

		return where;
	}

	@Override
	public List<T> searchByObjectExample(Object o) {
		return baseRepository.findAll(calculateSpecificationObject(o));
	}

	@Override
	public Long countByObjectExample(Object o) {
		return baseRepository.count(calculateSpecificationObject(o));
	}

	@Override
	public <Breif> Page<Breif> searchByExampleWithProjection(T t, Class<Breif> clazz, Pageable pageable) {
		return baseRepository.findAll(calculateSpecification(t), clazz, pageable);
	}

	@Override
	public <Breif> Page<Breif> searchByObjectExampleWithProjection(Object o, Class<Breif> clazz, Pageable pageable) {
		return baseRepository.findAll(calculateSpecificationObject(o), clazz, pageable);
	}

	@Override
	public <Breif> Page<Breif> findAllWithProjection(Class<Breif> clazz, Pageable pageable) {
		return baseRepository.findAll(null, clazz, pageable);
	}

}
