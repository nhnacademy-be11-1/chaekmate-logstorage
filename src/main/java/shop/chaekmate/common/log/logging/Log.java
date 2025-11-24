package shop.chaekmate.common.log.logging;

import shop.chaekmate.common.log.dto.BaseLog;
import shop.chaekmate.common.log.dto.ErrorLog;
import shop.chaekmate.common.log.dto.InfoLog;
import shop.chaekmate.common.log.dto.ResponseTimeLog;
import shop.chaekmate.common.log.worker.LogWorker;
public class Log {
    private static final String UNKNOWN_EVENT_TYPE = "UNKNOWN";

    public static void InfoWithEventType(String serviceName,
                                         String eventType,
                                         String message,
                                         Object... args) {
        StackTraceElement caller = CallerResolver.resolveCaller();
        submit(convertInfoLog(serviceName, eventType, caller.getClassName(), caller.getMethodName(), message, args));
    }

    public static void Info(String serviceName,
                            String message,
                            Object... args) {
        StackTraceElement caller = CallerResolver.resolveCaller();
        submit(convertInfoLog(serviceName, LogContext.getEventType().orElse(UNKNOWN_EVENT_TYPE), caller.getClassName(),
                caller.getMethodName(), message, args));
    }

    public static void InfoWithClassMethod(String serviceName,
                                           String className,
                                           String method,
                                           String message,
                                           Object... args) {
        submit(convertInfoLog(serviceName, LogContext.getEventType().orElse(UNKNOWN_EVENT_TYPE), className, method,
                message, args));
    }

    protected static void InfoWithEventTypeClassMethod(String serviceName,
                                                       String eventType,
                                                       String className,
                                                       String methodName,
                                                       String message,
                                                       Object... args) {
        submit(convertInfoLog(serviceName, eventType, className, methodName, message, args));
    }


    public static void ErrorWithEventType(String serviceName,
                                          String eventType,
                                          Exception e,
                                          int status,
                                          String message,
                                          Object... args) {
        submit(convertErrorLog(serviceName, eventType, e, status, message, args));
    }

    public static void Error(String serviceName,
                             Exception e,
                             int status,
                             String message,
                             Object... args) {
        submit(convertErrorLog(serviceName, LogContext.getEventType().orElse(UNKNOWN_EVENT_TYPE), e, status, message,
                args));
    }

    public static void ResponseTimeWithEventType(String serviceName,
                                            Long responseTime,
                                            String eventType,
                                            String message,
                                            Object... args) {
        StackTraceElement callerResolver = CallerResolver.resolveCaller();
        submit(convertResponseTime(serviceName,responseTime,eventType,callerResolver.getClassName(),callerResolver.getMethodName(),message,args));
    }



    public static void ResponseTime(String serviceName,
                                    Long responseTime,
                                    String message,
                                    Object... args) {
        StackTraceElement callerResolver = CallerResolver.resolveCaller();
        submit(convertResponseTime(serviceName,responseTime,LogContext.getEventType().orElse(UNKNOWN_EVENT_TYPE),callerResolver.getClassName(),callerResolver.getMethodName(),message,args));
    }

    public static void ResponseTimeWithClassMethod(String serviceName,
                                                   Long responseTime,
                                                   String className,
                                                   String methodName,
                                                   String message,
                                                   Object... args){
        submit(convertResponseTime(serviceName,responseTime,LogContext.getEventType().orElse(UNKNOWN_EVENT_TYPE),className,methodName,message,args));
    }
    protected static void ResponseTimeWithEventTypeClassMethod(String serviceName,
                                                   String eventType,
                                                   Long responseTime,
                                                   String className,
                                                   String methodName,
                                                   String message,
                                                   Object... args){
        submit(convertResponseTime(serviceName,responseTime,eventType,className,methodName,message,args));
    }
    private static InfoLog convertInfoLog(String serviceName, String eventType, String className, String methodName,
                                          String message, Object[] args) {
        return InfoLog.of(
                serviceName,
                eventType,
                className,
                methodName,
                message,
                args
        );
    }

    private static BaseLog convertErrorLog(String serviceName, String eventType, Exception e, int status,
                                           String message, Object[] args) {
        return ErrorLog.of(
                serviceName,
                eventType,
                e,
                status,
                message,
                args
        );
    }
    private static BaseLog convertResponseTime(String serviceName, Long responseTime, String eventType, String className, String methodName, String message, Object[] args) {
        return ResponseTimeLog.of(
                serviceName,
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
