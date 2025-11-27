package shop.chaekmate.common.log.autoconfigure;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import shop.chaekmate.common.log.logging.LogContext;

@Configuration
@ConfigurationProperties(prefix = "chaekmate.logstorage")
public class LogProperties {
    private boolean enabled = true;

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @PostConstruct
    public void init() {
        LogContext.setEnabled(enabled);
    }
}