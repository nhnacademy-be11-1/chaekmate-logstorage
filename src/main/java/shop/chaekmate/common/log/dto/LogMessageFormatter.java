package shop.chaekmate.common.log.dto;

public class LogMessageFormatter {
    public static String format(String message, Object... args) {
        if (message == null) {
            return "";
        }
        if (args == null || args.length == 0) {
            return message;
        }

        StringBuilder sb = new StringBuilder(message.length() + 50);
        int argIndex = 0;
        int pos = 0;

        while (pos < message.length()) {
            int idx = message.indexOf("{}", pos);
            if (idx == -1) {
                sb.append(message.substring(pos));
                break;
            }
            if (idx > 0 && message.charAt(idx - 1) == '\\') {
                sb.append(message, pos, idx - 1).append("{}");
                pos = idx + 2;
                continue;
            }
            sb.append(message, pos, idx);
            if (argIndex < args.length) {
                sb.append(args[argIndex++]);
            } else {
                sb.append("{}");
            }
            pos = idx + 2;
        }
        return sb.toString();
    }
}
