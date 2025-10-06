package id.my.hendisantika.springbootaopjamon.health;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.Status;
import org.springframework.scheduling.annotation.Scheduled;

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

    private int getStatus(Status status) {
        return switch (status.getCode()) {
            case "NO_ACCOUNT" -> 0;
            case "DOWN" -> 1;
            case "OUT_OF_SERVICE" -> 2;
            case "UP" -> 3;
            default -> -1;
        };
    }
}
