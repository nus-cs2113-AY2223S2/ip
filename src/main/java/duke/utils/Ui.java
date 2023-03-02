package duke.utils;

public class Ui {
    public static String LINE = "────────────────────────────────────────────────────────────────────────\n";
    public static String GENERAL_ERROR_MESSAGE = LINE + "Invalid input. Please try again! (=O^O=)\n" + LINE;
    public static String INVALID_NUM_ERROR_MESSAGE = LINE + "The task number is out of bound. Please try again! (=O^O=)\n" + LINE;
    public static String EVENT_TIME_ERROR_MESSAGE = LINE + "There is no start and end time for the event. " +
            "Please try again by using the keywords /from and /to! (=O^O=)\n" + LINE;
    public static String DEADLINE_TIME_ERROR_MESSAGE = LINE + "There is no deadline for this task. " +
            "Please try again by using the keywords /by! (=O^O=)\n" + LINE;
    public static String LOGO = "  ／l、     meow    \n" +
            "（ﾟ､ ｡ ７         \n" +
            "  l  ~ヽ       \n" +
            "  じしf_,)ノ ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀";
    public static String GREETING = LINE +
            " Meow! I'm Ashy n(=^-^=)n\n" +
            " What can I do for you?\n" +
            LINE;

    public static String FAREWELL_MESSAGE =
            " Bye. Hope to see you again soon meow!\n" + LINE;
   public void printGreetingMessage() {
       System.out.println(LOGO);
       System.out.println(GREETING);
   }
   public void printFarewellMessage() {
       System.out.println(FAREWELL_MESSAGE);
   }
    public void printListTasksMessage() {
        System.out.println("Here is your list!");
    }
    public void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }
}
