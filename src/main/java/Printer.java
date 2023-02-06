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

    public static void intro() {
        System.out.println(LINE_BREAK + LOGO + LINE_BREAK + GREETING + LINE_BREAK);
    }
    public static void parseError(){
        System.out.println(LINE_BREAK + PARSE_ERROR + LINE_BREAK);
    }
    public static void exit(){
        System.out.println(LINE_BREAK + EXIT + LINE_BREAK);
    }
}
