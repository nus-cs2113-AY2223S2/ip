package task;

/**
 * inspiration for creating a new task class was derived from a senior's advice when consulted for help
 * senior consulted was Jonathan, github.com/wutdequack/ip
 * task class further improved with notes from course website https://nus-cs2113-ay2223s2.github.io/website/schedule/
 * week3/project.html
 */

public class Task {
    public String name;
    protected String description;
    public boolean isDone;

    public Task(String name) {
        setName(name);
        this.isDone = false;
    }

    public String getName() {
        return name;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void setName(String name) {
        this.name = name;
    }

    public void taskDone() {
        this.isDone = true;
    }

    public String getTaskType() {
        return " ";
    }


    public String getDate() {
        String date = null;
        if (getTaskType().equalsIgnoreCase("T")) {
            date = " ";
        }  else if (getTaskType().equalsIgnoreCase("D")) {
            date = toString(); //change later
        } else if (getTaskType().equalsIgnoreCase("E")) {
            date = toString(); //change later
        }
        return date;
    }

    public int getTaskStatus() {
        return (isDone ? 1 : 0);
    }
    public String taskStringFormat() {
        return String.format(getTaskType() + " | " + getTaskStatus() + " | " + getName()) + " | " + getDate();
    }
}
