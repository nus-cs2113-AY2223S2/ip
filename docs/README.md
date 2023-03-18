# User Guide
Duke is an application for managing task list via a **Command Line Interface (CLI)** that helps you to remember the tasks that you do not wish to forget.

+ [Quick Start](#quick-start)
+ [Features](#features)
  + [Create a ToDo task: `todo`](#create-a-todo-task--todo)
  + [Create a Deadline task: `deadline`](#create-a-deadline-task--deadline)
  + [Create an Event task: `event`](#create-an-event-task--event)
  + [Mark a task as done: `mark`](#mark-a-task-as-done--mark)
  + [Mark a task as not done: `unmark`](#mark-a-task-as-not-done--unmark)
  + [List out the tasks: `list`](#list-out-the-tasks--list)
  + [Delete a specific task: `delete`](#delete-a-specific-task--delete)
  + [Find a task by keyword: `find`](#find-a-task-by-keyword--find)
  + [Exit the application: `bye`](#exit-the-application--bye)

### Quick Start
1. Ensure that you have Java 11 installed.
2. Download the latest duke.jar from [here]().
3. Copy the file to the folder you want your Duke to be in.
4. Open a command terminal, navigate into the folder using `cd`
5. Run the application using `-jar duke.jar`

### Features
#### Create a ToDo Task: `todo`
Adds a todo task into the list.

Format: `todo TASK_NAME`

Example: `todo borrow book`

Output:
```
____________________________________________________
Got it. I've added this tasks:
[T][ ] borrow book
Now you have 1 tasks in the list.
____________________________________________________
```
#### Create a Deadline Task: `deadline`
Adds a deadline task into the list that includes the date of the deadline.

Format: `deadline TASK_NAME /by DATE`

Example: `deadline return book /by Sunday`

Output:
```
____________________________________________________
Got it. I've added this tasks:
[D][ ] return book(by: Sunday)
Now you have 2 tasks in the list.
____________________________________________________
```

#### Create an Event Task: `event`
Adds an event task into the list that include the start and end time of the event.

Format: `event TASK_NAME /from START_TIME /to END_TIME`

Example: `event project meeting /from Mon 2pm /to 4pm`

Output:
```
____________________________________________________
Got it. I've added this tasks:
[E][ ] project meeting(from: Mon 2pm to: 4pm)
Now you have 3 tasks in the list.
____________________________________________________
```

#### Mark a task as done: `mark`
Mark an existing task in the list as completed.

Format: `mark TASK_NUMBER`

Example: `mark 1`

Output:
```
____________________________________________________
Nice! I've marked this task as done:
[T][X] borrow book
____________________________________________________
```

#### Mark a task as not done: `unmark`
Mark an existing task in the list as not completed.

Format: `unmark TASK_NUMBER`

Example: `unmark 1`

Output:
```
____________________________________________________
Nice! I've marked this task as not done yet:
[T][ ] borrow book
____________________________________________________
```

#### List out the tasks: `list`
List out the tasks in the order it was added.

Format: `list`

Example: `list`

Output:
```
____________________________________________________
Here are the tasks in your list:
1.[T][ ] borrow book
2.[D][ ] return book(by: Sunday)
3.[E][ ] project meeting(from: Mon 2pm to: 4pm)
____________________________________________________
```


#### Delete a specific task: `delete`
Remove a specific task by its number in the list.

Format: `delete TASK_NUMBER`

Example: `delete 3`

Output:
```
____________________________________________________
Noted. I've removed this task:
[E][ ] project meeting(from: Mon 2pm to: 4pm)
Now you have 2 tasks in the list.
____________________________________________________
```

#### Find a task by keyword: `find`
Find the tasks containing the keyword.

Format: `find KEYWORD`

Example: `find book`

Output:
```
____________________________________________________
Here are the matching tasks in your list:
1.[T][ ] borrow book
2.[D][ ] return book(by: Sunday)
____________________________________________________
```

#### Exit the application: `bye`
Exits the application Duke.

Format: `bye`

Example: `bye`

Output:
```
____________________________________________________
Bye. Hope to see you again soon!
____________________________________________________
```
