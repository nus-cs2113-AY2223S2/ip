import java.util.Scanner;
public class Duke {
    public static void greet() {
        String line = "____________________________________________________________";
        String greeting = " Hello! I'm Duke\n"
                + " What can I do for you?";
        System.out.println(line);
        System.out.println(greeting);
        System.out.println(line + "\n");
    }
    public static void bye() {
        String line = "____________________________________________________________";
        String goodbye = " Bye. Hope to see you again soon!";
        System.out.println(line);
        System.out.println(goodbye);
        System.out.print(line + "\n");
    }
    public static void echo(String input) {
        String line = "____________________________________________________________";
        System.out.println(line);
        System.out.println(" added: " + input);
        System.out.println(line + "\n");
    }
    public static void add(Task[] texts, String input, int counter) {
        texts[counter] = new Task(input);
        echo(input);
    }
    public static void list(Task[] texts, int counter) {
        String line = "____________________________________________________________";
        System.out.println(line);
        if (counter == 0) {
            System.out.println(" The list is empty!");
        } else {
            System.out.println(" Here are the tasks in your lists:");
            for (int i = 0; i < counter; i += 1) { 
                System.out.println(" " + (i + 1) + ".[" + texts[i].getStatusIcon() + "] " + texts[i].taskName);
            }
        }
        System.out.println(line + "\n");
    }

    public static void printMark(boolean isDone, String taskName) {
        String line = "____________________________________________________________";
        System.out.println(line);
        if (isDone == true) {
            System.out.println(" Nice! I've marked this task as done:");
            System.out.println("   [X] " + taskName);
        } else {
            System.out.println(" OK, I've marked this task as not done yet:");
            System.out.println("   [ ] " + taskName);
        }
        System.out.println(line + "\n");
    }
    public static void main(String[] args) {
        greet();
        Task[] texts = new Task[100];
        int counter = 0;
        String input;
        Scanner in = new Scanner(System.in);
        input = in.nextLine();
        while (input.equals("bye") == false) {
            if (input.equals("list")) {
                list(texts, counter);
            } else {
                String[] arrayOfInput = input.split(" ");
                if (arrayOfInput[0].equals("mark")) {
                    texts[Integer.parseInt(arrayOfInput[1]) - 1].markAsDone();
                    printMark(texts[Integer.parseInt(arrayOfInput[1]) - 1].isDone,
                            texts[Integer.parseInt(arrayOfInput[1]) - 1].taskName);
                } else if (arrayOfInput[0].equals("unmark")) {
                    texts[Integer.parseInt(arrayOfInput[1]) - 1].markAsNotDone();
                    printMark(texts[Integer.parseInt(arrayOfInput[1]) - 1].isDone,
                            texts[Integer.parseInt(arrayOfInput[1]) - 1].taskName);
                } else {
                    add(texts, input, counter);
                    counter += 1;
                }
            }
            in = new Scanner(System.in);
            input = in.nextLine();
        }
        bye();
    }
}
