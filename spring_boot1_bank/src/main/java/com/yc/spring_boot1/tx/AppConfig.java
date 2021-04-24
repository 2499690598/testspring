package com.yc.spring_boot1.tx;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Configuration
@ComponentScan(basePackages = {"com.yc"})
@EnableTransactionManagement
public class AppConfig {

    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        DataSource ds = new ComboPooledDataSource();
        ((ComboPooledDataSource) ds).setDriverClass("com.mysql.jdbc.Driver");
        //如果MySQL版本较高（>=5.5) 就需要修改url
        ((ComboPooledDataSource) ds).setJdbcUrl("jdbc:mysql://localhost:3306/testBank?serverTimezone=GMT%2B8");
        ((ComboPooledDataSource) ds).setUser("root");
        ((ComboPooledDataSource) ds).setPassword("root");

        return ds;
    }

    @Bean   //@Bean注解的优先级高于@Component @Service...
    public TransactionManager DataSourceTransactionManager(DataSource ds) {
        return new DataSourceTransactionManager(ds);
    }
}
