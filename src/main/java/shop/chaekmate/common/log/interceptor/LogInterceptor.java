package shop.chaekmate.common.log.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import shop.chaekmate.common.log.logging.LogContext;

import java.util.UUID;

public class LogInterceptor implements HandlerInterceptor {
    private final String serverName;

    public LogInterceptor(String serviceName) {
        this.serverName = serviceName;
    }

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) {
        String httpMethod = req.getMethod();
        String path = (String) req.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        if (path == null) {
            path = req.getRequestURI();
        }
        String eventType = String.format("%s:%s:%s", serverName, httpMethod, path);
        LogContext.setEventType(eventType);
        LogContext.setTraceId(UUID.randomUUID());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object handler, Exception ex) {
        LogContext.clearThreadLocal();
    }
}
