package ir.mine.project.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ir.mine.project.base.repository.BaseRepository;
import ir.mine.project.domain.Approvals;

/**
 * Spring Data repository for the Approvals entity.
 */
@Repository
public interface ApprovalsRepository extends BaseRepository<Approvals, Long> {

	@Query("select ap from Approvals ap join ap.meeting m join m.invitees inv where  inv.id=?1")
	Page<Approvals> findAllByMeeting_Invitees_id(Long id, Pageable pageable);
}
