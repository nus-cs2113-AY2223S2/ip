# User Guide

Orca is a desktop application for managing tasks, optimized for use via a Command Line Interface (CLI). Orca can help a person keep track of various tasks, such as deadlines, events and todos.

## Quick start

1. Ensure you have Java 11 or above installed in your Computer.

2. Download the latest `orca.jar` from [here](https://github.com/L0Z1K/ip/releases).

3. Copy the file to the folder you want to use as the home folder for your Orca.

4. Open a command terminal, navigate to the folder containing `ip.jar` and run the command `java -jar ip.jar` to start the program.

<img width="386" alt="image" src="https://user-images.githubusercontent.com/64528476/221753363-38259ea3-7279-4097-9d9c-c62d9c5145c9.png">

5. Refer to the [Features](README.md#features) below for details of each command.

## Features 

### Adding a todo task: `todo`

Adds a todo task to the task list.

Format: `todo TASK_DESCRIPTION`

Example of usage: `todo read book`

Expected outcome:

```
todo read book
    --------------------------------------------------
    Got it. I've added this task:
      [T][ ] read book
    Now you have 1 tasks in the list.
    --------------------------------------------------
```

### Adding a deadline task: `deadline`

Adds a deadline task to the task list.

Format: `deadline TASK_DESCRIPTION /by DATE`

Example of usage: `deadline return book /by 2023-04-25`

Expected outcome:

```
deadline return book /by 2023-04-25
    --------------------------------------------------
    Got it. I've added this task:
      [D][ ] return book (by: 2023-04-25)
    Now you have 2 tasks in the list.
    --------------------------------------------------
```

### Adding an event task: `event`

Adds an event task to the task list.

Format: `event TASK_DESCRIPTION /from DATE /to DATE`

Example of usage: `event project meeting /from 2023-04-25 /to 2023-04-26`

Expected outcome:

```
event project meeting /from 2023-04-25 /to 2023-04-26
    --------------------------------------------------
    Got it. I've added this task:
      [E][ ] project meeting (from: 2023-04-25 to: 2023-04-26)
    Now you have 3 tasks in the list.
    --------------------------------------------------
```

### Listing all tasks: `list`

Shows a list of all tasks in the task list.

Format: `list`

Example of usage: `list`

Expected outcome:

```
list
    --------------------------------------------------
    Here are the tasks in your list:
    1.[T][ ] read book
    2.[D][ ] return book (by: 2023-04-25)
    3.[E][ ] project meeting (from: 2023-04-25 to: 2023-04-26)
    --------------------------------------------------
```

### Marking a task as done: `mark`

Marks a task as done in the task list.

Format: `mark TASK_INDEX`

Example of usage: `mark 1`

Expected outcome:

```
mark 1
    --------------------------------------------------
    Nice! I've marked this task as done:
      [T][X] read book
    --------------------------------------------------
```

### Marking a task as undone: `unmark`

Marks a task as undone in the task list.

Format: `unmark TASK_INDEX`

Example of usage: `unmark 1`

Expected outcome:

```
unmark 1
    --------------------------------------------------
    Nice! I've marked this task as not done yet:
      [T][ ] read book
    --------------------------------------------------
```

### Deleting a task: `delete`

Deletes a task from the task list.

Format: `delete TASK_INDEX`

Example of usage: `delete 1`

Expected outcome:

```
delete 1
    --------------------------------------------------
    Noted. I've removed this task:
      [T][ ] read book
    Now you have 2 tasks in the list.
    --------------------------------------------------
```

### Finding a task: `find`

Finds a task from the task list.

Format: `find KEYWORD`

Example of usage: `find book`

Expected outcome:

```
find book
    --------------------------------------------------
    Here are the matching tasks in your list:
    1.[D][ ] return book (by: 2023-04-25)
    --------------------------------------------------
```

### Exiting the program: `bye`

Exits the program.

Format: `bye`

Example of usage: `bye`

Expected outcome:

```
bye
    --------------------------------------------------
    Bye. Hope to see you again soon!
    --------------------------------------------------
```