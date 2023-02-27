import java.util.Scanner;  // Import the Scanner class
import java.util.Arrays;
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
        String input = scan.nextLine();

        Task tasks[] = new Task[100];  //Create a list of tasks
        int curPos = 0;  //Initialise current position

        while(!(input.isEmpty())) {
            drawLine();
            if(input.equals("bye")) {  //Terminate while loop
                break;
            }
            else if(input.equals("list")) {  //List out all the tasks with done/undone status
                System.out.println ("Here are the tasks in your list:");
                for(int i = 0; i < curPos; i++) {
                    System.out.println((i+1) + ". " + tasks[i].getStatusIcon() + " " + tasks[i].description);
                }
                drawLine();
            }
            else if(input.startsWith ("mark") || input.startsWith ("unmark")){
                String[] arrOfInput = input.split(" ", 2);
                int index = Integer.parseInt (arrOfInput[1]) - 1;  //index = curPos + 1
                if(input.startsWith ("mark")){
                    tasks[index].isDone = true;
                    System.out.println ("Nice! I've marked this task as done:");
                    System.out.println ("   " + tasks[index].getStatusIcon () + " " + tasks[index].description);
                }
                else {
                    tasks[index].isDone = false;
                    System.out.println ("OK, I've marked this task as not done yet:");
                    System.out.println ("   " + tasks[index].getStatusIcon () + " " + tasks[index].description);
                }
                drawLine();;
            }
            else{
                System.out.println ("added: " + input);
                tasks[curPos] = new Task(input);
                curPos++;
                drawLine();
            }
            input = scan.nextLine ();
        }

        System.out.println("Bye. Hope to see you again soon!");
        drawLine();

    }

}
