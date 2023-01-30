import java.util.Scanner;

public class Duke {
    private static String LINE = "---------------------------------------------------------";
    private static Task taskList[] = new Task[100];
    private static int taskNum = 0;

    private static void printGreeting() {        
        System.out.println(LINE);
        System.out.println("Hello! I'm Duke"+System.lineSeparator()+"What can I do for you?");
        System.out.println(LINE);
    }

    private static void showTasks() {
        System.out.println(LINE);
        for(int i =0; i <taskNum; i++) {
            System.out.println("   > "+Integer.toString(i+1)+".["+taskList[i].getTaskStatus()+"] "+taskList[i].getTaskDiscription());
        }
        System.out.println(LINE);
    }

    public static void main(String[] args) {
        printGreeting();
        Scanner in = new Scanner(System.in);
        boolean isEnd = false;

        while(!isEnd) {
            String command = in.nextLine();
            String splittedCommand[] = command.split(" ",2); 

            switch (splittedCommand[0]){
            case "list":
                showTasks();
                break;
            case "mark":
                int idx = Integer.parseInt(splittedCommand[1])-1;
                taskList[idx].markAsDone();
                System.out.println(LINE);
                System.out.println("   > Nice! I've marked this task as done:");
                System.out.println("   > ["+taskList[idx].getTaskStatus()+"] "+taskList[idx].getTaskDiscription());
                System.out.println(LINE);
                break;
            case "unmark":
                idx = Integer.parseInt(splittedCommand[1])-1;
                taskList[idx].unmark();
                System.out.println(LINE);
                System.out.println("   > OK, I've marked this task as not done yet:");
                System.out.println("   > ["+taskList[idx].getTaskStatus()+"] "+taskList[idx].getTaskDiscription());
                System.out.println(LINE);
                break;
            case "bye":
                isEnd = true;
                break;
            default:
                System.out.println(LINE);
                System.out.println("   > add: "+command);
                System.out.println(LINE);

                taskList[taskNum++] = new Task(command);
                break;
            }
        }
        
        System.out.println(LINE);
        System.out.println("    > Bye. Hope to see you again soon!");
        System.out.println(LINE);

        in.close();
    }
}