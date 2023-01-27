import java.util.Scanner;

public class Duke {

    public static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String HORIZONTAL_LINE = "____________________________________________________________";

    public static void main(String[] args) {
        greeting();
        Scanner in = new Scanner(System.in);
        Task[] list = new Task[100];
        int counter = 0;
        boolean op = true;
        while(op){
            String ins = in.nextLine();
            switch (ins){
                case "list":
                    if (counter == 0) {
                        System.out.println(HORIZONTAL_LINE +"\n");
                        System.out.println("Your day is clear!");
                        System.out.println(HORIZONTAL_LINE +"\n");
                    } else {
                        printListOfTasks(list, counter);
                    }
                    break;

                case "bye":
                    op = false;
                    bye();
                    break;

                default:
                    if (ins.contains("unmark")){
                        unmarkTask(list, ins);
                        break;
                    } else if (ins.contains("mark")){
                        markTask(list, ins);
                        break;
                    } else {
                        if (ins.contains("todo")){
                            addTodoTask(list, counter, ins);
                        } else if (ins.contains("deadline")) {//Task type D
                            addDeadlineTask(list, counter, ins);
                        } else if (ins.contains("event")) {
                            addEventTask(list, counter, ins);
                        }
                        counter++;
                        break;
                    }
            }
        }
    }

    private static void markTask(Task[] list, String ins) {
        System.out.println(HORIZONTAL_LINE + "\n");
        int idx = 3;
        int num = Integer.parseInt(ins.substring(idx+2));
        System.out.println("Nice! You have done Task " + num);
        list[num - 1].isDone = true;
        System.out.println(HORIZONTAL_LINE + "\n");
    }

    private static void unmarkTask(Task[] list, String ins) {
        int idx = 5;
        System.out.println(HORIZONTAL_LINE + "\n");
        int num = Integer.parseInt(ins.substring(idx+2));
        System.out.println("Okay, I have unmarked Task " + num);
        System.out.println(HORIZONTAL_LINE + "\n");
        list[num - 1].isDone = false;
    }

    private static void bye() {
        System.out.println(HORIZONTAL_LINE +"\n");
        System.out.println("Bye! See you next time!");
        System.out.println(HORIZONTAL_LINE +"\n");
    }

    private static void greeting() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println(HORIZONTAL_LINE +"\n");
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");
        System.out.println(HORIZONTAL_LINE +"\n");
    }

    private static void printListOfTasks(Task[] list, int counter) {
        System.out.println(HORIZONTAL_LINE +"\n");
        for (int i = 0; i < counter; i++){
            System.out.println(list[i].toString());
        }
        System.out.println("\n" + "Found " + (counter) +" Task");
        System.out.println(HORIZONTAL_LINE +"\n");
    }

    private static void addEventTask( Task[] list, int counter, String ins) {
        System.out.println(HORIZONTAL_LINE + "\n");
        String[] arrOfStr = ins.split("event",2);
        arrOfStr = arrOfStr[1].split("/",3);
        String name = arrOfStr[0];
        String from = arrOfStr[1].split("from")[1];
        String to = arrOfStr[2].split("to")[1];
        list[counter] = new Event(name, counter + 1,from,to);
        System.out.println("Added: " + ins);
        System.out.println(HORIZONTAL_LINE + "\n");
    }

    private static void addDeadlineTask(Task[] list, int counter, String ins) {
        System.out.println(HORIZONTAL_LINE + "\n");
        String[] arrOfStr = ins.split("deadline");
        arrOfStr = arrOfStr[1].split("/by ",2);
        list[counter] = new Deadline(arrOfStr[0], counter + 1, arrOfStr[1]);
        System.out.println("Added: " + ins);
        System.out.println(HORIZONTAL_LINE + "\n");
    }

    private static void addTodoTask(Task[] list, int counter, String ins) {
        System.out.println(HORIZONTAL_LINE + "\n");
        String[] arrOfStr = ins.split("todo",2);
        list[counter] = new Todo(arrOfStr[1], counter + 1);
        System.out.println("Added: " + ins);
        System.out.println(HORIZONTAL_LINE + "\n");
    }
}
