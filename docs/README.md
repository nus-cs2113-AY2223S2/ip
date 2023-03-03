# User Guide

## Features 

### Adding and deleting tasks
Easily add or remove tasks with a single command.

### Viewing all tasks
See a list containing all of your tasks.

### 3 different types of tasks
The typical to-do, deadline for tasks that needs to be done by a certain time, and events with start and end times.

### Marking and unmarking tasks as done
Easily mark a task as completed, or unmark it if you changed your mind.

### Searching for tasks by keyword
If your task list is too long, you can search for specific tasks using a keyword.

---

## Usage

### `list` - Displays all tasks

Shows a list containing all your tasks

Example of usage: 

`list`

Expected outcome:

```
____________________________________________________________
    Here are the tasks in your list:
    1.[T][X] Buy stationaries
    2.[E][X] Attend class (from: 5pm to: 6pm)
    3.[D][ ] Do assignment (by: Friday 2359)
____________________________________________________________
```

---

### `mark` - Marks a task as done

Indicate task to mark by its index in the task list

Example of usage:

`mark 1`

Expected outcome:

```
____________________________________________________________
    Nice! I've marked this task as done:
[T][X] Buy stationaries
____________________________________________________________
```

---

### `unmark` - Mark a task as not done

Indicate task to unmark by its index in the task list

Example of usage:

`unmark 1`

Expected outcome:

```
____________________________________________________________
    OK, I've marked this task as not done yet:
[T][ ] Buy stationaries
____________________________________________________________
```

---

### `todo` - Create a new todo

A todo only has a description

Example of usage:

`todo Buy lunch`

Expected outcome:

```
____________________________________________________________
    Got it. I've added this task:
    [T][ ] Buy lunch
    Now you have 4 tasks in the list.
____________________________________________________________
```

---

### `deadline` - Creates a new deadline

A deadline has a due date in addition to a description. Use the /by flag to indicate the due date.

Example of usage:

`deadline Submit assignment /by Mon 2359`

Expected outcome:

```
____________________________________________________________
    Got it. I've added this task:
    [D][ ] Submit assignment (by: Mon 2359)
    Now you have 5 tasks in the list.
____________________________________________________________
```

---

### `event` - Create a new event

An event has description, start time, and end time. Use /from to label the start time and /to to label the end time.

Example of usage:

`event Midterms /from 10am /to 12pm`

Expected outcome:

```
____________________________________________________________
    Got it. I've added this task:
    [E][ ] Midterms (from: 10am to: 12pm)
    Now you have 6 tasks in the list.
____________________________________________________________
```

---

### `delete` - Deletes a task by its index

Example of usage:

`delete 3`

Expected outcome:

```
____________________________________________________________
    Noted. I've removed this task:
[T][ ] Buy lunch
____________________________________________________________
```

---

### `bye` - Exits the program

Example of usage:

`bye`

Expected outcome:

```
____________________________________________________________
    Bye. Hope to see you again soon!
____________________________________________________________
```

---

### `find` - Finds tasks based on a keyword

Example of usage:

`find assignment`

Expected outcome:

```
____________________________________________________________
    Here are the tasks containing 'assignment':
    [D][ ] Submit assignment (by: Mon 2359)
____________________________________________________________
```