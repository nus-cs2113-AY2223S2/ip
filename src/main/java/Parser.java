import java.util.ArrayList;

public class Parser {
    private boolean exit = false;

    /**
     * Parse the user command and store to the taskList if applicable
     * @param command the user input
     */
    public void parse(String command) {
        if (command.equalsIgnoreCase("list")) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < TaskList.list.size(); i++) {
                System.out.println(i + 1 + "." + TaskList.list.get(i).toString());
            }
            UI.showLine();
        } else if (command.equalsIgnoreCase("bye")) {
            UI.showBye();
            Storage.save(TaskList.list);
            this.exit = true;
        } else if (command.toLowerCase().contains("mark")) {
            try {
                String[] split = command.split("\\s+");
                int toMark = Integer.parseInt(split[1]);
                if (split[0].equalsIgnoreCase("mark")) {
                    TaskList.list.get(toMark - 1).markAsDone();
                    System.out.println("Nice! I've marked this task as done: ");
                } else {
                    TaskList.list.get(toMark - 1).markAsUnDone();
                    System.out.println("OK, I've marked this task as not done yet: ");
                }
                System.out.println(TaskList.list.get(toMark - 1).toString() + '\n' + UI.lineBreak);
            } catch (NullPointerException e) {
                System.out.println("Item is not in list!");
            }
        } else if (command.toLowerCase().contains("delete")) {
            String[] split = command.split("\\s+");
            int toDelete = Integer.parseInt(split[1]);
            TaskList.taskListDelete(toDelete - 1);
        } else {
            Task t;
            if (command.toLowerCase().contains("deadline")) {
                try {
                    String description = command.substring(command.indexOf(' ') + 1, command.indexOf('/'));
                    String ddl = command.substring(command.indexOf('/') + 1);
                    String by = ddl.replace("by", "");
                    t = new Deadline(description, by);
                    TaskList.taskListAdd(t);
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! The description of a deadline cannot be empty." + '\n' + UI.lineBreak);
                }
            } else if (command.toLowerCase().contains("event")) {
                try {
                    String substring = command.substring(command.indexOf(' ') + 1);
                    String[] info = substring.split("/");
                    String from = info[1].replace("from", "");
                    String to = info[2].replace("to", "");
                    t = new Event(info[0], from, to);
                    TaskList.taskListAdd(t);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! The description of a event cannot be empty." + '\n' + UI.lineBreak);
                }
            } else if (command.toLowerCase().contains("todo")) {
                if (command.indexOf(' ') == -1) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty." + '\n' + UI.lineBreak);
                } else {
                    String description = command.substring(command.indexOf(' ') + 1);
                    t = new Todo(description);
                    TaskList.taskListAdd(t);
                }
            } else if (command.toLowerCase().contains("find")) {
                String substring = command.substring(command.indexOf(' ') + 1);
                ArrayList<Task> result=new ArrayList<>();
                for(Task k:TaskList.list){
                    if(k.description.contains(substring)){
                        result.add(k);
                    }
                }
                System.out.println("Here are the matching tasks in your list:");
                for(int i=0;i<result.size();i++){
                    System.out.println(i + 1 + "." + TaskList.list.get(i).toString());
                }
                UI.showLine();
            } else {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(" + '\n' + UI.lineBreak);
            }
        }
    }

    /**
     * @return check if the user want to stop the program
     */
    public boolean isExit() {
        return exit;
    }
}
