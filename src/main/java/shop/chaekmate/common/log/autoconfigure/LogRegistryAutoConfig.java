package shop.chaekmate.common.log.autoconfigure;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import shop.chaekmate.common.log.config.ServerInfo;
import shop.chaekmate.common.log.registry.LogRegistryClient;
import shop.chaekmate.common.log.registry.LogRegistryInitializer;
import shop.chaekmate.common.log.registry.LogUnRegistry;
import shop.chaekmate.common.log.registry.RegistrySendRequest;

@Configuration
@ConditionalOnProperty(
        prefix = "chaekmate.logstorage",
        name = "enabled",
        havingValue = "true",
        matchIfMissing = true
)
@EnableFeignClients(basePackageClasses = RegistrySendRequest.class)
public class LogRegistryAutoConfig {
    @Bean
    public ServerInfo serverInfo(
            @Value("${spring.application.name:missingName}") String serviceName,
            @Value("${server.port}") String port
    ) {
        return new ServerInfo(serviceName, port);
    }
    @Bean(name = "logRegistryExecutorService")
    public ExecutorService logRegistryExecutorService() {
        return Executors.newSingleThreadExecutor();
    }

    @Bean
    public LogRegistryClient registryClient(
            ServerInfo serverInfo,
            @Qualifier("logRegistryExecutorService") ExecutorService executorService,
            RegistrySendRequest registrySendRequest
    ) {
        return new LogRegistryClient(serverInfo, executorService, registrySendRequest);
    }

    @Bean
    public LogRegistryInitializer registryInitializer(LogRegistryClient client) {
        return new LogRegistryInitializer(client);
    }

    @Bean
    public LogUnRegistry logUnRegistry(RegistrySendRequest registrySendRequest, ServerInfo serverInfo) {
        return new LogUnRegistry(registrySendRequest, serverInfo);
    }

    @Bean
    public ApplicationRunner registryStarter(LogRegistryInitializer initializer) {
        return args -> initializer.init();
    }
}