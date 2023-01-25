import java.util.Scanner;

public class Duke {
    static int limitTask = 100;
    static Task[] task = new Task[limitTask];
    static int taskCount = 0;



    public static void listInput(){
        int idxCount = 1;
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i=0 ;i < taskCount;i++){
            System.out.println(idxCount + ".[" + task[i].getStatusIcon() + "] " + task[i].getDescription());
            idxCount++;
        }
        System.out.println("____________________________________________________________");
    }
    public static void addList(String input){
        if(taskCount == limitTask ){
            System.out.println("____________________________________________________________");
            System.out.println("Too much tasks");
            System.out.println("____________________________________________________________");
        }else{
            Task t = new Task(input);
            task[taskCount] = t;
            taskCount++;
            System.out.println("____________________________________________________________");
            System.out.println("added: " + input);
            System.out.println("____________________________________________________________");
        }

    }

    public static void markTask(int index){
        if(index < taskCount && index >=0){
            task[index].markAsDone();
            System.out.println("____________________________________________________________");
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("[X] " + task[index].getDescription());
            System.out.println("____________________________________________________________");
        }
    }

    public static void unmarkTask(int index){
        if(index < taskCount && index >=0){
            task[index].markAsNotDone();
            System.out.println("____________________________________________________________");
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("[ ] " + task[index].getDescription());
            System.out.println("____________________________________________________________");
        }
    }
    public static void startBot(){
        Scanner in = new Scanner((System.in));
        while(true){
            String input  = in.nextLine();
            String[] args = input.split(" ");
            int index;
            switch(args[0]){
                case "bye":
                    return;
                case "list":
                    listInput();
                    break;
                case "mark":
                    index = Integer.parseInt(args[1]) - 1;
                    markTask(index);
                    break;
                case "unmark":
                    index = Integer.parseInt(args[1]) - 1;
                    unmarkTask(index);
                    break;
                default:
                    addList(input);

            }
        }
    }
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Duke\n" +
                " What can I do for you?\n");

        startBot();
        System.out.println( "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n");

    }
}
