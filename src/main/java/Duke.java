import java.util.Objects;
import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void main(String[] args) {
        String logo = """
                 ____        _       \s
                |  _ \\ _   _| | _____\s
                | | | | | | | |/ / _ \\
                | |_| | |_| |   <  __/
                |____/ \\__,_|_|\\_\\___|
                """;


        System.out.println("Hello from\n" + logo);

        //to greet user
        PrintHorizontalBar();
        System.out.println("Hello! I'm Duke! \n");
        PrintHorizontalBar();


        String newTask;
        String[] tasks = new String[100];
        boolean hasTask = true;
        int numberoftasks = 0;

        while (hasTask && numberoftasks < 100) {
            System.out.println("What can I do for your today?");
            newTask = ReadTask();
            PrintHorizontalBar();

            if (Objects.equals(newTask, "list")) {
                printTaskList(tasks, numberoftasks);
            } else if (Objects.equals(newTask, "bye")){
                break;
            }


            else {
                System.out.println("added:  " + newTask);
                tasks[numberoftasks] = newTask;
                numberoftasks++;
            }
            PrintHorizontalBar();
            hasTask = CheckTask();


        }

        //to say bye
        System.out.println("Bye! Hope to see you again soon!\n");
        PrintHorizontalBar();

    }

    private static void printTaskList(String[] list, int len) {
        int i = 1;
        while (i <= len) {
            System.out.println(i + ": " + list[i - 1]);
            i++;
        }
    }

    private static void PrintHorizontalBar() {
        String horizontalBar = "-----------------------------------------\n";
        System.out.println(horizontalBar);
    }

    private static String ReadTask() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        return line;

    }

    private static boolean CheckTask() {

        System.out.println("Do you have any other task for me? Enter Y for 'Yes' and N for 'No'.");
        String response = ReadTask();
        if (Objects.equals(response, "N")) {
            return false;
        } else if (!Objects.equals(response, "Y")) {
            System.out.println("Invalid input. Enter Y for 'Yes' and N for 'No'.");
            response = ReadTask();
            if ( Objects.equals(response, "N")  ) {

                return false;
            }
            else if (!Objects.equals(response, "Y") ) {
                System.out.println("Invalid input. Closing App. :( ");
                return false;
            }
        }
        return true;

    }


}
