package shop.chaekmate.common.log.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class ResponseTimeLog extends BaseLog {
    Long responseTime;
    public static ResponseTimeLog of(
            String serviceName,
            String eventType,
            Long responseTime,
            String methodName,
            String className,
            String message,
            Object... args
    ) {
        return ResponseTimeLog.builder()
                .logType("RESPONSE-TIME")
                .serviceName(serviceName)
                .eventType(eventType)
                .responseTime(responseTime)
                .className(className)
                .methodName(methodName)
                .occurrenceTime(LocalDateTime.now())
                .message(LogMessageFormatter.format(message, args))
                .build();
    }

}
