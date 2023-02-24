# User Guide For Sage Task Manager

## Features

### Add Task (Todo, Event or Deadline)

This feature allows the user to add a task to the task list. The user can add a task to the application.

### Delete A Task

To delete a task from the application

### List All Tasks

To list all tasks in the application

### Find A Task

To find tasks with a matching query in the task description.

### Mark/Unmark Task

You can mark/unmark a task as done/not done in the application.

### Data Persistence Support

Your previous session data will be automatically stored (in ./data/sage.txt) and restored (If applicable)

## Usage

### `Todo` - Add a Todo Task

Adds a Todo task to the task list.

Example of usage:

`todo buy groceries`

Expected outcome:

```
Got it. I've added this task:
[T][ ] buy groceries
Now you have X tasks in the list.
```

### `deadline` - Add a Deadline Task

Adds a Deadline task to the task list.

Example of usage:

`deadline submit report /by 2022-03-31`

Expected outcome:

```
Got it. I've added this task:
[D][ ] submit report (by: 31 Mar 2022)
Now you have X tasks in the list.
```

### `event` - Add an Event Task

Adds an Event task to the task list.

Example of usage:

`event project meeting /from 2022-04-01 /to 2022-04-02`

Expected outcome:

```
Got it. I've added this task:
[E][ ] project meeting (from: 2022-04-01 to: 2022-04-02)
Now you have X tasks in the list.
```

### `delete` - Delete a Task

Deletes the specified task from the task list.

Example of usage:

`delete 2`

Expected outcome:

```
Noted. I've removed this task:
[D][ ] submit report (by: 31 Mar 2022)
Now you have X tasks in the list.
```

### `find` - Find Tasks

Displays a list of tasks that match the specified keyword.

Example of usage:

`find meeting`

Expected outcome:

```
Found 1 matching queries!
1.[E][ ] project meeting (from: 2022-04-01 to: 2022-04-02)
```

### `list` - List All Tasks

Displays a list of all tasks in the task list.

Example of usage:

`list`

Expected outcome:

```
Here are the tasks in your list:
1.[T][ ] buy groceries
2.[E][ ] project meeting (from: 2022-04-01 to: 2022-04-02)
```

### `mark` - Mark a Task as Done

Marks the specified task as done.

Example of usage:

`mark 1`

Expected outcome:

```
Nice! I've marked this task as done:
[T][X] buy groceries
```

### `unmark` - Unmark a Task as Undone

Marks the specified task as undone.

Example of usage:

`unmark 1`

Expected outcome:

```
Noted. I've marked this task as undone:
[T][ ] buy groceries
```

## Other Commands

### `bye` - Quit the application

Exit the application
