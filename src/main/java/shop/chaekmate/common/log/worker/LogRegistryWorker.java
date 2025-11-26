package shop.chaekmate.common.log.worker;

import lombok.extern.slf4j.Slf4j;
import shop.chaekmate.common.log.config.ServerInfo;
import shop.chaekmate.common.log.registry.RegistrySendRequest;

@Slf4j
public class LogRegistryWorker implements Runnable {
    private final RegistrySendRequest registrySendRequest;
    private final ServerInfo serverInfo;

    public LogRegistryWorker(ServerInfo serverInfo, RegistrySendRequest registrySendRequest) {
        this.registrySendRequest = registrySendRequest;
        this.serverInfo = serverInfo;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(3000);
                registrySendRequest.registry(serverInfo.getInstanceId(), serverInfo.getPort());
                return;
            } catch (Exception e) {
                log.info("registry fail retry");
            }
        }
    }
}
