import java.util.Scanner;
public class Duke {

    public static void drawLine () {
        System.out.println("=====================================================================================");
    }

    public static void dukeChatBox(){
        System.out.println("Duke:\n");
    }

    public static void userChatBox(String name){
        System.out.println(name + ":\n");
    }
    public static String greetAndAskName () {
        drawLine();
        dukeChatBox();
        System.out.println("Hello! I'm Duke\n" + "What is your name?");
        drawLine();

        System.out.print("Please enter your name here: ");
        String name;
        Scanner in = new Scanner(System.in);
        name = in.nextLine();
        drawLine();

        dukeChatBox();
        System.out.println("Nice to meet you! " + name + "!");
        System.out.println("What can I do for you?");
        drawLine();

        return name;
    }

    public static void sayGoodbye(String name) {
        drawLine();
        dukeChatBox();
        System.out.println("Goodbye, " + name + "! Hope to see you again soon!");
        drawLine();
    }

    public static void addTask(String line, int sequenceOfTask, Task[] taskList){
        //add task
        Task newTask = new Task(line);

        //draw "add task" chat
        drawLine();
        dukeChatBox();
        System.out.println("added: " + line);
        drawLine();

        //add new task to list
        taskList[sequenceOfTask] = newTask;
    }

    public static void markAsDone(String line, Task[] taskList){
        int spacePosition = line.indexOf(" ");
        String numberString = line.substring(spacePosition+1);
        int number = Integer.parseInt(numberString);
        taskList[number-1].isDone = true;

        //draw chat box
        drawLine();
        dukeChatBox();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("[" + taskList[number-1].getStatusIcon() + "] " + taskList[number-1].getTaskName());
        drawLine();
    }

    public static void markAsUndone(String line, Task[] taskList){
        int spacePosition = line.indexOf(" ");
        String numberString = line.substring(spacePosition+1);
        int number = Integer.parseInt(numberString);
        taskList[number-1].isDone = false;

        //draw chat box
        drawLine();
        dukeChatBox();
        System.out.println("Ok! I've marked this task as nor done yet: ");
        System.out.println("[" + taskList[number-1].getStatusIcon() + "] " + taskList[number-1].getTaskName());
        drawLine();
    }

    public static void printList(Task[] taskList, int sequenceOfTask ){
        drawLine();
        dukeChatBox();

        System.out.println("Below is your task list");

        for (int i = 0; i < sequenceOfTask; ++i){
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

        String name = greetAndAskName();

        String line;
        Scanner in = new Scanner(System.in);
        userChatBox(name);
        line = in.nextLine();

        Task[] taskList = new Task[100];
        int sequenceOfTask = 0;

        while(!line.equals("bye")){
            if(line.equals("list")){
                printList(taskList, sequenceOfTask);
            }
            else if(line.contains("unmark")){
                markAsUndone(line, taskList);
            }
            else if(line.contains("mark")){
                markAsDone(line, taskList);
            }
            else {
                addTask(line, sequenceOfTask, taskList);
                sequenceOfTask ++;
            }

            userChatBox(name);
            line = in.nextLine();
        }

        sayGoodbye(name);
    }
}