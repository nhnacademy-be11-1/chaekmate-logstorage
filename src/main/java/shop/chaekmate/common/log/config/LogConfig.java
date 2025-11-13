package shop.chaekmate.common.log.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogConfig {
    @Value("${executor.default.size:1}")
    private int defaultSize;
    @Value("${executor.max.size:4}")
    private int maxSize;
    @Value("${executor.boundary.size:500}")
    private int boundarySize;
    @Bean
    public ExecutorService logExecutorService(){
        return new ThreadPoolExecutor(
                defaultSize,
                maxSize,
                60, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(boundarySize)
        );
    }
}
