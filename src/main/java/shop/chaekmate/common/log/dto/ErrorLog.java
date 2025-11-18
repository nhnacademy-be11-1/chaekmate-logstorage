package shop.chaekmate.common.log.dto;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import shop.chaekmate.common.log.logging.LogContext;

@Getter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class ErrorLog extends BaseLog {
    private int errorStatus;
    private String errorType;
    private String errorTrace;

    public static ErrorLog of(
            String serviceName,
            String eventType,
            Exception e,
            int status,
            String message,
            Object... args
    ) {
        StackTraceElement origin = e.getStackTrace()[0];

        return ErrorLog.builder()
                .logHint("ERROR")
                .logType("ERROR")
                .serviceName(serviceName)
                .eventType(eventType)
                .occurrenceTime(LocalDateTime.now())
                .className(origin.getClassName())
                .methodName(origin.getMethodName())
                .message(LogMessageFormatter.format(message, args))
                .errorStatus(status)
                .errorType(e.getClass().getSimpleName())
                .errorTrace(traceToString(e))
                .build();
    }
    private static String traceToString(Exception e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }

}
