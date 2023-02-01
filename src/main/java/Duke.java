import java.util.Scanner;

public class Duke {

    public static final String HORIZONTAL_LINES = "____________________________________________________________\n";
    public static final String INDENTATIONS = "    ";

    public static void main(String[] args) {
        System.out.println(INDENTATIONS + HORIZONTAL_LINES +
                INDENTATIONS + "Hello! I'm Duke\n" +
                INDENTATIONS + "What can I do for you?\n" +
                INDENTATIONS + HORIZONTAL_LINES);
        Scanner in = new Scanner(System.in);
        String readLine;
        Task[] tasks = new Task[100];
        int taskIndex = 0;
        while(true) {
            readLine = in.nextLine();
            if(readLine.equals("bye")) {
                System.out.println(INDENTATIONS + HORIZONTAL_LINES +
                        INDENTATIONS + "Bye. Hope to see you again soon!\n" +
                        INDENTATIONS + HORIZONTAL_LINES);
                break;
            } else if (readLine.equals("list")) {
                // list out the tasks and status in todo
                System.out.println(INDENTATIONS + HORIZONTAL_LINES +
                        INDENTATIONS + "Here are the tasks in your list: ");

                for (int i = 0; i < taskIndex; ++i) {
                    int taskNumber = i+1;
                    System.out.println(INDENTATIONS + taskNumber + "." +
                            "[" + tasks[i].getStatusIcon() + "] " + tasks[i].getTaskDescription());
                }

                System.out.println(INDENTATIONS + HORIZONTAL_LINES);

            } else if (readLine.startsWith("mark")) {
                String toMark = readLine.substring(5);
                toMark = toMark.trim();
                int indexToMark = Integer.parseInt(toMark) - 1;
                tasks[indexToMark].setDone(true);
                System.out.println(INDENTATIONS + "Nice! I've marked this task as done:\n" +
                        INDENTATIONS + "  [X] " + tasks[indexToMark].getTaskDescription() + '\n' +
                        INDENTATIONS + HORIZONTAL_LINES);

            } else if (readLine.startsWith("unmark")) {
                String toMark = readLine.substring(7);
                toMark = toMark.trim();
                int indexToMark = Integer.parseInt(toMark) - 1;
                tasks[indexToMark].setDone(false);
                System.out.println(INDENTATIONS + "OK, I've marked this task as not done yet: \n" +
                        INDENTATIONS + "  [ ] " + tasks[indexToMark].getTaskDescription() + '\n' +
                        INDENTATIONS + HORIZONTAL_LINES);
                
            }else {
                // add new tasks into todo
                tasks[taskIndex] = new Task(readLine);
                System.out.println(INDENTATIONS + HORIZONTAL_LINES +
                        INDENTATIONS + "added: " + readLine + '\n' +
                        INDENTATIONS + HORIZONTAL_LINES);
                taskIndex++;
            }
        }

    }
}
