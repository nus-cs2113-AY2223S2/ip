import java.util.*;

public class Duke {

    public static final int TASK_LENGTH = 100;
    static String[] tasksList = new String[TASK_LENGTH];
    static int tasksListIndex = 0;

    public static void sendLogo(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public static void sendGreeting(){
        String greeting_word="------------------------------------------------------------\n"
            + "Hello! I'm miniJohn\n"
            + "What can I do for you?\n"
            + "------------------------------------------------------------\n"
            + "Bye. Hope to see you again soon!\n"
            + "------------------------------------------------------------";
        System.out.println(greeting_word);
    }

    public static String getUserInput(){
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        return line;
    }

    public static String[] getCommandTypeAndParams(String userCommand){
        String[] userCommandWords = userCommand.split(" ");
        String commandType = userCommandWords[0];
        String commandParams = null;
        if(userCommandWords.length > 1){
            commandParams = String.join(" ",Arrays.copyOfRange(userCommandWords,1,userCommandWords.length));
        }
        String[] commandTypeAndParams = {commandType, commandParams};
        return commandTypeAndParams;
    }

    public static String executeListCommand(){
        String tasksListString = "";
        int sequence = 1;
        for(String task: tasksList){
            if(task == null)break;
            tasksListString += sequence + ". " + task + "\n";
            sequence += 1;
        }
        return tasksListString;
    }

    public static String executeAddTaskCommand(String newTask){
        if(newTask == null)return "";

        tasksList[tasksListIndex] = newTask;
        tasksListIndex += 1;

        String feedback = "added: " + newTask;
        return feedback;
    }

    public static String executeCommand(String userCommand){
        String[] commandTypeAndParams = getCommandTypeAndParams(userCommand);
        String commandType = commandTypeAndParams[0];
        String commandParams = commandTypeAndParams[1];

//        System.out.println("commandType="+commandType);
//        System.out.println("commandParams="+commandParams);

        String feedback = null;
        if(commandType.equals("list")){
            feedback = executeListCommand();
        }else{//add an item
            feedback = executeAddTaskCommand(commandParams);
        }
        return feedback;
    }

    public static void showResultToUser(String feedback){
        if(feedback == ""){
            return;
        }
        //print String feedback line by line
        System.out.println("\t------------------------------------------------------------");
        Scanner scanner = new Scanner(feedback);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            System.out.println('\t'+line);
        }
        scanner.close();
        System.out.println("\t------------------------------------------------------------");

        return;
    }

    public static void main(String[] args) {
        sendLogo();
        sendGreeting();
        while(true){
            String userCommand = getUserInput();
            if(userCommand.toLowerCase().equals("bye")){
                break;
            }
            String feedback = executeCommand(userCommand);
            showResultToUser(feedback);
        }
        return;
    }
}
