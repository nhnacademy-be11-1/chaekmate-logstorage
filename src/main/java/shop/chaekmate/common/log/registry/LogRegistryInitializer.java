package shop.chaekmate.common.log.registry;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LogRegistryInitializer {
    private final LogRegistryClient client;
    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        client.register();
    }
}