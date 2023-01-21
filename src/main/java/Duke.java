import java.util.Scanner;
public class Duke {

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        String input;
        Task[] tasks = new Task[100];
        int lstID = 1;
        while (true) {
            Scanner sc = new Scanner(System.in);
            input = sc.nextLine();
            String[] inputs = input.split(" ");
            if (inputs[0].equals("bye")) {
                // terminate
                System.out.println("Bye. Hope to see you again soon!");
                return;
            } else if (input.equals("list")) {
                lstID = 1;
                System.out.println("Here are the tasks in your list:");
                for (Task tsk : tasks) {
                    if (tsk==null){break;}
                    System.out.println(lstID + ".["  + tsk.getStatusIcon() + "] "+ tsk.description);
                    lstID += 1;
                }
                lstID = 1;
            } else if(inputs[0].equals("unmark")){
                if (Integer.parseInt(inputs[1]) >= lstID) {
                    System.out.println("Error 404: Item not found!");
                    continue;
                }
                tasks[Integer.parseInt(inputs[1]) - 1].markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                Task tsk = tasks[Integer.parseInt(inputs[1]) - 1];
                System.out.println("[" + tsk.getStatusIcon() + "] " + tsk.description);
            } else if(inputs[0].equals("mark")){
                if (Integer.parseInt(inputs[1]) >= lstID) {
                    System.out.println("Error 404: Item not found!");
                    continue;
                }
                tasks[Integer.parseInt(inputs[1]) - 1].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                Task tsk = tasks[Integer.parseInt(inputs[1]) - 1];
                System.out.println("[" + tsk.getStatusIcon() + "] " + tsk.description);
            } else if(inputs[0].equals("add") && inputs[1].equals("task")){
                Task tsk = new Task(inputs[2]);
                tasks[lstID-1] = tsk;
                lstID += 1;
                System.out.println("Task added!");
            }
            else {
                // echo
                System.out.println(input);
            }
        }
    }
}
