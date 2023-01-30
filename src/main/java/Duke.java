import java.util.Scanner;
public class Duke {

    public static void drawLine(String symbol) {
        for (int i = 0; i < 40; i++) {
            System.out.print(symbol);
        }
        System.out.println();
    }

    public static void echo(String text) {
        drawLine("_");
        System.out.println("added: " + text);
        drawLine("_");
    }

    public static void printTaskList(Task[] tasks, int taskID) {
        drawLine("_");
        for (int i = 0; i < taskID; i++) {
            System.out.print((i + 1) + ".");
            tasks[i].printTask();
        }
        drawLine("_");
    }

    public static void markTask(Task[] tasks, int currentID, boolean isDone) {
        drawLine("_");
        if (currentID < 0 || currentID >= Task.getTaskCount()) {
            System.out.println("Cannot find this task!");
        }
        else if (isDone) {
            tasks[currentID].markDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("   ");
            tasks[currentID].printTask();
        }
        else {
            tasks[currentID].unmarkDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("   ");
            tasks[currentID].printTask();
        }
        drawLine("_");
    }

    public static void loopAdd(Task[] tasks){
        Scanner in = new Scanner(System.in);
        int taskID = 0;
        String text = in.nextLine();
        while(!text.equals("bye")){
            if (text.equals("list")) {
                printTaskList(tasks, taskID);
                text = in.nextLine();
            }
            else if (text.contains("mark")) {
                int currentID = Integer.parseInt(text.substring(text.length() - 1)) - 1;
                boolean isDone;
                if (text.contains("unmark")) {
                    isDone = false;
                    markTask(tasks, currentID, isDone);
                }
                else {
                    isDone = true;
                    markTask(tasks, currentID, isDone);
                }
                text = in.nextLine();
            }
            else {
                Task task = new Task(text, taskID);
                tasks[taskID] = task;
                echo(text);
                taskID += 1;
                text = in.nextLine();
            }
        }
    }
    public static void main(String[] args) {
        Task[] tasks = new Task[100];
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        drawLine("_");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        drawLine("_");
        loopAdd(tasks);
        drawLine("_");
        System.out.println("Bye. Hope to see you again soon!");
        drawLine("_");
    }
}
