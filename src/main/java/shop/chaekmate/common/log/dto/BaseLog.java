package shop.chaekmate.common.log.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
public abstract class BaseLog {
    String logType;
    String serviceName;
    LocalDateTime occurrenceTime;
    String className;
    String methodName;
    String message;


}
