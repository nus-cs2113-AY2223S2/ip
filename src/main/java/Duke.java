import java.util.Scanner;

public class Duke {

    public static Task[] tasks = new Task[100];
    public static int tasksLength = 0;

    public static void printTasks() {
        Greeting.printSeperator();
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < tasksLength; i++) {
            System.out.println("\t" + (i+1) + "." + tasks[i].printTask());
        }
        Greeting.printSeperator();
    }

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
        Scanner in = new Scanner(System.in);
        userInput = in.nextLine();
        String[] inputType = userInput.split(" ", 2);

        while (!inputType[0].equals("bye")) {

            switch (inputType[0]) {
            case "list":
                printTasks();
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
                try {
                    addTodo(userInput);
                    printNewTask(tasksLength);
                } catch (IllegalInputException e){
                    Greeting.printEmptyTask();
                }
                break;

            case "deadline":
                try {
                    addDeadline(userInput);
                    printNewTask(tasksLength);
                } catch (IllegalInputException e){
                    Greeting.printEmptyTask();
                } catch (MissingCommandException e) {
                    Greeting.printEmptyCommand();
                } catch (IllegalDayException e) {
                    Greeting.printEmptyDate();
                }
                break;

            case "event":
                try {
                    addEvent(userInput);
                    printNewTask(tasksLength);
                } catch (IllegalInputException e){
                    Greeting.printEmptyTask();
                } catch (MissingCommandException e) {
                    Greeting.printEmptyCommand();
                } catch (IllegalDayException e) {
                    Greeting.printEmptyDate();
                }
                break;

            default:
                Greeting.printHelp();
            }
            userInput = in.nextLine();
            inputType = userInput.split(" ", 2);
        }
    }

     
    private static void addTodo(String userInput) throws IllegalInputException {

        String todoTask = userInput.substring(4).trim();
        if (todoTask == "") {
            throw new IllegalInputException();
        }
        tasks[tasksLength] = new Todo (todoTask);
        return;
    }

    private static void addDeadline(String userInput) throws IllegalInputException, MissingCommandException, IllegalDayException {

        if ((userInput.indexOf("/by") < 0 )){
            throw new MissingCommandException();
        }
        String deadlineTask = userInput.substring(8, userInput.indexOf("/by")).trim();
        if (deadlineTask == "") {
            throw new IllegalInputException();
        }
        String deadlineDay = userInput.substring(userInput.indexOf("/by") + 3).trim();
        if (deadlineDay == "") {
            throw new IllegalDayException();
        }
        tasks[tasksLength] = new Deadline(deadlineTask, deadlineDay);
        return;
    }

    private static void addEvent(String userInput) throws IllegalInputException, MissingCommandException, IllegalDayException {

        if ((userInput.indexOf("/from") < 0 ) || (userInput.indexOf("/to") < 0 )){
            throw new MissingCommandException();
        }
        String eventTask = userInput.substring(5, userInput.indexOf("/")).trim();
        if (eventTask == "") {
            throw new IllegalInputException();
        }
        String eventFrom = userInput.substring(userInput.indexOf("/from") + 5, userInput.indexOf("/to")).trim();
        String eventTo = userInput.substring(userInput.indexOf("/to") + 3).trim();
        if (eventFrom == "" || eventTo == "") {
            throw new IllegalDayException();
        }
        tasks[tasksLength] = new Event(eventTask, eventFrom, eventTo);
        return;
    }



    public static void main (String[]args){
        Greeting.printLogo();
        Greeting.printWelcome();
        ChatPolling();
        Greeting.printGoodbye();
    }
}