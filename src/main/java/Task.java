public class Task {

    public String taskDescription;
    public String status;
    public String taskChar;

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.status = "[ ]";
    }

    public void setDone() {
        status = "[X]";
        print();
    }

    public void setUndone() {
        status = "[ ]";
        print();
    }

    public void print() {
        System.out.println(taskChar + status + " " + taskDescription);
    }
}
