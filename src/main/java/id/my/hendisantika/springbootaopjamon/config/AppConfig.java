package id.my.hendisantika.springbootaopjamon.config;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-aop-jamon
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 07/10/25
 * Time: 06.05
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@ComponentScan(basePackages = {"id.my.hendisantika.springbootaopjamon.controller", "id.my.hendisantika.springbootaopjamon.service"})
public class AppConfig {
    @Bean
    MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
        return registry -> registry.config().commonTags("application", "HENDISANTIKA");
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
