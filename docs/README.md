# User Guide - Duke

## Features

### Feature - Adding and Deleting Tasks from a Task List

Create and manage a list of tasks with descriptions to
better organize your time. Add, delete, and display
tasks.

### Feature - Task Types

Add three different types of tasks - todos, deadlines,
and events to better specify time constraints and task requirements.

### Feature - Saving and Uploading Tasks

Tasks in the list are saved when Duke closes, and uploaded
back to the task list when re-opened.

### Feature - Searching for Tasks

Filter tasks using keywords to simplify task management.

### Feature - Marking and Unmarking Tasks as Complete

Mark tasks as complete or incomplete to track their status.

## Usage

### `list` - Display task list

Displays a list of all tasks on your list.

Example of usage:

`list`

Expected outcome:

A list of your current tasks.

```
____________________________________________________________

Time for a productive day, me hearties! Here be yer list of tasks:
1. [T][ ] CS2113 iP
2. [E][X] Visit Thailand (from: Feb 21 2023 to: Feb 26 2023)
3. [D][ ] Apply for scholarship (by: Sunday)
____________________________________________________________
```

### `mark` - Mark a task as complete

Marks a task at a certain index as complete.

Example of usage:

`mark 1`

Expected outcome:

The task description with an 'X' marked on the completion box.

```
____________________________________________________________

mark 1

Nice! I've marked this task as done, me hearties!

     [T][X] homework
____________________________________________________________

```

### `unmark` - Display task list

Marks a task at a certain index as incomplete.

Example of usage:

`unmark 1`

Expected outcome:

The task description with an 'X' removed from the completion box.

```
____________________________________________________________

unmark 1

Aye, I've marked this task as not done yet, ye scallywag:

     [T][ ] homework
____________________________________________________________
```

### `help` - Display command list

Displays a list of commands.

Example of usage:

`help`

Expected outcome:

A list of commands.

```
____________________________________________________________

Avast! Here be the commands ye can use to make me do yer bidding!
- list: lists all current tasks
- mark x: marks task x as complete
-unmark x: unmarks task x as complete
- todo 'description': adds a task to do with the given description
- deadline 'description' /by 'deadline': adds a deadline task with the given date and description
- event 'description' /from 'start' /to 'end': adds an event with the start and endtime
- bye: exits Duke
- find 'searchKey': shows all relevant tasks
- delete 'num': removes task 'num' from list
____________________________________________________________
```

### `todo` - Add a 'todo' Task

Create a todo task with a specified description and add it to the list.

Example of usage:

`todo CS2113 iP`

Expected outcome:

A confirmation message and the length of your task list.

```
____________________________________________________________

added: [T][ ] CS2113 iP
Now you have 1 task in the list!
____________________________________________________________
```

### `deadline` - Add a 'deadline' Task

Create a deadline task with a specified description and due date, and add it to the list.

Example of usage:

`deadline Apply for scholarship /by Sunday`

Expected outcome:

A confirmation message and the length of your task list.

```
____________________________________________________________

added: [D][ ] Apply for scholarship (by: Sunday)
Now you have 2 tasks in the list!
____________________________________________________________
```

### `event` - Add an 'event' Task

Create an event task with a specified description, start time, and end time, and add it to the list.

Example of usage:

`event Visit Thailand /from 2023-02-21 /to 2023-02-25`

Expected outcome:

A confirmation message and the length of your task list.

```
____________________________________________________________

added: [E][ ] Visit Thailand (from: Feb 21 2023 to: Feb 25 2023)
Now you have 3 tasks in the list!
____________________________________________________________
```

### `delete` - Delete a Task from the List

Delete a task at a specified index of your task list.

Example of usage:

`delete 1`

Expected outcome:

A confirmation message and the length of your task list.

```
____________________________________________________________

Aye! The task be removed:
    [T][ ] CS2113 iP
Now you have 2 tasks in the list!
____________________________________________________________
```

### `find` - Filter tasks

Displays a list of all tasks on your list that contain a certain keyword.

Example of usage:

`find Thailand`

Expected outcome:

A list of tasks containing the keyword.

```
____________________________________________________________

Tasks found:
1. [E][ ] Visit Thailand (from: Feb 21 2023 to: Feb 25 2023)
____________________________________________________________
```
