import java.util.Scanner;

public class Duke {

    public static void printError(String errMsg){
        String printContent = "    ____________________________________________________________\n"
        + "     " + "ERROR: " + errMsg + "!\n"
        + "    ____________________________________________________________\n";
        System.out.println(printContent);
    }

    public static void doCommand(String line, String command, String cmdContent, int cmdIdx){
        TodoList todoList = new TodoList();
        if(line.equals("list")){
            // show to-do list
            todoList.showList();
        }else if(command.equals("mark")){
            // mark work as done
            int index;
            try {                        
                index = Integer.parseInt(line.substring(cmdIdx+1, line.length())) - 1;
            } catch (Exception e) {
                index = -1;
            }
            todoList.markItem(index, true);
        }else if(command.equals("unmark")){
            // mark work as unfinished
            int index;
            try {                        
                index = Integer.parseInt(line.substring(cmdIdx+1, line.length())) - 1;
            } catch (Exception e) {
                index = -1;
            }
            todoList.markItem(index, false);
        }else if(command.equals("todo")){
            Todo todo = Todo.toTodo(cmdContent);
            if(todo == null){
                printError("Wrong todo format");
                return;
            }
            todoList.addItem(todo);
        }else if(command.equals("deadline")){
            Deadline deadline = Deadline.toDeadline(cmdContent);
            if(deadline == null){
                printError("Wrong deadline format");
                return;
            }
            todoList.addItem(deadline);
        }else if(command.equals("event")){
            Event event = Event.toEvent(cmdContent);
            if(event == null){
                printError("Wrong event format");
                return;
            }
            todoList.addItem(Event.toEvent(cmdContent));
        }else{
            // add item to to-do list
            Task todo = new Todo(line);
            todoList.addItem(todo);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
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
        while(true){
            String line = in.nextLine();
            if(line.equals("bye")){
                // quit
                System.out.println(exitPrompt);
                break;
            }else{
                // parse input
                int cmdIdx = line.indexOf(" ");
                if(cmdIdx == -1){
                    cmdIdx = line.length();
                }
                String command = line.substring(0, cmdIdx);
                String cmdContent;
                if(cmdIdx == line.length()){
                    cmdContent = "";
                }else{
                    cmdContent = line.substring(cmdIdx+1);
                }

                // do commands
                doCommand(line, command, cmdContent, cmdIdx);
            }
        }
        in.close();
    }
}
