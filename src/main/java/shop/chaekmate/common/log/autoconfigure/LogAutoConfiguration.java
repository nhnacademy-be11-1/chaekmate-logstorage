package shop.chaekmate.common.log.autoconfigure;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import shop.chaekmate.common.log.config.LogConfig;
import shop.chaekmate.common.log.config.LogWebConfig;
import shop.chaekmate.common.log.controller.LogController;
import shop.chaekmate.common.log.executor.LogThreadPool;
import shop.chaekmate.common.log.logging.LogContext;
import shop.chaekmate.common.log.storage.LogStorage;

@EnableConfigurationProperties(LogProperties.class)
@Configuration
@Import({
        LogConfig.class,
        LogThreadPool.class,
        LogStorage.class,
        LogContext.class,
        LogController.class,
        LogWebConfig.class
})
public class LogAutoConfiguration {
}