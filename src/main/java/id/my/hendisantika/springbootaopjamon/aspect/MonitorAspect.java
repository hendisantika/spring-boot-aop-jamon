package id.my.hendisantika.springbootaopjamon.aspect;

import com.jamonapi.Monitor;
import com.jamonapi.MonitorFactoryInterface;
import id.my.hendisantika.springbootaopjamon.annotation.MethodMonitor;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
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

    @Around(value = "controllerMethod() && @annotation(methodMonitor)")
    public Object monitorMethodExecution(ProceedingJoinPoint joinPoint, MethodMonitor methodMonitor) throws Throwable {
        Monitor monitor = monitorFactoryInterface.start(methodMonitor.name());
        try {
            return joinPoint.proceed();
        } finally {
            monitor.stop();
            Counter.builder("http.requests.count")
                    .tag("uri", methodMonitor.uri())
                    .tag("method", methodMonitor.method())
                    .register(meterRegistry).increment();
            Gauge
                    .builder("http.response.time", monitor::getLastValue)
                    .tag("uri", methodMonitor.uri())
                    .tag("method", methodMonitor.method())
                    .register(meterRegistry);
        }
    }
}
