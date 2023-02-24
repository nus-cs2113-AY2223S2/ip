package constants;

// Use of interface here as I am not creating any object
public interface Command {
  String FIND = "find";
  String DELETE = "delete";
  String DEADLINE = "deadline";
  String LIST = "list";
  String TODO = "todo";
  String EVENT = "event";
  String MARK = "mark";
  String UNMARK = "unmark";
  String BYE = "bye";
}
