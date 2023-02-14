package duke;

public class Processor {
    public static String[] command(String userCommand) {
        final String[] split = userCommand.trim().split("\\s+", 2);
        return (split.length == 2) ? split : new String[]{split[0], ""};
    }

    public static String[] deadline(String param) {
        String[] split = param.trim().split("\\s/by\\s", 2);
        return split.length == 2 ? split : null;
    }

    public static String[] event(String param) {
        String[] split = param.trim().split("\\s/from\\s|\\s/to\\s", 3);
        return split.length == 3 ? split : null;
    }
}
