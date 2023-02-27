# User Guide

This is the user guide for the Duke app, a task management app.
## Features 

### Add Various types of tasks

The app supports several types of tasks

  1. ToDos
  2. Deadlines
  3. Events

### Task operations

  1. Delete tasks
  2. Mark/unmark tasks as done
  3. Search for tasks with a specific keyword
  4. List all saved tasks

### Task data

  1. Reads and writes task data to a txt file ```memory.txt```

## Usage

### `todo` - Add a todo task

do 2023

Example of usage: 

`todo do 2023`

Expected outcome:

Acknowledgment of task addition and display of new task.

```
覚えましたよ～ (todo recorded) do 2023
```

### `deadline` - Add a deadline task

Add a new deadline task.

Example of usage: 

`deadline submit withdrawl form /by week 6`

Expected outcome:

Acknowledgment of task addition and display of new task.

```
覚えましたよ～ (event recorded) interview at McDonalds (from: 4pm to: 6pm)
```

### `event` - Add an event task

Add a new event task.

Example of usage: 

`event interview at McDonalds /from 4pm /to 6pm`

Expected outcome:

Acknowledgment of task addition and display of new task.

```
覚えましたよ～ (event recorded) interview at McDonalds (from: 4pm to: 6pm)
```

### `list` - List all tasks

List all saved tasks.

Example of usage: 

`list`

Expected outcome:

List of all saved tasks.

```
1. [T][ ] do 2023
2. [D][ ] submit withdrawl form  (by: week 6)
3. [E][ ] interview at McDonalds (from: 4pm to: 6pm)
```


