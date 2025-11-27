package shop.chaekmate.common.log.registry;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "registryClient", url = "http://localhost:10328")
public interface RegistrySendRequest {
    @PostMapping("/api/registry")
    void registry(@RequestParam("id") String id,@RequestParam("host") String host);
    @DeleteMapping("/api/unregistry")
    void unRegistry(@RequestParam("id") String id);
}
