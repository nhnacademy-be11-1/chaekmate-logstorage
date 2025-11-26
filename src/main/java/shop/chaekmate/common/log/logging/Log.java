package shop.chaekmate.common.log.logging;

import shop.chaekmate.common.log.dto.BaseLog;
import shop.chaekmate.common.log.dto.ErrorLog;
import shop.chaekmate.common.log.dto.InfoLog;
import shop.chaekmate.common.log.dto.ResponseTimeLog;
import shop.chaekmate.common.log.worker.LogWorker;

public class Log {
    private static final String UNKNOWN_EVENT_TYPE = "UNKNOWN";

    public static void InfoWithEventType(
            String eventType,
            String message,
            Object... args) {
        StackTraceElement caller = CallerResolver.resolveCaller();
        submit(convertInfoLog(eventType, caller.getClassName(), caller.getMethodName(), message, args));
    }

    public static void Info(
            String message,
            Object... args) {
        StackTraceElement caller = CallerResolver.resolveCaller();
        submit(convertInfoLog(LogContext.getEventType().orElse(UNKNOWN_EVENT_TYPE), caller.getClassName(),
                caller.getMethodName(), message, args));
    }

    public static void InfoWithClassMethod(
            String className,
            String method,
            String message,
            Object... args) {
        submit(convertInfoLog(LogContext.getEventType().orElse(UNKNOWN_EVENT_TYPE), className, method,
                message, args));
    }

    protected static void InfoWithEventTypeClassMethod(
            String eventType,
            String className,
            String methodName,
            String message,
            Object... args) {
        submit(convertInfoLog(eventType, className, methodName, message, args));
    }


    public static void ErrorWithEventType(
            String eventType,
            Exception e,
            int status,
            String message,
            Object... args) {
        submit(convertErrorLog(eventType, e, status, message, args));
    }

    public static void Error(
            Exception e,
            int status,
            String message,
            Object... args) {
        submit(convertErrorLog(LogContext.getEventType().orElse(UNKNOWN_EVENT_TYPE), e, status, message,
                args));
    }

    public static void ResponseTimeWithEventType(
            Long responseTime,
            String eventType,
            String message,
            Object... args) {
        StackTraceElement callerResolver = CallerResolver.resolveCaller();
        submit(convertResponseTime(responseTime, eventType, callerResolver.getClassName(),
                callerResolver.getMethodName(), message, args));
    }


    public static void ResponseTime(
            Long responseTime,
            String message,
            Object... args) {
        StackTraceElement callerResolver = CallerResolver.resolveCaller();
        submit(convertResponseTime(responseTime, LogContext.getEventType().orElse(UNKNOWN_EVENT_TYPE),
                callerResolver.getClassName(), callerResolver.getMethodName(), message, args));
    }

    public static void ResponseTimeWithClassMethod(
            Long responseTime,
            String className,
            String methodName,
            String message,
            Object... args) {
        submit(convertResponseTime(responseTime, LogContext.getEventType().orElse(UNKNOWN_EVENT_TYPE), className,
                methodName, message, args));
    }

    protected static void ResponseTimeWithEventTypeClassMethod(
            String eventType,
            Long responseTime,
            String className,
            String methodName,
            String message,
            Object... args) {
        submit(convertResponseTime(responseTime, eventType, className, methodName, message, args));
    }

    private static InfoLog convertInfoLog(String eventType, String className, String methodName,
                                          String message, Object[] args) {
        return InfoLog.of(
                LogContext.serviceName,
                eventType,
                className,
                methodName,
                message,
                args
        );
    }

    private static BaseLog convertErrorLog(String eventType, Exception e, int status,
                                           String message, Object[] args) {
        return ErrorLog.of(
                LogContext.serviceName,
                eventType,
                e,
                status,
                message,
                args
        );
    }

    private static BaseLog convertResponseTime(Long responseTime, String eventType, String className, String methodName,
                                               String message, Object[] args) {
        return ResponseTimeLog.of(
                LogContext.serviceName,
                eventType,
                responseTime,
                className,
                methodName,
                message,
                args);
    }

    private static void submit(BaseLog log) {
        LogContext.threadPool().submit(new LogWorker(LogContext.storage(), log));
    }

}
