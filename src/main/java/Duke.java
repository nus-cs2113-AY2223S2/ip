import java.util.Scanner;
public class Duke {
    public static void printList(Task[] tasks, int taskCount){
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < taskCount; i++){
            System.out.println(i + 1 + "." + "[" + tasks[i].getStatusIcon() + "] " + tasks[i].description);
        }
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke" + System.lineSeparator() + "What can I do for you?");
        Task[] tasks;
        tasks = new Task[100];
        int taskCount = 0;
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while(!line.equals("bye")){
            if(line.equals("list")){
                printList(tasks, taskCount);
            }
            else if(line.contains("mark") && !line.contains("unmark")){
                int taskNum = Integer.parseInt(line.substring(5)) - 1;
                tasks[taskNum].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[" + tasks[taskNum].getStatusIcon() + "] " + tasks[taskNum].description);
            }
            else if(line.contains("unmark")){
                int unmarkNum = Integer.parseInt(line.substring(7)) - 1;
                tasks[unmarkNum].unmarkAsDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("[" + tasks[unmarkNum].getStatusIcon() + "] " + tasks[unmarkNum].description);
            }
            else{
                tasks[taskCount] = new Task(line);
                System.out.println("added: " + line);
                taskCount++;
            }
            line = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");

    }

}
