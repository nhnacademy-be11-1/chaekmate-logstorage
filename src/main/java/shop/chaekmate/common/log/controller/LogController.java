package shop.chaekmate.common.log.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.chaekmate.common.log.dto.BaseLog;
import shop.chaekmate.common.log.storage.LogStorage;

@RestController
@RequiredArgsConstructor
public class LogController {
    private final LogStorage logStorage;

    @GetMapping("/logs")
    public ResponseEntity<List<BaseLog>> getLogs(){
        return ResponseEntity.ok(logStorage.flush());
    }
}
