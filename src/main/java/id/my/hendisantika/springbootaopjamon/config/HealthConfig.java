package id.my.hendisantika.springbootaopjamon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ReflectiveScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-aop-jamon
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 07/10/25
 * Time: 06.06
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@EnableScheduling
@ReflectiveScan
public class HealthConfig {

    private final JdbcTemplate jdbcTemplate;

    @Bean
    public AccountHealthIndicator accountHealthIndicator() {
        return new AccountHealthIndicator(jdbcTemplate);
    }

}
