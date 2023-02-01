import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = "███████████████████████████████████████████████████████████████████████████████████████████████\n"
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
        System.out.println("Hello! I'm Duke\n");
        System.out.println("What can I do for you?\n");
        String[] tasks = new String[100];
        int taskCount = 0;
        boolean run = true;
        while (run) {
            String command = input.nextLine();
            switch (command) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!\n");
                    run = false;
                    break;
                case "list":
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println((i + 1) + ". " + tasks[i]);
                    }
                    break;
                default:
                    tasks[taskCount] = command;
                    System.out.println("added: " + command);
                    taskCount++;
                    break;
            }
        }
        System.out.println(symbol);
        input.close();
        }
    }


