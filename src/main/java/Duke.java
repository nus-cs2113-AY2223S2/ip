import java.util.Scanner;
public class Duke {
    public static void printList(String[] tasks, int taskCount){
        for(int i = 0; i < taskCount; i++){
            System.out.println(tasks[i]);
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
        String[] tasks;
        tasks = new String[100];
        int taskCount = 0;
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while(!line.equals("bye")){
            if(line.equals("list")){
                printList(tasks, taskCount);
            }
            else{
                tasks[taskCount] = line;
                System.out.println("added: " + line);
            }
            line = in.nextLine();
            taskCount++;
        }
        System.out.println("Bye. Hope to see you again soon!");

    }

}
