import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;


public class Duke {

    public static ArrayList<Task> tasks = new ArrayList<>();

    //public static int tasksLength = 0;

    public static void printTasks() {
        Greeting.printSeperator();
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t" + (i+1) + "." + tasks.get(i).printTask());
        }
        Greeting.printSeperator();
    }

    public static void printNewTask(int taskNumber) {
        Greeting.printSeperator();
        System.out.println("\tGot it. I've added this task: \n"+ "\t\t" + tasks.get(tasks.size()-1).printTask());
        //tasksLength ++;
        System.out.println("\tNow you have " + tasks.size() + " tasks in the list.");
        Greeting.printSeperator();
    }

    public static void mark(String userInput) {
        int taskNumber = Integer.parseInt(userInput);
        tasks.get(taskNumber - 1).MarkStatusDone();
        Greeting.printSeperator();
        System.out.println("\tNice! I've marked this task as done:\n" +
                "\t\t" + tasks.get(taskNumber - 1).printTask());
        Greeting.printSeperator();
    }

    public static void unmark(String userInput) {
        int taskNumber = Integer.parseInt(userInput);
        tasks.get(taskNumber - 1).MarkStatusUndone();
        Greeting.printSeperator();
        System.out.println("\tOK, I've marked this task as not done yet:\n" +

                "\t\t" + tasks.get(taskNumber - 1).printTask());
        Greeting.printSeperator();
    }

    public static void deleteTask(String userInput) {
        int taskNumber = Integer.parseInt(userInput);
        Task taskToBeRemoved = tasks.get(taskNumber - 1);
        tasks.remove(taskNumber - 1);
        Greeting.printSeperator();
        System.out.println("\tNoted. I've removed this task:\n" +
                "\t\t" + taskToBeRemoved.printTask());
        Greeting.printSeperator();
    }

    public static void ChatPolling() {
        String userInput;
        Scanner in = new Scanner(System.in);
        userInput = in.nextLine();
        String[] inputType = userInput.split(" ", 2);
        Boolean isPolling = true;

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

            case "delete":
                String taskNumberDelete = userInput.substring(7);
                deleteTask(taskNumberDelete);
                break;

            case "todo":
                try {
                    addTodo(userInput);
                    printNewTask(tasks.size());
                } catch (IllegalInputException e){
                    Greeting.printEmptyTask();
                }
                break;

            case "deadline":
                try {
                    addDeadline(userInput);
                    printNewTask(tasks.size());
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
                    printNewTask(tasks.size());
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
        isPolling = false;

    }

    private static void updateFile() {
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
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
                    writeToFile("T:"+taskStatusBinary+":"+taskName+":"+deadlineDay);
                } catch (IOException e){
                    System.out.println(e);
                }
                break;
            case "E":
                String eventFrom = currentTask.getFrom();
                String eventTo = currentTask.getTo();
                try {
                    writeToFile("T:"+taskStatusBinary+":"+taskName+":"+eventTo+":"+eventFrom);
                } catch (IOException e){
                    System.out.println(e);
                }
                break;
            default:
                Greeting.printHelp();
            }
        }
    }

     
    private static void addTodo(String userInput) throws IllegalInputException {

        String todoTask = userInput.substring(4).trim();
        if (todoTask == "") {
            throw new IllegalInputException();
        }
        tasks.add(new Todo (todoTask));

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
        tasks.add(new Deadline(deadlineTask, deadlineDay));

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
        tasks.add(new Event(eventTask, eventFrom, eventTo));

        try {
            writeToFile("T:0:"+eventTask+":"+eventFrom+":"+eventTo);
        } catch (IOException e){
            System.out.println(e);
        }

        return;
    }

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
                tasks.add(new Todo (taskName));
                break;
            case "D":
                String deadlineDay = newInput[3];
                tasks.add(new Deadline(taskName, deadlineDay));
                break;
            case "E":
                String eventFrom = newInput[3];
                String eventTo = newInput[4];
                tasks.add(new Event(taskName, eventFrom, eventTo));
                break;
            default:
                Greeting.printHelp();
            }
            if (newInput[1].equals("1")) {
                tasks.get(tasks.size() - 1).MarkStatusDone();
            }
        }
    }

    private static void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter("data/duke.txt", true);
        fw.write(textToAdd+System.lineSeparator());
        fw.close();
    }

    private static void clearFile() throws IOException {
        FileWriter fw = new FileWriter("data/duke.txt");
        fw.write("");
        fw.close();
    }

    public static void main (String[]args){
        try {
            readFileContents("data/duke.txt");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        Greeting.printLogo();
        Greeting.printWelcome();
        ChatPolling();
        Greeting.printGoodbye();
    }
}
