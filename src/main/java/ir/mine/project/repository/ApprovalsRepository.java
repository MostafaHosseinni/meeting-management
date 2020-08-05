package ir.mine.project.repository;

import org.springframework.stereotype.Repository;

import ir.mine.project.base.repository.BaseRepository;
import ir.mine.project.domain.Approvals;


/**
 * Spring Data  repository for the Approvals entity.
 */
@Repository
public interface ApprovalsRepository extends BaseRepository<Approvals, Long> {

}
