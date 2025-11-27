package shop.chaekmate.common.log.registry;

public class LogRegistryInitializer {
    private final LogRegistryClient client;

    public LogRegistryInitializer(LogRegistryClient client) {
        this.client = client;
    }

    public void init() {
        client.register();
    }
}