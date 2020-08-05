package ir.mine.project.base.authenticate.sec.authenticate.user.server.operation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ir.mine.project.base.authenticate.sec.authenticate.user.server.operationinfo.OperationDescription;
import ir.mine.project.base.authenticate.sec.authenticate.user.server.operationinfo.OperationGroupName;
import ir.mine.project.base.authorization.autoupdate.annot.SecurityModel;
import ir.mine.project.base.authorization.autoupdate.annot.SecurityOperation;
import ir.mine.project.base.service.BaseServiceImpl;


/**
 * Service Implementation for managing Operation.
 */
@Service("OperationServiceImpl")
@Transactional
@SecurityModel(operations = {
        @SecurityOperation(operationName = "operation:create", operationEntityName = "Operation", displayName = OperationDescription.operationC, oprationGroup = OperationGroupName.operation),
        @SecurityOperation(operationName = "operation:read", operationEntityName = "Operation", displayName = OperationDescription.operationR, oprationGroup = OperationGroupName.operation),
        @SecurityOperation(operationName = "operation:update", operationEntityName = "Operation", displayName = OperationDescription.operationU, oprationGroup = OperationGroupName.operation),
        @SecurityOperation(operationName = "operation:delete", operationEntityName = "Operation", displayName = OperationDescription.operationD, oprationGroup = OperationGroupName.operation),})
public class OperationServiceImpl extends BaseServiceImpl<Operation, Long, OperationRepository> implements OperationService {

    private final Logger log = LoggerFactory.getLogger(OperationServiceImpl.class);


    public OperationServiceImpl(OperationRepository operationRepository) {
        super(operationRepository);
    }

    @Override
    public List<Operation> findByEntityName(String entityName) {
        return baseRepository.findByEntityName(entityName);
    }

    @Override
    public List<Operation> findByName(String Name) {
        return baseRepository.findByName(Name);
    }

    @Override
    public List<Operation> findAllByOrderByEntityName() {
        return baseRepository.findAllByOrderByEntityName();
    }
}
