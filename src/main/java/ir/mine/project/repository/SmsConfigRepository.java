package ir.mine.project.repository;

import org.springframework.stereotype.Repository;

import ir.mine.project.base.repository.BaseRepository;
import ir.mine.project.domain.SmsConfig;


/**
 * Spring Data  repository for the SmsConfig entity.
 */
@Repository
public interface SmsConfigRepository extends BaseRepository<SmsConfig, Long> {

}
