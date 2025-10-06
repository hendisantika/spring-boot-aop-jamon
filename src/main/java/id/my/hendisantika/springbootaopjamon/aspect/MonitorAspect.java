package id.my.hendisantika.springbootaopjamon.aspect;

import com.jamonapi.MonitorFactoryInterface;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-aop-jamon
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 07/10/25
 * Time: 06.03
 * To change this template use File | Settings | File Templates.
 */
@Aspect
@Component
@RequiredArgsConstructor
public class MonitorAspect {

    private final MonitorFactoryInterface monitorFactoryInterface;
    private final MeterRegistry meterRegistry;

}
