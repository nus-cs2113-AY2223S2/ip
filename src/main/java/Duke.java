import java.util.Scanner;
import util.Task;

public class Duke {
    public static int next_index=1;

    public static Task[] createList(){
        Task[] commands_list=new Task[100];
        return commands_list;
    }
    
    public static void addToList(String line,Task[] commands_list){
        commands_list[next_index]=new Task(line);
        next_index++;
    }
    public static void displayCommandsList(Task[] commands_list){
        int i=1;
        System.out.println("\t_____________________________________________________");
        while (i<next_index){
            System.out.println("\t"+i+".["+commands_list[i].getStatusIcon()+"] "+commands_list[i].getDescription());
            i++;
        }
        System.out.println("\t_____________________________________________________");
    }
    public static String ask() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        return line;
        }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(" Hello! I'm Duke ");
        System.out.println(" What can I do for you? \n");
        System.out.println("____________________________________________________________\n");
        Task[] commands_list=createList();
        boolean not_finished= true;
        while (not_finished==true) {
            String line=ask();
            if (line.equals("bye")){
                System.out.println("____________________________________________________________\n");
                System.out.println("\t Bye. Hope to see you again soon!\n");
                System.out.println("____________________________________________________________\n");
                not_finished=false;
            }
            else if (line.equals("list")){
                displayCommandsList(commands_list);
            }
            else if (line.length()>5 && line.substring(0, 4).equals("mark")){
                int index=Integer.parseInt(line.substring(5,6));
                commands_list[index].setDone(true);
                System.out.println("\t Nice! I've marked this task as done:");
                System.out.println("\t"+index+".["+commands_list[index].getStatusIcon()+"] "+commands_list[index].getDescription());
            }
            else if (line.length()>7 && line.substring(0, 6).equals("unmark")){
                int index=Integer.parseInt(line.substring(7,8));
                commands_list[index].setDone(false);
                System.out.println("\t OK, I've marked this task as not done yet:");
                System.out.println("\t"+index+".["+commands_list[index].getStatusIcon()+"] "+commands_list[index].getDescription());
            }
            else{
                addToList(line, commands_list);
                System.out.println("\t_____________________________________________________");
                System.out.println("\t added: "+line);
                System.out.println("\t_____________________________________________________");
            }
        }


    }
}
