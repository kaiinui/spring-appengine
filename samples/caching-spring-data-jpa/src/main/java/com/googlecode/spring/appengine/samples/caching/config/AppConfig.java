package com.googlecode.spring.appengine.samples.caching.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.support.ClasspathScanningPersistenceUnitPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

@Configuration
@ComponentScan(basePackages = { "com.googlecode.spring.appengine.samples.caching" })
@EnableJpaRepositories(basePackages = { "com.googlecode.spring.appengine.samples.caching.repository" })
@EnableSpringConfigured
@EnableTransactionManagement
public class AppConfig {

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setPersistenceUnitName("transactions-optional");
        entityManagerFactory.setPersistenceUnitPostProcessors(new ClasspathScanningPersistenceUnitPostProcessor("com.googlecode.spring.appengine.samples.caching.domain"));
        return entityManagerFactory;
    }

    @Bean
    public MemcacheService memcacheService() {
        return MemcacheServiceFactory.getMemcacheService();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean
    public RepositoryPopulator repositoryPopulator() {
        return new RepositoryPopulator();
    }
}