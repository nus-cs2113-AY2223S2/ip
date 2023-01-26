import java.util.Scanner;

public class Duke {
    public static String[] createList(){
        String[] commands_list=new String[100];
        commands_list[99]="1";
        return commands_list;
    }
    public static int next_index(String[] commands_list){
        return Integer.parseInt(commands_list[99]);
    }
    public static void addToList(String line,String[] commands_list){
        int next_index=next_index(commands_list);
        commands_list[next_index]=line;
        next_index++;
        commands_list[99]=Integer.toString(next_index);
    }
    public static void displayCommandsList(String[] commands_list){
        int next_index=next_index(commands_list);
        int i=1;
        System.out.println("\t_____________________________________________________");
        while (i<next_index){
            System.out.println("\t"+i+". "+commands_list[i]);
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
        String[] commands_list=createList();
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
            else{
                addToList(line, commands_list);
                System.out.println("\t_____________________________________________________");
                System.out.println("\t added: "+line);
                System.out.println("\t_____________________________________________________");
            }
        }


    }
}
