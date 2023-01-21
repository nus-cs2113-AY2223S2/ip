import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line;

        String[] tasks = new String[100];
        int indexCount = 0;
        String lineBreak = "____________________________________________________________\n";
        System.out.println(lineBreak + "Hello I'm Duke!\nWhat can I do for you?\n" +
                "Input your tasks and I'll keep track of them!\n" + lineBreak);

        do {
            System.out.print("Enter Your Task Here: ");
            line = in.nextLine();
            System.out.println(lineBreak);
            if (line.equals("")){
                System.out.println("Please input a valid task!\n" + lineBreak);
            } else if (line.equals("bye")){
                break;
            } else {
                tasks[indexCount++] = line;

                for (int i = 0; i < indexCount; i++){
                    System.out.println(i+1 + ". " + tasks[i]);
                }

                System.out.println(lineBreak);
            }

        } while (true);

        System.out.println("Aww you're going? Hope to see you again soon!\n" + lineBreak);
    }
}
