import java.util.Scanner;

public class Ui {

    public static final String EXIT_MESSAGE = "Go away Anna\nO-kay bye......";
    static final int DESCRIPTION_INDEX = 1;
    public static final String STARTDATE_USERINPUT_PREFIX = "/from";
    public static final String ENDDATE_USERINPUT_PREFIX = "/to";
    public static final String DEADLINE_USERINPUT_PREFIX = "/by";

    static final int STARTDATE_INDEX = 0;
    static final int ENDDATE_INDEX = 1;
    private static final String WELCOME_MESSAGE = "Hi it's Anna!\nWhat do you need to do?";

    public static void welcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
    }

    public static void exitMessage() {
        System.out.println(EXIT_MESSAGE);
    }
    public static String getItemDescription(String userInput) {
        Scanner in = new Scanner(System.in);
        String description;
        try {
            description = userInput.split(" ", 2)[DESCRIPTION_INDEX];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("What are you referring to?");
            description = in.nextLine().trim();
        }
        return description;
    }

    public static String getDueDate(String userInput) {
        Scanner in = new Scanner(System.in);
        String dueDate;
        if (userInput.contains(DEADLINE_USERINPUT_PREFIX)) {
            dueDate = userInput.substring(userInput.indexOf(DEADLINE_USERINPUT_PREFIX));
        } else {
            System.out.println("When is this due by?");
            dueDate = in.nextLine().trim();
        }
        return dueDate;
    }
    public static String[] getStartEndDates(String userInput) {
        Scanner in = new Scanner(System.in);
        String[] StartEndDates = new String[2];

        if (userInput.contains(STARTDATE_USERINPUT_PREFIX)) {
            StartEndDates[STARTDATE_INDEX] = userInput.substring(userInput.indexOf(STARTDATE_USERINPUT_PREFIX),userInput.indexOf(ENDDATE_USERINPUT_PREFIX)).trim();
        } else {
            System.out.println("When does this event start?");
            StartEndDates[STARTDATE_INDEX] = in.nextLine().trim();
        }

        if (userInput.contains(ENDDATE_USERINPUT_PREFIX)) {
            StartEndDates[ENDDATE_INDEX] = userInput.substring(userInput.indexOf(ENDDATE_USERINPUT_PREFIX)).trim();
        } else {
            System.out.println("When does this event end?");
            StartEndDates[ENDDATE_INDEX] = in.nextLine().trim();
        }
        return StartEndDates;
    }
}
