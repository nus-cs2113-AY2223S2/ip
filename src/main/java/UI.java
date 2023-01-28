import java.util.Scanner;
public class UI {
    public void listCurrentTasks(Task[] tasks, int count){
        Formatter formatter = new Formatter();
        Tool tool = new Tool();
        formatter.drawSeparationLine();
        System.out.println("    Here are the tasks in your list: ");
        for (int i=1; i<=count; i+=1){
            formatter.printIndentation(6);
            System.out.print(i+".");
            System.out.print(tasks[i-1]);
            System.out.print('\n');
        }
        formatter.drawSeparationLine();
    }

    public String readInput(){
        Scanner in = new Scanner(System.in);
        String inputCommand = in.nextLine();
        return inputCommand;
    }

    public void echoNewTask(int numTasks, Task task){
        Formatter formatter = new Formatter();;
        formatter.drawSeparationLine();
        System.out.println("      Got it. I've added this task:");
        formatter.printIndentation(8);
        System.out.println(task);
        System.out.println("      Now you have "+numTasks+" tasks in the list.");
        formatter.drawSeparationLine();
    }

    public void updateTaskStatus(Task task, String caption){
        Formatter formatter = new Formatter();;
        formatter.drawSeparationLine();
        System.out.println(caption);
        formatter.printIndentation(6);
        System.out.println(task);
        formatter.drawSeparationLine();

    }

}
