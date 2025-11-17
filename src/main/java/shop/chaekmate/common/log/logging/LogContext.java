package shop.chaekmate.common.log.logging;

import jakarta.annotation.PostConstruct;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import shop.chaekmate.common.log.executor.LogThreadPool;
import shop.chaekmate.common.log.storage.LogStorage;

@Component
@RequiredArgsConstructor
public class LogContext {
    private static final ThreadLocal<String> eventType = new ThreadLocal<>();
    private final LogThreadPool logThreadPool;
    private final LogStorage logStorage;

    private static LogThreadPool staticThreadPool;
    private static LogStorage staticStorage;

    @PostConstruct
    public void init() {
        staticThreadPool = this.logThreadPool;
        staticStorage = this.logStorage;
    }

    public static LogThreadPool threadPool() {
        return staticThreadPool;
    }

    public static LogStorage storage() {
        return staticStorage;
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