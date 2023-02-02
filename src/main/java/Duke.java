import java.util.Scanner;

public class Duke {

    public static void printError(String errMsg){
        String printContent = "    ____________________________________________________________\n"
        + "     " + "ERROR: " + errMsg + "!\n"
        + "    ____________________________________________________________\n";
        System.out.println(printContent);
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
        TodoList todoList = new TodoList();
        while(true){
            String line = in.nextLine();
            System.out.println(line);
            if(line.equals("bye")){
                // quit
                System.out.println(exitPrompt);
                break;
            }else{
                // parse input
                int funcIdx = line.indexOf(" ");
                if(funcIdx == -1){
                    funcIdx = line.length();
                }
                String func = line.substring(0, funcIdx);
                String instruction;
                if(funcIdx == line.length()){
                    instruction = "";
                }else{
                    instruction = line.substring(funcIdx+1);
                }

                // do instructions
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
                }else if(func.equals("todo")){
                    Todo todo = Todo.toTodo(instruction);
                    if(todo == null){
                        printError("Wrong todo format");
                        continue;
                    }
                    todoList.addItem(todo);
                }else if(func.equals("deadline")){
                    Deadline deadline = Deadline.toDeadline(instruction);
                    if(deadline == null){
                        printError("Wrong deadline format");
                        continue;
                    }
                    todoList.addItem(deadline);
                }else if(func.equals("event")){
                    Event event = Event.toEvent(instruction);
                    if(event == null){
                        printError("Wrong event format");
                        continue;
                    }
                    todoList.addItem(Event.toEvent(instruction));
                }else{
                    // add item to to-do list
                    Task todo = new Todo(line);
                    todoList.addItem(todo);
                }
            }
        }
        in.close();
    }
}
