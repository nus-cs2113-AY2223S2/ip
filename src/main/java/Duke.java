import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {

        Duke.logo();
        Duke.greeting();

        Scanner input = new Scanner(System.in);
        String line = "";

        Task[] tasks = new Task[100];
        //int tasksCount = 0;

        while (!(line = input.nextLine()).equals("bye")) {
           Duke.processInput(line, tasks);
        }

        Duke.ending();
        System.exit(0);

    }

    public static void logo(){
        String logo =
                "   _____  .__   _____                   .___\n" +
                        "  /  _  \\ |  |_/ ____\\______   ____   __| _/\n" +
                        " /  /_\\  \\|  |\\   __\\\\_  __ \\_/ __ \\ / __ |\n" +
                        "/    |    \\  |_|  |   |  | \\/\\  ___// /_/ |\n" +
                        "\\____|__  /____/__|   |__|    \\___  >____ |\n" +
                        "        \\/                        \\/     \\/\n";

        System.out.println("Hello from\n" + logo);
    }

    public static void greeting(){
        String intro = "____________________________________________________________\n" +
                " Hello! I'm Alfred Pennyworth.\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";
        System.out.println(intro);
    }

    public static void ending(){
        String ending = " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________";
        System.out.println(ending);
    }
    public static void printAddTaskMessage(Task t){
        System.out.println("Got it. I've added this task:\n  " + t + "\nNow you have " + t.getNumberOfTasks() +" tasks in the list.");
    }
    public static void processInput(String line, Task[] tasks){

        String[] words = line.split(" ",2);
        String command = words[0];
        int tasksCount = 0;
        if(tasks[0]!=null){
            tasksCount = tasks[0].getNumberOfTasks();
        }
        // words[0] is the command, words[n] is the next few words

        switch (command) {
        case "todo":
            line = words[1]; // to remove the command
            Task td = new Todo(words[1]);
            tasks[tasksCount] = td;
            printAddTaskMessage(td);
            break;
        case "deadline":
            line = words[1]; // to remove the command
            String[] deadlineDetails = line.split(("/by "));
            Task d = new Deadline(deadlineDetails[0],deadlineDetails[1]);
            tasks[tasksCount++] = d;
            printAddTaskMessage(d);
            break;
        case "event":
            line = words[1]; // to remove the command
            String[] eventDetails = line.split(("/from "));
            String eventName = eventDetails[0];
            String from = line.substring(line.indexOf("/from ") + 6, line.indexOf(" /to"));
            eventDetails = line.split(("/to "));
            String to = eventDetails[1];
            Task e = new Event(eventName,from,to);
            tasks[tasksCount] = e;
            printAddTaskMessage(e);
            break;

        case "mark":
            int markIndex = Integer.parseInt(words[1]) - 1;
            System.out.println("Nice! I've marked this task as done: ");
            tasks[markIndex].markAsDone();
            System.out.println(tasks[markIndex]);
            break;
        case "unmark":
            int unmarkIndex = Integer.parseInt(words[1]) - 1;
            System.out.println("Okay, I've marked this task as not done yet: ");
            tasks[unmarkIndex].markAsNotDone();
            System.out.println(tasks[unmarkIndex]);
            break;
        case "list":
            int count = 0;
            for (Task t : tasks){
                if(t != null){
                    System.out.println(++count +". " + t);
                }
            }
            break;

        default:
            System.out.println("added: "+line+"\n");
            Task t = new Task(line);
            tasks[tasksCount] = t;
        }


    }
}
