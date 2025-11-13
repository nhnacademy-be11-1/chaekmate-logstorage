package shop.chaekmate.common.log.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class InfoLog extends BaseLog {
    String eventType;

    public static InfoLog of(
            String serviceName,
            String eventType,
            String className,
            String methodName,
            String message,
            Object... args

    ) {
        return InfoLog.builder()
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
