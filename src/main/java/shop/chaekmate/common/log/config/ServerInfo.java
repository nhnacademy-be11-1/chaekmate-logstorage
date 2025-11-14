package shop.chaekmate.common.log.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Getter
public class ServerInfo {

    private final String instanceId;
    private final String port;

    public ServerInfo(@Value("${spring.application.name:missingName}") String serviceName, @Value("${server.port}") String port) {
        this.instanceId = serviceName + "-" + UUID.randomUUID().toString().substring(0, 8);
        this.port = port;
    }

}