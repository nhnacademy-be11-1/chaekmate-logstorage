package shop.chaekmate.common.log.logging;

public class CallerResolver {
    private static final String LOG_PACKAGE_PREFIX =
            CallerResolver.class.getPackage().getName();

    private static final String[] EXCLUDE_PREFIXES = {
            "java.", "javax.", "sun.", "jdk.",
            "org.springframework.aop.",
            "org.springframework.cglib.",
            "com.sun.proxy.",
            "jdk.proxy."
    };

    public static StackTraceElement resolveCaller() {
        StackTraceElement[] stack = Thread.currentThread().getStackTrace();

        boolean passedLogLibrary = false;

        for (StackTraceElement ste : stack) {
            String className = ste.getClassName();

            if (!passedLogLibrary) {
                if (isLogLibraryFrame(className)) {
                    passedLogLibrary = true;
                }
                continue;
            }

            if (isLogLibraryFrame(className) || isExcluded(className)) {
                continue;
            }

            return ste;
        }

        return stack[stack.length - 1];
    }

    private static boolean isLogLibraryFrame(String className) {
        return className.startsWith(LOG_PACKAGE_PREFIX)
                || className.equals(CallerResolver.class.getName());
    }

    private static boolean isExcluded(String className) {
        for (String prefix : EXCLUDE_PREFIXES) {
            if (className.startsWith(prefix)) {
                return true;
            }
        }
        return false;
    }
}
