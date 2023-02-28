# User Guide

## Features 

### List

List all tasks in the task list.

### Delete

Delete a task from the task list.

### Mark

Mark a task as done.

### Unmark

Unmark a task as undone.

### Find

Find tasks that contain a keyword.

### Todo

Add a todo task to the task list.

### Deadline

Add a deadline task to the task list.

### Event

Add an event task to the task list.

### Bye

Exit the program.


## Usage

### `list` - List all tasks



Example of usage: 

`list`

Outputs the list of tasks in the task list.

Expected outcome: 

```
Here are the tasks in your list:
1.[T][ ] read book
2.[D][ ] submit report  (by: Jan 12 2023 23:59)
3.[E][ ] football match  (from: Feb 20 2023 20:00 to: Feb 20 2023 22:00)
```

### `delete` - Delete a task
Example of Usage:

`delete 1`

Deletes the first task in the task list.

Expected outcome:

```
Noted. I've removed this task:
[T][ ] read book
Now you have 2 tasks in the list.
```

### `todo` - Add a todo task
Example of Usage:

`todo read book`

Adds a todo task to the task list.

Expected outcome:

```
Got it. I've added this task:
[T][ ] read book
Now you have 1 tasks in the list.
```

### `deadline` - Add a deadline task
Example of Usage:

`deadline submit report /by 2023-01-12T23:59`

Adds a deadline task to the task list.

Expected outcome:

```
Got it. I've added this task:
[D][ ] submit report  (by: Jan 12 2023 23:59)
Now you have 2 tasks in the list.
```

### `event` - Add an event task
Example of Usage:

`event football match /at 2023-02-20T20:00-22:00`

Adds an event task to the task list.

Expected outcome:

```
Got it. I've added this task:
[E][ ] football match  (from: Feb 20 2023 20:00 to: Feb 20 2023 22:00)
Now you have 3 tasks in the list.
```

### `mark` - Mark a task as done
Example of Usage:

`mark 1`

Marks the first task in the task list as done.

Expected outcome:

```
Nice! I've marked this task as done:
[T][X] read book
```

### `unmark` - Unmark a task as undone
Example of Usage:

`unmark 1`

Unmarks the first task in the task list as undone.

Expected outcome:

```
OK, I've marked this task as not done yet:
[T][ ] read book
```

### `find` - Find tasks that contain a keyword
Example of Usage:

`find book`

Finds tasks that contain the keyword "book".

Expected outcome:

```
Here are the matching tasks in your list:
1.[T][ ] return book
2.[T][ ] read book
```

### `bye` - Exit the program
Example of Usage:

`bye`

Exits the program.

Expected outcome:

```
Bye. Hope to see you again soon!

Process finished with exit code 0
```