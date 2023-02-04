public class TaskList {

    private Task[] taskList;

    private int listIndex = 0;

    public TaskList() {

        this.taskList = new Task[100];

    }

    public void printList() {

        for (int i = 0; i < listIndex; i++) {
            System.out.println((i + 1) + ". " + taskList[i].toString());
        }

    }

    public void addTask(Task t) {

        taskList[listIndex] = t;
        listIndex++;

    }

    public int getListIndex() {

        return listIndex;

    }

    public Task getTask(int index) {

        return taskList[index];

    }

    public Task getLastTask() {

        return taskList[listIndex - 1];

    }
}
