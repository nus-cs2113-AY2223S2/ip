import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String enterGreet = "    ____________________________________________________________\n"
                           + "     Hello! I'm Duke\n"
                           + "     What can I do for you?\n"
                           + "    ____________________________________________________________\n";
        String exitPrompt = "    ____________________________________________________________\n"
                           + "     Bye. Hope to see you again soon!\n"
                           + "    ____________________________________________________________\n";
        System.out.println(enterGreet);
        TodoList todoList = new TodoList();
        while(true){
            Scanner in = new Scanner(System.in);
            String line = in.nextLine();            // get user input
            if(line.equals("bye")){             // quit
                System.out.println(exitPrompt);
                break;
            }else{
                int funcIdx = line.indexOf(" ");
                if(funcIdx == -1){
                    funcIdx = line.length();
                }
                String func = line.substring(0, funcIdx);
                if(line.equals("list")){
                    // show to-do list
                    todoList.showList();
                }else if(func.equals("mark")){
                    // mark work as done
                    int index;
                    try {                        
                        index = Integer.parseInt(line.substring(funcIdx+1, line.length())) - 1;
                    } catch (Exception e) {
                        index = -1;
                    }
                    todoList.markItem(index, true);
                }else if(func.equals("unmark")){
                    // mark work as unfinished
                    int index;
                    try {                        
                        index = Integer.parseInt(line.substring(funcIdx+1, line.length())) - 1;
                    } catch (Exception e) {
                        index = -1;
                    }
                    todoList.markItem(index, false);
                }else{
                    // add item to to-do list
                    todoList.addItem(line);
                // }else{
                //     String repeatInput = "    ____________________________________________________________\n"
                //                        + "    " + line + "\n"
                //                        + "    ____________________________________________________________\n";
                //     System.out.println(repeatInput);
                }
            }
        }
    }
}
