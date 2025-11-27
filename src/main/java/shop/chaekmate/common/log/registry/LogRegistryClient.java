package shop.chaekmate.common.log.registry;

import java.util.concurrent.ExecutorService;
import org.springframework.beans.factory.annotation.Qualifier;
import shop.chaekmate.common.log.config.ServerInfo;
import shop.chaekmate.common.log.worker.LogRegistryWorker;

public class LogRegistryClient {
    private final ServerInfo serverInfo;
    private final ExecutorService executorService;
    private final RegistrySendRequest registrySendRequest;
    public LogRegistryClient(ServerInfo serverInfo, @Qualifier("logRegistryExecutorService") ExecutorService executorService,
                             RegistrySendRequest registrySendRequest) {
        this.serverInfo = serverInfo;
        this.executorService = executorService;
        this.registrySendRequest = registrySendRequest;
    }

    public void register() {
        try {
            registrySendRequest.registry(serverInfo.getInstanceId(),serverInfo.getPort());
        }catch (Exception e){
            executorService.submit(new LogRegistryWorker(serverInfo,registrySendRequest));
        }
    }

}
