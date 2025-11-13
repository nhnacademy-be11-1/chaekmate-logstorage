package shop.chaekmate.common.log.logging;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import shop.chaekmate.common.log.executor.LogThreadPool;
import shop.chaekmate.common.log.storage.LogStorage;

@Component
@RequiredArgsConstructor
public class LogContext {

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
}