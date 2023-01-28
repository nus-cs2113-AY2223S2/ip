import java.util.Scanner;
public class Rolex {

    public static void main(String[] args) {

        System.out.println("Hello! I'm ROLEX");
        System.out.println("What can I do for you?\n\n");

        Scanner ReadInput = new Scanner(System.in);

        Tasks[] task = new Tasks[100];
        int taskCount = 0;

        while(true){
            String UserInput = ReadInput.nextLine();

            if(UserInput.equalsIgnoreCase("bye")){
                System.out.println("--------------------------------------------------");
                System.out.println("Bye. Hope to see you again soon!\n");
                System.out.println("--------------------------------------------------");
                System.exit(0);
            }

            else if(UserInput.equalsIgnoreCase("list")){
                Tasks.ListTasks(task);
            }

            else if(UserInput.startsWith("mark")){
                int index = Integer.parseInt(UserInput.substring(5));
                task[index-1].MarkTask();
                System.out.println("--------------------------------------------------");
                System.out.println("Well Done. This task is marked as done:");
                System.out.println("[" + task[index-1].TaskStatus() + "] " + task[index-1].TaskName);
                System.out.println("--------------------------------------------------");
            }

            else if(UserInput.startsWith("unmark")){
                int index = Integer.parseInt(UserInput.substring(7));
                task[index-1].unMarkTask();
                System.out.println("--------------------------------------------------");
                System.out.println("Oh no, I've unmarked this task as it is not done:");
                System.out.println("[" + task[index-1].TaskStatus() + "] " + task[index-1].TaskName);
                System.out.println("--------------------------------------------------");
            }

            else{
                task[taskCount] = new Tasks(UserInput);
                taskCount++;
                System.out.println("--------------------------------------------------");
                System.out.println(" I've added this task: " + UserInput);
                System.out.println("--------------------------------------------------");
            }
        }
    }
}






