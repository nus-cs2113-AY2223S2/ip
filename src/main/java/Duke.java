import java.util.Scanner;

public class Duke {
    static final int COMMAND_INDEX = 0;
    static final int MAX_COMMAND_LENGTH = 1;
    static final int STARTDATE_INDEX = 0;
    static final int ENDDATE_INDEX = 1;
    public static final String DEADLINE_USERINPUT_PREFIX = "/by";
    static final String TASK_NO_EXIST = "Task does not exist!";
    static final String DELETE_TASK_MESSAGE = "Okay! I've deleted task: ";
    public static final int STATUSTYPE_DONE = 1;
    public static final int STATUSTYPE_NOTDONE = 0;


    public static void main(String[] args) {
        Ui.welcomeMessage();
        Scanner in = new Scanner(System.in);
        while (true) {
            String userInput = in.nextLine().trim();
            String inputCommand = userInput.split(" ", 2)[COMMAND_INDEX];

            switch (inputCommand) {
            case "exit":
            case "bye":
                Ui.exitMessage();
                return;
            case "list":
                Commands.showList();
                break;
            case "mark":
                Commands.markTask(userInput, STATUSTYPE_DONE);
                break;
            case "unmark": {
                Commands.markTask(userInput, STATUSTYPE_NOTDONE);
                break;
            }
            case "delete": {
                String itemNum = Ui.getItemDescription(userInput);
                int itemIndex = Integer.parseInt(itemNum) - STATUSTYPE_DONE;
                if (itemIndex <= TaskList.getNumItems() - STATUSTYPE_DONE) {
                    TaskList.deleteTask(itemIndex);
                    System.out.println(DELETE_TASK_MESSAGE + itemNum);
                } else {
                    System.out.println(TASK_NO_EXIST);
                }
                break;
            }
            case "save": {
                Storage.writeToTaskList();
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
                    itemDescription = userInput.split(DEADLINE_USERINPUT_PREFIX, 2)[STATUSTYPE_NOTDONE];
                    dueDate = userInput.split(DEADLINE_USERINPUT_PREFIX, 2)[STATUSTYPE_DONE];
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
                Commands.invalidCommand();
                break;
            }
        }
    }
}




