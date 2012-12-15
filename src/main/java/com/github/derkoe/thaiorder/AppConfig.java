package com.github.derkoe.thaiorder;

import java.beans.PropertyVetoException;

import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.github.derkoe.thaiorder.model.ProductFiller;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configurable
@EnableTransactionManagement
@PropertySource("classpath:com/github/derkoe/thaiorder/app.properties")
@EnableJpaRepositories(basePackageClasses = AppConfig.class, entityManagerFactoryRef = "emf")
@ComponentScan(basePackageClasses = AppConfig.class)
public class AppConfig
{
    @Inject
    private Environment env;

    @Bean
    public DataSource dataSource() throws PropertyVetoException
    {
        ComboPooledDataSource ds = new ComboPooledDataSource();
        ds.setJdbcUrl(env.getProperty("db.jdbcUrl"));
        ds.setDriverClass(env.getProperty("db.driverClass"));
        ds.setUser(env.getProperty("db.user"));
        ds.setPassword(env.getProperty("db.password"));
        return ds;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean emf() throws PropertyVetoException
    {
        LocalContainerEntityManagerFactoryBean emfFactoryBean = new LocalContainerEntityManagerFactoryBean();
        emfFactoryBean.setDataSource(dataSource());
        emfFactoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        emfFactoryBean.setPackagesToScan("com.github.derkoe.thaiorder.model");
        return emfFactoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf)
    {
        return new JpaTransactionManager(emf);
    }

    private JpaVendorAdapter jpaVendorAdapter()
    {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(env.getProperty("db.showSql", Boolean.class, Boolean.FALSE));
        vendorAdapter.setGenerateDdl(env.getProperty("db.generateDdl", Boolean.class, Boolean.FALSE));
        return vendorAdapter;
    }

    @Bean
    ProductFiller fillProducts()
    {
        return new ProductFiller();
    }
}
