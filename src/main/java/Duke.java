import java.util.Scanner;

public class Duke {

    public static Task[] tasks = new Task[100];
    public static int tasksLength = 0;

    public static void printNewTask(int taskNumber) {
        Greeting.printSeperator();
        System.out.println("\tGot it. I've added this task: \n"+ "\t\t" + tasks[tasksLength].printTask());
        tasksLength ++;
        System.out.println("\tNow you have " + tasksLength + " tasks in the list.");
        Greeting.printSeperator();
    }

    public static void mark(String userInput) {
        int taskNumber = Integer.parseInt(userInput);
        tasks[taskNumber - 1].MarkStatusDone();
        Greeting.printSeperator();
        System.out.println("\tNice! I've marked this task as done:\n" +
                "\t\t[X] " + tasks[taskNumber - 1].getTask());
        Greeting.printSeperator();
    }

    public static void unmark(String userInput) {
        int taskNumber = Integer.parseInt(userInput);
        tasks[taskNumber - 1].MarkStatusUndone();
        Greeting.printSeperator();
        System.out.println("\tOK, I've marked this task as not done yet:\n" +
                "\t\t[ ] " + tasks[taskNumber - 1].getTask());
        Greeting.printSeperator();
    }

    public static void ChatPolling() {
        String userInput;

        while (true) {
            Scanner in = new Scanner(System.in);
            userInput = in.nextLine();
            String[] inputType = userInput.split(" ", 2);
            //System.out.println(userInput);

            switch (inputType[0]) {
                case "bye": 
                    Greeting.printSeperator();
                    return;

                case "list":
                    Greeting.printSeperator();
                    System.out.println("\tHere are the tasks in your list:");
                    for (int i = 0; i < tasksLength; i++) {
                        System.out.println("\t" + (i+1) + "." + tasks[i].printTask());
                    }
                    Greeting.printSeperator();
                    break;

                case "unmark":
                    String taskNumberUnmark = userInput.substring(7);
                    unmark(taskNumberUnmark);
                    break;

                case "mark":
                    String taskNumberMark = userInput.substring(5);
                    mark(taskNumberMark);
                    break;

                case "todo":
                    String todoTask = userInput.substring(4);
                    tasks[tasksLength] = new Todo (todoTask);
                    printNewTask(tasksLength);
                   
                    break;

                case "deadline":
                    if ((userInput.indexOf("/by") < 0 )){
                        Greeting.printHelp();;
                        break;
                    }
                    String deadlineTask = userInput.substring(8, userInput.indexOf("/by"));
                    String deadlineDay = userInput.substring(userInput.indexOf("/by") + 4);
                    tasks[tasksLength] = new Deadline(deadlineTask, deadlineDay);
                    printNewTask(tasksLength);

                    break;

                case "event":
                    if ((userInput.indexOf("/from") < 0 ) || (userInput.indexOf("/to") < 0 )){
                        Greeting.printHelp();;
                        break;
                    }
                    String eventTask = userInput.substring(5, userInput.indexOf("/"));
                    String eventFrom = userInput.substring(userInput.indexOf("/from") + 6, userInput.indexOf("/to"));
                    String eventTo = userInput.substring(userInput.indexOf("/to") + 4);
                    tasks[tasksLength] = new Event(eventTask, eventFrom, eventTo);
                    printNewTask(tasksLength);
                    
                    break;

                default:
                    Greeting.printHelp();
            }
        }
    }
    public static void main (String[]args){
        Greeting.printLogo();
        Greeting.printWelcome();
        ChatPolling();
        Greeting.printGoodbye();
    }
}