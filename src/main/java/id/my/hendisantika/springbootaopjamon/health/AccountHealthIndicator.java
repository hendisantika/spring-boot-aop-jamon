package id.my.hendisantika.springbootaopjamon.health;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-aop-jamon
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 07/10/25
 * Time: 06.11
 * To change this template use File | Settings | File Templates.
 */
public record AccountHealthIndicator(JdbcTemplate jdbcTemplate) implements HealthIndicator {

}
