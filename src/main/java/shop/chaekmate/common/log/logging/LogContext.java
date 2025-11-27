package shop.chaekmate.common.log.logging;

import jakarta.annotation.PostConstruct;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import shop.chaekmate.common.log.config.LogWebConfig;
import shop.chaekmate.common.log.executor.LogThreadPool;
import shop.chaekmate.common.log.storage.LogStorage;

@RequiredArgsConstructor
public class LogContext {
    private static final ThreadLocal<String> eventType = new ThreadLocal<>();
    public static boolean enabled = false;

    private final LogThreadPool logThreadPool;
    private final LogStorage logStorage;
    private final LogWebConfig logWebConfig;
    private static LogThreadPool staticThreadPool;
    private static LogStorage staticStorage;
    public static String serviceName;
    @PostConstruct
    public void init() {
        staticThreadPool = this.logThreadPool;
        staticStorage = this.logStorage;
        serviceName = this.logWebConfig.serviceName;
    }
    public static LogThreadPool threadPool() {
        return staticThreadPool;
    }

    public static LogStorage storage() {
        return staticStorage;
    }

    public static void setEnabled(boolean isEnabled) {
        enabled = isEnabled;
    }

    public static Optional<String>  getEventType(){
        return Optional.ofNullable(eventType.get());
    }
    public static void setThreadLocal(String type) {
        eventType.set(type);
    }
    public static void clearThreadLocal(){
        eventType.remove();
    }
}