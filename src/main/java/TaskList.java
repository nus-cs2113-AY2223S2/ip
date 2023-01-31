public class TaskList {
    private Task[] tasks;
    private int noOfTasks;

    private void printTaskType(int taskNo) {
        System.out.print('[' + tasks[taskNo].getTaskType() + ']');
    }

    private void printStatus(int taskNo) {
        if (tasks[taskNo].isDone()) {
            System.out.print("[X]");
        } else {
            System.out.print("[ ]");
        }
    }

    private void printTaskName(int taskNo) {
        System.out.print(tasks[taskNo].getName());
    }

    private void printDescription(int taskNo) {
        switch(tasks[taskNo].getTaskType()) {
            case "d":
            case "e":
        }
    }

    private boolean isValidTaskNo(int taskNo) {
        if (taskNo <= 0) {
            System.out.println("Negative task number entered, please don't try to crash the program a**h***.");
            return false;
        }
        if (taskNo > noOfTasks) {
            System.out.println("Task number does not exist, there are only " + noOfTasks + " tasks in total.");
            return false;
        }
        return true;
    }

    public TaskList(int size) {
        this.tasks = new Task[size];
        noOfTasks = 0;
    }

    private void printAddTaskMessage(int taskNo) {
        System.out.println("Got it. I have added this task:");
        printTaskType(taskNo);
        printStatus(taskNo);
        printTaskName(taskNo);
        printDescription(taskNo);
        System.out.println();
        System.out.println("Now you have " + noOfTasks + " tasks in the list");
    }

    public void addToDo(String args) {
        noOfTasks++;
        args = args.trim();
        tasks[noOfTasks] = new ToDo(args);
        printAddTaskMessage(noOfTasks);
    }

    public void addDeadline(String args) {
        noOfTasks++;
        int indexOfBy = args.indexOf("/by");
        String name = args.substring(0, indexOfBy);
        name = name.trim();
        indexOfBy+=3;
        String by = args.substring(indexOfBy);
        by = by.trim();
        tasks[noOfTasks] = new Deadline(name, by);
        printAddTaskMessage(noOfTasks);
    }

    public void addEvent(String args) {
        noOfTasks++;
        int indexOfFrom = args.indexOf("/from");
        String name = args.substring(0, indexOfFrom);
        name = name.trim();
        indexOfFrom+=5;
        String from = args.substring(indexOfFrom);
        from = from.trim();
        int indexOfTo = args.indexOf("/to");
        indexOfTo+=3;
        String to = args.substring(indexOfTo);
        to=to.trim();
        tasks[noOfTasks] = new Event(name, from, to);
        printAddTaskMessage(noOfTasks);
    }

    public void listTasks() {
        if (noOfTasks == 0) {
            System.out.println("No tasks yet. Please input a task");
        }
        for (int i = 1; i <= noOfTasks; i++) {
            System.out.print(i + ". "); //TODO: Refactor into print task number
            printTaskType(i);
            printStatus(i);
            printTaskName(i);
            printDescription(i);
        }
    }

    public void markDone(int taskNo) {
        if (!isValidTaskNo(taskNo)) {
            return;
        }
        if (tasks[taskNo].isDone()) {
            System.out.println("Already done.");
        } else {
            tasks[taskNo].setStatus(true);
            System.out.println("Nice! I have marked this task as done.");
            printStatus(taskNo);
            printTaskName(taskNo);
            System.out.println();
        }
    }

    public void unmarkDone(int taskNo) {
        if (!isValidTaskNo(taskNo)) {
            return;
        }
        if (!tasks[taskNo].isDone()) {
            System.out.println("Not done yet. Please finish it.");
        } else {
            tasks[taskNo].setStatus(false);
            System.out.println("Ok I have marked this as not done yet.");
            printStatus(taskNo);
            printTaskName(taskNo);
            System.out.println();
        }
    }
}
