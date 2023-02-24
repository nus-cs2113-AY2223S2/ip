import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Duke {
    static TaskList tasks = new TaskList();

    /**
    * This method polls for user commands
    */
    public static void ChatPolling() {
        String userInput;
        Scanner in = new Scanner(System.in);
        userInput = in.nextLine();
        String[] inputType = userInput.split(" ", 2);

        while (!inputType[0].equals("bye")) {
            switch (inputType[0]) {
            case "list":
                tasks.printTasks();
                break;

            case "find":
                String taskKeyWord = userInput.substring(5);
                tasks.findTask(taskKeyWord);
                break;

            case "unmark":
                String taskNumberUnmark = userInput.substring(7);
                tasks.unmark(taskNumberUnmark);
                break;

            case "mark":
                String taskNumberMark = userInput.substring(5);
                tasks.mark(taskNumberMark);
                break;

            case "delete":
                String taskNumberDelete = userInput.substring(7);
                tasks.deleteTask(taskNumberDelete);
                break;

            case "todo":
                try {
                    tasks.addTodo(userInput);
                    tasks.printNewTask(tasks.getSize());
                } catch (IllegalInputException e){
                    Greeting.printEmptyTask();
                }
                break;

            case "deadline":
                try {
                    tasks.addDeadline(userInput);
                    tasks.printNewTask(tasks.getSize());
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
                    tasks.addEvent(userInput);
                    tasks.printNewTask(tasks.getSize());
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

        try {
            Storage.clearFile();
        } catch (IOException e){
            System.out.println(e);
        }
        Storage.updateFile(tasks);
    }

    public static void main (String[]args){

        try {
            tasks = Storage.readFileContents("duke.txt");
            Greeting.printDataFound();
        } catch (FileNotFoundException e) {
            Greeting.printDataNotFound();
        }

        Greeting.printLogo();
        Greeting.printWelcome();
        ChatPolling();
        Greeting.printGoodbye();
    }
}
