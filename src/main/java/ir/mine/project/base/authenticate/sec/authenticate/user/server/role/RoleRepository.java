package ir.mine.project.base.authenticate.sec.authenticate.user.server.role;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data JPA repository for the Role entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    List<Role> findByName(String name);
    
    Role findOneByName(String name);

}
