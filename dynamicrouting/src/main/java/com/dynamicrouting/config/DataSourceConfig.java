package com.dynamicrouting.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

import jakarta.persistence.EntityManagerFactory;

@Configuration
public class DataSourceConfig {

    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource() {
        Map<Object, Object> dataSources = new HashMap<>();

        dataSources.put("maindb", createDataSource("maindb"));
        dataSources.put("princedb", createDataSource("princedb"));
        dataSources.put("kingdb", createDataSource("kingdb"));

        DynamicRoutingDataSource routingDataSource = new DynamicRoutingDataSource();
        routingDataSource.setTargetDataSources(dataSources);
        routingDataSource.setDefaultTargetDataSource(dataSources.get("maindb"));

        return routingDataSource;
    }

    private DataSource createDataSource(String dbName) {
        HikariDataSource dataSource = new HikariDataSource();

        dataSource.setJdbcUrl(environment.getProperty("spring.datasource." + dbName + ".url"));
        dataSource.setUsername(environment.getProperty("spring.datasource." + dbName + ".username"));
        dataSource.setPassword(environment.getProperty("spring.datasource." + dbName + ".password"));
        dataSource.setDriverClassName(environment.getProperty("spring.datasource." + dbName + ".driver-class-name"));

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource)
                .packages("com.dynamicrouting.entity") // your package
                .persistenceUnit("default")
                .build();
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
}

