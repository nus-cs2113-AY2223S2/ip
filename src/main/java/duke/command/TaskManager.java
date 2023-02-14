package duke.command;

import duke.exception.EmptyTaskException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.exception.IllegalCommandException;
import duke.SaveManager.SaveState;

public class TaskManager {
    protected Task[] taskList;
    protected int taskCount;

    private SaveState saveState = new SaveState();
    public TaskManager(int taskCount){
        this.taskCount = 0;
        taskList = new Task[100];
    }
    public void addTask(Task upcomingTask){
        taskList[taskCount] = upcomingTask;
        taskCount += 1;
    }

    public void markTaskAsDone(String taskIndex){
        int arrayIndex=101; //larger than array size. Will definitely raise IndexOutOfBoundsException if unchanged.
        try{
            arrayIndex = Integer.parseInt(taskIndex) - 1;
        } catch (NumberFormatException e){
            System.out.println("Enter a valid number");
        }
        try {
            taskList[arrayIndex].markAsDone();
            System.out.println("Nice! I've marked this task as done: ");
            System.out.println(taskList[arrayIndex].toString());
        } catch (IndexOutOfBoundsException e){
            System.out.println("No task at specified index");
        } catch (NullPointerException e){
            System.out.println("Not a valid ID");
        }

    }
    public void markTaskAsUndone(String taskIndex){
        int arrayIndex = 101; //as per markTaskAsDone
        try{
            arrayIndex = Integer.parseInt(taskIndex) - 1;
        } catch (NumberFormatException e){
            System.out.println("Enter a valid number");
        }
        try {
            taskList[arrayIndex].markAsUndone(); //catch exception here too.
            System.out.println("OK, I've marked this task as not done yet: ");
            System.out.println(taskList[arrayIndex].toString());
        } catch (IndexOutOfBoundsException e){
            System.out.println("Not a valid ID");
        } catch (NullPointerException e){
            System.out.println("Not a valid ID");
        }
    }
    public void listTasks(){
        for (int i = 0; i < taskCount; i++){
            System.out.print((i+1)+ ".");
            System.out.println(taskList[i].toString());
        }
    }

    public void handleCommand(String input) throws IllegalCommandException {
        input = input.trim(); //removes excess whitespace in front and back of command
        String[] commandLine = input.split(" "); //split substrings by whitespaces
        String commandBody = input.substring(input.indexOf(" ")+1);
        commandBody = commandBody.trim();
        switch (commandLine[0]){
        case "list":
            this.listTasks();
            break;
        case "mark":
            this.markTaskAsDone(commandLine[1]);
            saveState.saveToFile(this.taskList);
            break;
        case "unmark":
            this.markTaskAsUndone(commandLine[1]);
            saveState.saveToFile(this.taskList);
            break;
        case "todo":
            try {
                generateToDo(commandBody);
                saveState.saveToFile(this.taskList);
            }catch (EmptyTaskException a){
                System.out.println("The description of a todo cannot be empty.");
            }
            break;
        case "deadline":
            try {
                generateDeadline(commandBody);// as per todo
                saveState.saveToFile(this.taskList);
            }catch(EmptyTaskException a){
                System.out.println("The description of a event cannot be empty.");
            }
            break;

        case "event":
            try {
                generateEvent(commandBody); //as per todo
                saveState.saveToFile(this.taskList);
            }
            catch(EmptyTaskException a){
                System.out.println("The description of event cannot be empty.");
            }
            break;
        case "help":
            printHelp();
            break;
        default:
            throw new IllegalCommandException();
        }

    }

    private void generateToDo(String input) throws EmptyTaskException{
        if (input.equals("todo")){
            throw new EmptyTaskException();
        }
        ToDo newTask = new ToDo(input);
        //taskList[TaskItems.Task.getTaskNumber()] = newTask;
        this.addTask(newTask);
        System.out.println("Got it. I've added this to Duke.task:");
        System.out.println("    " + newTask.toString());
    }
    private void generateDeadline(String input) throws EmptyTaskException{
        if (input.equals("deadline")){
            throw new EmptyTaskException();
        }
        int indexSeparator = input.indexOf("/");
        String taskDescription = "";
        String taskDue = "";
        try {
            taskDescription = input.substring(0, indexSeparator);
            taskDue = input.substring(indexSeparator + 4);
        }catch(StringIndexOutOfBoundsException a){
            System.out.println("Input error. Use '/by' in command line to specify deadline. Type 'help' for " +
                    "more information");
            return;
        }
        Deadline newTask = new Deadline(taskDescription, taskDue);
        this.addTask(newTask);
        System.out.println("Got it. I've added this to Duke.task:");
        System.out.println("    " + newTask.toString());
    }

    private void generateEvent(String input) throws EmptyTaskException{
        if (input.equals("event")){
            throw new EmptyTaskException();
        }
        int indexSeparator = input.indexOf("/");
        String taskDescription = "";
        String taskDates = "";
        try {
            taskDescription = input.substring(0, indexSeparator); //locates location of first / for "from"
            taskDates = input.substring(indexSeparator + 6); //creates "from" substring
        }catch (StringIndexOutOfBoundsException a){
            System.out.println("Input error. Use '/from' in command line to specify starting time. Type 'help' for " +
                    "more information");
            return;
        }
        indexSeparator = taskDates.indexOf("/"); //locates location of second / for "by"
        String taskStart = "";
        String taskEnd = "";
        try {
            taskStart = taskDates.substring(0, indexSeparator - 1); //creates "by" substring
            taskEnd = taskDates.substring(indexSeparator + 4);
        }catch(StringIndexOutOfBoundsException a){
            System.out.println("Input error. Use /to in command line to specify ending time. Type 'help' for " +
                    "more information");
            return;
        }
        Event newTask = new Event(taskDescription, taskStart, taskEnd);
        this.addTask(newTask);
        System.out.println("Got it. I've added this to Duke.task:");
        System.out.println("    " + newTask.toString());
    }

    private void printHelp(){ //Duke prints this if none of the commands below are used
        System.out.println("Looks like you did not enter a valid Duke.command.\n");
        System.out.println("Command list:");
        System.out.println("    1. todo: Adds a Duke.task to be done");
        System.out.println("        Usage example: todo eat dinner");
        System.out.println("    2. deadline: Adds a Duke.task to be done, use /by to specify when it is due");
        System.out.println("        Usage example: deadline submit homework /by 3pm tonight");
        System.out.println("    3. event: Adds an upcoming event. Use /from and /to to specify when it starts " +
                "and ends respectively.");
        System.out.println("        Usage example: event lecture this evening /from 4pm /to 6pm");
        System.out.println("    4. list: lists all tasks recorded by duke. Only enter the keyword.");
        System.out.println("    5. mark: Marks a specific Duke.task as done. Command requires a specific " +
                "index starting from 1");
        System.out.println("        Usage example: mark 2 (assuming that there are more than two tasks in the list");
        System.out.println("    6. bye: Exits duke");
    }
}
