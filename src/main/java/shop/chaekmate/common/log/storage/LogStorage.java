package shop.chaekmate.common.log.storage;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import shop.chaekmate.common.log.dto.BaseLog;

@Component
public class LogStorage {
    List<BaseLog> buffer = new ArrayList<>();
    public synchronized void offer(BaseLog baseLog) {
        buffer.add(baseLog);
    }
    public synchronized List<BaseLog> flush(){
        List<BaseLog> flushList = buffer;
        buffer = new ArrayList<>();
        return flushList;
    }
}
