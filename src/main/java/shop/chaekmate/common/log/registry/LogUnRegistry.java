package shop.chaekmate.common.log.registry;

import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import shop.chaekmate.common.log.config.ServerInfo;

@Component
@RequiredArgsConstructor
public class LogUnRegistry {
    private final RegistrySendRequest registrySendRequest;
    private final ServerInfo serverInfo;
    @PreDestroy
    public void onShutdown() {
        registrySendRequest.unRegistry(serverInfo.getInstanceId());
    }
}
