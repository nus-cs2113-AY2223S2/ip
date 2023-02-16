# User Guide

Duke is a CLI task manager for keeping track of your tasks. There is a variety of supported tasks, with auto-saving and
loading on startup.

## Features

### Adding and Managing Tasks

Tasks come in 3 flavours:

- Todo: Contains a simple description
- Deadline: Contains a description and deadline
- Event: Contains a description, start date and end date

Tasks can be added, marked as done, re-marked as undone, and deleted. The user may also list all existing tasks or list
tasks that contain a specified keyword.

### Persistent Storage

Tasks that are created, marked, or deleted will automatically be saved on the user's computer with no manual
intervention required. Upon program startup, tasks from previous sessions will be automatically loaded.

### Syntax Guiding

Upon inputting a command with incorrect syntax, Duke will prompt the user with the correct expected syntax.

## Usage

### `todo` - Add Todo

Adds a Todo to the task list.

**Example of usage**:

`todo CS2113 IP`

**Expected outcome**:

Addition of a Todo to the task list

```
____________________________________________________________
Hello! I'm Duke
What can I do for you?
____________________________________________________________
> todo CS2113 IP
Got it. I've added this task:
[T][ ] CS2113 IP
Now you have 1 task(s) in the list.
____________________________________________________________
```

### `deadline` - Add Deadline

Adds a deadline to the task list. Expects a deadline to be specified for the task.

**Example of usage**:

`deadline CS2113 IP /by Yesterday 2359`

**Expected outcome**:

Addition of a deadline task to the task list

```
____________________________________________________________
Hello! I'm Duke
What can I do for you?
____________________________________________________________
> deadline CS2113 IP /by Yesterday 2359
Got it. I've added this task:
[D][ ] CS2113 IP (by: Yesterday 2359)
Now you have 1 task(s) in the list.
____________________________________________________________
```

### `event` - Add Event

Adds an event to the task list. Expects a start and end date to be specified for the task.

**Example of usage**:

`event CS2113 Midterm /from Friday 0800 /to 2000`

**Expected outcome**:

Addition of an event task to the task list

```
____________________________________________________________
Hello! I'm Duke
What can I do for you?
____________________________________________________________
> event CS2113 Midterm /from Friday 0800 /to 2000
Got it. I've added this task:
[E][ ] CS2113 Midterm (from: Friday 0800 to: 2000)
Now you have 1 task(s) in the list.
____________________________________________________________
```

### `list` - Lists all tasks

Shows the user all tasks that have been entered alongside the task IDs.

**Example of usage**:

`list`

**Expected outcome**:

Shows all tasks with their descriptions and deadline/duration (if applicable). The task types can be differentiated by
their tags.

```
____________________________________________________________
Hello! I'm Duke
What can I do for you?
____________________________________________________________
> list
1. [T][x] CS2113 IP
2. [D][ ] CS2113 IP (by: Yesterday 2359)
3. [E][ ] CS2113 Midterm (from: Friday 0800 to: 2000)
____________________________________________________________
```

### `find` - Lists all tasks with keyword

Shows the user all tasks that have a description matching the case-sensitive input keyword.

**Example of usage**:

`find Midterm`

**Expected outcome**:

Shows all tasks with matching descriptions.

```
____________________________________________________________
Hello! I'm Duke
What can I do for you?
____________________________________________________________
> find Midterm
1. [E][ ] CS2113 Midterm (from: Friday 0800 to: 2000)
____________________________________________________________
```

### `mark` - Mark task as done

Mark the specified task as done. Tasks that are marked as done will be displayed with an `[x]` in the list. Changes are
automatically saved to file.

**Example of usage**:

`mark 1`

**Expected outcome**:

Mark task as done.

```
____________________________________________________________
Hello! I'm Duke
What can I do for you?
____________________________________________________________
> mark 1
Nice! I've marked this task as done
[T][x] CS2113 IP
____________________________________________________________
```

### `unmark` - Mark task as undone

Mark the specified task as undone. Tasks that are marked as not done will be displayed with an `[ ]` in the list.
Changes are
automatically saved to file.

**Example of usage**:

`mark 1`

**Expected outcome**:

Mark task as undone.

```
____________________________________________________________
Hello! I'm Duke
What can I do for you?
____________________________________________________________
> unmark 1
Ok, I've marked this task as not done yet:
[T][ ] CS2113 IP
____________________________________________________________
```

### `delete` - Delete task

Delete the task from task list; subsequently calls to `list` will not show this task. Changes are automatically saved to
file.

**Example of usage**:

`delete 1`

**Expected outcome**:

Successfully deletes task.

```
____________________________________________________________
Hello! I'm Duke
What can I do for you?
____________________________________________________________
> delete 1
Alrighty, I've removed this task:
[T][ ] CS2113 IP
Now you have 0 task(s) in the list.
____________________________________________________________
```

### `bye` - Exits Duke

Exits the application. No worries, your tasks will still be saved!

**Example of usage**:

`bye`

**Expected outcome**:

Closes the application.

```
____________________________________________________________
Hello! I'm Duke
What can I do for you?
____________________________________________________________
> bye
____________________________________________________________
Bye. Hope to see you again soon!