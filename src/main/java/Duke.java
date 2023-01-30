import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greeting = "Hello! I'm Duke\n" + "What can I do for you?\n";
        String exit = "Bye. Hope to see you again soon! \n";
        String border = "____________________________________________________________ \n";

        Task[] tasks = new Task[100];
        int listCount = 0;

        System.out.println(logo + border + greeting + border);

        Scanner input = new Scanner(System.in);
        String entry = input.nextLine();


        while (!entry.equals("bye")){
            if(entry.equals("list")){
                System.out.println(border);

                int counter = 1;
                for (Task t: tasks){
                    if (t== null){
                        break;
                    }
                    else{
                        System.out.println(counter + ". " + t);
                        counter++;
                    }

                }
                System.out.println(border);
                entry = input.nextLine();
            }

//            else if (entry.contains("mark")){
//                Task task_mark = entry.substring(5);
//                task_mark.setStatus("mark");
//            }
//
//            else if (entry.contains("unmark")){
//                Task task_mark = entry.substring(5);
//                task_mark.setStatus("unmark");
//            }

            else {
                tasks[listCount] = new Task(entry);
                listCount ++;
                System.out.println(border + "added: " + entry + "\n" + border);
                entry = input.nextLine();
            }
        }

        System.out.println(border + exit + border);
    }
}
