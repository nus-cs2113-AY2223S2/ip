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
                            tasks[i].toString());
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
                
            }else if (readLine.startsWith("todo")) {
                // add new tasks into todo
                String toAdd = readLine.substring(5);
                toAdd = toAdd.trim();
                Todo addTodo = new Todo(toAdd);
                tasks[taskIndex] = addTodo;
                taskIndex++;
                System.out.println(INDENTATIONS + HORIZONTAL_LINES +
                        INDENTATIONS + "Got it. I've added this task: \n " +
                        INDENTATIONS + "  " + addTodo.toString() + '\n' +
                        INDENTATIONS + "Now you have " + taskIndex + " tasks in the list. \n" +
                        INDENTATIONS + HORIZONTAL_LINES);
            } else if (readLine.startsWith("deadline") & readLine.contains("/by")) {
                int byIndex = readLine.indexOf("/by");
                String toAddDeadline = readLine.substring(9, byIndex - 1);
                String by = readLine.substring(byIndex + 3);
                toAddDeadline = toAddDeadline.trim();
                by = by.trim();
                Deadline addDeadline = new Deadline(toAddDeadline, by); // taskDesc + by
                tasks[taskIndex] = addDeadline;
                taskIndex++;
                System.out.println(INDENTATIONS + HORIZONTAL_LINES +
                        INDENTATIONS + "Got it. I've added this task: \n" +
                        INDENTATIONS + "  " + addDeadline.toString() + '\n' +
                        INDENTATIONS + "Now you have " + taskIndex + " tasks in the list. \n" +
                        INDENTATIONS + HORIZONTAL_LINES);
            } else if (readLine.startsWith("event") &
                    readLine.contains("/from") & readLine.contains("/to")) {
                int fromIndex = readLine.indexOf("/from");
                int toIndex = readLine.indexOf("/to");
                String toAddEvent = readLine.substring(6, fromIndex - 1);
                String fromTime = readLine.substring(fromIndex + 5, toIndex - 1);
                String toTime = readLine.substring(toIndex + 3);
                toAddEvent = toAddEvent.trim();
                fromTime = fromTime.trim();
                toTime = toTime.trim();
                Event addEvent = new Event(toAddEvent, fromTime, toTime);
                tasks[taskIndex] = addEvent;
                taskIndex++;
                System.out.println(INDENTATIONS + HORIZONTAL_LINES +
                        INDENTATIONS + "Got it. I've added this task: \n" +
                        INDENTATIONS + "  " + addEvent.toString() + '\n' +
                        INDENTATIONS + "Now you have " + taskIndex + " tasks in the list. \n" +
                        INDENTATIONS + HORIZONTAL_LINES);
            }
        }

    }
}
