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

    public static String executeListCommand(String commandParams) {
        //[have not done]: exception
        if(commandParams != null){
            return "ListParamsError: List command do not have Params!";
        }

        String tasksListString = "";
        for(int i = 0; i < tasksList.size() ; i++){
            tasksListString += (i + 1) + "." + tasksList.get(i).toString() + "\n";
        }
        return tasksListString;
    }

    public static String executeAddTaskCommand(String newTaskString){
        if(newTaskString == null)return "";

        Task newTaskObject = new Task(newTaskString);
        tasksList.add(newTaskObject);

        String feedback = "Got it. I've added this task:\n"
                + newTaskObject.toString() + "\n"
                + "Now you have " + tasksList.size() +" tasks in the list";
        return feedback;
    }

    public static String executeMarkUnmarkTaskCommand(String taskToMarkIndexString, boolean IsMarkAsDone) throws TaskIndexNotFoundException {
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
            throw new TaskIndexNotFoundException();
        }

        //set mark or unmark status, and get feedback
        Task taskToMark = tasksList.get(index);
        taskToMark.setStatus(IsMarkAsDone);

        if(IsMarkAsDone == true){
            feedback = "Nice! I've marked this task as done:\n"
                    + taskToMark.toString();
        }else{
            feedback = "OK, I've marked this task as not done yet:\n"
                    + taskToMark.toString();
        }
        return feedback;
    }

    public static String executeTodoCommand(String todoString){
        if(todoString == null){
            throw new NullPointerException();
        }

        Todo newTodoObject = new Todo(todoString);
        tasksList.add(newTodoObject);

        String feedback = "Got it. I've added this task:\n"
                + newTodoObject.toString() + "\n"
                + "Now you have " + tasksList.size() +" tasks in the list";
        return feedback;
    }

    public static String executeDeadlineCommand(String commandParams)
            throws ArrayIndexOutOfBoundsException, DeadlineParamsFormatException{
        //input: String /by [ddl]        [have not done]: String /by ddl or /by ddl String
        //Exception 1: No '/'
        if(commandParams.indexOf('/')==-1){
            throw new NullPointerException();
        }

        //Exception 2: Multiple '/'
        String[] commandParamsList = commandParams.split("/");
        if(commandParamsList.length!=2){
            throw new ArrayIndexOutOfBoundsException();
        }

        //Exception 3: no 'by'
        if(!commandParamsList[1].startsWith("by ")){
            throw new DeadlineParamsFormatException();
        }
        String todoString = commandParamsList[0];
        String deadlineString = commandParamsList[1].substring(3);

        Deadline newDeadlineObject = new Deadline(todoString, deadlineString);
        tasksList.add(newDeadlineObject);

        String feedback =  "Got it. I've added this task:\n"
                + newDeadlineObject.toString() + "\n"
                + "Now you have " + tasksList.size() +" tasks in the list";
        return feedback;
    }

    public static String executeEventCommand(String commandParams) throws EventParamsFormatException{
        //input: String /from [startTime] /to [endTime]        [haven't done]: process case like /to /from
        //Exception 1: No '/'
        if(commandParams.indexOf('/') == -1){
            throw new NullPointerException();
        }

        //Exception 2: Less than 2 '/' or Multiple '/'
        String[] commandParamsList = commandParams.split("/");
        if(commandParamsList.length != 3){
            throw new ArrayIndexOutOfBoundsException();
        }

        //Exception 3: no 'from' or 'to'
        String eventString = commandParamsList[0].trim();
        if(!(commandParamsList[1].startsWith("from ")&&commandParamsList[2].startsWith("to "))){
            throw new EventParamsFormatException();
        }
        String fromString = commandParamsList[1].substring(5).trim();   //magic number
        String toString = commandParamsList[2].substring(3).trim();

        Event newEventObject = new Event(eventString, fromString, toString);
        tasksList.add(newEventObject);

        String feedback = "Got it. I've added this task:\n"
                + newEventObject.toString() + "\n"
                + "Now you have " + tasksList.size() +" tasks in the list";
        return feedback;
    }

    public static String executeCommand(String userCommand){
        String[] commandTypeAndParams = getCommandTypeAndParams(userCommand);
        String commandType = commandTypeAndParams[0];
        String commandParams = commandTypeAndParams[1];

        String feedback = "";

        /*
        /[have not done]: create methods to deal with input commandParams,
        instead of dealing them in execute methods.
        Then execute methods' length can be shorter
         */

        switch (commandType){
            case("list"):{
                if(commandParams != null){
                    return "ListParamsError: List command do not have Params!";
                }
                feedback = executeListCommand(commandParams);
                break;
            } case("add"):{
                feedback = executeAddTaskCommand(commandParams);
                break;
            } case("mark"):{
                try{
                    feedback = executeMarkUnmarkTaskCommand(commandParams, true);
                }catch(TaskIndexNotFoundException e){
                    feedback = "TaskIndexNotFoundError: Cannot find the index in TasksList!";
                }
                break;
            } case("unmark"):{
                try{
                    feedback = executeMarkUnmarkTaskCommand(commandParams, false);
                }catch(TaskIndexNotFoundException e){
                    feedback = "TaskIndexNotFoundError: Cannot find the index in TasksList!";
                }
                break;
            } case("todo"):{
                try {
                    feedback = executeTodoCommand(commandParams);
                }catch(NullPointerException e){
                    feedback = "ParamsError: Please input something.";
                }
                break;
            } case("deadline"):{
                //[have not done]: maybe multiple feedback should be compacted to one
                try {
                    feedback = executeDeadlineCommand(commandParams);
                }catch(ArrayIndexOutOfBoundsException e){
                    feedback = "ParamsError: Please input in the format: [String] /by [time]";
                }catch(NullPointerException e){
                    feedback = "ParamsError: Please input something.";
                }catch (DeadlineParamsFormatException e){
                    feedback = "ParamsError: Please input in the format: [String] /by [time]";
                }
                break;
            } case("event"):{
                try{
                    feedback = executeEventCommand(commandParams);
                }catch(ArrayIndexOutOfBoundsException e){
                    feedback = "ParamsError: Please input in the format: [String] /[from] /[to]";
                }catch(NullPointerException e){
                    feedback = "ParamsError: Please input something.";
                }catch(EventParamsFormatException e){
                    feedback = "ParamsError: Please input in the format: [String] /[from] /[to]";
                }
                break;
            } default:{
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
