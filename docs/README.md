# User Guide

Duke is a Command-Line Interface (CLI) task manager. It allows you to keep track of your tasks quickly and navigate
through those tasks quickly.

## Features

- Adding different types of tasks:
    - Todo: the most basic type of task
    - Deadline: type of task which you can assign the due date of it
    - Event: type of task which has a start and end date
- Searching based on keyword and listing all tasks are also supported

## Getting started

> Java 11 is recommended to run `Duke`

1. Download the latest duke.jar from here [here](https://github.com/kristianachwan/ip/releases)
2. Go to the directory where you downloaded
3. Run `java -jar duke.jar`

## Add ToDo

Adds todo to your list. Format: `todo <todo-name>`

### Adding `ToDo` example:

```
todo CS2113 tP Meeting
    ____________________________________________________________
    Got it. I've added this task:
    [T][ ]	CS2113 tP Meeting
    ____________________________________________________________
```

## Add Deadline

Adds deadline to your list. Format: `deadline <deadline-name> /<due-by>`

### Adding `Deadline` example:

```
deadline tP Meeting /Friday 16.00pm
    ____________________________________________________________
    Got it. I've added this task:
    [D][ ]	tP Meeting (by: Friday 16.00pm)
    ____________________________________________________________
```

## Add Deadline

Adds event to your list. Format: `event <event-name> /<start-time> /<end-time> `

### Adding `Event` example:

```
event CS2113 tP Meeting /Friday 16.00pm /18.00pm
    ____________________________________________________________
    Got it. I've added this task:
    [E][ ]	CS2113 tP Meeting (from: Friday 16.00pm to: 18.00pm)
    ____________________________________________________________

```

### Listing all tasks

List all of your tasks. Format: `list`

### Listing all tasks example:

```
list
    ____________________________________________________________
      1. [T][ ]	CS2113 tP Meeting
      2. [D][ ]	tP Meeting (by: Friday 16.00pm)
      3. [E][ ]	CS2113 tP Meeting (from: Friday 16.00pm to: 18.00pm)
    ____________________________________________________________
```

## Deleting a task:

Delete your task by indicate the index of the task. Format: `delete <task-index>`

### Deleting a task example:

```
delete 1
    ____________________________________________________________
    Noted! I've removed this task
    [T][ ]	CS2113 tP Meeting
    ____________________________________________________________
```

## Searching specific tasks

Search your task based on specific keyword. Format: `search <keyword>`

### Searching specific tasks example

```
find tP
    ____________________________________________________________
    [D][ ]	tP Meeting (by: Friday 16.00pm)
    [E][ ]	CS2113 tP Meeting (from: Friday 16.00pm to: 18.00pm)
    ____________________________________________________________
```

## Marking task as done

Mark your task as done. It will have `[X]` when it is listed again.
Format: `mark <task-index>`

### Marking task as done example

```
mark 1 
    ____________________________________________________________
    Nice! I've marked this task as done:
    [D][X]	tP Meeting (by: Friday 16.00pm)
    ____________________________________________________________
```

### Marking task as undone

Mark your task as undone (or unmark). It will revert back to `[]`.
Format: `unmark <task-index>`

```
unmark 1
    ____________________________________________________________
    Ok, I've marked this task as not done:
    [D][ ]	tP Meeting (by: Friday 16.00pm)
    ____________________________________________________________
```

### Exiting the program

```
bye
    ____________________________________________________________
    Bye. Hope to see you again soon!
    ____________________________________________________________
```