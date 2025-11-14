package shop.chaekmate.common.log.logging;

import lombok.RequiredArgsConstructor;
import shop.chaekmate.common.log.dto.BaseLog;
import shop.chaekmate.common.log.dto.ErrorLog;
import shop.chaekmate.common.log.dto.InfoLog;
import shop.chaekmate.common.log.dto.ResponseTimeLog;
import shop.chaekmate.common.log.worker.LogWorker;

@RequiredArgsConstructor
public class Log {
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

    public static void Error(String serviceName,
                             Exception e,
                             int status,
                             String message,
                             Object... args) {

        ErrorLog log = ErrorLog.of(
                serviceName,
                e,
                status,
                message,
                args
        );
        submit(log);
    }

    public static void ResponseTime(String serviceName,
                                    Long responseTime,
                                    String path,
                                    String httpMethod,
                                    String message,
                                    Object... args) {
        StackTraceElement classAndMethod = Thread.currentThread().getStackTrace()[2];

        ResponseTimeLog log = ResponseTimeLog.of(
                serviceName,
                responseTime,
                path,
                httpMethod,
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
