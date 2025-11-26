package shop.chaekmate.common.log.worker;

import shop.chaekmate.common.log.dto.BaseLog;
import shop.chaekmate.common.log.storage.LogStorage;

public class LogWorker implements Runnable {
    private final LogStorage logStorage;
    private final BaseLog baseLog;
    public LogWorker(LogStorage logStorage, BaseLog baseLog) {
        this.logStorage = logStorage;
        this.baseLog = baseLog;
    }

    @Override
    public void run() {
        logStorage.offer(baseLog);
    }
}
