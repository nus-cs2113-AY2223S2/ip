# User Guide

## Features

This is a list of features currently supported by Duke.

### Adding of tasks

Duke currently supports _3_ different task types.

1. Todo
2. Deadline
3. Event

### List

Displays a list of all current tasks.

### Mark

Marks a specific task done/undone.

### Find

Displays a list of current tasks that match specified keywords.

### Delete

Deletes a specified task.

### Bye

Terminates Duke program.

## Usage

### `Todo` - Adds a Todo task.

This command adds a Todo type task to the tasks list.

Example of usage:

`/todo CS2113 Assignment`

Expected outcome:

User will be able to see a message showing details of the newly added Todo task.

```
---------------------------------------------------
[MESSAGE] Added the following task:
[TODO]
[ ] CS2113 Assignment
---------------------------------------------------
```

### `Event` - Adds an Event task.

This command adds an Event type task to the tasks list. A start and end time is required via the `/start` and `/end` commands.

Example of usage:

`/event Lunch with John /start 1pm /end 2pm`

Expected outcome:

User will be able to see a message showing details of the newly added Event task.

```
---------------------------------------------------
[MESSAGE] Added the following task:
[EVENT]
[ ] Lunch with John (From: 1pm | To: 2pm)
---------------------------------------------------
```

### `Deadline` - Adds a Deadline task.

This command adds a Deadline type task to the tasks list. A cut off time is **_required_** via the `/by` command.

Example of usage:

`/deadline Submit CS2113 assignment /by 01-08-2023`

Expected outcome:

User will be able to see a message showing details of the newly added Deadline task.

```
---------------------------------------------------
[MESSAGE] Added the following task:
[DEADLINE]
[ ] Submit CS2113 assignment (By: 01-08-2023)
---------------------------------------------------
```

### `List` - Displays a list of all current tasks.

Example of usage:

`/list`

Expected outcome:

User will be able to see a message showing a lists of current tasks.

```
---------------------------------------------------
[MESSAGE] These are the following tasks you have:
---------------------------------------------------
---------------------------------------------------
1.[TODO]
[ ] CS2113 Assignment
---------------------------------------------------
---------------------------------------------------
2.[EVENT]
[ ] Lunch with John (From: 1pm | To: 2pm)
---------------------------------------------------
---------------------------------------------------
3.[DEADLINE]
[ ] Submit CS2113 assignment (By: 01-08-2023)
---------------------------------------------------
```

### `Mark` - Marks a task done/undone.

This command marks a user-specified task done/undone. A numerical task index is **_required_**.

Example of usage:

`/mark 2`

Expected outcome:

```
---------------------------------------------------
[MESSAGE] The following task has been marked done: [X] Lunch with John
---------------------------------------------------
```

### `Find` - Find specific tasks.

This command allows the user to find certain tasks with specified keywords. At least 1 keyword is **_required_**.

<sub>**Note: specified keywords are _non case-sensitive_**</sub>

Example of usage:

`/find ASSIGNMENT`

Expected outcome:

User will be able to see a list of current tasks that matches **_any_** of the provided keywords.

```
---------------------------------------------------
/find ASSIGNMENT
---------------------------------------------------
[MESSAGE] These are the tasks that matched your keyword!
---------------------------------------------------
---------------------------------------------------
1.[TODO]
[ ] CS2113 Assignment
---------------------------------------------------
---------------------------------------------------
2.[DEADLINE]
[ ] Submit CS2113 assignment (By: 01-08-2023)
---------------------------------------------------
```

### `Delete` - Deletes a specific task.

This command allows the user to delete a specified task. A numerical task index is **required**.

Example of usage:

`/delete 2`

Expected outcome:

```
---------------------------------------------------
[MESSAGE] The following task has been removed: [ ] Lunch with John
---------------------------------------------------
```

### `Bye` - Terminates Duke program.

Example of usage:

`/bye`

Expected outcome:

```
---------------------------------------------------
Bye! Hope to see you again soon!
---------------------------------------------------
```
