package com.example.crisproject.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.*;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.*;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.example.crisproject.employee.repo",
        entityManagerFactoryRef = "employeeEntityManager",
        transactionManagerRef = "employeeTransactionManager"
)
public class EmployeeDBConfig {

    @Bean
    public DataSource employeeDataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:mysql://localhost:3306/employee")
                .username("root")
                .password("")
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean employeeEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(employeeDataSource());
        em.setPackagesToScan("com.example.crisproject.employee.entity");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Map<String, Object> props = new HashMap<>();
        props.put("hibernate.hbm2ddl.auto", "update");

        em.setJpaPropertyMap(props);
        return em;
    }

    @Bean
    public PlatformTransactionManager employeeTransactionManager(
            @Qualifier("employeeEntityManager") LocalContainerEntityManagerFactoryBean em) {
        return new JpaTransactionManager(em.getObject());
    }
}