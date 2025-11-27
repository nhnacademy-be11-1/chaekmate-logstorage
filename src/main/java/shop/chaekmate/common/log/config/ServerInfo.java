package shop.chaekmate.common.log.config;

import java.util.UUID;
import lombok.Getter;

@Getter
public class ServerInfo {

    private final String instanceId;
    private final String port;

    public ServerInfo(String serviceName, String port) {
        this.instanceId = serviceName + "-" + UUID.randomUUID().toString().substring(0, 8);
        this.port = port;
    }

}