package shop.chaekmate.common.log.worker;

import lombok.extern.slf4j.Slf4j;
import shop.chaekmate.common.log.registry.RegistrySendRequest;

@Slf4j
public class LogRegistryWorker implements Runnable {
    private final RegistrySendRequest registrySendRequest;

    public LogRegistryWorker(RegistrySendRequest registrySendRequest) {
        this.registrySendRequest = registrySendRequest;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(3000);
                registrySendRequest.registry("a", "b");
                return;
            } catch (Exception e) {
                log.info("registry fail retry");
            }
        }
    }
}
