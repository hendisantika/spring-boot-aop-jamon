package id.my.hendisantika.springbootaopjamon;

import id.my.hendisantika.springbootaopjamon.config.AppConfig;
import id.my.hendisantika.springbootaopjamon.config.HealthConfig;
import id.my.hendisantika.springbootaopjamon.config.MonitorConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({AppConfig.class, MonitorConfig.class, HealthConfig.class})
public class SpringBootAopJamonApplication {

    static void main(String[] args) {
        SpringApplication.run(SpringBootAopJamonApplication.class, args);
    }

}
