package model.storage;

/**
 * Uses the following model to store the data in the format inside of the JSON file.
 */
public class JsonStorage {

  public JsonStorage(String description, boolean marked, String end, String type, String start) {
    this.description = description;
    this.marked = marked;
    this.type = type;
    this.start = start;
    this.end = end;
  }

  public String getDescription() {
    return description;
  }

  public String getType() {
    return type;
  }

  public boolean isMarked() {
    return marked;
  }

  public String getStart() {
    return start;
  }

  public String getEnd() {
    return end;
  }

  protected String description;

  protected String start;

  protected boolean marked;

  protected String end;

  protected String type;
}
