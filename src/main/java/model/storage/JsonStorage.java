package model.storage;

public class JsonStorage {

  public JsonStorage(String description, int index, boolean marked, String end, String type, String start) {
    this.index = index;
    this.description = description;
    this.marked = marked;
    this.start = start;
    this.end = end;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getType() {
    return type;
  }

  protected String description;

  protected int index;

  protected String start;

  protected boolean marked;

  protected String end;

  protected String type;
}
