import java.util.Arrays;
import java.util.Scanner;

public class CommandsListener {
    static final String divider = "--------------------------------------------------------------------";

    private static void printLines(String... lines) {
        System.out.println(divider);
        for (String line: lines) {
            System.out.println(line);
        }
        System.out.println(divider + System.lineSeparator());
       }

   public static void greet() {
       String logo =

                " ______ _   _  ___________ _     _____ _____  _   __" + "\n"
               + "/  ___| | | ||  ___| ___ \\ |   |  _  /  __ \\| | / /" + "\n"
               + "\\ `--.| |_| || |__ | |_/ / |   | | | | /  \\/| |/ /" + "\n"
                + "`--. \\  _  ||  __||    /| |   | | | | |    |    \\" + "\n"
               + "/\\__/ / | | || |___| |\\ \\| |___\\ \\_/ / \\__/\\| |\\  \\" + "\n"
               + "\\____/\\_| |_/\\____/\\_| \\_\\_____/\\___/ \\____/\\_| \\_/";


       printLines("Hello from", logo);

       printLines("Hello! I'm SHERLOCK", "What can I do for you?");
   }
   public static void listen() {
        Scanner in = new Scanner(System.in);
        String line;

         line = in.nextLine();

        if(line.equalsIgnoreCase("bye")) {
          printLines("Bye. Hope to see you again soon!");
          return;
        }
        printLines(line);
        listen();
    }
    private static TaskList taskList = new TaskList(100);
    public static void lisetnForTasks() {
        Scanner in = new Scanner(System.in);
        String line;

        line = in.nextLine();

        if (line.equalsIgnoreCase("list")) {
            String[] tasks = taskList.getTasks();
            int index = 1;
            System.out.println(divider);
            for (String task: tasks){
                if(task != null) {
                    System.out.println(String.format("%d. %s", index++, task ));
                }
            }
            System.out.println(divider);
        }

        else if (line.equalsIgnoreCase("bye")) {
            printLines("Bye. Hope to see you again soon!");
            return;
        }
        else {
            taskList.addTask(line);
            printLines("added: " + line);
        }

        listen(expectedTasksCount);
    }
}
