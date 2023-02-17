package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.commands.Event;
import duke.commands.Task;
import duke.commands.Todo;
import duke.commands.Deadline;
import duke.exceptions.LackOfTaskDetail;
import duke.exceptions.DukeException;
import duke.exceptions.TaskNumberOutOfRange;

public class Duke {
    private static final String LINE = "---------------------------------------------------------";
    private static final String KEQING = """
                    ....,***//*,,,,,,,**,,,,,,,,,,,,.......................
                    .,*%&&,.......,...
                    ..,%%&&&%.    ..                            .
                    .,,%%&&&&&&#,.. ../((/....       /     .####(
                      .#%%&&&&&&%%%%#############(#,..##########(
                       /#%%%&%%%%#####################%%%#%%%##(.
                       .############################%###&%%%%##*
                       (#((((##############((((######(####%####
                      ,/((((####(###((((((((((((##(((#########.
                     ./(((((#(#####(((((((((/(((#(/((///(((##//
                   .*//((((((((((#((((((********#//////(//(##//.    .
                ,,** */#((((((((#(/***/*********,%/**//#//////.     .
                     //#(((((((#/***%%********/...,****(/////.
                     //((((//(.***,/.*,,,,,/,*.*,...***(*///(.
                    ////***.##(###..,*,,, (*.........****///%#
                   ////*(*/(, ((##,*(,      (/.#####..**///(##
                   /(#***(**   **            //(/  /.,*/***&##
                  /(((**,****                      (******(#((.
                 /(((#%*#,##.                      **,***((((((
                ////###/,,%# .                      ,,,*#((((((
               /////####,*,*((((/             *    .,,,/#((((((.
            ..//////(###(&*.*.*((((/     */((((*   ,,/%(((((((((.
            ./(//////###/((,.  ,#(#../(..((*.     ,,,#(/////////(     .
            /(////////##//.   ,####% , %##((*....,...///////////(/..,,.
                """;

    private static ArrayList<Task> taskList = new ArrayList<>();
    private static int taskNum = 0;

    private static void init() {
        System.out.println(LINE);
        System.out.println(KEQING);
        System.out.println("Hello! I'm keqing" + System.lineSeparator() + "What can I do for you?");
        System.out.println(LINE);
    }

    private static void listTasks() {
        System.out.println(LINE);
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("   > " + Integer.toString(i + 1) + "." + taskList.get(i).toString());
        }
        System.out.println(LINE);
    }

    private static void markThisTask(String command) throws TaskNumberOutOfRange {
        int idx = Integer.parseInt(command) - 1;
        if (idx >= taskList.size() || idx < 0) {
            throw new TaskNumberOutOfRange("   > task number out of range!!");
        } else {
            taskList.get(idx).markAsDone();
            System.out.println(LINE);
            System.out.println("   > Nice! I've marked this task as done:");
            System.out.println("   > " + taskList.get(idx).toString());
            System.out.println(LINE);
        }
    }

    private static void unmarkThisTask(String command) throws TaskNumberOutOfRange {
        int idx = Integer.parseInt(command) - 1;
        if (idx >= taskList.size() || idx < 0) {
            throw new TaskNumberOutOfRange("task number out of range!!");
        } else {
            taskList.get(idx).unmark();
            System.out.println(LINE);
            System.out.println("   > OK, I've marked this task as not done yet:");
            System.out.println("   > " + taskList.get(idx).toString());
            System.out.println(LINE);
        }
    }

    private static void deleteThisTask(String command) throws TaskNumberOutOfRange {
        int idx = Integer.parseInt(command) - 1;
        if (idx >= taskList.size() || idx < 0) {
            throw new TaskNumberOutOfRange("   > task number out of range!!");
        }
        Task taskDiscription = taskList.get(idx);
        taskList.remove(idx);
        System.out.println(LINE + System.lineSeparator() + "Noted. I've removed this task:");
        System.out.println("   > " + taskDiscription.toString());
        System.out.println("Now you have " + Integer.toString(taskList.size()) + " tasks in the list."
                + System.lineSeparator() + LINE);
    }

    private static void showAddTask(Task taskDiscription) {
        System.out.println(LINE + System.lineSeparator() + "Got it. I've added this task:");
        System.out.println("   > " + taskDiscription.toString());
        System.out.println("Now you have " + Integer.toString(taskList.size()) + " tasks in the list."
                + System.lineSeparator() + LINE);
    }

    private static String changeTaskDescription() {
        String content = "";
        for (int i = 0; i < taskList.size(); i++) {
            Task curtask = taskList.get(i);
            content += (Integer.toString(i) + " | ");
            content += ((curtask.getTaskStatus().equals("X") ? "1" : "0") + " | " + curtask.getTaskDiscription());
            if (!curtask.getClass().getName().equals("commands.Todo")) {
                content += ("| " + taskList.get(i).getDue());
            }
            content += System.lineSeparator();
        }
        return content;
    }

    private static void autoSave() throws IOException {
        File f = new File("./data/duke.txt");
        if (!f.getParentFile().exists()) {
            f.getParentFile().mkdirs();
        }
        FileWriter fw = new FileWriter("./data/duke.txt");
        fw.write(changeTaskDescription());
        fw.close();
    }

    public static void main(String[] args) {
        init();
        Scanner in = new Scanner(System.in);
        boolean isEnd = false;

        while (!isEnd) {
            String command = in.nextLine();
            String splittedCommand[] = command.split(" ", 2);

            switch (splittedCommand[0]) {
            case "list":
                listTasks();
                break;
            case "mark":
                try {
                    markThisTask(splittedCommand[1]);
                } catch (TaskNumberOutOfRange e) {
                    System.out.println("   > Please enter a valid NUMBER!");
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("   > Please enter a valid NUMBER!");
                } catch (NumberFormatException e) {
                    System.out.println("   > Please enter a valid NUMBER!");
                }
                try {
                    autoSave();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "unmark":
                try {
                    unmarkThisTask(splittedCommand[1]);
                } catch (TaskNumberOutOfRange e) {
                    System.out.println("   > Please enter a valid NUMBER!");
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("   > Please enter a valid NUMBER!");
                } catch (NumberFormatException e) {
                    System.out.println("   > Please enter a valid NUMBER!");
                }
                try {
                    autoSave();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "bye":
                isEnd = true;
                break;
            case "todo":
                try {
                    Todo newtodo = new Todo(splittedCommand[1]);
                    taskList.add(newtodo);
                    showAddTask(newtodo);
                } catch (LackOfTaskDetail e) {
                    System.out.println("   > lack of task detail");
                }
                try {
                    autoSave();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "deadline":
                try {
                    String splittedDiscription[] = splittedCommand[1].split("/by ", 2);
                    Deadline newddl = new Deadline(splittedDiscription[0], splittedDiscription[1]);
                    taskList.add(newddl);
                    showAddTask(newddl);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("   > Please enter a task and a deadline behind the task seperated by \"/by\" ");
                } catch (LackOfTaskDetail e) {
                    System.out.println("   > lack of task detail!");
                }
                try {
                    autoSave();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "event":
                try {
                    String[] splittedDiscription = splittedCommand[1].split("/at ", 2);
                    Event newevent = new Event(splittedDiscription[0], splittedDiscription[1]);
                    taskList.add(newevent);
                    showAddTask(newevent);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("   > Please enter a task and a time behind the task seperated by \"/at\" ");
                } catch (LackOfTaskDetail e) {
                    System.out.println("   > lack of task detail!");
                }
                try {
                    autoSave();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "delete":
                try {
                    deleteThisTask(splittedCommand[1]);
                } catch (TaskNumberOutOfRange e) {
                    System.out.println("   > Please enter a valid NUMBER!");
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("   > Please enter a valid NUMBER!");
                } catch (NumberFormatException e) {
                    System.out.println("   > Please enter a valid NUMBER!");
                }
                try {
                    autoSave();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                break;
            default:
                System.out.println(LINE);
                System.out.println("   > Sorry, command not found");
                System.out.println(LINE);
                break;
            }
        }

        System.out.println(LINE);
        System.out.println("    > Bye. Hope to see you again soon!");
        System.out.println(LINE);

        in.close();
    }
}
