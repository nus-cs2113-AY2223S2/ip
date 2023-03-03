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
    public static void exitMessage() {
        System.out.println(EXIT_MESSAGE);
    }
    public static void welcome() {
        System.out.println(WELCOME_MESSAGE);
        System.out.println("You may load existing data using the load command");
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

    public static int getItemNumber(String userInput) {
        Scanner in = new Scanner(System.in);
        int itemNumber;
        try {
            itemNumber = Integer.parseInt(userInput.split(" ", 2)[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            //TODO: change to updated UI code
            TaskList.viewList();
            System.out.println("What is the number of the item in the list?");
            try {
                itemNumber = in.nextInt();
            } catch (InputMismatchException i){
                System.out.println("Please enter a number");
                itemNumber = getItemNumber(userInput);
            }
        } catch (NumberFormatException n) {
            itemNumber = getItemNumber(userInput);
        }
        return itemNumber;
    }

    public static int getItemIndex(String userInput) {
        return getItemNumber(userInput) - 1;
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

    public static int getStartMarkerIndex(String userInput) {
        if (userInput.contains(STARTDATE_USERINPUT_PREFIX)) {
            return userInput.indexOf(STARTDATE_USERINPUT_PREFIX);
        } else {
            return -99;
        }
    }
    public static int getEndMarkerIndex(String userInput) {
        if (userInput.contains(ENDDATE_USERINPUT_PREFIX)) {
            return userInput.indexOf(ENDDATE_USERINPUT_PREFIX);
        } else {
            return -99;
        }
    }
    public static String getStartDate(String userInput) {
        Scanner in = new Scanner (System.in);
        int startMarkerIndex = getStartMarkerIndex(userInput);
        int endMarkerIndex = getEndMarkerIndex(userInput);
        if (startMarkerIndex == -99) {
            System.out.println("When does this event start?");
            return in.nextLine().trim();
        } else {
            if (endMarkerIndex == -99) {
                return userInput.substring(startMarkerIndex + 1);
            } else {
                return userInput.substring(startMarkerIndex + 1,endMarkerIndex);
            }
        }
    }
    public static String getEndDate(String userInput) {
        Scanner in = new Scanner (System.in);
        int startMarkerIndex = getStartMarkerIndex(userInput);
        int endMarkerIndex = getEndMarkerIndex(userInput);
        if (endMarkerIndex == -99) {
            System.out.println("When does this event end?");
            return in.nextLine().trim();
        } else {
            if (startMarkerIndex > endMarkerIndex) {
                return userInput.substring(endMarkerIndex + 1,startMarkerIndex);
            } else {
                return userInput.substring(endMarkerIndex + 1);
            }
        }
    }
    public static String[] getStartEndDates(String userInput) {
        String[] StartEndDates = new String[2];
        StartEndDates[STARTDATE_INDEX] = getStartDate(userInput);
        StartEndDates[ENDDATE_INDEX] = getEndDate(userInput);
        return StartEndDates;
    }

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
