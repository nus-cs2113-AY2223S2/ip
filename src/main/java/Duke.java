import java.util.Scanner;
public class Duke {

    public static void printBorder() {
        String border = "____________________________________________________________ \n";
        System.out.println(border);
    }

    public static void printGreeting(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String border = "____________________________________________________________ \n";
        String greeting = "Hello! I'm Duke\n" + "What can I do for you?\n";
        System.out.println(logo + border+ greeting);
    }

    //prints exit statement
    public static void printExit(){
        String exit = "Bye. Hope to see you again soon! \n";
        System.out.println(exit);
    }


    //add a new to do
    public static void addTodo(Task[] tasks, int listCount, String name){
        tasks[listCount] = new Todo(name);
        printBorder();
        System.out.println("added: " + name + "\n");
        printBorder();
    }

    //add a new deadline task
    public static void addDeadline(Task[] tasks, int listCount, String name, String deadline){
        tasks[listCount] = new Deadline(name, deadline);
        printBorder();
        System.out.println("added: " + name + "\n");
        printBorder();
    }

    //add a new event
    public static void addEvent(Task[] tasks, int listCount, String name, String start, String end){
        tasks[listCount] = new Event(name, start, end);
        printBorder();
        System.out.println("added: " + name + "\n");
        printBorder();
    }

    //print list of tasks
    public static void printList(Task[] tasks){
        printBorder();

        int counter = 1;
        for (Task t : tasks) {
            if (t == null) {
                break;
            } else {
                System.out.println(counter + ". " + t);
                counter++;
            }

        }
        printBorder();
    }

    //mark task as done
    public static void markTask(Task[] tasks, int taskIndex){
        tasks[taskIndex].setStatus("mark");
        System.out.println("Nice! I've marked this task as done: \n");
        System.out.println(tasks[taskIndex] + "\n");
        printBorder();
    }

    //mark task as undone
    public static void unmarkTask(Task[] tasks, int taskIndex){
        tasks[taskIndex].setStatus("unmark");
        System.out.println("OK, I've marked this task as not done yet: \n");
        System.out.println(tasks[taskIndex] + "\n");
        printBorder();
    }

    public static void main(String[] args) {

        Task[] tasks = new Task[100];
        Integer listCount = 0;


        printGreeting();
        printBorder();


        Scanner input = new Scanner(System.in);
        String entry = input.nextLine();



        do {

            String[] userInput = entry.split(" ", 2);

            switch (userInput[0]) {
                case "list":
                    printList(tasks);
                    entry = input.nextLine();
                    break;


                case "mark":
                    Integer index = Integer.parseInt(userInput[1]);
                    markTask(tasks, index - 1);
                    entry = input.nextLine();
                    break;


                case "unmark":
                    Integer index_um = Integer.parseInt(userInput[1]);
                    unmarkTask(tasks, index_um - 1);
                    entry = input.nextLine();
                    break;

                case "deadline":
                    String[] info = userInput[1].split("/by", 2);
                    addDeadline(tasks, listCount, info[0], info[1]);
                    listCount++;
                    entry = input.nextLine();
                    break;


                case "todo":
                    addTodo(tasks, listCount, userInput[1]);
                    listCount++;
                    entry = input.nextLine();
                    break;

                case "event":
                    String[] info_e = userInput[1].split("/", 3);

                    addEvent(tasks, listCount, info_e[0], info_e[1], info_e[2]);
                    listCount++;
                    entry = input.nextLine();
                    break;

            }

        }while(!entry.equals("bye"));


        printBorder();
        printExit();
        printBorder();
    }
}
