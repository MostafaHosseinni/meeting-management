package ir.mine.project.base.service;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;

public class BaseServiceImpl<T, PK extends Serializable, Repository extends JpaRepository<T, PK>>
		implements BaseService<T, PK> {

	public static final String CREATE_OPERATION = "create";
	public static final String READ_OPERATION = "read";
	public static final String UPDATE_OPERATION = "update";
	public static final String DELETE_OPERATION = "delete";
	protected final Repository baseRepository;
	protected Class<T> entityClass;

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
}
