import java.util.*;

public class Duke {

    static ArrayList<Task> tasksList = new ArrayList<Task>();

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

    public static String executeListCommand(String commandParams){
        if(commandParams != null){
            return "ListParamsError: List command do not have Params!";
        }

        String tasksListString = "";
        for(int i = 0; i < tasksList.size() ; i++){
            tasksListString +=(i+1) + "." + tasksList.get(i).getStatusIcon() + " " + tasksList.get(i).getDescription() + "\n";
        }
        return tasksListString;
    }

    public static String executeAddTaskCommand(String newTaskString){
        if(newTaskString == null)return "";

        Task newTaskObject = new Task(newTaskString);
        tasksList.add(newTaskObject);

        String feedback = "added: " + newTaskString;
        return feedback;
    }

    public static String executeMarkUnmarkTaskCommand(String taskToMarkIndexString, boolean IsMarkAsDone){
        int index;
        String feedback;

        //Input cannot format into an Index
        try{
            index = Integer.parseInt(taskToMarkIndexString);
        }catch (NumberFormatException nfe){
            feedback = "TaskIndexFormatError: Cannot format your input to a Task Index!";
            return feedback;
        }

        //Input Index out of range
        index = index - 1;
        if(index < 0 || index >= tasksList.size()){
            feedback = "TaskIndexNotFoundError: Cannot find the index in TasksList!";
            return feedback;
        }

        //set mark or unmark status, and get feedback
        Task taskToMark = tasksList.get(index);
        taskToMark.setStatus(IsMarkAsDone);

        if(IsMarkAsDone == true){
            feedback = "Nice! I've marked this task as done:\n"
                    + taskToMark.getStatusIcon()  + taskToMark.getDescription();
        }else{
            feedback = "OK, I've marked this task as not done yet:\n"
                    + taskToMark.getStatusIcon()  + taskToMark.getDescription();
        }
        return feedback;
    }

    public static String executeCommand(String userCommand){
        String[] commandTypeAndParams = getCommandTypeAndParams(userCommand);
        String commandType = commandTypeAndParams[0];
        String commandParams = commandTypeAndParams[1];

        String feedback = null;
        switch (commandType){
            case("list"):{
                feedback = executeListCommand(commandParams);
                break;
            }
            case("add"):{
                feedback = executeAddTaskCommand(commandParams);
                break;
            }
            case("mark"):{
                feedback = executeMarkUnmarkTaskCommand(commandParams, true);
                break;
            }
            case("unmark"):{
                feedback = executeMarkUnmarkTaskCommand(commandParams, false);
                break;
            }
            default:{
                feedback = "CommandError: I can't understand \"" + userCommand +"\"!";
                break;
            }
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
    }
}
