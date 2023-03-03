import tasktypes.Task;

import java.util.ArrayList;
import java.util.InputMismatchException;
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
    public static final String WELCOME_LOAD_PROMPT = "You may load existing data using the load command";
    public static final String ITEM_NUMBER_PROMPT = "What is the number of the item in the list?";
    public static final String REJECTED_NON_NUMBER_INPUT = "Please enter a number";
    public static final String DUEDATE_PROMPT = "When is this due by?";
    public static final String EVENT_START_PROMPT = "When does this event start?";
    public static final String EVENT_END_PROMPT = "When does this event end?";
    public static final String ITEM_DESCRIPTION_PROMPT = "What are you referring to?";
    public static final int NOT_FOUND_IN_STRING = -99;

    /**
     * prints the exit message when shutdown is desired
     */
    public static void exitMessage() {
        System.out.println(EXIT_MESSAGE);
    }

    /**
     * prints a welcome message on startup.
     * Also prompts user if they would like to load an existing file
     */
    public static void welcome() {
        System.out.println(WELCOME_MESSAGE);
        System.out.println(WELCOME_LOAD_PROMPT);
    }

    /**
     * gets the description from the user for a new task object
     * will prompt the user if additional information is required
     * @param userInput input string from the user
     * @return returns the description of the task
     */
    public static String getItemDescription(String userInput) {
        Scanner in = new Scanner(System.in);
        String description;
        try {
            description = userInput.split(" ", 2)[DESCRIPTION_INDEX];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(ITEM_DESCRIPTION_PROMPT);
            description = in.nextLine().trim();
        }
        if (description.contains("/")) {
            description = description.split("/",2)[0];
        }
        return description;
    }

    /**
     * gets the item number for the user supplied command to act on
     * @param userInput command input from the user
     * @return returns number of the item in the tasklist (1-based)
     */
    public static int getItemNumber(String userInput) {
        Scanner in = new Scanner(System.in);
        int itemNumber;
        try {
            itemNumber = Integer.parseInt(userInput.split(" ", 2)[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printList(TaskList.getList(),"","");
            System.out.println(ITEM_NUMBER_PROMPT);
            try {
                itemNumber = in.nextInt();
            } catch (InputMismatchException i){
                System.out.println(REJECTED_NON_NUMBER_INPUT);
                itemNumber = getItemNumber(userInput);
            }
        } catch (NumberFormatException n) {
            itemNumber = getItemNumber(userInput);
        }
        return itemNumber;
    }

    /**
     * gets the item index for the user supplied command to act on
     * @param userInput command input from the user
     * @return returns index of the item in the tasklist (0-based)
     */
    public static int getItemIndex(String userInput) {
        return getItemNumber(userInput) - 1;
    }

    /**
     * gets a duedate for the creation of a new deadline object
     * will prompt the user if additional information is required
     * @param userInput command input from the user
     * @return duedate of the deadline object
     */
    public static String getDueDate(String userInput) {
        Scanner in = new Scanner(System.in);
        String dueDate;
        if (userInput.contains(DEADLINE_USERINPUT_PREFIX)) {
            dueDate = userInput.substring(userInput.indexOf(DEADLINE_USERINPUT_PREFIX));
        } else {
            System.out.println(DUEDATE_PROMPT);
            dueDate = in.nextLine().trim();
        }
        return dueDate;
    }

    /**
     * gets the index of the startdate marker in the user supplied input
     * @param userInput user supplied input
     * @return index of the startdate marker, else -99 if not found
     */
    public static int getStartMarkerIndex(String userInput) {
        if (userInput.contains(STARTDATE_USERINPUT_PREFIX)) {
            return userInput.indexOf(STARTDATE_USERINPUT_PREFIX);
        } else {
            return NOT_FOUND_IN_STRING;
        }
    }

    /**
     * gets the index of the enddate marker in the user supplied input
     * @param userInput user supplied input
     * @return index of the enddate marker, else -99 if not found
     */
    public static int getEndMarkerIndex(String userInput) {
        if (userInput.contains(ENDDATE_USERINPUT_PREFIX)) {
            return userInput.indexOf(ENDDATE_USERINPUT_PREFIX);
        } else {
            return NOT_FOUND_IN_STRING;
        }
    }
    /**
     * gets the startdate from the user supplied input
     * will prompt the user to enter the startdate if not found
     * @param userInput user supplied command input
     * @return startdate of the event
     */
    public static String getStartDate(String userInput) {
        Scanner in = new Scanner (System.in);
        int startMarkerIndex = getStartMarkerIndex(userInput);
        int endMarkerIndex = getEndMarkerIndex(userInput);
        if (startMarkerIndex == NOT_FOUND_IN_STRING) {
            System.out.println(EVENT_START_PROMPT);
            return in.nextLine().trim();
        } else {
            if (endMarkerIndex == NOT_FOUND_IN_STRING) {
                return userInput.substring(startMarkerIndex + 1);
            } else {
                return userInput.substring(startMarkerIndex + 1,endMarkerIndex);
            }
        }
    }

    /**
     * gets the enddate from the user supplied input
     * will prompt the user to enter the enddate if not found
     * @param userInput user supplied command input
     * @return enddate of the event
     */
    public static String getEndDate(String userInput) {
        Scanner in = new Scanner (System.in);
        int startMarkerIndex = getStartMarkerIndex(userInput);
        int endMarkerIndex = getEndMarkerIndex(userInput);
        if (endMarkerIndex == NOT_FOUND_IN_STRING) {
            System.out.println(EVENT_END_PROMPT);
            return in.nextLine().trim();
        } else {
            if (startMarkerIndex > endMarkerIndex) {
                return userInput.substring(endMarkerIndex + 1,startMarkerIndex);
            } else {
                return userInput.substring(endMarkerIndex + 1);
            }
        }
    }
    /**
     * gets the start and end dates for the creation of a new event object
     * will prompt the user if additional information is required
     * @param userInput command input from the user
     * @return a string array containing the start and end dates respectively
     */
    public static String[] getStartEndDates(String userInput) {
        String[] StartEndDates = new String[2];
        StartEndDates[STARTDATE_INDEX] = getStartDate(userInput);
        StartEndDates[ENDDATE_INDEX] = getEndDate(userInput);
        return StartEndDates;
    }

    /**
     * prints a given arrayList of Tasks with a header and footer if required
     * @param itemList arrayList of Task objects
     * @param header line to print before the arrayList. input empty string if not required
     * @param footer line to print after the arrayList. input empty string if not required
     */
    public static void printList(ArrayList<Task> itemList, String header, String footer) {
        if (!header.isBlank()) {
            System.out.println(header);
        }
        for (int i = 0 ; i < itemList.size(); ++i) {
            System.out.print((i+1)+ ". ");
            System.out.println(itemList.get(i).getTask());
        }
        if (!footer.isBlank()) {
            System.out.println(footer);
        }
    }
}
