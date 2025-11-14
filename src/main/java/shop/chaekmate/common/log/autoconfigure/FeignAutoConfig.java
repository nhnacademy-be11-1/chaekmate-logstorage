package shop.chaekmate.common.log.autoconfigure;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "shop.chaekmate.common.log.registry")
public class FeignAutoConfig {
}