import java.util.Arrays;
import java.util.Scanner;

public class Limey {
    public static void main(String[] args) {
        Task[] tasks = new Task[100];
        int numTasks = 0;
        String lineIn;

        Speech.sayHi();
        Scanner in = new Scanner(System.in);
        lineIn = in.nextLine();
        //switch case to decide what to do
        while(!lineIn.equals("bye")) {
            switch(lineIn) {
            case "list":
                Speech.printTaskList(tasks, numTasks);
                break;
            default:
                Task taskIn = new Task(lineIn);
                tasks[numTasks] = taskIn;
                Speech.printResponse(lineIn);
                break;
            }
            //update new line and numTasks for next iteration
            lineIn = in.nextLine();
            numTasks++;
        }

        Speech.sayBye();
    }

}

