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
        while(true) {
            readLine = in.nextLine();
            if(readLine.equals("bye")) {
                System.out.println(indentations + horizontalLines +
                        indentations + "Bye. Hope to see you again soon!\n" +
                        indentations + horizontalLines);
                break;
            } else {
                System.out.println(indentations + horizontalLines +
                        indentations + readLine + '\n' +
                        indentations + horizontalLines);
            }
        }

    }
}
