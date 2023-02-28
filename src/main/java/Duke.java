import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static final int COMMAND_INDEX = 0;
    static final int MAX_COMMAND_LENGTH = 1;
    static final int DESCRIPTION_INDEX = 1;
    static final int STARTDATE_INDEX = 0;
    static final int ENDDATE_INDEX = 1;
    static final String DEADLINE_MARKER = "/by";

    static final String TASK_NO_EXIST = "Task does not exist!";
    static final String DELETE_TASK_MESSAGE = "Okay! I've deleted task: ";

    public static void exitMessage() {
        System.out.println("Go away Anna");
        System.out.println("O-kay bye......");
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
        if (userInput.contains("/by")) {
            dueDate = userInput.substring(userInput.indexOf("/by"));
        } else {
            System.out.println("When is this due by?");
            dueDate = in.nextLine().trim();
         }
        return dueDate;
    }

    public static String[] getStartEndDates(String userInput) {
        Scanner in = new Scanner(System.in);
        String[] StartEndDates = new String[2];

        if (userInput.contains("/from")) {
            StartEndDates[STARTDATE_INDEX] = userInput.substring(userInput.indexOf("/from"),userInput.indexOf("/to")).trim();
        } else {
            System.out.println("When does this event start?");
            StartEndDates[STARTDATE_INDEX] = in.nextLine().trim();
        }

        if (userInput.contains("/to")) {
            StartEndDates[ENDDATE_INDEX] = userInput.substring(userInput.indexOf("/to")).trim();
        } else {
            System.out.println("When does this event end?");
            StartEndDates[ENDDATE_INDEX] = in.nextLine().trim();
        }
        return StartEndDates;
    }
    public static void main(String[] args) {
        System.out.println("Hi it's Anna!\nWhat do you need to do?");
        Scanner in = new Scanner(System.in);

        while (true) {
            String userInput = in.nextLine().trim();
            ArrayList <String> input = new ArrayList<>();
            input.add(COMMAND_INDEX, userInput.split(" ", 2)[COMMAND_INDEX]);
            String inputCommand = input.get(COMMAND_INDEX);

            switch (inputCommand) {
            case "bye":
                exitMessage();
                return;
            case "list":
                if (TaskList.getNumItems() == 0) {
                    System.out.println("We are free! Let's go play!");
                } else {
                    System.out.println("Here's what we've gotta do:");
                    TaskList.viewList();
                    System.out.println("We currently have " + TaskList.getNumItems() + " tasks");
                }
                break;

            case "mark": {
                String itemNum = getItemDescription(userInput);

                TaskList.markDone(Integer.parseInt(itemNum) - 1);
                System.out.println("Okay I've marked item " + itemNum + " as done:");
                TaskList.printItem(Integer.parseInt(itemNum) - 1);
                break;
            }

            case "unmark": {
                String itemNum = getItemDescription(userInput);

                TaskList.markNotDone(Integer.parseInt(itemNum) - 1);
                System.out.println("Oh no! Are we not done with " + itemNum + " after all?");
                TaskList.printItem(Integer.parseInt(itemNum) - 1);
                break;
            }

            case "add": {
                String itemDescription = getItemDescription(userInput);
                Task newTask = new Task(itemDescription);
                TaskList.addItem(newTask);
                break;
            }

            case "delete": {
                String itemNum = getItemDescription(userInput);
                int itemIndex = Integer.parseInt(itemNum) - 1;
                if (itemIndex <= TaskList.getNumItems()-1) {
                    TaskList.deleteTask(itemIndex);
                    System.out.println(DELETE_TASK_MESSAGE + itemNum);
                } else {
                    System.out.println(TASK_NO_EXIST);
                }
                break;
            }

            case "todo": {
                String itemDescription = getItemDescription(userInput);
                Todo newTask = new Todo(itemDescription);
                TaskList.addItem(newTask);
                break;
            }

            case "deadline": {
                String itemDescription, dueDate;
                if (userInput.contains(DEADLINE_MARKER)) {
                    itemDescription = userInput.split(DEADLINE_MARKER, 2)[0];
                    dueDate = userInput.split(DEADLINE_MARKER, 2)[1];
                } else {
                    itemDescription = getItemDescription(userInput);
                    dueDate = getDueDate(userInput);
                }
                Deadline newTask = new Deadline(itemDescription,dueDate);
                TaskList.addItem(newTask);
                break;
            }

            case "event":
                String itemDescription = getItemDescription(userInput);
                String[] StartEndDates = getStartEndDates(userInput);
                String startDate = StartEndDates[STARTDATE_INDEX];
                String endDate = StartEndDates[ENDDATE_INDEX];
                Event newTask = new Event(itemDescription,startDate,endDate);
                TaskList.addItem(newTask);
                break;

            default:
                System.out.println("I didn't get that!");
                break;

            }
        }
    }
}




