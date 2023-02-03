public class Task {
    String description;
    boolean status;

    Task(String description) {

        this.status = false;
    }

    public String getTaskStatus() {
        String status = " ";

        if (this.status == true) {
            status = "X";
        }
        return status;
    }


    public void mark() {
        this.status = true;
        System.out.println("目標達成！- [" + this.getTaskStatus() + "] " + this.description);

    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "description: " + description;
    }

    public void unMark() {
        this.status = false;
        System.out.println("えええ？噓つき。- [" + this.getTaskStatus() + "] " + this.description);
    }
}
