import java.util.Arrays;
import java.util.Scanner;
public class Duke {



    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        String userInput;
        Scanner in = new Scanner(System.in);


        //String[] userCommands = new String[0];
        Task[] userTasks = new Task[0];



        Boolean isContinue = true;
        while (isContinue) {
            userInput = in.nextLine();
            String[] userCommands = userInput.split(" ");
            String userCommand = userCommands[0];

            int taskIndex; // for the mark and unmark case where userCommands has two elements 1. task name 2. task index (if mark or unmark cmd)
            //Task newTask;
            switch (userCommand) {

            case "list":
                for(int i = 0; i < userTasks.length; i++) {
                    if (userTasks[i].getisDone()) {
                        System.out.println( (i+1) + ". " + userTasks[i]);
                    } else {
                        System.out.println((i + 1) + ". " + userTasks[i]);
                    }
                }
                break;

            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                isContinue = false;
                break;

            case "mark":
                System.out.println("Nice! I've marked this task as done:");

                taskIndex = Integer.parseInt(userCommands[1]) - 1;
                userTasks[taskIndex].setisDone(true);
                //System.out.println("  [X] " + userTasks[taskIndex].getTaskName());
                System.out.println(userTasks[taskIndex]);
                break;

            case "unmark":
                System.out.println("OK, I've marked this task as not done yet:");
                taskIndex = Integer.parseInt(userCommands[1]) - 1;
                userTasks[taskIndex].setisDone(false);
                //System.out.println("  [ ] " + userTasks[taskIndex].getTaskName());
                System.out.println(userTasks[taskIndex]);
                break;

            case "todo":
                System.out.println("Got it. I've added this task:"); // shift this line below with the another print statement later
                String taskString = GetTaskString(userInput,userCommand);
                String taskName = GetToDoTaskName(taskString);
                Todo newToDoTask = new Todo(taskName);
                userTasks = AddUserTask(userTasks, newToDoTask);
                System.out.println(userTasks[userTasks.length-1]);
                System.out.println("Now you have " + userTasks.length + " in the list.");
                break;


            case "deadline":
                System.out.println("Got it. I've added this task:"); // shift this line below with the another print statement later
                taskString = GetTaskString(userInput,userCommand);
                taskName = GetDeadlineTaskName(taskString);

                String deadlineDueDate = GetDeadlineDueDateString(taskString);
                Deadline newDeadlineTask = new Deadline(taskName,deadlineDueDate);
                userTasks = AddUserTask(userTasks, newDeadlineTask);
                System.out.println(userTasks[userTasks.length-1]);
                System.out.println("Now you have " + userTasks.length + " in the list.");
                break;

            case "event":
                System.out.println("Got it. I've added this task:"); // shift this line below with the another print statement later
                taskString = GetTaskString(userInput,userCommand);
                taskName = GetEventTaskName(taskString);

                String eventFromDate = GetEventFromDate(taskString);
                String eventToDate = GetEventToDate(taskString);
                Event newEventTask = new Event(taskName,eventFromDate,eventToDate);
                userTasks = AddUserTask(userTasks, newEventTask);
                System.out.println(userTasks[userTasks.length-1]);
                System.out.println("Now you have " + userTasks.length + " in the list.");
                break;


            default:
                Task newTask = new Task(userInput);
                userTasks = AddUserTask(userTasks, newTask);
                System.out.println("added: " + newTask.getTaskName());
                System.out.println("Now you have " + userTasks.length + " in the list.");
                break;
            }
        }




    }

    private static Task[] AddUserTask(Task[] userTasks, Task newTask) {
        userTasks = Arrays.copyOf(userTasks, userTasks.length+1);
        userTasks[userTasks.length-1] = newTask;
        return userTasks;
    }

    private static String GetToDoTaskName(String taskString) {
        return taskString;
    }
    private static String GetDeadlineTaskName(String taskString) {
        int slashIndex = taskString.indexOf("/");
        String taskName = taskString.substring(0,slashIndex);
        return taskName;
    }

    private static String GetEventTaskName(String taskString) {
        int slashIndex = taskString.indexOf("/");
        String taskName = taskString.substring(0,slashIndex);
        return taskName;
    }

    private static String GetEventFromDate (String taskString) {
        int slashIndex = taskString.indexOf("/");
        int secondSlashIndex = taskString.indexOf("/",slashIndex+1);
        String fromDate = taskString.substring(slashIndex+5,secondSlashIndex); // + 4 to account for the "from"
        fromDate = fromDate.trim();
        return fromDate;
    }

    private static String GetEventToDate (String taskString) {
        int slashIndex = taskString.indexOf("/");
        int secondSlashIndex = taskString.indexOf("/",slashIndex+1);
        String fromDate = taskString.substring(secondSlashIndex+3); // change this magic literal later
        fromDate = fromDate.trim();
        return fromDate;
    }

    private static String GetDeadlineDueDateString(String taskString) {
        int slashIndex = taskString.indexOf("/");
        String dueDateString = taskString.substring(slashIndex+3); // +3 to account for the /by
        dueDateString = dueDateString.trim();
        return dueDateString;
    }
    private static String GetTaskString(String userInput, String userCommand) {
        return userInput.substring(userCommand.length()+1);
    }
}




//    int indexOfFirstSpace = userInput.indexOf(" ");
//            System.out.println(indexOfFirstSpace);
//
//                    String userCommand = "";
//                    String userTaskString = "";
//                    System.out.println("userInput " + userInput);
//                    if (indexOfFirstSpace != -1) {
//                    userCommand = userInput.substring(0, indexOfFirstSpace);
//                    System.out.println("userCommand: " + userCommand);
//                    userTaskString = userInput.substring(userCommand.length()+1);
//                    } else if (userInput == "list") {
//                    userCommand = "list";
//                    System.out.println("list case ");
//                    } else {
//                    userTaskString = userInput;
//                    }