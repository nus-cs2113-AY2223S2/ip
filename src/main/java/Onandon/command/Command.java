package Onandon.command;

import Onandon.storage.Storage;
import Onandon.module.*;
import Onandon.ui.Ui;

/**
 * Representation of the configuration for the parsed command.
 */
public class Command {
    protected String commandType;
    protected String description;
    protected String by;
    protected String from;
    protected String to;
    protected String find;
    protected int index;

    /**
     * Create new Command class with the specified command type.
     *
     * @param commandType Command type of the given command, e.g, todo, event, deadline, and etc.
     * @param description Description of the command.
     * @param by 'by' for the deadline command.
     * @param from 'from' for the event command.
     * @param to 'to' for the event command.
     * @param index Index of the task you want to process in the mark, unmark, delete command.
     * @param find Search key you want to find in the find command.
     */
    public Command(
            String commandType,
            String description,
            String by,
            String from,
            String to,
            int index,
            String find
    ) {
        this.commandType = commandType;
        this.description = description;
        this.by = by;
        this.from = from;
        this.to = to;
        this.index = index;
        this.find = find;
    }

    /**
     * Executes the command by the specified command type.
     * Returns boolean value for .
     *
     * @param tasks ArrayList of the tasks
     */
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
            tasks.subtractNum();
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
        case "find":
            Ui.printFind();

            int cnt = 0;
            for(int i=0; i<tasks.getNum(); i++){
                if(tasks.get(i).getDescription().contains(find)) {
                    System.out.println("\t " + (cnt + 1) + ". " + tasks.get(i).toString());
                    cnt += 1;
                }
            }
            Ui.printUnderline();
            break;
        case "exit":
            exit = true;
            Storage.storeCheckpoint(tasks);
            break;
        }
        return exit;
    }
}
