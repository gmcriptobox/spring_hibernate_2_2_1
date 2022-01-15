package hiber.config;

import hiber.model.Car;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Properties;


@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScan(value = "hiber")
public class AppConfig {

   @Autowired
   private Environment env;

   @Bean
   public LocalContainerEntityManagerFactoryBean userEntityManager() {
      LocalContainerEntityManagerFactoryBean em
              = new LocalContainerEntityManagerFactoryBean();
      em.setDataSource(userDataSource());
      em.setPackagesToScan("hiber.model.user");

      HibernateJpaVendorAdapter vendorAdapter
              = new HibernateJpaVendorAdapter();
      em.setJpaVendorAdapter(vendorAdapter);
      HashMap<String, Object> properties = new HashMap<>();
      properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
      properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
      em.setJpaPropertyMap(properties);

      return em;
   }


   @Bean
   public DataSource userDataSource() {
      DriverManagerDataSource dataSource
              = new DriverManagerDataSource();
      dataSource.setDriverClassName(
              env.getProperty("db.driver"));
      dataSource.setUrl(env.getProperty("db.url"));
      dataSource.setUsername(env.getProperty("db.user"));
      dataSource.setPassword(env.getProperty("db.pass"));
      return dataSource;
   }


   @Bean
   public PlatformTransactionManager userTransactionManager() {

      JpaTransactionManager transactionManager
              = new JpaTransactionManager();
      transactionManager.setEntityManagerFactory(
              userEntityManager().getObject());
      return transactionManager;
   }
}
