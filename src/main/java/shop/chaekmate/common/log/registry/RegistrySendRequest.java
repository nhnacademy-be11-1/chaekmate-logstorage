package shop.chaekmate.common.log.registry;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "registryClient", url = "http://localhost:12345")
public interface RegistrySendRequest {
    @GetMapping("/api/registry")
    void registry(@RequestParam String id,@RequestParam String host);
    @DeleteMapping("/api/unregistry")
    void unRegistry(@RequestParam String id);
}
