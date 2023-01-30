public class TaskManager {
    protected Task[] taskList;
    protected int taskCount;
    TaskManager(int taskCount){
        this.taskCount = 0;
        taskList = new Task[100];
    }
    public void addTask(Task upcomingTask){
        taskList[taskCount] = upcomingTask;
        taskCount += 1;
    }

    public void markTaskAsDone(int taskIndex){
        taskList[taskIndex-1].markAsDone();
    }
    public void markTaskAsUndone(int taskIndex){
        taskList[taskIndex-1].markAsUndone();
    }
    public void markTask(String input) {
        int whitespaceIndex = input.indexOf(" ");
        int startingIndex = Integer.parseInt(input.substring(whitespaceIndex + 1));
        if (input.indexOf("m") == 0) {
            //taskList[startingIndex - 1].markAsDone();
            this.markTaskAsDone(startingIndex);
            System.out.println("Nice! I've marked this task as done: ");
        } else {
            //taskList[startingIndex - 1].markAsUndone();
            this.markTaskAsUndone(startingIndex);
            System.out.println("OK, I've marked this task as not done yet: ");
        }
        System.out.println(taskList[startingIndex - 1].toString());
        //printSeparator();
    }
    public void listTasks(){
        for (int i = 0; i < taskCount; i++){
            System.out.print((i+1)+ ".");
            System.out.println(taskList[i].toString());
        }
    }

    public void handleCommand(String input){
        if (input.equals("list")) {
            this.listTasks();
        } else if (input.contains("mark")) {
            this.markTask(input);
        } else if (input.contains("todo")){
            generateToDo(input);
        } else if (input.contains("deadline")){
            generateDeadline(input);
        } else if (input.contains("event")){
            generateEvent(input);
        } else{
            printHelp();
        }
    }

    private void generateToDo(String input) {
        String job = input.substring(5);
        //System.out.println(job);
        ToDo newTask = new ToDo(job);
        //taskList[Task.getTaskNumber()] = newTask;
        this.addTask(newTask);
        System.out.println("Got it. I've added this to task:");
        System.out.println("    " + newTask.toString());
    }
    private void generateDeadline(String input) {
        String job = input.substring(8);
        int indexSeparator = job.indexOf("/");
        String taskDescription = job.substring(0,indexSeparator);
        String taskDue = job.substring(indexSeparator+4);
        Deadline newTask = new Deadline(taskDescription, taskDue);
        this.addTask(newTask);
        System.out.println("Got it. I've added this to task:");
        System.out.println("    " + newTask.toString());
    }

    private void generateEvent(String input){
        String job = input.substring(6);
        int indexSeparator = job.indexOf("/");
        String taskDescription = job.substring(0, indexSeparator);
        String taskDates = job.substring(indexSeparator+6);
        indexSeparator = taskDates.indexOf("/");
        String taskStart = taskDates.substring(0, indexSeparator-1);
        String taskEnd = taskDates.substring(indexSeparator+4);
        Event newTask = new Event(taskDescription, taskStart, taskEnd);
        this.addTask(newTask);
        System.out.println("Got it. I've added this to task:");
        System.out.println("    " + newTask.toString());
    }

    private void printHelp(){ //Duke prints this if none of the commands below are used
        System.out.println("Looks like you did not enter a valid command.\n");
        System.out.println("Command list:");
        System.out.println("    1. todo: Adds a task to be done");
        System.out.println("        Usage example: todo eat dinner");
        System.out.println("    2. deadline: Adds a task to be done, use /by to specify when it is due");
        System.out.println("        Usage example: deadline submit homework /by 3pm tonight");
        System.out.println("    3. event: Adds an upcoming event. Use /from and /to to specify when it starts " +
                "and ends respectively.");
        System.out.println("        Usage example: event lecture this evening /from 4pm /to 6pm");
        System.out.println("    4. list: lists all tasks recorded by duke. Only enter the keyword.");
        System.out.println("    5. mark: Marks a specific task as done. Command requires a specific " +
                "index starting from 1");
        System.out.println("        Usage example: mark 2 (assuming that there are more than two tasks in the list");
        System.out.println("    6. bye: Exits duke");
    }
}
