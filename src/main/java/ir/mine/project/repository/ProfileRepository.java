package ir.mine.project.repository;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ir.mine.project.base.repository.BaseRepository;
import ir.mine.project.domain.Profile;
import ir.mine.project.domain.enumeration.ProfileType;

/**
 * Spring Data repository for the Project entity.
 */
@Repository
public interface ProfileRepository extends BaseRepository<Profile, Long> {

	Profile findFirstByUserName(String currentUsername);

	List<Profile> findByProfileType(ProfileType profileType);

	Long countByLastLoginTimeBetween(ZonedDateTime from, ZonedDateTime to);

	@Modifying
	@Query(nativeQuery = true, value = "insert into profile (id , profile_type) values (:id , 'ADMIN')")
	void addAdminUser(@Param("id") Long id);

}