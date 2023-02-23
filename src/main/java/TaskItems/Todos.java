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

    public void setMark() {
        this.isMarked = true;
    }

    public void unMark() {
        this.isMarked = false;
    }

    public boolean is_marked() {
        return isMarked;
    }
}
