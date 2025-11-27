package shop.chaekmate.common.log.registry;

import jakarta.annotation.PreDestroy;
import shop.chaekmate.common.log.config.ServerInfo;

public class LogUnRegistry {
    private final RegistrySendRequest registrySendRequest;
    private final ServerInfo serverInfo;

    public LogUnRegistry(RegistrySendRequest registrySendRequest, ServerInfo serverInfo) {
        this.registrySendRequest = registrySendRequest;
        this.serverInfo = serverInfo;
    }

    @PreDestroy
    public void onShutdown() {
        registrySendRequest.unRegistry(serverInfo.getInstanceId());
    }
}