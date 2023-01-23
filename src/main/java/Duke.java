import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {

        Duke.logo();
        Duke.greeting();

        Scanner input = new Scanner(System.in);
        String line = "";

        Task[] tasks = new Task[100];
        int tasksCount = 0;

        while (!(line = input.nextLine()).equals("bye")) {
           tasksCount = Duke.processInput(line, tasks,tasksCount);
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

    public static int processInput(String line, Task[] tasks, int tasksCount){

        String[] words = line.split(" ");
        String command = words[0];

        switch (command) {
        case "mark":
            int markIndex = Integer.parseInt(words[1]) - 1;
            System.out.println("Nice! I've marked this task as done: ");
            tasks[markIndex].markAsDone();
            System.out.println("[" + tasks[markIndex].getStatusIcon() + "] " + tasks[markIndex].getDescription());
            break;
        case "unmark":
            int unmarkIndex = Integer.parseInt(words[1]) - 1;
            System.out.println("Okay, I've marked this task as not done yet: ");
            tasks[unmarkIndex].markAsNotDone();
            System.out.println("[" + tasks[unmarkIndex].getStatusIcon() + "] " + tasks[unmarkIndex].getDescription());
            break;
        case "list":
            int count = 0;
            for (Task t : tasks){
                if(t != null){
                    System.out.println(++count +". " + "[" + t.getStatusIcon() + "] " + t.getDescription());
                }
            }
            break;

        default:
            System.out.println("added: "+line+"\n");
            Task t = new Task(line);
            tasks[tasksCount++] = t;
        }
        return tasksCount;

    }
}
