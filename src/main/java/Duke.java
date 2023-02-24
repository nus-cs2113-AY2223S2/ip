import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;


public class Duke {
    static TaskList tasks = new TaskList();

    /*
     * =================================================================================
     *                                Polling of commands
     *                                      vvvvvvvvvv
     * =================================================================================
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
                    addTodo(userInput);
                    tasks.printNewTask(tasks.getSize());
                } catch (IllegalInputException e){
                    Greeting.printEmptyTask();
                }
                break;

            case "deadline":
                try {
                    addDeadline(userInput);
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
                    addEvent(userInput);
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
            clearFile();
        } catch (IOException e){
            System.out.println(e);
        }
        updateFile();
    }



    /*
     * =================================================================================
     *                      Adding of New Tasks (Todo/ Deadline/ Event)
     *                                      vvvvvvvvvv
     * =================================================================================
     */

    private static void addTodo(String userInput) throws IllegalInputException {

        String todoTask = userInput.substring(4).trim();
        if (todoTask == "") {
            throw new IllegalInputException();
        }
        tasks.addTask(new Todo (todoTask));

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
        tasks.addTask(new Deadline(deadlineTask, deadlineDay));

        try {
            writeToFile("T:0:"+deadlineTask+":"+deadlineDay);
        } catch (IOException e){
            System.out.println(e);
        }

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
        tasks.addTask(new Event(eventTask, eventFrom, eventTo));

        try {
            writeToFile("T:0:"+eventTask+":"+eventFrom+":"+eventTo);
        } catch (IOException e){
            System.out.println(e);
        }

        return;
    }



    /*
     * =================================================================================
     *                                File Reading/ Writing
     *                                      vvvvvvvvvv
     * =================================================================================
     */

    private static void readFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String newLine = s.nextLine(); 
            String[] newInput = newLine.split(":");
            String taskName = newInput[2];
            String taskType = newInput[0];

            switch (taskType) {
            case "T":
                tasks.addTask(new Todo (taskName));
                break;
            case "D":
                String deadlineDay = newInput[3];
                tasks.addTask(new Deadline(taskName, deadlineDay));
                break;
            case "E":
                String eventFrom = newInput[3];
                String eventTo = newInput[4];
                tasks.addTask(new Event(taskName, eventFrom, eventTo));
                break;
            default:
                Greeting.printHelp();
            }
            if (newInput[1].equals("1")) {
                tasks.MarkStatusAsDone(tasks.getSize() - 1);
            }
        }
    }

    private static void updateFile() {
        for (int i = 0; i < tasks.getSize(); i++) {
            Task currentTask = tasks.getTask(i);
            String taskStatus = currentTask.getStatusIcon();
            String taskType = currentTask.getTaskIcon();
            String taskName = currentTask.getTask();
            String taskStatusBinary = (taskStatus == "X") ? "1" : "0";

            switch (taskType) {
            case "T":
                try {
                    writeToFile("T:"+taskStatusBinary+":"+taskName);
                } catch (IOException e){
                    System.out.println(e);
                }
                break;
            case "D":
                String deadlineDay = currentTask.getBy();
                try {
                    writeToFile("D:"+taskStatusBinary+":"+taskName+":"+deadlineDay);
                } catch (IOException e){
                    System.out.println(e);
                }
                break;
            case "E":
                String eventFrom = currentTask.getFrom();
                String eventTo = currentTask.getTo();
                try {
                    writeToFile("E:"+taskStatusBinary+":"+taskName+":"+eventTo+":"+eventFrom);
                } catch (IOException e){
                    System.out.println(e);
                }
                break;
            default:
                Greeting.printHelp();
            }
        }
    }

    private static void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter("duke.txt", true);
        fw.write(textToAdd+System.lineSeparator());
        fw.close();
    }

    private static void clearFile() throws IOException {
        FileWriter fw = new FileWriter("duke.txt");
        fw.write("");
        fw.close();
    }




    /*
     * =================================================================================
     *                                         Main
     *                                      vvvvvvvvvv
     * =================================================================================
     */
    public static void main (String[]args){

        try {
            readFileContents("duke.txt");
            System.out.println("Data load successfull");
        } catch (FileNotFoundException e) {
            System.out.println("No Past Data Found. Will create a new data file after ending the Bot...");
            Greeting.printSeperator();
        }

        Greeting.printLogo();
        Greeting.printWelcome();
        ChatPolling();
        Greeting.printGoodbye();
    }
}
