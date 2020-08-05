package ir.mine.project.base.authenticate.sec.authenticate.user.server.user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.dozer.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ir.mine.project.base.authenticate.sec.authenticate.user.server.authentication.IAuthenticationAPI;
import ir.mine.project.base.authenticate.sec.authenticate.user.server.operation.Operation;
import ir.mine.project.base.authenticate.sec.authenticate.user.server.role.Role;
import ir.mine.project.base.authenticate.sec.authenticate.user.shared.dto.BaseUserDTO;
import ir.mine.project.base.excphndl.exceptionServer.BaseServerBusinessException;

@Service
@Transactional
public class BaseUserAPI implements
        IBaseUserAPI {
    private final IAuthenticationAPI authenticationAPI;

    private final BaseUserRepository userYasRepository;


    private final Mapper mapper;

    public BaseUserAPI(IAuthenticationAPI authenticationAPI, BaseUserRepository userYasRepository, Mapper mapper) {
        this.authenticationAPI = authenticationAPI;
        this.userYasRepository = userYasRepository;
        this.mapper = mapper;
    }


    @Override
    public List<BaseUser> findByUsername(String name) {
        return userYasRepository.findByUserName(name);
    }

    @Override
    public BaseUser findFirstByUsername(String username) {
        return userYasRepository.findFirstByUserName(username);
    }


    @Override
    public List<Operation> getOperationsUser() throws BaseServerBusinessException {
        String currentUsername = authenticationAPI.getCurrentUsername();
        BaseUser firstByUserName = userYasRepository.findFirstByUserName(currentUsername);
        Set<Role> roleSet = firstByUserName.getRoles();

        Set<Operation> operationSet = new HashSet<>();


        for (Role role : roleSet) {
            operationSet.addAll(role.getOperations());
        }

        return new ArrayList<>(operationSet); //TODO
    }

    @Override
    public BaseUser getCurrentUser() throws BaseServerBusinessException {
        String currentUsername = authenticationAPI.getCurrentUsername();
        BaseUser firstByUserName = userYasRepository.findFirstByUserName(currentUsername);
        Set<Role> roles = firstByUserName.getRoles();
        for (Role role :
                roles) {
            role.getOperations();
        }
        return firstByUserName;
    }

    @Override
    public BaseUserDTO findUserWithRoleAndOperation(Long id) {
        BaseUser one = userYasRepository.getOne(id);
        return mapper.map(one, BaseUserDTO.class);
    }
}
