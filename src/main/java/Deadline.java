public class Deadline extends Task {
    public String description;


    public Deadline(String input) throws InvalidInputException{
        String[] strArray = input.split("/");
        if (strArray.length != 2 || !strArray[1].startsWith("by")) {
            throw new InvalidInputException();
        }
        this.description = strArray[0].replace("deadline ", "") + ( "(" + strArray[1].replace("by", "by:") + ")");
        items.add(description);
        marked.add(false);
        tasks.add(TaskType.DEADLINE);
        print();
    }

}
