package ir.mine.project.repository;

import org.springframework.stereotype.Repository;

import ir.mine.project.base.repository.BaseRepository;
import ir.mine.project.domain.RoomService;


/**
 * Spring Data  repository for the RoomService entity.
 */
@Repository
public interface RoomServiceRepository extends BaseRepository<RoomService, Long> {

}
