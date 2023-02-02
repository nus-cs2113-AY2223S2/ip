import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "███████████████████████████████████████████████████████████████████████████████████████████"
                + "████\n"
                + "█████████░░░░░░█░░░░░░░░░░█░░░░░░░░░░░░░░█░░░░░░░░░░░░░░█░░░░░░░░░░░░░░█░░░░░░██████████░░░░░░█\n"
                + "█████████░░▄▀░░█░░▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀░░██████████░░▄▀░░█\n"
                + "█████████░░▄▀░░█░░░░▄▀░░░░█░░▄▀░░░░░░░░░░█░░▄▀░░░░░░░░░░█░░▄▀░░░░░░▄▀░░█░░▄▀░░██████████░░▄▀░░█\n"
                + "█████████░░▄▀░░███░░▄▀░░███░░▄▀░░█████████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░██████████░░▄▀░░█\n"
                + "█████████░░▄▀░░███░░▄▀░░███░░▄▀░░█████████░░▄▀░░░░░░░░░░█░░▄▀░░░░░░▄▀░░█░░▄▀░░██░░░░░░██░░▄▀░░█\n"
                + "█████████░░▄▀░░███░░▄▀░░███░░▄▀░░██░░░░░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀░░██░░▄▀░░██░░▄▀░░█\n"
                + "█░░░░░░██░░▄▀░░███░░▄▀░░███░░▄▀░░██░░▄▀░░█░░░░░░░░░░▄▀░░█░░▄▀░░░░░░▄▀░░█░░▄▀░░██░░▄▀░░██░░▄▀░░█\n"
                + "█░░▄▀░░██░░▄▀░░███░░▄▀░░███░░▄▀░░██░░▄▀░░█████████░░▄▀░░█░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░▄▀░░░░░░▄▀░░█\n"
                + "█░░▄▀░░░░░░▄▀░░█░░░░▄▀░░░░█░░▄▀░░░░░░▄▀░░█░░░░░░░░░░▄▀░░█░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀░░█\n"
                + "█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░▄▀░░░░░░▄▀░░█\n"
                + "█░░░░░░░░░░░░░░█░░░░░░░░░░█░░░░░░░░░░░░░░█░░░░░░░░░░░░░░█░░░░░░██░░░░░░█░░░░░░██░░░░░░██░░░░░░█\n"
                + "███████████████████████████████████████████████████████████████████████████████████████████████\n";
        String symbol = "─────▄██▀▀▀▀▀▀▀▀▀▀▀▀▀██▄─────\n"
                + "────███───────────────███────\n"
                + "───███─────────────────███───\n"
                + "──███───▄▀▀▄─────▄▀▀▄───███──\n"
                + "─████─▄▀────▀▄─▄▀────▀▄─████─\n"
                + "─████──▄████─────████▄──█████\n"
                + "█████─██▓▓▓██───██▓▓▓██─█████\n"
                + "█████─██▓█▓██───██▓█▓██─█████\n"
                + "█████─██▓▓▓█▀─▄─▀█▓▓▓██─█████\n"
                + "████▀──▀▀▀▀▀─▄█▄─▀▀▀▀▀──▀████\n"
                + "███─▄▀▀▀▄────███────▄▀▀▀▄─███\n"
                + "███──▄▀▄─█──█████──█─▄▀▄──███\n"
                + "███─█──█─█──█████──█─█──█─███\n"
                + "███─█─▀──█─▄█████▄─█──▀─█─███\n"
                + "███▄─▀▀▀▀──█─▀█▀─█──▀▀▀▀─▄███\n"
                + "████─────────────────────████\n"
                + "─███───▀█████████████▀───████\n"
                + "─███───────█─────█───────████\n"
                + "─████─────█───────█─────█████\n"
                + "───███▄──█────█────█──▄█████─\n"
                + "─────▀█████▄▄███▄▄█████▀─────\n"
                + "──────────█▄─────▄█──────────\n"
                + "──────────▄█─────█▄──────────\n"
                + "───────▄████─────████▄───────\n"
                + "─────▄███████───███████▄─────\n"
                + "───▄█████████████████████▄───\n"
                + "─▄███▀───███████████───▀███▄─\n"
                + "███▀─────███████████─────▀███\n"
                + "▌▌▌▌▒▒───███████████───▒▒▐▐▐▐\n"
                + "─────▒▒──███████████──▒▒─────\n"
                + "──────▒▒─███████████─▒▒──────\n"
                + "───────▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒───────\n"
                + "─────────████░░█████─────────\n"
                + "────────█████░░██████────────\n"
                + "──────███████░░███████───────\n"
                + "─────█████──█░░█──█████──────\n"
                + "─────█████──████──█████──────\n"
                + "──────████──████──████───────\n"
                + "──────████──████──████───────\n"
                + "──────████───██───████───────\n"
                + "──────████───██───████───────\n"
                + "──────████──████──████───────\n"
                + "─██────██───████───██─────██─\n"
                + "─██───████──████──████────██─\n"
                + "─███████████████████████████─\n"
                + "─██─────────████──────────██─\n"
                + "─██─────────████──────────██─\n"
                + "────────────████─────────────\n"
                + "─────────────██──────────────\n";

        System.out.println(logo);
        Scanner input = new Scanner(System.in);
        System.out.println("Hello! I'm Jigsaw\n");
        System.out.println("What can I do for you?\n");
        Task[] tasks = new Task[100];
        int taskCount = 0;
        boolean isActive = true;
        while (isActive) {
            String command = input.nextLine();
            String[] commands = command.split(" ",2);
            switch (commands[0]) {
            case "bye":
                System.out.println("Bye. Hope to see you again soon!\n");
                isActive = false;
                break;
            case "list":
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + "." + tasks[i].toString());
                }
                break;
            case "mark":
                tasks[Integer.parseInt(commands[1]) - 1].markAsDone();
                System.out.println(tasks[Integer.parseInt(commands[1]) - 1].printTask());
                break;
            case "unmark":
                tasks[Integer.parseInt(commands[1]) - 1].markAsUndone();
                System.out.println(tasks[Integer.parseInt(commands[1]) - 1].printTask());
                break;
            case "todo":
                Todo todo = new Todo(commands[1]);
                tasks[taskCount] = todo;
                taskCount ++;
                System.out.println("Got it I have added this task:");
                System.out.println("  " + todo.toString());
                System.out.println("Now you have " + taskCount + " tasks in the list.");
                break;
            case "deadline":
                String[] message = commands[1].split("/by", 2);
                Deadline deadline = new Deadline(message[0], message[1]);
                tasks[taskCount] = deadline;
                taskCount ++;
                System.out.println("Got it I have added this task:");
                System.out.println("  " + deadline.toString());
                System.out.println("Now you have " + taskCount + " tasks in the list.");
                break;
            case "event":
                String[] task = commands[1].split("/from");
                String[] period = task[1].split("/to");
                Event event = new Event(task[0], period[0], period[1]);
                tasks[taskCount] = event;
                taskCount ++;
                System.out.println("Got it I have added this task:");
                System.out.println("  " + event.toString());
                System.out.println("Now you have " + taskCount + " tasks in the list.");
                break;
            default:
                break;
            }
        }
        System.out.println(symbol);
        input.close();
    }
}


