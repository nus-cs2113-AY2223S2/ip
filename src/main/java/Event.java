public class Event extends Task {
    public Event(String description) {
        super(description);
        this.taskType = TaskType.EVENT;
    }

    @Override
    public String toString() {
        String[] descriptionParts = this.description.split("/from");
        String[] descriptionFromTo = descriptionParts[1].split("/to");
        String descriptionDetails = descriptionParts[0];
        String descriptionFrom = descriptionFromTo[0];
        String descriptionTo = descriptionFromTo[1];

        return descriptionDetails + "(from:" + descriptionFrom + "To:" + descriptionTo + ")";
    }
}
