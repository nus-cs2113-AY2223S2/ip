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
        String[] tasks = new String[100];
        int taskIndex = 0;
        while(true) {
            readLine = in.nextLine();
            if(readLine.equals("bye")) {
                System.out.println(indentations + horizontalLines +
                        indentations + "Bye. Hope to see you again soon!\n" +
                        indentations + horizontalLines);
                break;
            } else if (readLine.equals("list")) {
                System.out.println(indentations + horizontalLines);

                for (int i = 0; i < taskIndex; ++i) {
                    int taskNumber = i+1;
                    System.out.println(indentations + taskNumber + ". " + tasks[i]);
                }

                System.out.println(indentations + horizontalLines);

            } else {
                tasks[taskIndex] = readLine;
                System.out.println(indentations + horizontalLines +
                        indentations + "added: " + readLine + '\n' +
                        indentations + horizontalLines);
                taskIndex++;
            }
        }

    }
}
