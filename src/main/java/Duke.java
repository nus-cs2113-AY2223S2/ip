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

    public static void addTask(String echoWords, String[] tasks, int numOfTasks){
        drawLine();
        dukeChatBox();

        tasks[numOfTasks] = echoWords;

        System.out.println("added: " + echoWords);
        drawLine();
    }

    public static void listOutTasks(String[] tasks){
        drawLine();
        dukeChatBox();

        System.out.println("Below is your task list");

        int i = 1;
        for(String task: tasks){
            if(task == null){
                break;
            }
            System.out.println(i + ". " + task);
            i++;
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

        String[] tasks = new String[100];
        int numOfTasks = 0;

        while(line.equals("bye") == false){
            if(line.equals("list")){
                listOutTasks(tasks);
            }
            else {
                addTask(line, tasks, numOfTasks);
                numOfTasks ++;
            }

            userChatBox(name);
            line = in.nextLine();
        }
        sayGoodbye(name);
    }
}