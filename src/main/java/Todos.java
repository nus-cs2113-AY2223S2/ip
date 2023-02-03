public class Todos {

    public String item;
    public boolean marked;

    public String type;

    Todos(String name, boolean marked, String type) {
        this.item = name;
        this.marked = marked;
        this.type = type;
    }

    public String getType() {
        return "[T]";
    }

    public void setMark() {
        this.marked = true;
    }

    public void unMark() {
        this.marked = false;
    }

    public boolean is_marked() {
        return marked;
    }
}
