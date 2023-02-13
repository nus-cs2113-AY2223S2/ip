package shizuka;

public class Printer {
    final static String LOGO = "   _____  __     _               __        \n"
            + "  / ___/ / /_   (_)____  __  __ / /__ ____ _\n"
            + "  \\__ \\ / __ \\ / //_  / / / / // //_// __ `/\n"
            + " ___/ // / / // /  / /_/ /_/ // ,<  / /_/ /\n"
            + "/____//_/ /_//_/  /___/\\__,_//_/|_| \\__,_/ \n";
    final static String LINE_BREAK = "____________________\n";
    final static String GREETING = "Hello, I am Shizuka.\nHow can I be of assistance?\n";
    final static String EXIT = "Goodbye.\n";
    final static String PARSE_ERROR = "I'm sorry, but I don't recognise that command.\n";
    final static String FORMAT_ERROR = "Please reformat your command input.\n";
    final static String NO_ARGS_ERROR = "Please provide arguments for this command.";
    final static String IO_ERROR = "I'm sorry, but I'm having trouble reading your file.\n";
    static final String HAVE_ADDED = "I have added ";
    static final String TO_LIST = " to your todo list.\n";

    public static void intro() {
        System.out.println(LINE_BREAK + LOGO + LINE_BREAK + GREETING + LINE_BREAK);
    }

    public static void parseError() {
        System.out.println(LINE_BREAK + PARSE_ERROR + LINE_BREAK);
    }

    public static void exit() {
        System.out.println(LINE_BREAK + EXIT + LINE_BREAK);
    }

    public static void formatError() {
        System.out.println(LINE_BREAK + FORMAT_ERROR + LINE_BREAK);
    }

    public static void noArgsError() {
        System.out.println(LINE_BREAK + NO_ARGS_ERROR + LINE_BREAK);
    }

    public static void addToList(String args) {
        System.out.println(LINE_BREAK + HAVE_ADDED + args + TO_LIST + LINE_BREAK);
    }

    public static void ioError() {
        System.out.println(LINE_BREAK + IO_ERROR + LINE_BREAK);
    }
}
