package id.my.hendisantika.springbootaopjamon.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-aop-jamon
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 07/10/25
 * Time: 06.07
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@ComponentScan(basePackages = "id.my.hendisantika.springbootaopjamon.aspect")
@EnableAspectJAutoProxy
public class MonitorConfig {
}
