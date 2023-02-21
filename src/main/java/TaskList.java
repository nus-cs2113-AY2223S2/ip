import duke.exception.IllegalCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static final int MARK_OR_UNMARK_COMMAND_LENGTH = 2;
    private static final int DELETE_COMMAND_LENGTH = 2;
    private static final int TODO_COMMAND_LENGTH = 2;
    private static final int FIND_COMMAND_LENGTH = 2;
    /*
     * Sets a task as either marked or unmarked
     *
     * @param commands Set of commands from the user
     * @param setDone Sets the task to done if true, sets the task to not done if false
     */
    private static void setMarkedOrUnmarked(String[] commands, boolean setDone){
        if (commands.length != MARK_OR_UNMARK_COMMAND_LENGTH){
            System.out.println(Ui.LINE);
            if(setDone) {
                System.out.println(Ui.INVALID_MARK_LENGTH_STRING);
            }else{
                System.out.println(Ui.INVALID_UNMARK_LENGTH_STRING);
            }
            System.out.println(Ui.INVALID_COMMAND_STRING);
            System.out.println(Ui.LINE);
            return;
        }
        int index;
        try{
            index = Integer.parseInt(commands[1]);
        } catch (NumberFormatException numberFormatException){
            System.out.println(Ui.LINE + '\n' + Ui.NONNUMERIC_INDEX_STRING + '\n' + Ui.LINE);
            System.out.println(Ui.INVALID_COMMAND_STRING);
            return;
        }
        index--;
        try{
            tasks.get(index).setDone(setDone);
        } catch (IndexOutOfBoundsException e){
            System.out.println(Ui.LINE + '\n' + Ui.INVALID_INDEX_STRING + '\n' + Ui.LINE);
            System.out.println(Ui.INVALID_COMMAND_STRING);
            return;
        } catch (IllegalCommandException e){
            if(setDone){
                System.out.println(Ui.LINE + '\n' + Ui.ALREADY_MARKED_STRING + '\n' + Ui.LINE);
            }else{
                System.out.println(Ui.LINE + '\n' + Ui.ALREADY_UNMARKED_STRING + '\n' + Ui.LINE);
            }
            System.out.println(Ui.INVALID_COMMAND_STRING);
            return;
        }
        System.out.println(Ui.LINE);
        System.out.println(Ui.VALID_COMMAND_STRING);
        if(setDone){
            System.out.println(Ui.MARKED_AS_COMPLETED_STRING);
        }else{
            System.out.println(Ui.MARKED_AS_NOT_COMPLETED_STRING);
        }
        System.out.println(tasks.get(index).toString());
        System.out.println(Ui.LINE);
        try{
            Storage.save();
        }catch(IOException e){
            System.out.println(Ui.SAVING_ERROR_STRING);
        }
    }
    /*
     * Deletes the task specified by the user
     *
     * @param commands Set of commands from the user
     */
    private static void delete(String[] commands){
        if (commands.length != DELETE_COMMAND_LENGTH){
            System.out.println(Ui.LINE);
            System.out.println(Ui.INVALID_DELETE_COMMAND_STRING);
            System.out.println(Ui.INVALID_COMMAND_STRING);
            System.out.println(Ui.LINE);
            return;
        }
        int index;
        try{
            index = Integer.parseInt(commands[1]);
        } catch (NumberFormatException numberFormatException){
            System.out.println(Ui.LINE + '\n' + Ui.NONNUMERIC_INDEX_STRING + '\n' + Ui.LINE);
            System.out.println(Ui.INVALID_COMMAND_STRING);
            return;
        }
        index--;
        String taskString;
        try{
            taskString = tasks.get(index).toString();
            tasks.remove(index);
        } catch (IndexOutOfBoundsException e){
            System.out.println(Ui.LINE + '\n' + Ui.INVALID_INDEX_STRING + '\n' + Ui.LINE);
            System.out.println(Ui.INVALID_COMMAND_STRING);
            return;
        }
        System.out.println(Ui.LINE);
        System.out.println(Ui.VALID_COMMAND_STRING);
        System.out.println(Ui.TASK_DELETED_STRING);
        System.out.println(taskString);
        System.out.println(Ui.LINE);
        try{
            Storage.save();
        }catch(IOException e){
            System.out.println(Ui.SAVING_ERROR_STRING);
        }
    }
    /*
     * Adds the command specified by the user
     *
     * @param command Command string from the user
     * @throws IllegalCommandException if the command is invalid
     */
    private static void addTask(String command) throws IllegalCommandException{
        Parser.taskType currentTaskType = Parser.getTaskType(command);
        if(currentTaskType == Parser.taskType.INVALID){
            System.out.println(Ui.LINE + '\n' + Ui.INVALID_TASK_TYPE_STRING + '\n' + Ui.LINE);
            throw new IllegalCommandException("Illegal task type");
        }else if(currentTaskType == Parser.taskType.TODO){
            if(command.split(" ").length<TODO_COMMAND_LENGTH){
                System.out.println(Ui.LINE + '\n' + Ui.TODO_SYNTAX_STRING + '\n' + Ui.LINE);
                throw new IllegalCommandException("Illegal todo command");
            }
            tasks.add(new Todo(command));
        }else if(currentTaskType == Parser.taskType.DEADLINE){
            if(!command.contains("/by")){
                System.out.println(Ui.LINE + '\n' + Ui.DEADLINE_SYNTAX_STRING + '\n' + Ui.LINE);
                throw new IllegalCommandException("Illegal deadline command");
            }
            tasks.add(new Deadline(command));
        }else if(currentTaskType == Parser.taskType.EVENT){
            if(!(command.contains("/from")&&command.contains("/to"))){
                System.out.println(Ui.LINE + '\n' + Ui.EVENT_SYNTAX_STRING + '\n' + Ui.LINE);
                throw new IllegalCommandException("Illegal event command");
            }
            tasks.add(new Event(command));
        }
        System.out.println(Ui.VALID_COMMAND_STRING);
        System.out.println(Ui.LINE + '\n' + Ui.TASK_ADDED_STRING + tasks.get(tasks.size()-1).toString() + '\n' + Ui.LINE);
        try{
            Storage.save();
        }catch(IOException e){
            System.out.println(Ui.SAVING_ERROR_STRING);
        }
    }
    /*
     * Finds the task specified by the user
     *
     * @param commands Set of commands from the user
     */
    private static void findTask(String[] commands){
        String keyword = "";
        if(commands.length < FIND_COMMAND_LENGTH){
            System.out.println(Ui.LINE);
            System.out.println(Ui.MISSING_FIND_KEYWORD_STRING);
            System.out.println(Ui.LINE);
            return;
        }
        for(int i = 1; i < commands.length ; i++){
            keyword+=commands[i];
            if(i < commands.length - 1){
                keyword+=" ";
            }
        }
        int counter = 1;
        System.out.println(Ui.LINE);
        System.out.println(Ui.VALID_COMMAND_STRING);
        System.out.println(Ui.TASKS_FOUND_STRING);
        for(int i = 0; i < tasks.size(); i++){
            if(tasks.get(i).getDescription().toLowerCase().contains(keyword.toLowerCase())){
                System.out.println(counter + ": " + tasks.get(i).toString());
                counter++;
            }
        }
        System.out.println(Ui.LINE);
    }
    /*
     * Adds task without any output
     *
     * @param command Command string from the saved file
     * @throws IllegalCommandException if the command is invalid
     */
    public static void silentlyAddTask(String command) throws IllegalCommandException{
        Parser.taskType currentTaskType = Parser.getTaskType(command);
        if(currentTaskType == Parser.taskType.INVALID){
            throw new IllegalCommandException("Illegal task type");
        }else if(currentTaskType == Parser.taskType.TODO){
            if(command.split(" ").length< TODO_COMMAND_LENGTH){
                throw new IllegalCommandException("Illegal todo command");
            }
            tasks.add(new Todo(command));
        }else if(currentTaskType == Parser.taskType.DEADLINE){
            if(!command.contains("/by")){
                throw new IllegalCommandException("Illegal deadline command");
            }
            tasks.add(new Deadline(command));
        }else if(currentTaskType == Parser.taskType.EVENT){
            if(!(command.contains("/from")&&command.contains("/to"))){
                throw new IllegalCommandException("Illegal event command");
            }
            tasks.add(new Event(command));
        }
    }
    /*
     * Execute command specified by the command string
     *
     * @param command Command string from the user
     */
    public static void executeCommand(String command){
        if(command.equals("bye")){
            System.out.println(Ui.LINE + '\n' + Ui.GOODBYE_STRING + '\n' + Ui.LINE);
            System.exit(0);
        }
        if(command.equals("list")){
            printList();
        }else if(command.equals("help")){
            Ui.printHelp();
        }else{
            String[] commands = command.split(" ");
            if(commands[0].equals("mark")){
                setMarkedOrUnmarked(commands, true);
            }else if(commands[0].equals("unmark")){
                setMarkedOrUnmarked(commands, false);
            }else if(commands[0].equals("delete")){
                delete(commands);
            }else if(commands[0].equals("find")){
                findTask(commands);
            }else{
                try{
                    addTask(command);
                }catch (IllegalCommandException e){
                    System.out.println(Ui.INVALID_COMMAND_STRING);
                    return;
                }
            }
        }
    }
    /*
     * Prints all tasks
     */
    public static void printList(){
        System.out.println(Ui.LINE);
        System.out.println(Ui.VALID_COMMAND_STRING);
        for(int i=0; i<tasks.size(); i++){
            System.out.print(i + 1 + ":");
            System.out.println(tasks.get(i).toString());
        }
        System.out.println(Ui.LINE);
    }
    /*
     * Sets the last task in the task lists as done or not done
     *
     * @param isDone When true, the task is set as done, else the task is set as not done
     */
    public static void setLastTaskMarkedOrUnmarked(boolean isDone){
        try{
            tasks.get(tasks.size()-1).setDone(isDone);
        }catch(Exception e){
            return;
        }
    }
    /*
     * Retrieves an arraylist of commands used to initialise tasks from all tasks
     *
     * @return Arraylist of save file strings
     */
    public static ArrayList<String> getSaveStrings(){
        ArrayList<String> saveStrings = new ArrayList<>();
        for(Task t: tasks){
            saveStrings.add(t.getSaveString());
        }
        return saveStrings;
    }
}
