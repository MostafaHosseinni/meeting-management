package ir.mine.project.base.authenticate.sec.authenticate.user.server.operation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data JPA repository for the Operation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {

    List<Operation> findByEntityName(String entityName);

    List<Operation> findByName(String Name);

    List<Operation> findAllByOrderByEntityName();
}
