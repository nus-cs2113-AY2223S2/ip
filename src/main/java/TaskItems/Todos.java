package TaskItems;

public class Todos {

    public String item;
    public boolean isMarked;

    public String type;

    public Todos(String name, boolean isMarked, String type) {
        this.item = name;
        this.isMarked = isMarked;
        this.type = type;
    }

    public String getType() {
        return "[T]";
    }

    /**
     * marks a task task as done
     */
    public void setMark() {
        this.isMarked = true;
    }

    /**
     * unmarks a task if undone
     */
    public void unMark() {
        this.isMarked = false;
    }

    /**
     * checks if a task is marked or not
     * @return True if marked and false if unmarked
     */
    public boolean is_marked() {
        return isMarked;
    }
}
