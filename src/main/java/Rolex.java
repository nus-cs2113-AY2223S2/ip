import java.util.Scanner;
public class Rolex {
    public static void printLines(){
        System.out.println("--------------------------------------------------");
    }

    public static void main(String[] args) {

        System.out.println("Hello! I'm ROLEX");
        System.out.println("What can I do for you?\n\n");

        Scanner ReadInput = new Scanner(System.in);

        Tasks[] task = new Tasks[100];
        int taskCount = 0;

        while(true){
            String UserInput = ReadInput.nextLine();

            if(UserInput.equalsIgnoreCase("bye")){
                printLines();
                System.out.println("Bye. Hope to see you again soon!\n");
                printLines();
                System.exit(0);
            }

            else if(UserInput.equalsIgnoreCase("list")){
                Tasks.ListTasks(task);
            }

            else if(UserInput.startsWith("mark")){
                int index = Integer.parseInt(UserInput.substring(5));
                task[index-1].MarkTask();
            }

            else if(UserInput.startsWith("unmark")){
                int index = Integer.parseInt(UserInput.substring(7));
                task[index-1].unMarkTask();
            }

            else{
                task[taskCount] = new Tasks(UserInput);
                taskCount++;
            }
        }
    }
}