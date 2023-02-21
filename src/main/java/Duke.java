import duke.exception.IllegalCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
public class Duke {
    private static final String LINE = "____________________________________________________________";
    private static final String INVALID_COMMAND_STRING = "One hour of lifespan has been deducted,"
            + " in accordance with our Terms and Services.";
    private static final String VALID_COMMAND_STRING = "Command acknowledged. Reducing user lifespan"
            + " by 30 minutes.";
    private static final String INVALID_MARK_LENGTH_STRING = "Please only input \"mark\" followed by an integer.";
    private static final String INVALID_UNMARK_LENGTH_STRING = "Please only input \"unmark\" followed by an integer.";
    private static final String LOGO =
              "    // | |     //   ) )  // | |  \\\\ / / \\\\    / / //   ) )\n"
            + "   //__| |    //___/ /  //__| |   \\  /   \\\\  / / ((\n"
            + "  / ___  |   / ___ (   / ___  |   / /     \\\\/ /    \\\\\n"
            + " //    | |  //   | |  //    | |  / /\\\\     / /       ) )\n"
            + "//     | | //    | | //     | | / /  \\\\   / / ((___ / /";
    private static final int MARK_OR_UNMARK_COMMAND_LENGTH = 2;
    private static final int DELETE_COMMAND_LENGTH = 2;
    private static final int TODO_COMMAND_LENGTH = 2;
    public static final String ALREADY_MARKED_STRING = "The task specified is already marked.";
    public static final String ALREADY_UNMARKED_STRING = "The task specified is already unmarked.";
    public static final String MARKED_AS_COMPLETED_STRING = "Task has been marked as: completed";
    public static final String MARKED_AS_NOT_COMPLETED_STRING = "Task has been marked as: not completed";
    public static final String NONNUMERIC_INDEX_STRING = "Sorry, index is not numeric.";
    public static final String INVALID_INDEX_STRING = "Sorry, index is invalid.";
    public static final String SAVING_ERROR_STRING = "There was an error in saving.";
    public static final String INVALID_DELETE_COMMAND_STRING = "Please only input \"delete\" followed by an integer.";
    public static final String TASK_DELETED_STRING = "Task has been deleted:";
    public static final String INVALID_TASK_TYPE_STRING = "Invalid task type specified.";
    public static final String TODO_SYNTAX_STRING = "Syntax: todo {task}";
    public static final String DEADLINE_SYNTAX_STRING = "Syntax: deadline {task} /by {endDate}";
    public static final String EVENT_SYNTAX_STRING = "Syntax: event {task} /from {startDate} /to {endDate}";
    public static final String TASK_ADDED_STRING = "New task has been added: ";
    public static final String GOODBYE_STRING = "Goodbye. To reach customer service, just look outside your window.";
    public static final String LIST_DESCRIPTION_STRING = "list: lists out all current items and their current status.";
    public static final String LIST_SYNTAX_STRING = "Syntax: list";
    public static final String TODO_DESCRIPTION_STRING = "todo: adds a todo task.";
    public static final String DEADLINE_DESCRIPTION_STRING = "deadline: adds a deadline task.";
    public static final String EVENT_DESCRIPTION_STRING = "event: adds an event task.";
    public static final String MARK_DESCRIPTION_STRING = "mark: marks a task as done.";
    public static final String MARK_SYNTAX_STRING = "Syntax: mark {index}";
    public static final String UNMARK_DESCRIPTION_STRING = "unmark: marks a task as not done.";
    public static final String UNMARK_SYNTAX_STRING = "Syntax: unmark {index}";
    public static final String DELETE_DESCRIPTION_STRING = "delete: deletes an event.";
    public static final String DELETE_SYNTAX_STRING = "Syntax: delete {index}";
    public static final String HELP_DESCRIPTION_STRING = "help: brings you here.";
    public static final String HELP_SYNTAX_STRING = "Syntax: help";
    public static final String EXIT_DESCRIPTION_STRING = "bye: exits the program.";
    public static final String EXIT_SYNTAX_STRING = "Syntax: bye";
    public static final String LOADING_SYSTEM_ERROR_STRING = "There was an system error in loading.";
    public static final String LOADING_PROGRAM_ERROR_STRING = "There was a program error in loading. 3 hours of "
            + "lifetime have been added for your inconvenience.";
    private static String command;
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static Scanner in = new Scanner(System.in);
    private enum taskType{
        TODO,DEADLINE,EVENT,INVALID
    }
    private static void printList(){
        System.out.println(LINE);
        System.out.println(VALID_COMMAND_STRING);
        for(int i=0; i<tasks.size(); i++){
            System.out.print(i + 1 + ":");
            System.out.println(tasks.get(i).toString());
        }
        System.out.println(LINE);
    }
    private static void setMarkedOrUnmarked(String[] commands, boolean setMarked){
        if (commands.length != MARK_OR_UNMARK_COMMAND_LENGTH){
            System.out.println(LINE);
            if(setMarked) {
                System.out.println(INVALID_MARK_LENGTH_STRING);
            }else{
                System.out.println(INVALID_UNMARK_LENGTH_STRING);
            }
            System.out.println(INVALID_COMMAND_STRING);
            System.out.println(LINE);
            return;
        }
        int index;
        try{
            index = Integer.parseInt(commands[1]);
        } catch (NumberFormatException numberFormatException){
            System.out.println(LINE + '\n' + NONNUMERIC_INDEX_STRING + '\n' + LINE);
            System.out.println(INVALID_COMMAND_STRING);
            return;
        }
        index--;
        try{
            tasks.get(index).setDone(setMarked);
        } catch (IndexOutOfBoundsException e){
            System.out.println(LINE + '\n' + INVALID_INDEX_STRING + '\n' + LINE);
            System.out.println(INVALID_COMMAND_STRING);
            return;
        } catch (IllegalCommandException e){
            if(setMarked){
                System.out.println(LINE + '\n' + ALREADY_MARKED_STRING + '\n' + LINE);
            }else{
                System.out.println(LINE + '\n' + ALREADY_UNMARKED_STRING + '\n' + LINE);
            }
            System.out.println(INVALID_COMMAND_STRING);
            return;
        }
        System.out.println(LINE);
        System.out.println(VALID_COMMAND_STRING);
        if(setMarked){
            System.out.println(MARKED_AS_COMPLETED_STRING);
        }else{
            System.out.println(MARKED_AS_NOT_COMPLETED_STRING);
        }
        System.out.println(tasks.get(index).toString());
        System.out.println(LINE);
        try{
            save();
        }catch(IOException e){
            System.out.println(SAVING_ERROR_STRING);
        }
    }
    private static void delete(String[] commands){
        if (commands.length != DELETE_COMMAND_LENGTH){
            System.out.println(LINE);
            System.out.println(INVALID_DELETE_COMMAND_STRING);
            System.out.println(INVALID_COMMAND_STRING);
            System.out.println(LINE);
            return;
        }
        int index;
        try{
            index = Integer.parseInt(commands[1]);
        } catch (NumberFormatException numberFormatException){
            System.out.println(LINE + '\n' + NONNUMERIC_INDEX_STRING + '\n' + LINE);
            System.out.println(INVALID_COMMAND_STRING);
            return;
        }
        index--;
        String taskString;
        try{
            taskString = tasks.get(index).toString();
            tasks.remove(index);
        } catch (IndexOutOfBoundsException e){
            System.out.println(LINE + '\n' + INVALID_INDEX_STRING + '\n' + LINE);
            System.out.println(INVALID_COMMAND_STRING);
            return;
        }
        System.out.println(LINE);
        System.out.println(VALID_COMMAND_STRING);
        System.out.println(TASK_DELETED_STRING);
        System.out.println(taskString);
        System.out.println(LINE);
        try{
            save();
        }catch(IOException e){
            System.out.println(SAVING_ERROR_STRING);
        }
    }
    private static taskType getTaskType(String command){
        String[] commands = command.split(" ");
        switch (commands[0]) {
        case "deadline":
            return taskType.DEADLINE;
        case "event":
            return taskType.EVENT;
        case "todo":
            return taskType.TODO;
        default:
            return taskType.INVALID;
        }
    }
    private static void addTask(String command) throws IllegalCommandException{
        taskType currentTaskType = getTaskType(command);
        if(currentTaskType == taskType.INVALID){
            System.out.println(LINE + '\n' + INVALID_TASK_TYPE_STRING + '\n' + LINE);
            throw new IllegalCommandException("Illegal task type");
        }else if(currentTaskType == taskType.TODO){
            if(command.split(" ").length<TODO_COMMAND_LENGTH){
                System.out.println(LINE + '\n' + TODO_SYNTAX_STRING + '\n' + LINE);
                throw new IllegalCommandException("Illegal todo command");
            }
            tasks.add(new Todo(command));
        }else if(currentTaskType == taskType.DEADLINE){
            if(!command.contains("/by")){
                System.out.println(LINE + '\n' + DEADLINE_SYNTAX_STRING + '\n' + LINE);
                throw new IllegalCommandException("Illegal deadline command");
            }
            tasks.add(new Deadline(command));
        }else if(currentTaskType == taskType.EVENT){
            if(!(command.contains("/from")&&command.contains("/to"))){
                System.out.println(LINE + '\n' + EVENT_SYNTAX_STRING + '\n' + LINE);
                throw new IllegalCommandException("Illegal event command");
            }
            tasks.add(new Event(command));
        }
        System.out.println(VALID_COMMAND_STRING);
        System.out.println(LINE + '\n' + TASK_ADDED_STRING + command + '\n' + LINE);
        try{
            save();
        }catch(IOException e){
            System.out.println(SAVING_ERROR_STRING);
        }
    }
    private static void silentlyAddTask(String command) throws IllegalCommandException{
        taskType currentTaskType = getTaskType(command);
        if(currentTaskType == taskType.INVALID){
            throw new IllegalCommandException("Illegal task type");
        }else if(currentTaskType == taskType.TODO){
            if(command.split(" ").length<TODO_COMMAND_LENGTH){
                throw new IllegalCommandException("Illegal todo command");
            }
            tasks.add(new Todo(command));
        }else if(currentTaskType == taskType.DEADLINE){
            if(!command.contains("/by")){
                throw new IllegalCommandException("Illegal deadline command");
            }
            tasks.add(new Deadline(command));
        }else if(currentTaskType == taskType.EVENT){
            if(!(command.contains("/from")&&command.contains("/to"))){
                throw new IllegalCommandException("Illegal event command");
            }
            tasks.add(new Event(command));
        }
    }
    private static String getCommand(){
        return in.nextLine();
    }
    private static String transformCommand(String command){
        return command.trim();
    }
    private static void executeCommand(String command){
        if(command.equals("bye")){
            System.out.println(LINE + '\n' + GOODBYE_STRING + '\n' + LINE);
            System.exit(0);
        }
        if(command.equals("list")){
            printList();
        }else if(command.equals("help")){
            printHelp();
        }else{
            String[] commands = command.split(" ");
            if(commands[0].equals("mark")){
                setMarkedOrUnmarked(commands, true);
            }else if(commands[0].equals("unmark")){
                setMarkedOrUnmarked(commands, false);
            }else if(commands[0].equals("delete")){
                delete(commands);
            }else{
                try{
                    addTask(command);
                }catch (IllegalCommandException e){
                    System.out.println(INVALID_COMMAND_STRING);
                    return;
                }
            }
        }
    }
    private static void greet() {
        System.out.println(LINE+'\n'+LOGO+'\n'+LINE);
        System.out.println("Welcome to Araxys Systems, the only system powered by LifeForce™");
        System.out.println("How may I help you today?");
        System.out.println("Type \"help\" to get help.");
        System.out.println(LINE);
    }
    private static void printHelp(){
        System.out.println(LINE);
        System.out.println(LIST_DESCRIPTION_STRING);
        System.out.println(LIST_SYNTAX_STRING);
        System.out.println(TODO_DESCRIPTION_STRING);
        System.out.println(TODO_SYNTAX_STRING);
        System.out.println(DEADLINE_DESCRIPTION_STRING);
        System.out.println(DEADLINE_SYNTAX_STRING);
        System.out.println(EVENT_DESCRIPTION_STRING);
        System.out.println(EVENT_SYNTAX_STRING);
        System.out.println(MARK_DESCRIPTION_STRING);
        System.out.println(MARK_SYNTAX_STRING);
        System.out.println(UNMARK_DESCRIPTION_STRING);
        System.out.println(UNMARK_SYNTAX_STRING);
        System.out.println(DELETE_DESCRIPTION_STRING);
        System.out.println(DELETE_SYNTAX_STRING);
        System.out.println(HELP_DESCRIPTION_STRING);
        System.out.println(HELP_SYNTAX_STRING);
        System.out.println(EXIT_DESCRIPTION_STRING);
        System.out.println(EXIT_SYNTAX_STRING);
        System.out.println(LINE);
    }
    private static void load() throws IOException, IllegalCommandException {
        File folder = new File("data");
        if(!(folder.exists()&&folder.isDirectory())){
            new File("data").mkdirs();
        }
        File f = new File("data/araxys.txt");
        if(!f.exists()){
            f.createNewFile();
        }
        Scanner s = new Scanner(f);
        while(s.hasNext()){
            String nextLine =  s.nextLine();
            String[] saveLine = nextLine.split(" ");
            String cmd = "";
            for(int i=0; i<saveLine.length-1; i++){
                cmd += " ";
                cmd += saveLine[i];
            }
            cmd = cmd.trim();
            String done = saveLine[saveLine.length-1].trim();
            try{
                silentlyAddTask(cmd);
            }catch(IllegalCommandException e){
                throw new IllegalCommandException("parsing error");
            }
            if(done.equals("1")){
                tasks.get(tasks.size()-1).setDone(true);
            }
        }
    }
    private static void save() throws IOException {
        File f = new File("data/araxys.txt");
        if(f.exists()){
            f.delete();
        }
        f.createNewFile();
        FileWriter fw = new FileWriter(f);
        for(Task t: tasks){
            fw.write(t.getSaveString());
        }
        fw.close();
    }
    private static void initialise(){
        greet();
        try{
            load();
        }catch(IOException e){
            System.out.println(LOADING_SYSTEM_ERROR_STRING);
        }catch(IllegalCommandException e){
            System.out.println(LOADING_PROGRAM_ERROR_STRING);
        }
    }
    public static void main(String[] args) {
        initialise();
        while(true){
            command = getCommand();
            command = transformCommand(command);
            executeCommand(command);
        }
    }


}
