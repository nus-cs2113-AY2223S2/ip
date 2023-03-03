package bro.exceptions;

import bro.tasks.Type;

public class invalidInputFormat extends Exception {
    private Type queryType;
    public invalidInputFormat(Type queryType) {
        this.queryType = queryType;
    }
    @Override
    public String toString() {
        String result = "";
        switch (queryType) {
        case MARK:
            result = " Bro please follow this format:\n" +
                    " \"mark/unmark {index of task according to list}\"\n" +
                    " e.g. Finish homework /by Monday 2pm";
            break;
        case TODO:
            result = " Bro please follow this format:\n" +
                    " \"todo {Name of Todo}\"\n" +
                    " e.g. todo Maths homework";
            break;
        case DEADLINE:
            result = " Bro please follow this format:\n" +
                    " \"deadline {Name of Deadline} /by {Deadline}\"\n" +
                    " e.g. mark 3";
            break;
        case EVENT:
            result = " Bro please follow this format:\n" +
                    " \"event {Name of Event} /from {Start Date/Time} /to {End Date/Time}\"\n" +
                    " e.g. event project meeting /from Mon 2pm /to 4pm";
            break;
        case DELETE:
            result = " Bro please follow this format:\n" +
                    " \"delete {Index of Task in the list}\"\n" +
                    " e.g. delete 2";
            break;
        case FIND:
            result = " Bro please follow this format:\n" +
                    " \"find {single keyword to search [Case insensitive]}\"\n" +
                    " e.g. find book";
            break;
        default:
            break;
        }
        return result;
    }
}
