package shop.chaekmate.common.log.registry;

import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LogUnRegistry {
    private final RegistrySendRequest registrySendRequest;
    @PreDestroy
    public void onShutdown() {
        registrySendRequest.registry("a", "server-down");
    }
}
