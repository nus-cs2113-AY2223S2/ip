import java.util.Scanner;

public class level4 {
    public static void main(String[] args){
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        String line;
        Task[] tasks = new Task[100];
        boolean[] done = new boolean[100];
        String[] type = new String[100];
        int i = 0;
        while (true) {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            if (line.equals("bye")) {
                break;
            }
            if (line.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int j = 0; j < i; j++) {
                    System.out.print(j+1);
                    if (done[j]){
                        System.out.print("." + type[j] + "[X]");
                    }
                    if (!done[j]){
                        System.out.print("." + type[j] + "[ ]");
                    }
                    System.out.println(tasks[j]);
                }
            }
            else if (line.startsWith("mark")){
                char numbchar = line.charAt(line.length() - 1);
                int number = Character.getNumericValue(numbchar) - 1;
                done[number] = true;
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + type[number] + "[X]" + tasks[number]);
            }
            else if (line.startsWith("unmark")){
                char numbchar = line.charAt(line.length() - 1);
                int number = Character.getNumericValue(numbchar) - 1;
                done[number] = false;
                System.out.println("Ok, I've marked this task as not done yet:");
                System.out.println("  " + type[number] + "[ ]" + tasks[number]);
            }
            else{
                if (line.startsWith("todo")){
                    tasks[i] = new Task(line.substring(4));
                    type[i] = "[T]";
                }
                if (line.startsWith("deadline")){
                    int slash = line.indexOf("/");
                    String description = line.substring(8, slash-1);
                    String by = line.substring(slash+4);
                    tasks[i] = new Deadline(description, by);
                    type[i] = "[D]";
                }
                if (line.startsWith("event")) {
                    int slash1 = line.indexOf("/");
                    int slash2 = line.indexOf("/", slash1+1);
                    String description = line.substring(5, slash1-1);
                    String from = line.substring(slash1+6, slash2-1);
                    String to = line.substring(slash2+4);
                    tasks[i] = new Event(description, from, to);
                    type[i] = "[E]";
                }
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + type[i] + "[ ]" + tasks[i]);
                System.out.println("Now you have " + (i + 1) + " tasks in the list.");
                done[i] = false;
                i++;
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("change");
    }
}
