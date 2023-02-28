import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Duke {
    static final int COMMAND_INDEX = 0;
    static final int MAX_COMMAND_LENGTH = 1;
    static final int STARTDATE_INDEX = 0;
    static final int ENDDATE_INDEX = 1;
    public static final String DEADLINE_USERINPUT_PREFIX = "/by";
    static final String TASK_NO_EXIST = "Task does not exist!";
    static final String DELETE_TASK_MESSAGE = "Okay! I've deleted task: ";
    public static final String TASKLIST_EXPORT_PATH = "TaskList.txt";
    public static final String SUCCESS_EXPORT = "Successfully exported TaskList!";
    public static final String EXPORT_ERROR_PREFIX = "Error occurred while writing to ";
    
    public static void writeToTaskList() {
        File exportTaskList = new File (TASKLIST_EXPORT_PATH);
        try {
            FileWriter writeTaskList = new FileWriter(TASKLIST_EXPORT_PATH);
            int numTasks = TaskList.getNumItems();
            for (int i = 0; i < numTasks; ++i) {
                writeTaskList.write(TaskList.getItem(i).getTask());
                writeTaskList.write(System.lineSeparator());
            }
            writeTaskList.close();
            System.out.println(SUCCESS_EXPORT);
            System.out.println("Written to: " + TASKLIST_EXPORT_PATH);
        } catch (IOException e) {
            System.out.println(EXPORT_ERROR_PREFIX + exportTaskList.getAbsolutePath());
        }
    }
    
    public static void main(String[] args) {
        Ui.welcomeMessage();
        Scanner in = new Scanner(System.in);

        while (true) {
            String userInput = in.nextLine().trim();
            ArrayList <String> input = new ArrayList<>();
            input.add(COMMAND_INDEX, userInput.split(" ", 2)[COMMAND_INDEX]);
            String inputCommand = input.get(COMMAND_INDEX);

            switch (inputCommand) {

            case "exit":
            case "bye":
                Ui.exitMessage();
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
                String itemNum = Ui.getItemDescription(userInput);

                TaskList.markDone(Integer.parseInt(itemNum) - 1);
                System.out.println("Okay I've marked item " + itemNum + " as done:");
                TaskList.printItem(Integer.parseInt(itemNum) - 1);
                break;
            }

            case "unmark": {
                String itemNum = Ui.getItemDescription(userInput);

                TaskList.markNotDone(Integer.parseInt(itemNum) - 1);
                System.out.println("Oh no! Are we not done with " + itemNum + " after all?");
                TaskList.printItem(Integer.parseInt(itemNum) - 1);
                break;
            }

            case "add": {
                String itemDescription = Ui.getItemDescription(userInput);
                Task newTask = new Task(itemDescription);
                TaskList.addItem(newTask);
                break;
            }

            case "delete": {
                String itemNum = Ui.getItemDescription(userInput);
                int itemIndex = Integer.parseInt(itemNum) - 1;
                if (itemIndex <= TaskList.getNumItems() - 1) {
                    TaskList.deleteTask(itemIndex);
                    System.out.println(DELETE_TASK_MESSAGE + itemNum);
                } else {
                    System.out.println(TASK_NO_EXIST);
                }
                break;
            }

            case "save": {
                writeToTaskList();
                break;
            }

            case "todo": {
                String itemDescription = Ui.getItemDescription(userInput);
                Todo newTask = new Todo(itemDescription);
                TaskList.addItem(newTask);
                break;
            }

            case "deadline": {
                String itemDescription, dueDate;
                if (userInput.contains(DEADLINE_USERINPUT_PREFIX)) {
                    itemDescription = userInput.split(DEADLINE_USERINPUT_PREFIX, 2)[0];
                    dueDate = userInput.split(DEADLINE_USERINPUT_PREFIX, 2)[1];
                } else {
                    itemDescription = Ui.getItemDescription(userInput);
                    dueDate = Ui.getDueDate(userInput);
                }
                Deadline newTask = new Deadline(itemDescription,dueDate);
                TaskList.addItem(newTask);
                break;
            }

            case "event":
                String itemDescription = Ui.getItemDescription(userInput);
                String[] StartEndDates = Ui.getStartEndDates(userInput);
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




