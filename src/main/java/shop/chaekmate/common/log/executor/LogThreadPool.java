package shop.chaekmate.common.log.executor;

import java.util.concurrent.ExecutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import shop.chaekmate.common.log.worker.LogWorker;

@Component
@RequiredArgsConstructor
public class LogThreadPool {
    private final ExecutorService executorService;
    public void submit(LogWorker worker){
        executorService.submit(worker);
    }
}
