public class Event extends Task{
    public String description;
    public Event(String input) throws InvalidInputException {
        String[] strArray = input.split("/");
        if (strArray.length != 3 || !strArray[1].startsWith("from") || !strArray[2].startsWith("to")) {
            throw new InvalidInputException();
        }
        this.description = strArray[0].replace("deadline ", "") + ( "(" + strArray[1].replace("from", "from:")) + (strArray[2].replace("to", "to:") + ")");
        items.add(description);
        marked.add(false);
        tasks.add(TaskType.EVENT);
        print();
    }
}
