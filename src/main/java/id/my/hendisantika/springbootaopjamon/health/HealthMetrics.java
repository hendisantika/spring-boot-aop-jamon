package id.my.hendisantika.springbootaopjamon.health;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;

import static jdk.internal.org.jline.utils.Status.getStatus;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-aop-jamon
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 07/10/25
 * Time: 06.12
 * To change this template use File | Settings | File Templates.
 */
@RequiredArgsConstructor
public class HealthMetrics {
    private final AccountHealthIndicator accountHealthIndicator;
    private final MeterRegistry meterRegistry;

    @Scheduled(fixedRate = 15000, initialDelay = 0)
    public void reportHealth() {
        Gauge
                .builder("application.health",
                        () -> getStatus(accountHealthIndicator.getHealth(true).getStatus())
                )
                .register(meterRegistry);
    }
}
