public class Deadline extends Task {
    private String by;


    public void setBy(String by) {
        this.by = by;
    }

    Deadline(String description, String by) {
        super(description);
        setBy(by);
        System.out.println("Added!");
        System.out.println(String.format(" [%s] [%s] %s (%s)", 'D', " ", description, by));


    }

    public String getBy() {
        return by;
    }

    @Override
    public char getTypeIcon() {
        return 'D';
    }

    @Override
    public String getDescription() {
        return super.getDescription();
    }


}