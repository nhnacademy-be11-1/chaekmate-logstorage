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
    String path;
    String httpMethod;


    public static ResponseTimeLog of(
            String serviceName,
            Long responseTime,
            String path,
            String httpMethod,
            String methodName,
            String className,
            String message,
            Object... args

    ) {
        return ResponseTimeLog.builder()
                .logType("RESPONSE-TIME")
                .serviceName(serviceName)
                .httpMethod(httpMethod)
                .responseTime(responseTime)
                .className(className)
                .methodName(methodName)
                .path(path)
                .occurrenceTime(LocalDateTime.now())
                .message(LogMessageFormatter.format(message, args))
                .build();
    }

}
