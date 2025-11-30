package shop.chaekmate.common.log.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import shop.chaekmate.common.log.logging.LogContext;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class TrafficLog extends BaseLog {
    public static TrafficLog of(
            String serviceName,
            String eventType,
            String className,
            String methodName
    ) {
        return TrafficLog.builder()
                .logHint("TRAFFIC")
                .logType("TRAFFIC")
                .traceId(LogContext.getTraceId().orElse(UUID.randomUUID()))
                .eventType(eventType)
                .serviceName(serviceName)
                .occurrenceTime(LocalDateTime.now())
                .className(className)
                .methodName(methodName)
                .build();
    }
}
