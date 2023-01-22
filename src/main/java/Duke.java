import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo =
            "   _____  .__   _____                   .___\n" +
        "  /  _  \\ |  |_/ ____\\______   ____   __| _/\n" +
        " /  /_\\  \\|  |\\   __\\\\_  __ \\_/ __ \\ / __ |\n" +
        "/    |    \\  |_|  |   |  | \\/\\  ___// /_/ |\n" +
        "\\____|__  /____/__|   |__|    \\___  >____ |\n" +
        "        \\/                        \\/     \\/\n";

        System.out.println("Hello from\n" + logo);

        String intro = "____________________________________________________________\n" +
                " Hello! I'm Alfred Pennyworth.\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";
        System.out.println(intro);

        Scanner input = new Scanner(System.in);
        String line = input.nextLine();
        boolean isTerminated = false;
        while(!isTerminated) {
            switch (line) {
            case "bye":
                String ending = " Bye. Hope to see you again soon!\n" +
                        "____________________________________________________________";
                System.out.println(ending);
                //isTerminated = true;
                //not yet in use
                System.exit(0);
            default:
                System.out.println(line+"\n");
                line = input.nextLine();
            }
        }

    }
}
