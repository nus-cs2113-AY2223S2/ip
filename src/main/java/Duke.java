import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String horizontalLines = "____________________________________________________________\n";
        String indentations = "    ";
        System.out.println(indentations + horizontalLines +
                indentations + "Hello! I'm Duke\n" +
                indentations + "What can I do for you?\n" +
                indentations + horizontalLines);
        Scanner in = new Scanner(System.in);
        String readLine;
        Task[] tasks = new Task[100];
        int taskIndex = 0;
        while(true) {
            readLine = in.nextLine();
            if(readLine.equals("bye")) {
                System.out.println(indentations + horizontalLines +
                        indentations + "Bye. Hope to see you again soon!\n" +
                        indentations + horizontalLines);
                break;
            } else if (readLine.equals("list")) {
                // list out the tasks and status in todo
                System.out.println(indentations + horizontalLines +
                        indentations + "Here are the tasks in your list: ");

                for (int i = 0; i < taskIndex; ++i) {
                    int taskNumber = i+1;
                    System.out.println(indentations + taskNumber + "." +
                            "[" + tasks[i].getStatusIcon() + "] " + tasks[i].getTaskDescription());
                }

                System.out.println(indentations + horizontalLines);

            } else if (readLine.startsWith("mark")) {
                String toMark = readLine.substring(5);
                toMark = toMark.trim();
                int indexToMark = Integer.parseInt(toMark) - 1;
                tasks[indexToMark].setDone(true);
                System.out.println(indentations + "Nice! I've marked this task as done:\n" +
                        indentations + "  [X] " + tasks[indexToMark].getTaskDescription() + '\n' +
                        indentations + horizontalLines);

            } else if (readLine.startsWith("unmark")) {
                String toMark = readLine.substring(7);
                toMark = toMark.trim();
                int indexToMark = Integer.parseInt(toMark) - 1;
                tasks[indexToMark].setDone(false);
                System.out.println(indentations + "OK, I've marked this task as not done yet: \n" +
                        indentations + "  [ ] " + tasks[indexToMark].getTaskDescription() + '\n' +
                        indentations + horizontalLines);
                
            }else {
                // add new tasks into todo
                tasks[taskIndex] = new Task(readLine);
                System.out.println(indentations + horizontalLines +
                        indentations + "added: " + readLine + '\n' +
                        indentations + horizontalLines);
                taskIndex++;
            }
        }

    }
}
