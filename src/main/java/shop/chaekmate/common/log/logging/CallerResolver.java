package shop.chaekmate.common.log.logging;

public class CallerResolver {
    private static final String LOG_CLASS = "shop.chaekmate.common.log.logging.Log";
    public static StackTraceElement resolveCaller() {
        StackTraceElement[] stack = Thread.currentThread().getStackTrace();
        for (int i = 0; i < stack.length - 1; i++) {
            if (stack[i].getClassName().equals(LOG_CLASS)) {
                return stack[i + 1];
            }
        }
        return stack[stack.length - 1];
    }
}
