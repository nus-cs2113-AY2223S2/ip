import duke.exception.IllegalCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    private static final String LINE = "____________________________________________________________";
    private static final String INVALID_COMMAND_PRINTER = "One hour of lifespan has been deducted, in accordance with our Terms and Services.";
    private static final String VALID_COMMAND_PRINTER = "Command acknowledged. Reducing user lifespan by 30 minutes.";
    private static final String LOGO =
              "    // | |     //   ) )  // | |  \\\\ / / \\\\    / / //   ) )\n"
            + "   //__| |    //___/ /  //__| |   \\  /   \\\\  / / ((\n"
            + "  / ___  |   / ___ (   / ___  |   / /     \\\\/ /    \\\\\n"
            + " //    | |  //   | |  //    | |  / /\\\\     / /       ) )\n"
            + "//     | | //    | | //     | | / /  \\\\   / / ((___ / /";
    private static String command;
    private static ArrayList<Task>tasks = new ArrayList<>();
    private static Scanner in = new Scanner(System.in);
    private enum taskType{
        TODO,DEADLINE,EVENT,INVALID
    }
    private static void printList(){
        System.out.println(LINE);
        System.out.println(VALID_COMMAND_PRINTER);
        for(int i=0;i<tasks.size();i++){
            System.out.print(i+1+":");
            System.out.println(tasks.get(i).toString());
        }
        System.out.println(LINE);
    }
    private static void mark(String[] commands){
        if (commands.length!=2){
            System.out.println(LINE);
            System.out.println("Please only input \"mark\" followed by an integer.");
            System.out.println(INVALID_COMMAND_PRINTER);
            System.out.println(LINE);
            return;
        }
        int index;
        try{
            index = Integer.parseInt(commands[1]);
        } catch (NumberFormatException numberFormatException){
            System.out.println(LINE+'\n'+"Sorry, index is not numeric."+'\n'+LINE);
            System.out.println(INVALID_COMMAND_PRINTER);
            return;
        }
        index--;
        try{
            tasks.get(index).setDone(true);
        } catch (IndexOutOfBoundsException e){
            System.out.println(LINE+'\n'+"Sorry, index is invalid."+'\n'+LINE);
            System.out.println(INVALID_COMMAND_PRINTER);
            return;
        } catch (IllegalCommandException e){
            System.out.println(LINE+'\n'+"The task specified is already marked."+'\n'+LINE);
            System.out.println(INVALID_COMMAND_PRINTER);
            return;
        }
        System.out.println(LINE);
        System.out.println(VALID_COMMAND_PRINTER);
        System.out.println("Task has been marked as: completed");
        System.out.println(tasks.get(index).toString());
        System.out.println(LINE);
    }
    private static void unmark(String[] commands){
        if (commands.length!=2){
            System.out.println(LINE);
            System.out.println("Please only input \"unmark\" followed by an integer.");
            System.out.println(INVALID_COMMAND_PRINTER);
            System.out.println(LINE);
            return;
        }
        int index;
        try{
            index = Integer.parseInt(commands[1]);
        } catch (NumberFormatException numberFormatException){
            System.out.println(LINE+'\n'+"Sorry, index is not numeric."+'\n'+LINE);
            System.out.println(INVALID_COMMAND_PRINTER);
            return;
        }
        index--;
        try{
            tasks.get(index).setDone(false);
        } catch (IndexOutOfBoundsException e){
            System.out.println(LINE+'\n'+"Sorry, index is invalid."+'\n'+LINE);
            System.out.println(INVALID_COMMAND_PRINTER);
            return;
        } catch (IllegalCommandException e){
            System.out.println(LINE+'\n'+"The task specified is already unmarked."+'\n'+LINE);
            System.out.println(INVALID_COMMAND_PRINTER);
            return;
        }
        System.out.println(LINE);
        System.out.println(VALID_COMMAND_PRINTER);
        System.out.println("Task has been marked as: not completed");
        System.out.println(tasks.get(index).toString());
        System.out.println(LINE);
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
        if(currentTaskType==taskType.INVALID){
            System.out.println(LINE+'\n'+"Invalid task type specified."+'\n'+LINE);
            throw new IllegalCommandException("Illegal task type");
        }else if(currentTaskType==taskType.TODO){
            if(command.split(" ").length<=1){
                System.out.println(LINE+'\n'+"Syntax: todo {task}"+'\n'+LINE);
                throw new IllegalCommandException("Illegal todo command");
            }
            tasks.add(new Todo(command));
        }else if(currentTaskType==taskType.DEADLINE){
            if(!command.contains("/by")){
                System.out.println(LINE+'\n'+"Syntax: deadline {task} /by {endDate}"+'\n'+LINE);
                throw new IllegalCommandException("Illegal deadline command");
            }
            tasks.add(new Deadline(command));
        }else if(currentTaskType==taskType.EVENT){
            if(!(command.contains("/from")&&command.contains("/to"))){
                System.out.println(LINE+'\n'+"Syntax: event {task} /from {startDate} /to {endDate}"+'\n'+LINE);
                throw new IllegalCommandException("Illegal event command");
            }
            tasks.add(new Event(command));
        }
        System.out.println(VALID_COMMAND_PRINTER);
        System.out.println(LINE+'\n'+"New task has been added: "+command+'\n'+LINE);
    }
    private static String getCommand(){
        return in.nextLine();
    }
    private static String transformCommand(String command){
        return command.trim();
    }
    private static void executeCommand(String command){
        if(command.equals("bye")){
            System.out.println(LINE+'\n'+"Goodbye. To reach customer service, just look outside your window."+'\n'+LINE);
            System.exit(0);
        }
        if(command.equals("list")){
            printList();
        }else{
            String[] commands = command.split(" ");
            if(commands[0].equals("mark")){
                mark(commands);
            }else if(commands[0].equals("unmark")){
                unmark(commands);
            }else{
                try{
                    addTask(command);
                }catch (IllegalCommandException e){
                    System.out.println(INVALID_COMMAND_PRINTER);
                    return;
                }
            }
        }
    }
    private static void greet() {
        System.out.println(LINE+'\n'+LOGO+'\n'+LINE);
        System.out.println("Welcome to Araxys Systems, the only system powered by LifeForce™");
        System.out.println("How may I help you today?");
        System.out.println(LINE);
    }
    public static void main(String[] args) {
        greet();
        while(true){
            command = getCommand();
            command = transformCommand(command);
            executeCommand(command);
        }
    }


}
