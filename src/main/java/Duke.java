import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String LOGO = "███████████████████████████████████████████████████████████████████████████████████████████"
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
        String SYMBOL = "─────▄██▀▀▀▀▀▀▀▀▀▀▀▀▀██▄─────\n"
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
                + "IIII||───███████████───||IIII\n"
                + "─────||──███████████──||─────\n"
                + "──────||─███████████─||──────\n"
                + "───────|||||||||||||||───────\n"
                + "─────────████||█████─────────\n"
                + "────────█████||██████────────\n"
                + "──────███████||███████───────\n"
                + "─────█████──█||█──█████──────\n"
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



        System.out.println(LOGO);
        Scanner input = new Scanner(System.in);
        System.out.println("Hello! I'm Jigsaw\n");
        System.out.println("What can I do for you?\n");
        Task[] tasks = new Task[100];
        int taskCount = 0;
        boolean isActive = true;
        while (isActive) {
            String command = input.nextLine();
            String[] commands = command.split(" ",2);
            try {
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
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks[Integer.parseInt(commands[1]) - 1].printTask());
                    break;
                case "unmark":
                    tasks[Integer.parseInt(commands[1]) - 1].markAsUndone();
                    System.out.println("OK, I've marked this task as not done yet:");
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
                    addDeadline(commands[1], taskCount, tasks);
                    break;
                case "event":
                    addEvent(commands[1], taskCount, tasks);
                    break;
                default:
                    break;
                }
            } catch (IncorrectDeadlineException e) {
                System.out.println(e.printError());
            } catch (IncorrectEventException e) {
                System.out.println(e.printError());
            }
        }
        System.out.println(SYMBOL);
        input.close();
    }
    public static void addDeadline(String command, int taskCount, Task[] tasks) throws IncorrectDeadlineException {
        if (command.indexOf("/by") == -1) {
            throw new IncorrectDeadlineException();
        }
        String[] message = command.split(" /by", 2);
        Deadline deadline = new Deadline(message[0], message[1]);
        tasks[taskCount] = deadline;
        taskCount++;
        System.out.println("Got it I have added this task:");
        System.out.println("  " + deadline.toString());
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }
    public static void addEvent(String command, int taskCount, Task[] tasks) throws IncorrectEventException {
        if (command.indexOf("/from") == -1 || command.indexOf("/to") == -1) {
            throw new IncorrectEventException();
        }
        String[] message = command.split(" /from");
        String[] period = message[1].split(" /to");
        Event event = new Event(message[0], period[0], period[1]);
        tasks[taskCount] = event;
        taskCount++;
        System.out.println("Got it I have added this task:");
        System.out.println("  " + event.toString());
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }
}


