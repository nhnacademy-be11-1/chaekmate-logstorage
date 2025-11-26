package shop.chaekmate.common.log.executor;

import java.util.concurrent.ExecutorService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import shop.chaekmate.common.log.worker.LogWorker;

@Component
public class LogThreadPool {
    private final  ExecutorService executor;
    public LogThreadPool(@Qualifier("logExecutorService") ExecutorService executor) {
        this.executor = executor;
    }
    public void submit(LogWorker worker){
        executor.submit(worker);
    }
}
