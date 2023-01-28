public class Todos {

    public String item;
    public boolean marked;

    public Todos(String name, boolean marked) {
        this.item = name;
        this.marked = marked;
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