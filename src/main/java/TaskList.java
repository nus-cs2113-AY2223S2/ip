import java.util.ArrayList;



public class TaskList {

    protected ArrayList<Task> tasks;

    /**
    * Initializer for TaskList class
    */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
    * Method to get the size of the task list
    *
    * @return the size of list
    */
    public int getSize() {
        return tasks.size();
    }

    /**
    * Method to create a new task
    */
    public void addTask(Task newTask) {
        tasks.add(newTask);
    }

    /**
    * Method to get the task using the task number
    *
    * @param taskNumber the task number
    * @return the task
    */
    public Task getTask(int taskNumber) {
        return tasks.get(taskNumber);
    }

    /**
    * Method to mark the status of a task as done
    */
    public void MarkStatusAsDone(int taskNumber) {
        tasks.get(tasks.size() - 1).MarkStatusDone();
    }

    /**
    * Method to find and print the tasks that user is trying to find using keyword
    *
    * @param taskKeyWord the keyword
    */
    public void findTask(String taskKeyWord) {
        Greeting.printSeperator();
        System.out.println("Okay, Auntie find for you already... here:");
        for (int i = 0; i < tasks.size(); i++) {
            String taskDecription = tasks.get(i).getTask();
            if (taskDecription.contains(taskKeyWord)){
                System.out.println("\t" + (i+1) + "." + tasks.get(i).printTask());
            }
        }
        System.out.println("\n");
        Greeting.printSeperator();
    }

    /**
    * Method to print all Tasks
    */
    public void printTasks() {
        Greeting.printSeperator();
        System.out.println("\tAuntie show you ah... all this are your tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t" + (i+1) + "." + tasks.get(i).printTask());
        }
        System.out.println("\n");
        Greeting.printSeperator();
    }
 
    /**
    * Method to print the new task that user has just added
    *
    * @param taskNumber the task number of the new task
    */
    public void printNewTask(int taskNumber) {
        Greeting.printSeperator();
        System.out.println("\tOkay auntie add for you this one: \n"+ "\t\t" + tasks.get(tasks.size()-1).printTask());
        //tasksLength ++;
        System.out.println("\tNow you got " + tasks.size() + " tasks" + "\n");
        Greeting.printSeperator();
    }
 
    /**
    * Method to mark task as done
    *
    * @param userInput the task that the user has input
    */
    public void mark(String userInput) {
        int taskNumber = Integer.parseInt(userInput);
        tasks.get(taskNumber - 1).MarkStatusDone();
        Greeting.printSeperator();
        System.out.println("\tVery good... finish more task next time more successfull than me okay\n" +
                "\t\t" + tasks.get(taskNumber - 1).printTask() + "\n");
        Greeting.printSeperator();
    }

    /**
    * Method to mark task as not done
    *
    * @param userInput the task that the user has input
    */
    public void unmark(String userInput) {
        int taskNumber = Integer.parseInt(userInput);
        tasks.get(taskNumber - 1).MarkStatusUndone();
        Greeting.printSeperator();
        System.out.println("\tHuh... then Auntie ask you, you mark as done for what??\n" +

                "\t\t" + tasks.get(taskNumber - 1).printTask()+ "\n");
        Greeting.printSeperator();
    }

    /**
    * Method to delete task
    *
    * @param userInput the task that the user has input
    */
    public void deleteTask(String userInput) {
        int taskNumber = Integer.parseInt(userInput);
        Task taskToBeRemoved = tasks.get(taskNumber - 1);
        tasks.remove(taskNumber - 1);
        Greeting.printSeperator();
        System.out.println("\tAlways so lazy... Okay Auntie remove this one for you:\n" +
                "\t\t" + taskToBeRemoved.printTask() + "\n");
        Greeting.printSeperator();
    }
    

    /**
    * Method to add a new todo task
    *
    * @param userInput the user input
    */
    public void addTodo(String userInput) throws IllegalInputException {

        String todoTask = userInput.substring(4).trim();
        if (todoTask == "") {
            throw new IllegalInputException();
        }
        tasks.add(new Todo (todoTask));

    }

    /**
    * Method to add a new deadline task
    *
    * @param userInput the user input
    */
    public void addDeadline(String userInput) throws IllegalInputException, MissingCommandException, IllegalDayException {

        if ((userInput.indexOf("/by") < 0 )){
            throw new MissingCommandException();
        }
        String deadlineTask = userInput.substring(8, userInput.indexOf("/by")).trim();
        if (deadlineTask == "") {
            throw new IllegalInputException();
        }
        String deadlineDay = userInput.substring(userInput.indexOf("/by") + 3).trim();
        if (deadlineDay == "") {
            throw new IllegalDayException();
        }
        tasks.add(new Deadline(deadlineTask, deadlineDay));

    }

    /**
    * Method to add a new event task
    *
    * @param userInput the user input
    */
    public void addEvent(String userInput) throws WrongCommandOrderException, IllegalInputException, MissingCommandException, IllegalDayException {

        if ((userInput.indexOf("/from") < 0 ) || (userInput.indexOf("/to") < 0 )){
            throw new MissingCommandException();
        }
        if ((userInput.indexOf("/from")) > (userInput.indexOf("/to"))){
            throw new WrongCommandOrderException();
        }
        String eventTask = userInput.substring(5, userInput.indexOf("/")).trim();
        if (eventTask == "") {
            throw new IllegalInputException();
        }
        String eventFrom = userInput.substring(userInput.indexOf("/from") + 5, userInput.indexOf("/to")).trim();
        String eventTo = userInput.substring(userInput.indexOf("/to") + 3).trim();
        if (eventFrom == "" || eventTo == "") {
            throw new IllegalDayException();
        }
        tasks.add(new Event(eventTask, eventFrom, eventTo));

    }

}
