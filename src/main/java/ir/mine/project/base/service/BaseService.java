package ir.mine.project.base.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BaseService<T, PK extends Serializable> {

	T save(T t);

	Page<T> findAll(Pageable pageable);

	List<T> findAll();

	T findOne(PK id);

	void delete(PK id);

	Long countAll();

	Long countAllNoteSecure();

	T saveNotSecure(T t);

	Page<T> findAllNotSecure(Pageable pageable);

	List<T> findAllNotSecure();

	T findOneNotSecure(PK id);

	void deleteNotSecure(PK id);

}
