import java.util.Scanner;  // Import the Scanner class
public class Duke {

    public static void drawLine() {  // Draw horizontal lines
        for (int i = 0; i < 30; i++) {
            System.out.print("_");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        drawLine();

        System.out.println("Hello! I'm Duke");  // Greetings
        System.out.println("What can I do for you?");

        drawLine();

        Scanner scan = new Scanner(System.in);  // Create a Scanner object
        String echo = "echo";

        String[] list = new String[100];  //Create a list
        int curPos = 0;  //Initialise current position
        int index = 1;  //Set index

        while(true) {  //Endless loop until "bye" is detected
            echo = scan.nextLine();
            drawLine();
            if(echo.equals("bye")) {  //Terminate echo and exit while loop
                break;
            }
            else if(echo.equals("list")) {
                for(int i = 0; i < curPos; i++) {
                    System.out.println(index + ". " + list[index - 1]);
                    index++;
                }
                drawLine();
            }
            else {
                list[curPos] = echo;
                curPos++;
                System.out.println("added: " + echo);
                drawLine();
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
        drawLine();

    }

}
