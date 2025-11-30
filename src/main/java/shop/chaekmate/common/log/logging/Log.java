package shop.chaekmate.common.log.logging;

import shop.chaekmate.common.log.dto.*;
import shop.chaekmate.common.log.worker.LogWorker;

import java.util.Map;

public class Log {
    private static final String UNKNOWN_EVENT_TYPE = "UNKNOWN";

    public static void TrafficWithEventType(
            String eventType) {
        if (!LogContext.enabled) {
            return;
        }
        StackTraceElement caller = CallerResolver.resolveCaller();
        submit(convertTrafficLog(eventType, caller.getClassName(), caller.getMethodName()));
    }


    public static void Traffic() {
        if (!LogContext.enabled) {
            return;
        }

        StackTraceElement caller = CallerResolver.resolveCaller();
        submit(convertTrafficLog(LogContext.getEventType().orElse(UNKNOWN_EVENT_TYPE), caller.getClassName(),
                caller.getMethodName()));
    }

    public static void TrafficWithClassMethod(
            String className,
            String method) {
        if (!LogContext.enabled) {
            return;
        }
        submit(convertTrafficLog(LogContext.getEventType().orElse(UNKNOWN_EVENT_TYPE), className, method));
    }

    protected static void TrafficWithEventTypeClassMethod(
            String eventType,
            String className,
            String methodName) {
        if (!LogContext.enabled) {
            return;
        }

        submit(convertTrafficLog(eventType, className, methodName));
    }

    public static void InfoWithEventType(
            String eventType,
            String message,
            Object... args) {
        if (!LogContext.enabled) {
            return;
        }
        StackTraceElement caller = CallerResolver.resolveCaller();
        submit(convertInfoLog(eventType, caller.getClassName(), caller.getMethodName(), message, args));
    }

    public static void Info(
            String message,
            Object... args) {
        if (!LogContext.enabled) {
            return;
        }

        StackTraceElement caller = CallerResolver.resolveCaller();
        submit(convertInfoLog(LogContext.getEventType().orElse(UNKNOWN_EVENT_TYPE), caller.getClassName(),
                caller.getMethodName(), message, args));
    }

    public static void InfoWithClassMethod(
            String className,
            String method,
            String message,
            Object... args) {
        if (!LogContext.enabled) {
            return;
        }
        submit(convertInfoLog(LogContext.getEventType().orElse(UNKNOWN_EVENT_TYPE), className, method,
                message, args));
    }

    protected static void InfoWithEventTypeClassMethod(
            String eventType,
            String className,
            String methodName,
            String message,
            Object... args) {
        if (!LogContext.enabled) {
            return;
        }

        submit(convertInfoLog(eventType, className, methodName, message, args));
    }


    public static void ErrorWithEventType(
            String eventType,
            Exception e,
            int status) {
        if (!LogContext.enabled) {
            return;
        }

        submit(convertErrorLog(eventType, e, status));
    }

    public static void Error(
            Exception e,
            int status) {
        if (!LogContext.enabled) {
            return;
        }

        submit(convertErrorLog(LogContext.getEventType().orElse(UNKNOWN_EVENT_TYPE), e, status));
    }

    public static void ResponseTimeWithEventType(
            Long responseTime,
            String eventType) {
        if (!LogContext.enabled) {
            return;
        }

        StackTraceElement callerResolver = CallerResolver.resolveCaller();
        submit(convertResponseTime(responseTime, eventType, callerResolver.getClassName(),
                callerResolver.getMethodName()));
    }


    public static void ResponseTime(
            Long responseTime) {
        if (!LogContext.enabled) {
            return;
        }

        StackTraceElement callerResolver = CallerResolver.resolveCaller();
        submit(convertResponseTime(responseTime, LogContext.getEventType().orElse(UNKNOWN_EVENT_TYPE),
                callerResolver.getClassName(), callerResolver.getMethodName()));
    }

    public static void ResponseTimeWithClassMethod(
            Long responseTime,
            String className,
            String methodName) {
        if (!LogContext.enabled) {
            return;
        }

        submit(convertResponseTime(responseTime, LogContext.getEventType().orElse(UNKNOWN_EVENT_TYPE), className,
                methodName));
    }



    protected static void ResponseTimeWithEventTypeClassMethod(
            String eventType,
            Long responseTime,
            String className,
            String methodName) {
        if (!LogContext.enabled) {
            return;
        }

        submit(convertResponseTime(responseTime, eventType, className, methodName));
    }
    public static void Action(
            String eventType, Map<String, Object> detail
    ) {
        if (!LogContext.enabled) {
            return;
        }
        StackTraceElement caller = CallerResolver.resolveCaller();
        submit(convertActionLog(eventType, caller.getClassName(), caller.getMethodName(), detail));
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

    private static ErrorLog convertErrorLog(String eventType, Exception e, int status) {
        return ErrorLog.of(
                LogContext.serviceName,
                eventType,
                e,
                status
        );
    }

    private static ResponseTimeLog convertResponseTime(Long responseTime, String eventType, String className, String methodName) {
        return ResponseTimeLog.of(
                LogContext.serviceName,
                eventType,
                responseTime,
                className,
                methodName
        );
    }

    private static TrafficLog convertTrafficLog(String eventType, String className, String methodName) {
        return TrafficLog.of(
                LogContext.serviceName,
                eventType,
                className,
                methodName
        );
    }

    private static ActionLog convertActionLog(String eventType, String className, String methodName, Map<String, Object> detail) {
        return ActionLog.of(
                LogContext.serviceName,
                eventType,
                className,
                methodName,
                detail
        );
    }

    private static void submit(BaseLog log) {
        if (!LogContext.enabled) {
            return;
        }

        LogContext.threadPool().submit(new LogWorker(LogContext.storage(), log));
    }

}
