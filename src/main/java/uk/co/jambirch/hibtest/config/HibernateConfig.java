package uk.co.jambirch.hibtest.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import uk.co.jambirch.hibtest.model.Stock;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class HibernateConfig {

    @Bean
    public LocalSessionFactoryBean  getSessionFactory(DataSource dataSource){
        LocalSessionFactoryBean sf = new LocalSessionFactoryBean();
        sf.setDataSource(dataSource);
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.put("hibernate.show_sql", "true");
        sf.setHibernateProperties(properties);
        sf.setAnnotatedClasses(Stock.class);
        return sf;
    }

    @Bean
    public HibernateTransactionManager getTransactionManager(DataSource dataSource, SessionFactory sessionFactory) throws IOException {
        HibernateTransactionManager txName = new HibernateTransactionManager();
        txName.setSessionFactory(sessionFactory);
        txName.setDataSource(dataSource);
        return txName;
    }
}