package shop.chaekmate.common.log.dto;

import java.time.LocalDateTime;
import java.util.Optional;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import shop.chaekmate.common.log.logging.LogContext;

@Getter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class InfoLog extends BaseLog {

    public static InfoLog of(
            String serviceName,
            String eventType,
            String className,
            String methodName,
            String message,
            Object... args

    ) {
        return InfoLog.builder()
                .logHint("INFO")
                .logType("INFO")
                .eventType(eventType)
                .serviceName(serviceName)
                .occurrenceTime(LocalDateTime.now())
                .className(className)
                .methodName(methodName)
                .message(LogMessageFormatter.format(message, args))
                .build();
    }
}
