package me.eting.server.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import javax.servlet.ServletContext;
import javax.sql.DataSource;

/**
 * Created by lifenjoy51 on 12/3/14.
 */
@ComponentScan(basePackages =  "me.eting")
@EntityScan(basePackages =  "me.eting")
@EnableJpaRepositories(basePackages =  "me.eting")
@Configuration
@EnableAutoConfiguration
public class EtingApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(EtingApplication.class);
    }

    @Bean
    public StandardPasswordEncoder standardPasswordEncoder(){
        return new StandardPasswordEncoder("eting");
    }

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL).build();
    }


}