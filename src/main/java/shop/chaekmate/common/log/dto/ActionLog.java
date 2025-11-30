package shop.chaekmate.common.log.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import shop.chaekmate.common.log.logging.LogContext;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Getter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class ActionLog extends BaseLog {
    Map<String, Object> detail;

    public static ActionLog of(
            String serviceName,
            String eventType,
            String className,
            String methodName,
            Map<String, Object> detail
    ) {
        return ActionLog.builder()
                .logHint("ACTION")
                .logType("ACTION")
                .traceId(LogContext.getTraceId().orElse(UUID.randomUUID()))
                .eventType(eventType)
                .serviceName(serviceName)
                .occurrenceTime(LocalDateTime.now())
                .className(className)
                .methodName(methodName)
                .detail(detail)
                .build();
    }
}
