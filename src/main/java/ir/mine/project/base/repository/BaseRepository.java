package ir.mine.project.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import th.co.geniustree.springdata.jpa.repository.JpaSpecificationExecutorWithProjection;

@NoRepositoryBean
public interface BaseRepository<T, PK>
		extends JpaRepository<T, PK>, JpaSpecificationExecutor<T>, JpaSpecificationExecutorWithProjection<T> {

}
