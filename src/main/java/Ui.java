import java.util.Scanner;

public class Ui {

    static void loadDataMessage() {
        System.out.println(("Loading previous save data..."));
    }

    static void welcomeMessage() {
        System.out.println("Hi, I'm bob");
        System.out.println("What's up");
    }

    static void goodbyeMessage() {
        System.out.println("Bye! :D");
    }

    static String readCommand() {
        Scanner reader = new Scanner(System.in);
        return reader.nextLine();
    }

    static String printTask(Task task) {
        String taskType = task.getType();
        String formattedTask;

        switch (taskType) {
        case "todo":
            formattedTask = "[T][" + task.getStatusIcon() + "] " + task.description;
            break;

        case "deadline":
            formattedTask = "[D][" + task.getStatusIcon() + "] " + task.description;
            formattedTask += " (by: " + task.getTimings() + ")";
            break;

        case "event":
            formattedTask = "[E][" + task.getStatusIcon() + "] " + task.description;
            String[] timings = task.getTimings().split("/");
            formattedTask += " (from: " + timings[0] + " to: " + timings[1] + ")";
            break;

        default:
            formattedTask = "Unrecognised task";
        }

        return formattedTask;
    }
}
