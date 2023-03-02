package Onandon.command;

import Onandon.storage.Storage;
import Onandon.module.*;
import Onandon.ui.Ui;

// This class is for executing parsed command
public class Command {
    protected String commandType;
    protected String description;
    protected String by;
    protected String from;
    protected String to;
    protected int index;

    public Command(String commandType, String description, String by, String from, String to, int index) {
        this.commandType = commandType;
        this.description = description;
        this.by = by;
        this.from = from;
        this.to = to;
        this.index = index;
    }

    // Executing command by the case of the tasks
    public Boolean execute(TaskList tasks) {
        int num;
        Boolean exit = false;

        switch(this.commandType){
        case "todo":
            Ui.printFormAbove();
            num = tasks.getNum();
            tasks.add(new Todo(this.description));
            System.out.println("\t\t" + tasks.get(num).toString());
            tasks.addNum();
            Ui.printCnt(tasks.getNum());
            Storage.storeCheckpoint(tasks);
            break;
        case "deadline":
            Ui.printFormAbove();
            num = tasks.getNum();
            tasks.add(new Deadline(this.description, this.by));
            System.out.println("\t\t" + tasks.get(num).toString());
            tasks.addNum();
            Ui.printCnt(tasks.getNum());
            Storage.storeCheckpoint(tasks);
            break;
        case "event":
            Ui.printFormAbove();
            num = tasks.getNum();
            tasks.add(new Event(this.description, this.from, this.to));
            System.out.println("\t\t" + tasks.get(num).toString());
            tasks.addNum();
            Ui.printCnt(num);
            Storage.storeCheckpoint(tasks);
            break;
        case "mark":
            Ui.printMark();
            tasks.get(this.index).markAsDone();
            System.out.println("\t\t[" + tasks.get(this.index).getStatusIcon() + "] " +
                    tasks.get(this.index).getDescription());
            Ui.printUnderline();
            Storage.storeCheckpoint(tasks);
            break;
        case "unmark":
            Ui.printUnmark();
            tasks.get(this.index).markAsUnDone();
            System.out.println("\t\t[" + tasks.get(this.index).getStatusIcon() + "] " +
                    tasks.get(this.index).getDescription());
            Ui.printUnderline();
            Storage.storeCheckpoint(tasks);
            break;
        case "delete":
            Ui.printDelete();
            System.out.println("\t\t" + tasks.get(this.index).toString());
            tasks.remove(this.index);
            tasks.substractNum();
            System.out.println("\t Now you have " + tasks.getNum() + " tasks in the list.");
            Ui.printUnderline();
            Storage.storeCheckpoint(tasks);
            break;
        case "list":
            Ui.printList();
            for(int i=0; i<tasks.getNum(); i++){
                System.out.println("\t " + (i+1) + ". " + tasks.get(i).toString());
            }
            Ui.printUnderline();
            Storage.storeCheckpoint(tasks);
            break;
        case "exit":
            exit = true;
            Storage.storeCheckpoint(tasks);
            break;
        }
        return exit;
    }
}
