import java.util.Scanner;  // Import the Scanner class
public class Duke {

    public static void drawLine() {  // Draw horizontal lines
        for (int i = 0; i < 30; i++) {
            System.out.print("_");
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        drawLine();
        System.out.println();

        System.out.println("Hello! I'm Duke");  // Greetings
        System.out.println("What can I do for you?");

        drawLine();
        System.out.println();

        Scanner scan = new Scanner(System.in);  // Create a Scanner object
        String echo = "echo";

        while(true) {  //Endless loop until "bye" is detected
            echo = scan.nextLine();
            drawLine();
            System.out.println();
            if(echo.equals("bye")) {  //Terminate echo and exit while loop
                break;
            }
            System.out.println(echo);
            drawLine();
            System.out.println();
        }

        System.out.println("Bye. Hope to see you again soon!");
        drawLine();
        System.out.println();

    }

}
