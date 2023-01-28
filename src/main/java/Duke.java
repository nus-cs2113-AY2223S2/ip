import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    private static final String LINE = "____________________________________________________________";
    private static final String INVALID_COMMAND_PRINTER = "One hour of lifespan has been deducted, in accordance with our Terms and Services.";
    private static final String VALID_COMMAND_PRINTER = "Command acknowledged. Reducing user lifespan by 30 minutes.";
    private static ArrayList<Task>tasks = new ArrayList<>();
    private static Scanner in = new Scanner(System.in);
    private static void printList(){
        System.out.println(LINE);
        System.out.println(VALID_COMMAND_PRINTER);
        for(int i=0;i<tasks.size();i++){
            System.out.print(i+1+":["+tasks.get(i).getStatusIcon()+"]///////");
            System.out.println(tasks.get(i).getDescription());
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
        if(index<0||index>=tasks.size()){
            System.out.println(LINE+'\n'+"Sorry, index is invalid."+'\n'+LINE);
            System.out.println(INVALID_COMMAND_PRINTER);
        }else if(tasks.get(index).isDone()){
            System.out.println(LINE+'\n'+"The task specified is already marked."+'\n'+LINE);
            System.out.println(INVALID_COMMAND_PRINTER);
        }else{
            tasks.get(index).setDone(true);
            System.out.println(LINE);
            System.out.println(VALID_COMMAND_PRINTER);
            System.out.println("Task has been marked as: completed");
            System.out.println("[X] "+tasks.get(index).getDescription());
            System.out.println(LINE);
        }
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
        if(index<0||index>=tasks.size()){
            System.out.println(LINE+'\n'+"Sorry, index is invalid."+'\n'+LINE);
            System.out.println(INVALID_COMMAND_PRINTER);
        }else if(!tasks.get(index).isDone()){
            System.out.println(LINE+'\n'+"The task specified is already unmarked."+'\n'+LINE);
            System.out.println(INVALID_COMMAND_PRINTER);
        }else{
            tasks.get(index).setDone(false);
            System.out.println(LINE);
            System.out.println(VALID_COMMAND_PRINTER);
            System.out.println("Task has been marked as: not completed");
            System.out.println("[ ] "+tasks.get(index).getDescription());
            System.out.println(LINE);
        }
    }
    private static void addTask(String command){
        tasks.add(new Task(command));
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
            return;
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
                addTask(command);
            }
        }
    }
    public static void main(String[] args) {
        String logo = "    // | |     //   ) )  // | |  \\\\ / / \\\\    / / //   ) ) \n"
                + "   //__| |    //___/ /  //__| |   \\  /   \\\\  / / ((        \n"
                + "  / ___  |   / ___ (   / ___  |   / /     \\\\/ /    \\\\      \n"
                + " //    | |  //   | |  //    | |  / /\\\\     / /       ) )   \n"
                + "//     | | //    | | //     | | / /  \\\\   / / ((___ / /    ";
        System.out.println(LINE+'\n'+logo+'\n'+LINE);
        System.out.println("Welcome to Araxys Systems, the only system powered by LifeForce™");
        System.out.println("How may I help you today?");
        System.out.println(LINE);
        String command;
        while(true){
            command = getCommand();
            command = transformCommand(command);
            executeCommand(command);
        }
    }
}
