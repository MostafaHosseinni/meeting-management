package ir.mine.project.base.authenticate.sec.authenticate.user.server.user;

import java.util.List;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ir.mine.project.base.service.BaseServiceImpl;


/**
 * Service Implementation for managing UserYas.
 */
@Service("BaseUserServiceImpl")
@Transactional
public class BaseUserServiceImpl extends BaseServiceImpl<BaseUser, Long, BaseUserRepository> implements BaseUserService {

    private final Logger log = LoggerFactory.getLogger(BaseUserService.class);

    private final Mapper mapper;

    public BaseUserServiceImpl(BaseUserRepository baseUserRepository, Mapper mapper) {
        super(baseUserRepository);
        this.mapper = mapper;
    }

    @Override
    public List<BaseUser> findByUsername(String name) {
        return baseRepository.findByUserName(name);
    }

    @Override
    public BaseUser findFirstByUsername(String username) {
        return baseRepository.findFirstByUserName(username);
    }


}
