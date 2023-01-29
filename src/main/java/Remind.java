public class Remind extends Item {
    public Remind(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[R]" + super.toString();
    }
}