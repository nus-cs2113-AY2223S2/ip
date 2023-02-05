public class invalidInputFormat extends Exception {
    private String type;
    public invalidInputFormat(String type) {
        this.type = type;
    }
    @Override
    public String toString() {
        if (type.equals("m")){
            return " Bro please follow this format:\n" +
                    " \"mark/unmark {index of task according to list}\"\n" +
                    " e.g. Finish homework /by Monday 2pm";
        } else if (type.equals("t")) {
            return " Bro please follow this format:\n" +
                    " \"todo {Name of Todo}\"\n" +
                    " e.g. todo Maths homework";
        } else if (type.equals("d")) {
            return " Bro please follow this format:\n" +
                    " \"deadline {Name of Deadline} /by {Deadline}\"\n" +
                    " e.g. mark 3";
        }
        return "";
    }
}
