import java.util.Scanner;
public class Duke {

    public static void drawLine () {
        System.out.println("=====================================================================================");
    }

    public static void createDukeChatBox(){
        System.out.println("Duke:\n");
    }

    public static void createUserChatBox(String username){
        System.out.println(username + ":\n");
    }
    public static String greetAndAskName () {
        //greet and ask name
        drawLine();
        createDukeChatBox();
        System.out.println("Hello! I'm Duke\n" + "What is your name?");
        drawLine();

        //for user to enter name
        System.out.print("Please enter your name here: ");
        String username;
        Scanner in = new Scanner(System.in);
        username = in.nextLine().trim();
        drawLine();

        //Greet with name (personalisation)
        createDukeChatBox();
        System.out.println(">o< Nice to meet you! " + username + "! >o<");
        System.out.println("What can I do for you?");
        drawLine();

        return username;
    }

    public static void sayGoodbye(String username) {
        //say goodbye with name
        drawLine();
        createDukeChatBox();
        System.out.println(">o< Goodbye, " + username + "! Hope to see you again soon! >o<");
        drawLine();
    }

    public static void addTask(String taskName, int indexOfTask, Task[] taskList){
        //add task
        Task newTask = new Task(taskName.trim());

        //add new task to list
        taskList[indexOfTask] = newTask;

        //"add task" chat
        drawLine();
        createDukeChatBox();
        System.out.println("added: " + newTask.getTaskName());
        drawLine();
    }

    public static void markTaskAsDone(String taskName, Task[] taskList, int indexOfTask){
        //analyse what is the task to be marked as done
        int spacePosition = taskName.indexOf(" ");
        String numberString = taskName.substring(spacePosition+1);
        int taskNumber = Integer.parseInt(numberString);

        //draw chat box
        drawLine();
        createDukeChatBox();

        if(taskNumber >  indexOfTask) {
            System.out.println("): There is no Task number " + taskNumber + " yet! :(" );
        } else {
            //mark task as done
            taskList[taskNumber - 1].isDone = true;

            System.out.println("Nice! I've marked this task as done: ");
            System.out.println("[" + taskList[taskNumber - 1].getStatusIcon() + "] " + taskList[taskNumber - 1].getTaskName());
        }

        drawLine();
    }

    public static void markTaskAsUndone(String taskName, Task[] taskList, int indexOfTask) {
        //analyse what is the task to be marked as undone
        int spacePosition = taskName.indexOf(" ");
        String numberString = taskName.substring(spacePosition + 1);
        int taskNumber = Integer.parseInt(numberString);

        //draw chat box
        drawLine();
        createDukeChatBox();

        //if there is no such taskNumber being added yet
        if (taskNumber > indexOfTask) {
            System.out.println("): There is no Task number " + taskNumber + " yet! :(");
        } else {
            //mark task as undone
            taskList[taskNumber - 1].isDone = false;

            System.out.println("Ok! I've marked this task as nor done yet: ");
            System.out.println("[" + taskList[taskNumber - 1].getStatusIcon() + "] " + taskList[taskNumber - 1].getTaskName());
        }
        drawLine();
    }

    public static void printList(Task[] taskList, int indexOfTask ){
        drawLine();
        createDukeChatBox();

        System.out.println("Below is your task list");

        //printing out all the tasks
        for (int i = 0; i < indexOfTask; ++i){
            System.out.println( (i+1) + ". [" + taskList[i].getStatusIcon() + "] " + taskList[i].getTaskName());
        }

        drawLine();
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        //greet and ask name and greet again
        String username = greetAndAskName();
        String input;


        //create task list & index of task
        Task[] taskList = new Task[100];
        int indexOfTask = 0;

        //user input what he or she wants the chatbot to do
        Scanner in = new Scanner(System.in);
        createUserChatBox(username);
        input = in.nextLine().trim();

        //according to the input, chatbot reply accordingly
        //exit while loop only when userinput is "bye"
        while(!input.equals("bye")){
            if(input.equals("list")){
                printList(taskList, indexOfTask);
            }
            else if(input.contains("unmark")){
                markTaskAsUndone(input, taskList, indexOfTask);
            }
            else if(input.contains("mark")){
                markTaskAsDone(input, taskList, indexOfTask);
            }
            else {
                addTask(input, indexOfTask, taskList);
                indexOfTask ++;
            }

            createUserChatBox(username);
            input = in.nextLine().trim();
        }

        sayGoodbye(username);
    }
}