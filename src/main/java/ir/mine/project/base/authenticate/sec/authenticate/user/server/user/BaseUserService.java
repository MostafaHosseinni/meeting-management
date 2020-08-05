package ir.mine.project.base.authenticate.sec.authenticate.user.server.user;

import java.util.List;

import ir.mine.project.base.service.BaseService;

/**
 * Service Interface for managing UserYas.
 */
public interface BaseUserService extends BaseService<BaseUser, Long> {
    List<BaseUser> findByUsername(String username);

    BaseUser findFirstByUsername(String username);

}
