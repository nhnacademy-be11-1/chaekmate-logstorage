package shop.chaekmate.common.log.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import shop.chaekmate.common.log.logging.LogContext;

public class LogInterceptor implements HandlerInterceptor {
    String serverName;
    public LogInterceptor(String serviceName) {
        this.serverName = serviceName;
    }
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) {
        String httpMethod = req.getMethod();
        String path = req.getRequestURI();
        String serviceName = serverName;
        String eventType = String.format("%s:%s:%s", serviceName, httpMethod, path);
        LogContext.setThreadLocal(eventType);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object handler, Exception ex) {
        LogContext.clearThreadLocal();
    }
}
