package ir.mine.project.config;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import ir.mine.project.staticvariable.PackageName;
import th.co.geniustree.springdata.jpa.repository.support.JpaSpecificationExecutorWithProjectionImpl;

@Configuration
@EnableTransactionManagement
@EnableJpaAuditing
@EnableAutoConfiguration
@EnableJpaRepositories(repositoryBaseClass = JpaSpecificationExecutorWithProjectionImpl.class, basePackages = {PackageName.PACKAGE_NAME})
public class DataBaseConfiguration {
}
