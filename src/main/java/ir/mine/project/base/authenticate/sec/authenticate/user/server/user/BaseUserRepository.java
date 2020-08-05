package ir.mine.project.base.authenticate.sec.authenticate.user.server.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data JPA repository for the UserYas entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BaseUserRepository extends JpaRepository<BaseUser, Long> {
    List<BaseUser> findByUserName(String username);

    BaseUser findFirstByUserName(String username);
}
