package shop.chaekmate.common.log.executor;

import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import shop.chaekmate.common.log.logging.LogContext;

public class LogThreadPoolExecutor extends ThreadPoolExecutor {
    String eventType;

    public LogThreadPoolExecutor(int core, int max, long keepAlive, TimeUnit unit, BlockingQueue<Runnable> queue, String eventType) {
        super(core, max, keepAlive, unit, queue);
        this.eventType = eventType;
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        LogContext.setEventType(eventType);
        LogContext.setTraceId(UUID.randomUUID());
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        LogContext.clearThreadLocal();
    }
}
