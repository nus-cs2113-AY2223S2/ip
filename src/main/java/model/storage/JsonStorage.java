package model.storage;

/**
 * Uses the following model to store the data in the format inside the JSON file.
 * The builder design pattern is used here to grant flexibility when creating a
 * JSON Storage object.
 */
public class JsonStorage {


  public void setDescription(String description) {
    this.description = description;
  }

  public void setMarked(boolean isMarked) {
    this.isMarked = isMarked;
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
    return isMarked;
  }

  public String getStart() {
    return start;
  }

  public String getEnd() {
    return end;
  }

  protected String description;

  protected String start;

  protected boolean isMarked;

  protected String end;

  protected String type;
}
