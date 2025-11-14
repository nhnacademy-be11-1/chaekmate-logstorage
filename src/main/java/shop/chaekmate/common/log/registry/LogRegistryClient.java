package shop.chaekmate.common.log.registry;

import java.util.concurrent.ExecutorService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import shop.chaekmate.common.log.worker.LogRegistryWorker;

@Component
public class LogRegistryClient {
    private final ExecutorService executorService;
    private final RegistrySendRequest registrySendRequest;
    public LogRegistryClient(@Qualifier("logRegistryExecutorService") ExecutorService executorService,
                             RegistrySendRequest registrySendRequest) {
        this.executorService = executorService;
        this.registrySendRequest = registrySendRequest;
    }

    public void register() {
        try {
            registrySendRequest.registry("a","b");
        }catch (Exception e){
            executorService.submit(new LogRegistryWorker(registrySendRequest));
        }
    }

}
