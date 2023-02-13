import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.security.InvalidParameterException;
import java.util.MissingFormatArgumentException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;


public class Duke {

    private static final String LINE = "___________________________________________________";
    private static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";

    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_DELETE = "delete";

    private static final int REMOVE_FROM = 5;
    private static final int REMOVE_TO = 3;
    private static final int REMOVE_BY = 3;
    private static final File F = new File("Data/duke.txt");
    public static void main(String[] args) {
        printWelcomeMessage();
        boolean canPrintFeedback;
        Scanner in = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<Task>();
        ArrayList<String> inputCommands = new ArrayList<String>();
        try {
            canPrintFeedback = false;
            loadFileContents(F, tasks,inputCommands,canPrintFeedback);
        }catch(FileNotFoundException e){
            System.out.println("File does not exist.");
        }
        while (true) {
            String userInput = in.nextLine();
            canPrintFeedback = true;
            processUserInput(tasks,inputCommands, userInput,F,canPrintFeedback);
        }

    }
    private static void writeToFile(String filePath, ArrayList<String>inputCommands) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for(String s:inputCommands){
            fw.write(s);
            fw.write('\n');

        }
        fw.close();
    }

    private static void appendToFile(String filePath, String userInput) throws IOException{
        FileWriter fw = new FileWriter(filePath,true);
        fw.write(userInput);
        fw.write('\n');
        fw.close();
    }

    private static void loadFileContents(File F, ArrayList<Task>tasks, ArrayList<String>commandInput,
                                         boolean canPrintFeedback) throws FileNotFoundException {
        Scanner s = new Scanner(F);
        while(s.hasNext()){
            processUserInput(tasks,commandInput,s.nextLine(),F,canPrintFeedback);
        }
    }
    private static String[] splitCommandAndDescription(String userInput) {
        String[] commandAndArgs = userInput.split(" ", 2);
        return commandAndArgs.length == 2 ? commandAndArgs : new String[]{commandAndArgs[0], ""};
    }

    private static void processUserInput(ArrayList<Task> tasks, ArrayList<String> inputCommands,String userInput,File F,
                                         boolean canPrintFeedback) {
        inputCommands.add(userInput);
        String[] commandAndDescription = splitCommandAndDescription(userInput);
        String command = commandAndDescription[0];
        String description = commandAndDescription[1];
        try {
            switch (command) {
            case COMMAND_BYE:
                printByeMessage();
                break;
            case COMMAND_EVENT: //append
                executeEventCommand(tasks, description,canPrintFeedback);
                appendToFile(F.getPath(),userInput);
                break;
            case COMMAND_DEADLINE: //append
                executeDeadlineCommand(tasks, description,canPrintFeedback);
                appendToFile(F.getPath(),userInput);
                break;
            case COMMAND_LIST:
                executeListCommand(tasks,canPrintFeedback);
                break;
            case COMMAND_TODO: //append
                executeTodoCommand(tasks, description,canPrintFeedback);
                appendToFile(F.getPath(),userInput);
                break;
            case COMMAND_MARK: //write
                executeMarkCommand(tasks, description,canPrintFeedback);
                writeToFile(F.getPath(), inputCommands);
                break;
            case COMMAND_UNMARK://write
                executeUnmarkCommand(tasks, description,canPrintFeedback);
                writeToFile(F.getPath(), inputCommands);
                break;
            case COMMAND_DELETE:
                executeDeleteCommand(tasks,description);
                break;
            default:
                System.out.println("No such commands found! Please try again!");
                System.out.println(LINE);
            }
        }catch(IOException e){
            System.out.println("Unable to write/append to file.");
        }

    }

    private static void executeUnmarkCommand(ArrayList<Task> tasks, String description,boolean canPrintFeedback) {
        //description is the list number
        //convert description(string) to description(int)
        Integer index = Integer.parseInt(description) - 1;
        unmarkTaskAtIndex(tasks, index);
        if(canPrintFeedback) {
            printUnmarkFeedback(tasks, index);
        }
    }

    private static void unmarkTaskAtIndex(ArrayList<Task> tasks, Integer index) {

        tasks.get(index).isDone = false;
    }

    private static void printUnmarkFeedback(ArrayList<Task> tasks, Integer index) {
        System.out.println(LINE);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks.get(index).toString());
        System.out.println(LINE);
    }
    private static void executeDeleteCommand(ArrayList<Task>tasks, String description){
        Integer index = Integer.parseInt(description)-1;
        String deletedTask = deleteTaskAtIndex(tasks,index);
        printDeleteFeedback(tasks, deletedTask);
    }

    private static void executeMarkCommand(ArrayList<Task> tasks, String description,boolean canPrintFeedback) {
        Integer index = Integer.parseInt(description) - 1;
        markTaskAtIndex(tasks, index);
        if (canPrintFeedback) {
            printMarkFeedback(tasks, index);
        }
    }
    private static String deleteTaskAtIndex(ArrayList<Task>tasks, Integer index){
        String nameOfToBeDeletedTask = tasks.get(index).name;
        tasks.remove((int)index);
        return nameOfToBeDeletedTask;
    }

    public static void printDeleteFeedback(ArrayList<Task> tasks, String nameOfDeletedTask){
        System.out.println(LINE);
        System.out.println("Roger. I've removed this task:");
        System.out.println(nameOfDeletedTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(LINE);
    }

    private static void markTaskAtIndex(ArrayList<Task> tasks, Integer index) {
        tasks.get(index).isDone = true;
    }

    private static void printMarkFeedback(ArrayList<Task> tasks, Integer index) {
        System.out.println(LINE);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(index).toString());
        System.out.println(LINE);
    }

    private static void executeTodoCommand(ArrayList<Task> tasks, String description,boolean canPrintFeedback) {
        try {
            addTodoToList(tasks, description);
            if(canPrintFeedback) {
                printAcknowledgement(tasks);
            }
        }catch (Exception e){
            System.out.println("Unable to add todo: No tasks given.");
            System.out.println(LINE);
        }
    }

    private static void addTodoToList(ArrayList<Task> tasks, String description) throws DukeException {
        if(description==""){
            throw new DukeException(new IllegalArgumentException());
        }
        tasks.add(new Todo(description));
    }

    private static void executeDeadlineCommand(ArrayList<Task> tasks, String description,boolean canPrintFeedback) {
        try {
            String[] indexArr = splitDescriptionDeadline(description);
            String name = indexArr[0].trim();
            String byDate = indexArr[1].substring(REMOVE_BY).trim();
            addDeadlineToList(tasks, name, byDate);
            if(canPrintFeedback) {
                printAcknowledgement(tasks);
            }
        }catch(DukeException e){
            System.out.println("Not enough commands to execute \"deadline\"");
            System.out.println(LINE);
        }
    }

    private static void addDeadlineToList(ArrayList<Task> tasks, String description, String byDate) {
        tasks.add(new Deadline(description, byDate));
    }

    private static String[] splitDescriptionDeadline(String description) throws DukeException {
        String[] indexArr = description.split("/", 2);
        if(indexArr.length < 2){
            throw new DukeException(new IllegalArgumentException());
        }
        return indexArr;
    }

    private static void executeListCommand(ArrayList<Task> tasks,boolean canPrintFeedback) {
        if(canPrintFeedback) {
            System.out.println(LINE);
            printList(tasks);
            System.out.println(LINE);
        }
    }

    private static void printList(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print(i + 1 + ".");
            System.out.println(tasks.get(i).toString());
        }
    }

    private static void executeEventCommand(ArrayList<Task> tasks, String description,boolean canPrintFeedback) {
        try {
            String[] indexArr = splitDescriptionEvent(description);
            String name = indexArr[0].trim();
            String fromDate = indexArr[1].substring(REMOVE_FROM).trim();
            String toDate = indexArr[2].substring(REMOVE_TO).trim();
            addEventToList(tasks, name, fromDate, toDate);
            if(canPrintFeedback) {
                printAcknowledgement(tasks);
            }
        }catch (DukeException e){
            System.out.println("Not enough commands to execute \"event\"");
            System.out.println(LINE);
        }

    }

    private static void printAcknowledgement(ArrayList<Task> tasks) {
        System.out.println(LINE);
        int lastIndexOfTasks = tasks.size() - 1;
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.get(lastIndexOfTasks).toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(LINE);
    }

    private static void addEventToList(ArrayList<Task> tasks, String description, String fromDate, String toDate) {
        tasks.add(new Event(description, fromDate, toDate));
    }

    private static String[] splitDescriptionEvent(String description) throws DukeException {
        String[] indexArr = description.split("/", 3);
        if(indexArr.length < 3){
            throw new DukeException(new IllegalArgumentException());
        }
        return indexArr;
    }

    private static void printByeMessage() {
        System.out.println(LINE);
        System.out.println(BYE_MESSAGE);
        System.out.println(LINE);
        System.exit(0);
    }

    private static void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(LINE);
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
        System.out.println(LINE);
    }
}
