import java.util.Scanner;
import java.util.ArrayList;
class DukeManager {
    private static final int TODO = 5;
    private static final int DEADLINE = 9;
    private static final int BY = 3;
    private static final int EVENT = 6;
    private static final int FROM = 5;
    private static final int TO = 3;
    private final ArrayList<Task>taskList;

    DukeManager(ArrayList<Task>tl) {
        this.taskList = tl;
    }

    public void run() {
        Scanner io = new Scanner(System.in);
        String inputt = io.nextLine();
        while (!inputt.equals("bye")) {
            if (inputt.equals("list")) {
                printTasklist();
            } else if (inputt.contains("mark")) {
                //if mark / unmark is detected
                markTask(inputt, this.taskList);
            } else {
                //else add task to array; create task based on input by user
                boolean isValidInput = false;
                if (inputt.contains("todo")) {
                    isValidInput = true;
                    createToDo(inputt, this.taskList);
                } else if (inputt.contains("deadline")) {
                    isValidInput = true;
                    createDeadline(inputt, this.taskList);
                } else if (inputt.contains("event")){
                    isValidInput = true;
                    createEvent(inputt, this.taskList);
                }

                if (isValidInput) {
                    //printing to terminal to notify user
                    System.out.println("Got it. I've added this task:");
                    System.out.println(this.taskList.get(this.taskList.size() - 1).toString());
                    System.out.println("Now you have " + this.taskList.size() + " tasks in the list");
                }
            }
            inputt = io.nextLine();
        }
    }

    public void printTasklist() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.taskList.size(); i++) {
            int index = i + 1;
            System.out.println(index + "." + this.taskList.get(i).toString());
        }
    }

    public void markTask(String inputt, ArrayList<Task> taskList) {
        int index = Integer.parseInt(inputt.split(" ")[1]) - 1;
        Task updatedTask = taskList.get(index).mark();
        if (inputt.contains("unmark")) {
            updatedTask = updatedTask.unmark();
            System.out.println("OK, I've marked this task as not done yet:");
        } else {
            System.out.println("Nice! I've marked this task as done:");
        }
        taskList.set(index, updatedTask);
        System.out.println(updatedTask.toString());
    }

    public void createToDo(String inputt, ArrayList<Task> taskList) {
        String description = inputt.substring(TODO, inputt.length());
        ToDo toDo = new ToDo(false, description);
        taskList.add(toDo);
    }

    public void createDeadline(String inputt, ArrayList<Task> taskList) {
        String[] inputtArray = inputt.split("/");
        String description = inputtArray[0].substring(DukeManager.DEADLINE, inputtArray[0].length());
        String deadline = inputtArray[1].substring(DukeManager.BY, inputtArray[1].length());

        Deadline deadlineTask = new Deadline(false, description, deadline);
        taskList.add(deadlineTask);
    }

    public void createEvent(String inputt, ArrayList<Task> taskList) {
        String[] inputtArray = inputt.split("/");
        String description = inputtArray[0].substring(DukeManager.EVENT, inputtArray[0].length());
        String from = inputtArray[1].substring(DukeManager.FROM, inputtArray[1].length());
        String to = inputtArray[2].substring(DukeManager.TO, inputtArray[2].length());

        Event event = new Event(false, description, from, to);
        taskList.add(event);
    }

}