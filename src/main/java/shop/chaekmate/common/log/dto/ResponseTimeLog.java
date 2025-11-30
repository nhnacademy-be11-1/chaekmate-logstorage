package shop.chaekmate.common.log.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import shop.chaekmate.common.log.logging.LogContext;

@Getter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class ResponseTimeLog extends BaseLog {
    Long responseTime;
    public static ResponseTimeLog of(
            String serviceName,
            String eventType,
            Long responseTime,
            String className,
            String methodName
    ) {
        return ResponseTimeLog.builder()
                .logHint("RESPONSE-TIME")
                .logType("RESPONSE-TIME")
                .traceId(LogContext.getTraceId().orElse(UUID.randomUUID()))
                .serviceName(serviceName)
                .eventType(eventType)
                .responseTime(responseTime)
                .className(className)
                .methodName(methodName)
                .occurrenceTime(LocalDateTime.now())
                .build();
    }

}
