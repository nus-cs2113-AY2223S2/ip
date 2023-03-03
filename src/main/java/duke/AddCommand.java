package duke;

import java.io.IOException;
public class AddCommand extends Command {

    private TaskList arrayLL;
    private Ui ui;
    private Storage store;
    private int type;

    public AddCommand(Ui ui, Storage store, TaskList arrayLL, int type){
        this.ui = ui;
        this.store = store;
        this.arrayLL = arrayLL;
        this.type = type;
    }

    void complete(String line) throws IOException{
        if (type == 0){
            if (!line.equals("deadline") && line.contains("/")){
                int index = line.indexOf(" ");
                int pos = line.indexOf("/");
                String activity = line.substring(index, pos - 1);
                String dueDate = line.substring(pos + 3, line.length());
                Deadline deadline = new Deadline(activity, dueDate.trim());
                arrayLL.addTask(deadline);
                //tasks.add(deadline);
                System.out.println("Added: " + "\n" + deadline + "\n");
                //System.out.println("You have " + arrayLL.size() + " number of tasks in you list.");
                System.out.println("____________________________________________________________\n");
                //appendToFile(f.getAbsolutePath(), deadline + "\n");
                store.Storedata(deadline);
            }else{
                System.out.println(":(( Drink coffee and enter a valid deadline command");
                System.out.println("____________________________________________________________\n");
            }
        }
        if (type == 2){
            if (!line.equals("todo")){
                int index = line.indexOf(" ");
                String activity = line.substring(index, line.length());
                Todo toDo = new Todo(activity);
                arrayLL.addTask(toDo);
                //tasks.add(toDo);
                System.out.println("Added: " + "\n" + toDo + "\n");
                //System.out.println("You have " + arrayLL.size() + " number of tasks in you list.");
                System.out.println("____________________________________________________________\n");
                //appendToFile(f.getAbsolutePath(), toDo + "\n");
                store.Storedata(toDo);
            } else {
                System.out.println(":(( Drink coffee and enter a valid todo command");
                System.out.println("____________________________________________________________\n");
            }
        }
        if (type == 1){
            if (!line.equals("event") && line.contains("/")){
                int index = line.indexOf(" ");
                int slash = line.indexOf("/");
                String activity = line.substring(index, slash);
                String timeFrame = line.substring(slash + 3, line.length());
                String startDate = timeFrame.substring(0, 11);
                String endDate = timeFrame.substring(11, timeFrame.length());
                Event event = new Event(activity, startDate.trim(), endDate.trim());
                arrayLL.addTask(event);
                //tasks.add(event);
                System.out.println("Added: " + "\n" + event + "\n");
                //System.out.println("You have " + arrayLL.size() + " number of tasks in you list.");
                System.out.println("____________________________________________________________\n");
                //appendToFile(f.getAbsolutePath(), event + "\n");
                store.Storedata(event);
            }else{
                System.out.println(":(( Drink coffee and enter a valid event command");
                System.out.println("____________________________________________________________\n");
            }
        }

    }

}
