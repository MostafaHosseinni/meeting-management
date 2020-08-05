package ir.mine.project.repository;

import org.springframework.stereotype.Repository;

import ir.mine.project.base.repository.BaseRepository;
import ir.mine.project.domain.Sms;


/**
 * Spring Data  repository for the Sms entity.
 */
@Repository
public interface SmsRepository extends BaseRepository<Sms, Long> {

}
