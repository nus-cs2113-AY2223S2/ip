public class TaskManager {

    public static Task[] tasks = new Task[100];
    public static int task_count = 0;

    public static void handleCommand(String command){
        if (command.matches("^list$")){
            listTasks();
        }
        else if(command.matches("^mark [0-9]*$")){
            int task_number = Integer.parseInt(command.substring(5)) - 1;
            markTask(task_number);
        }
        else if (command.matches("^unmark [0-9]*$")){
            int task_number = Integer.parseInt(command.substring(7)) - 1;
            unmarkTask(task_number);
        }
        else{
            Task task = new Task(command);
            addTask(task);
        }
    }

    public static void addTask(Task task) {
        tasks[task_count] = task;
        task_count++;
        System.out.println("added: " + task.getDescription());
    }

    public static void markTask(int task_number){
        tasks[task_number].setDone(true);
        System.out.println("Nice! I've marked this task as done:");
        System.out.printf("[X] %s\n", tasks[task_number].getDescription());
    }

    public static void unmarkTask(int task_number){
        tasks[task_number].setDone(false);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.printf("[ ] %s\n", tasks[task_number].getDescription());
    }

    public static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int idx = 0; idx < task_count; idx++) {
            String task_status;
            if (tasks[idx].isDone()){
                task_status = "X";
            }
            else{
                task_status = " ";
            }
            System.out.printf("%d.[%s] %s\n", idx + 1, task_status, tasks[idx].getDescription());
        }
    }
}
