package duke.model;

public class Payload {
    protected String[] data;

    public Payload() {
        this.data = new String[0];
    }

    public Payload(String[] data) {
        this.data = data;
    }

    public String[] getData() {
        return data;
    }

}
