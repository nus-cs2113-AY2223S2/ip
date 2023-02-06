import java.util.Scanner;

public class Duke {

    private static Task[] tasks = new Task[100];
    private static int indexOfTask = 0;

    public static void drawLine() {
        System.out.println("=====================================================================================");
    }

    public static void createSiriChatBox() {
        System.out.println("Siri:\n");
    }

    public static void createUserChatBox() {
        System.out.println( "user: \n");
    }


    public static void greet() {
        //greet and ask name
        drawLine();
        createSiriChatBox();
        System.out.println("Hey, I'm Siri\n" + "What can I do for you?");
        drawLine();
    }

    public static void sayGoodbye() {
        //say goodbye with name
        drawLine();
        createSiriChatBox();
        System.out.println(">o< Goodbye! Hope to see you again soon! >o<");
        drawLine();
    }


    public static void printTaskList() {
        System.out.println("Below is your task list");
        for (int i = 0; i < indexOfTask; ++i) {
            System.out.println((i + 1) + ". " + tasks[i].toString());
        }
    }

    public static void markTask(String marker, String taskNumberString) {
        int taskNumber = Integer.parseInt(taskNumberString);
        if (taskNumber > indexOfTask) {
            //error handling
            System.out.println("): There is no Task number " + taskNumber + " yet! :(");
        } else {
            if (marker.equals("mark")) {
                //mark task as done
                tasks[taskNumber - 1].isDone = true;
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(tasks[taskNumber-1].toString());
            } else {
                //mark task as undone
                tasks[taskNumber - 1].isDone = false;
                System.out.println("Ok! I've marked this task as not done yet: ");
                System.out.println(tasks[taskNumber-1].toString());
            }
        }
    }

    //Task Description = taskD
    public static void addTask(String taskCommand, String taskD){
        switch (taskCommand){
        case "todo":
            tasks[indexOfTask] = new ToDo(taskD.trim());
            break;
        case "deadline":
            String[] deadlineTaskD = taskD.split("/by ", 2);
            tasks[indexOfTask] = new Deadline(deadlineTaskD[0],deadlineTaskD[1]);
            break;
        case "event":
            String[] eventName = taskD.split("/from ", 2);
            String[] eventDate = eventName[1].split("/to ", 2);
            tasks[indexOfTask] = new Event(eventName[0], eventDate[0], eventDate[1] );
        }
    }

    public static void printThisTask(){
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks[indexOfTask].toString());
        System.out.println("Now you have " + (indexOfTask+1) + " tasks in the list." );
    }

    public static void readUserInput(String input){

        String[] command = input.split(" ", 2);

        switch (command[0]){
        case "list":
            printTaskList();
            break;
        case "mark":
        case "unmark":
            markTask(command[0], command[1]);
            break;
        case "todo":
        case "deadline":
        case "event":
            addTask(command[0], command[1]);
            printThisTask();
            indexOfTask++;
            break;
        default:
            //error handling
            System.out.println("Wrong command!");
            break;
        }
    }

    public static void main(String[] args) {

        String logo = "  ______     __     __  _____     __\n"
                + " | _____|   |__|   |  |/ ____|   |__|\n"
                + " | |____     __    |   /          __\n"
                + " |_____ |   |  |   |  |          |  |\n"
                + " _____| |   |  |   |  |          |  |\n"
                + " |______|   |__|   |__|          |__|\n";

        System.out.println("Hello from \n" + logo);

        //greet and ask name and greet again
        greet();

        //user input what he or she wants the chatbot to do
        createUserChatBox();
        Scanner in = new Scanner(System.in);
        String input = in.nextLine().trim();

        //according to the input, chatbot reply accordingly
        //exit while loop only when userinput is "bye"
        while (!input.equals("bye")) {
            drawLine();
            createSiriChatBox();
            readUserInput(input);
            drawLine();
            createUserChatBox();

            input = in.nextLine().trim();
        }

        sayGoodbye();
    }
}