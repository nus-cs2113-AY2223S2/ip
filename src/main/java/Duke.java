import java.util.Scanner;

public class Duke {

    private static TodoList todoList = new TodoList();

    public static void printError(String errMsg){
        String printContent = "    ____________________________________________________________\n"
        + "     " + errMsg + "\n"
        + "    ____________________________________________________________\n";
        System.out.println(printContent);
    }

    public static void doCommand(String line, String command, String cmdContent, int cmdIdx) throws DukeException{
        if(line.equals("list")){
            // show to-do list
            todoList.showList();
        }else if(command.equals("mark")){
            // mark work as done
            int index;
            try {                        
                index = Integer.parseInt(line.substring(cmdIdx+1, line.length())) - 1;
            } catch (NumberFormatException e) {
                throw new DukeException("Wrong number format: " + line.substring(cmdIdx+1, line.length()));
            }
            todoList.markItem(index, true);
        }else if(command.equals("unmark")){
            // mark work as unfinished
            int index;
            try {                        
                index = Integer.parseInt(line.substring(cmdIdx+1, line.length())) - 1;
            } catch (NumberFormatException e) {
                throw new DukeException("Wrong number format: " + line.substring(cmdIdx+1, line.length()));
            }
            todoList.markItem(index, false);
        }else if(command.equals("todo")){
            Todo todo = Todo.toTodo(cmdContent);
            todoList.addItem(todo);
        }else if(command.equals("deadline")){
            Deadline deadline = Deadline.toDeadline(cmdContent);
            todoList.addItem(deadline);
        }else if(command.equals("event")){
            Event event = Event.toEvent(cmdContent);
            todoList.addItem(event);
        }else{
            // invalid command
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
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
                try{
                    doCommand(line, command, cmdContent, cmdIdx);
                }catch(DukeException e){
                    printError(e.getMessage());
                }
            }
        }
        in.close();
    }
}
