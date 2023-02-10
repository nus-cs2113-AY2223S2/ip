package util;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.util.ArrayList;

public class OutputUI {
    static final String UNABLE_TO_FIND_PIKACHU_FACE_LOGO = "⣿⣿⡶⢄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀   ⣀⢴⣾⣿⣿\n"
            + "⣿⣿⠀⠀⠈⠓⢤⡀⠀⠀⢀⡤⣶⠞⠟⠲⣲⢤⣀⠀⠀⠀⢀⠴⠊⠀⠀⢹⣿⠟\n"
            + "⠈⠻⢄⠀⠀⠀⠀⠙⢤⠖⢁⠞⠁⠀⢸⠀⠈⠑⣮⣑⣄⠔⠁⠀⠀⠀⢀⠜⠁⠀\n"
            + "⠀⠀⠀⠑⠠⣀⠀⠀⢸⣿⣾⣶⠶⠶⠾⠶⢶⣾⣿⣿⠻⠀⠀⢀⡠⠒⠁⠀⠀⠀\n"
            + "⠀⠀⠀⠀⠀⠀⠉⡳⣲⠯⠭⢤⠖⠒⠛⠒⠲⠯⠭⠭⠶⢗⠂⠁⠀⠀⡠⠔⠂⠀\n"
            + "⠀⠀⠀⢀⡀⠀⢰⠋⠀⠀⠀⠀⠈⠁⠒⠠⠤⠀⠤⠒⠀⠉⠆⠀⡴⠊⠀⠀⠀⠀\n"
            + "⠀⠂⡇⡼⠟⠀⡄⢠⣴⠶⠦⢖⣄⠀⠀⠀⠀⠀⢀⡀⠀⠀⢸⠊⠀⠀⠀⠀⠀⠀\n"
            + "⠀⠀⠹⠤⡄⠀⣧⡟⢡⣾⣈⡆⢱⡆⠀⠀⠀⢠⣇⣹⣦⠀⠈⠀⠀⠀⠀⠀⠀⠀\n"
            + "⠀⠀⠀⠸⠇⠸⠺⡱⡘⠠⠗⠁⣸⡇⡀⠀⠀⠀⠻⠿⠋⢠⠒⡆⠀⠀⠀⠀⠀⠀\n"
            + "⠀⠀⠀⠀⠀⢠⡀⢱⠹⣶⣶⠾⠋⠀⠀⠀⠀⠀⠀⠀⠀⠃⢀⠇⡀⢀⠤⠂⠀⠀\n"
            + "⠀⠀⠀⠀⠀⢀⠕⠊⠁⠈⠱⠀⠀⠀⠿⠀⠀⠀⠀⠀⠀⡣⠊⠠⢞⣁⡀⠀⠀⠀\n"
            + "⠀⠀⠀⠀⢠⡇⠀⠀⠀⣰⡃⠲⣀⠀⠀⠀⢀⠔⠀⠀⠈⠘⡤⡀⠀⡴⠁⠀⠀\n";
    static final String SURPRISED_PIKACHU_FACE_LOGO = "⣿⣿⣿⣿⣿⡏⠉⠛⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⣿\n"
            + "⣿⣿⣿⣿⣿⣿⠀⠀⠀⠈⠛⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⠛⠉⠁⠀⣿\n"
            + "⣿⣿⣿⣿⣿⣿⣧⡀⠀⠀⠀⠀⠙⠿⠿⠿⠻⠿⠿⠟⠿⠛⠉⠀⠀⠀⠀⠀⣸⣿\n"
            + "⣿⣿⣿⣿⣿⣿⣿⣷⣄⠀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⣿⣿\n"
            + "⣿⣿⣿⣿⣿⣿⣿⣿⣿⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⣴⣿⣿⣿⣿\n"
            + "⣿⣿⣿⣿⣿⣿⣿⣿⡟⠀⠀⢰⣹⡆⠀⠀⠀⠀⠀⠀⣭⣷⠀⠀⠀⠸⣿⣿⣿⣿ \n"
            + "⣿⣿⣿⣿⣿⣿⣿⣿⠃⠀⠀⠈⠉⠀⠀⠤⠄⠀⠀⠀⠉⠁⠀⠀⠀⠀⢿⣿⣿⣿ \n"
            + "⣿⣿⣿⣿⣿⣿⣿⣿⢾⣿⣷⠀⠀⠀⠀⡠⠤⢄⠀⠀⠀⠠⣿⣿⣷⠀⢸⣿⣿⣿ \n"
            + "⣿⣿⣿⣿⣿⣿⣿⣿⡀⠉⠀⠀⠀⠀⠀⢄⠀⢀⠀⠀⠀⠀⠉⠉⠁⠀⠀⣿⣿⣿ \n"
            + "⣿⣿⣿⣿⣿⣿⣿⣿⣧⠀⠀⠀⠀⠀⠀⠀⠈⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⣿⣿ \n"
            + "⣿⣿⣿⣿⣿⣿⣿⣿⣿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿\n";
    String ADD_TO_LIST_PIKACHU_LOGO = "░░░░░░░░▀████▀▄▄░░░░░░░░░░░░░░▄█\n"
            + "░░░░░░░░░░█▀░░░░▀▀▄▄▄▄▄░░░░▄▄▀▀█  (\\ \n"
            + "░░▄░░░░░░░░█░░░░░░░░░░▀▀▀▀▄░░▄▀   \\'\\ \n"
            + "░▄▀░▀▄░░░░░░▀▄░░░░░░░░░░░░░░▀▄▀    \\'\\     __________\n"
            + "▄▀░░░░█░░░░░█▀░░░▄█▀▄░░░░░░▄█      / '|   ()_________) \n"
            + "▀▄░░░░░▀▄░░█░░░░░▀██▀░░░░░██▄█     \\ '/    \\ ~~~~~~~~ \\ \n"
            + "░▀▄░░░░▄▀░█░░░▄██▄░░░▄░░▄░░▀▀░█      \\       \\ ~~~~~~   \\ \n"
            + "░░█░░▄▀░░█░░░░▀██▀░░░░▀▀░▀▀░░▄▀      ==).      \\__________\\ \n"
            + "░█░░░█░░█░░░░░░▄▄░░░░░░░░░░░▄▀      (__)       ()__________) \n";

    static final String SHOWING_LIST_PIKACHU_LOGO = "░░░░█░▀▄░░░░░░░░░░▄▄███▀░░\n"
            + "░░░░█░░░▀▄░▄▄▄▄▄░▄▀░░░█▀░░\n"
            + "░░░░░▀▄░░░▀░░░░░▀░░░▄▀░░░░\n"
            + "░░░░░░░▌░▄▄░░░▄▄░▐▀▀░░░░░░\n"
            + "░░░░░░▐░░█▄░░░▄█░░▌▄▄▀▀▀▀█\n"
            + "░░░░░░▌▄▄▀▀░▄░▀▀▄▄▐░░░░░░█\n"
            + "░░░▄▀▀▐▀▀░░░░░░░▀▀▌▄▄▄░░░█\n"
            + "░░░█░░░▀▄░░░░░░░▄▀░░░░█▀▀▀\n"
            + "Pikapi give you your list \n";


    private static void printLine() {
        System.out.println("  ____________________________________________________________");
    }

    public void markTaskMessage(Task task) {
        System.out.println(ADD_TO_LIST_PIKACHU_LOGO);
        System.out.println("Pikapi has marked the task as done\n");
        System.out.println("[" + task.getStatusIcon() + "] " + task.description);
    }

    public void unmarkTaskMessage(Task task) {
        System.out.println(ADD_TO_LIST_PIKACHU_LOGO);
        System.out.println("Pikapi has unmarked the task\n");
        System.out.println("[" + task.getStatusIcon() + "] " + task.description);
    }

    public void addToListMessage(Todo todo, int numTasks) {
        printLine();
        System.out.println(
                ADD_TO_LIST_PIKACHU_LOGO + "\n" + "Pikapi add this task: " + "\n" + "  [T][ ]" + todo.description);
        System.out.println("Pikapi sees that now you have " + numTasks + " tasks in the list");
    }

    public void addToListMessage(Deadline deadline, int numTasks) {
        printLine();
        System.out.println(ADD_TO_LIST_PIKACHU_LOGO + "\n" + "Pikapi add this task: " + "\n" + "  [D][ ]"
                + deadline.description + "(by :" + deadline.by + ")");
        System.out.println("Pikapi sees that now you have " + numTasks + " tasks in the list");
    }

    public void addToListMessage(Event event, int numTasks) {
        printLine();
        System.out.println(ADD_TO_LIST_PIKACHU_LOGO + "\n" + "Pikapi add this task: " + "\n" + "  [E][ ] "
                + event.description + "(from: " + event.startDate + " to: " + event.endDate + ")");
        System.out.println("Pikapi sees that now you have " + numTasks + " tasks in the list");
    }

    public void printList(ArrayList<Task> tasks, int numTasks) {

        if ( numTasks == 0){
            System.out.println(UNABLE_TO_FIND_PIKACHU_FACE_LOGO + "\n" + "Pikapi's list is completely empty, please add some tasks!");
        }
        else{
            System.out.println(SHOWING_LIST_PIKACHU_LOGO);
            for (int i = 0; i < numTasks; i++) {
                System.out.println((i + 1) + ". " + tasks.get(i).toString());
            }
        }
    }

    public void printByeByeMessage() {
        System.out.println("    *surprised Pikachu face* \n");
        System.out.println(SURPRISED_PIKACHU_FACE_LOGO + "\n");
        System.out.println("Pikapi is surprised to see you go, see you soon fwen\n");
    }
}
