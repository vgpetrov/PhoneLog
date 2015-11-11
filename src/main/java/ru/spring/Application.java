package ru.spring;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.*;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import ru.db.hibernate.HibernateSessionFactory;

import java.util.Properties;

/**
 * Created by vitaly on 05/02/15.
 *
 * Spring application configuration stuff
 */
@Configuration
@ComponentScan(basePackages = {"ru.rest", "ru.controller", "ru.db.dao", "ru.service"})
@EnableAutoConfiguration
@Import({ WebSecurityConfig.class })
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }

    private static Class<Application> applicationClass = Application.class;

//    @Bean(name = "hibernateSessionFactory", autowire = Autowire.BY_TYPE)
//    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
//    public HibernateSessionFactory hibernateSessionFactory() {
//        return HibernateSessionFactory.init();
//    }

    @Bean
    public SessionFactory sessionFactory() {
        LocalSessionFactoryBuilder builder =
                new LocalSessionFactoryBuilder(dataSource());
        builder.scanPackages("ru.db.entities")
                .addProperties(getHibernateProperties());

        return builder.buildSessionFactory();
    }

    private Properties getHibernateProperties() {
        Properties prop = new Properties();
        prop.put("hibernate.format_sql", "true");
        prop.put("hibernate.show_sql", "true");
        prop.put("connection.autocommit", false);
        prop.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        return prop;
    }

    @Bean(name = "dataSource")
    public BasicDataSource dataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://10.211.55.3:3306/deployer");
        ds.setUsername("remote_admin");
        ds.setPassword("remote_admin");
        return ds;
    }
}