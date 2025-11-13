package shop.chaekmate.common.log.autoconfigure;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import shop.chaekmate.common.log.config.LogConfig;
import shop.chaekmate.common.log.controller.LogController;
import shop.chaekmate.common.log.executor.LogThreadPool;
import shop.chaekmate.common.log.logging.Log;
import shop.chaekmate.common.log.logging.LogContext;
import shop.chaekmate.common.log.storage.LogStorage;

@Configuration
@Import({
        LogConfig.class,
        LogThreadPool.class,
        LogStorage.class,
        LogContext.class,
        LogController.class,
        Log.class
})
public class LogAutoConfiguration {
}
