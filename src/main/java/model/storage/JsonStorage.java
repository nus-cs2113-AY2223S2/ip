package model.storage;

/**
 * Uses the following model to store the data in the format inside the JSON file.
 * The builder design pattern is used here.
 */
public class JsonStorage {


  public void setDescription(String description) {
    this.description = description;
  }

  public void setMarked(boolean marked) {
    this.marked = marked;
  }

  public void setStart(String start) {
    this.start = start;
  }

  public void setEnd(String end) {
    this.end = end;
  }

  public void setType(String type) {
    this.type = type;
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
