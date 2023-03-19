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

* [`todo`](#todo) - Adds a Todo task (tasks with no deadline, do at your own pace)
* [`deadline`](#deadline) - Adds a deadline (Task with an end date but no interval or start date)
* [`event`](#event) - adds an event (Task with both a start and end date)

### Edit commands

* [`mark`](#mark) - ticks a task as done by marking it with an 'X'
* [`unmark`](#unmark) - unticks a task as undone by removing the 'X' mark
* [`delete`](#delete) - deletes a todo item completely
* [`list`](#itemList) - displays all the added tasks in a list format
* [`find`](#find) - finds all the task that contains teh search query
* [`bye`](#bye) - Quits program

How to use Duke and What features are available:

# List
1. `list` command: <a name = "itemList"></a><br />
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

# Mark
2. `mark` command: <a name = "markIt"></a><br />
   --> adds an "X" to indicate that a task is completed<br />
   --> format: mark [index of item to mark]

      ```
      >>mark 1
      Item has been marked
      1. [T][X]  buy book
      ```

# unmark
3. `unmark` Command: <a name = "unmark"></a><br />
   --> unmarks an item in the todolist by removing the "X"<br />
   --> format: umark [index of item to unmark]

      ```
      >>unmark 1
      Item has been unmarked
      1. [T][ ]  buy book
      ```

# todo
4. `todo` command: <a name = "todo"></a><br />
   --> adds a todo item<br />
   --> format: todo [task_description]

      ```
      >>todo eat breakfast
      Got it, Ive done the Following!
      Added: [T][ ] eat breakfast
      now you have: 4 tasks in this list.
      ```
# deadline
5. `deadline` command <a name = "deadline"></a><br />
   --> adds a Deadline item<br />
   --> format: deadline [task_description] /by [date ONLY in YYYY-MM-DD format]

      ```
      >>deadline return book /by 2019-12-01
      Got it, ive done the Following
      Added: return book (by: 2019-12-01)
      now you have: 4 tasks in this list.
      ```

# event
6. `event` command: <a name = "event"></a><br />
   --> adds an Event item<br />
   --> format: event [task_description] /from [start_date] /to [end_date]

      ```
      >>event project meeting /from Mon 2pm /to 4pm
      Got it, ive done the Following
      Added: project meeting  (from: Mon 2pm to: 4pm)
      now you have: 5 tasks in this list.
      ```

# delete
7. `delete` Command: <a name = "delete"></a><br />
   --> deletes item from the todo list<br />
   --> format: delete [index of item to delete]
      ```
      >>delete 2
      i have deleted the task: buy book
      ```
# find
8. `find` Command <a name = "find"></a><br />
   --> searches for todo items that contain the search key term<br />
   --> format: find [search term]

      ```
      >>find buy
      -------TODO-LIST------
      ----------------------
      1. [T][ ]  buy jams
      2. [T][ ]  buy phone
      ----------------------
      ```
   
# bye
9. `bye` Command: <a name = "bye"></a><br />
   --> exits the program

   ```
   >>bye
   Bye! see you soon!
   ```