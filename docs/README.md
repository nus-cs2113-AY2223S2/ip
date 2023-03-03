# DukeMain.Duke project template

   ```
   Hello from
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   ```
## Duke User Guide

# Features

### Supports addition of 3 types of tasks.

* `todo` - Adds a Todo task (tasks with no deadline, do at your own pace)
* `deadline` - Adds a deadline (Task with an end date but no interval or start date)
* `event` - adds an event (Task with both a start and end date)

### Edit commands

* `mark` - ticks a task as done by marking it with an 'X'
* `unmark` - unticks a task as undone by removing the 'X' mark
* `delete` - deletes a todo item completely
* `list` - displays all the added tasks in a list format
* `find` - finds all the task that contains teh search query
* `bye` - Quits program

How to use Duke and What features are available:

1. `list` command:
   --> renders a list of all the items in the Todo List

      ```
      >>list
      -------TODO-LIST------
      ----------------------
      1. [T][ ]  buy book
      2. [T][ ]  eat cookies
      3. [T][ ]  watch Tv
      ----------------------
      
      ```

2. `mark` command:
   --> adds an "X" to indicate that a task is completed
   --> format: mark <index of item to mark>
      ```
      >>mark 1
      Item has been marked
      1. [T][X]  buy book
      ```

3. `unmark` Command:
   --> unmarks an item in the todolist by removing the "X"
   --> format: umark <index of item to unmark>
      ```
      >>unmark 1
      Item has been unmarked
      1. [T][ ]  buy book
      ```

4. `todo` command:
   --> adds a todo item
   --> format: todo <task_description>

      ```
      >>todo eat breakfast
      Got it, Ive done the Following!
      Added: [T][ ] eat breakfast
      now you have: 4 tasks in this list.
      ```

5. `deadline` command
   --> adds a Deadline item
   --> format: deadline <task_description> /by <date in YYYY-MM-DD format>

      ```
      >>deadline return book /by 2019-12-01
      Got it, ive done the Following
      Added: return book (by: 2019-12-01)
      now you have: 4 tasks in this list.
      ```

6. `event` command:
   --> adds an Event item
   --> format: event <task_description> /from <start_date> /to <end_date>

      ```
      >>event project meeting /from Mon 2pm /to 4pm
      Got it, ive done the Following
      Added: project meeting  (from: Mon 2pm to: 4pm)
      now you have: 5 tasks in this list.
      ```

7. `delete` Command:
   --> deletes item from the todo list
   --> format: delete <index of item to delete>
      ```
      >>delete 2
      i have deleted the task: buy book
      ```

8. `find` Command
   --> searches for todo items that contain the search key term
      ```
      >>find buy
      -------TODO-LIST------
      ----------------------
      1. [T][ ]  buy jams
      2. [T][ ]  buy phone
      ----------------------
      ```

9. `bye` Command:
   --> exits the program

   ```
   >>bye
   Bye! see you soon!
   ```