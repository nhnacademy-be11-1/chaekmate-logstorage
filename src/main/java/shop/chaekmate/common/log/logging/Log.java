package shop.chaekmate.common.log.logging;

import lombok.RequiredArgsConstructor;
import shop.chaekmate.common.log.dto.BaseLog;
import shop.chaekmate.common.log.dto.ErrorLog;
import shop.chaekmate.common.log.dto.InfoLog;
import shop.chaekmate.common.log.dto.ResponseTimeLog;
import shop.chaekmate.common.log.worker.LogWorker;

@RequiredArgsConstructor

public class Log {
    private static final String UNKNOWN_EVENT_TYPE = "UNKNOWN";
    public static void Info(String serviceName,
                            String eventType,
                            String message,
                            Object... args) {
        StackTraceElement classAndMethod = Thread.currentThread().getStackTrace()[2];
        InfoLog log = InfoLog.of(
                serviceName,
                eventType,
                classAndMethod.getClassName(),
                classAndMethod.getMethodName(),
                message,
                args
        );

        submit(log);
    }
    public static void Info(String serviceName,
                            String message,
                            Object... args) {
        StackTraceElement classAndMethod = Thread.currentThread().getStackTrace()[2];
        InfoLog log = InfoLog.of(
                serviceName,
                LogContext.getEventType().orElse(UNKNOWN_EVENT_TYPE),
                classAndMethod.getClassName(),
                classAndMethod.getMethodName(),
                message,
                args
        );

        submit(log);
    }

    public static void Error(String serviceName,
                             Exception e,
                             String eventType,
                             int status,
                             String message,
                             Object... args) {

        ErrorLog log = ErrorLog.of(
                serviceName,
                eventType,
                e,
                status,
                message,
                args
        );
        submit(log);
    }
    public static void Error(String serviceName,
                             Exception e,
                             int status,
                             String message,
                             Object... args) {

        ErrorLog log = ErrorLog.of(
                serviceName,
                LogContext.getEventType().orElse(UNKNOWN_EVENT_TYPE),
                e,
                status,
                message,
                args
        );
        submit(log);
    }

    public static void ResponseTime(String serviceName,
                                    Long responseTime,
                                    String eventType,
                                    String message,
                                    Object... args) {
        StackTraceElement classAndMethod = Thread.currentThread().getStackTrace()[2];

        ResponseTimeLog log = ResponseTimeLog.of(
                serviceName,
                eventType,
                responseTime,
                classAndMethod.getMethodName(),
                classAndMethod.getClassName(),
                message,
                args);
        submit(log);
    }
    public static void ResponseTime(String serviceName,
                                    Long responseTime,
                                    String message,
                                    Object... args) {
        StackTraceElement classAndMethod = Thread.currentThread().getStackTrace()[2];

        ResponseTimeLog log = ResponseTimeLog.of(
                serviceName,
                LogContext.getEventType().orElse(UNKNOWN_EVENT_TYPE),
                responseTime,
                classAndMethod.getMethodName(),
                classAndMethod.getClassName(),
                message,
                args);
        submit(log);
    }

    private static void submit(BaseLog log) {
        LogContext.threadPool().submit(new LogWorker(LogContext.storage(), log));
    }

}
