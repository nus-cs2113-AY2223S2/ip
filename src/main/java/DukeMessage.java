public class DukeMessage {
    public static final String GREETING = " Hello! I'm Duke\n"
            + " What can I do for you?";

    public static final String GOODBYE = " Bye. Hope to see you again soon!";

    public static final String LINE_DIVIDER = "____________________________________________________________";

    public static void lineBreak(){
        System.out.println(LINE_DIVIDER);
    }
    public static void greet(){
        lineBreak();
        System.out.println(GREETING);
    }
    public static void goodbye(){
        lineBreak();
        System.out.println(GOODBYE);
    }

}
