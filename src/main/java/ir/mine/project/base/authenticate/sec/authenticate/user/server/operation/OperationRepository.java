package ir.mine.project.base.authenticate.sec.authenticate.user.server.operation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ir.mine.project.base.repository.BaseRepository;


/**
 * Spring Data JPA repository for the Operation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OperationRepository extends BaseRepository<Operation, Long> {

    List<Operation> findByEntityName(String entityName);

    List<Operation> findByName(String Name);

    List<Operation> findAllByOrderByEntityName();
}
