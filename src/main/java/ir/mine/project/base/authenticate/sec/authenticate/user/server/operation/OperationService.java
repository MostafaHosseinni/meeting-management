package ir.mine.project.base.authenticate.sec.authenticate.user.server.operation;

import java.util.List;

import ir.mine.project.base.service.BaseService;

/**
 * Service Interface for managing Operation.
 */
public interface OperationService extends BaseService<Operation, Long> {

    List<Operation> findByEntityName(String entityName);

    List<Operation> findByName(String Name);

    List<Operation> findAllByOrderByEntityName();
}
