# User Guide

Duke is a chatbot that can help you to manage your tasks,
which you can use via a command line interface.

## Features

### Add tasks
There are currently 3 types of tasks you can add to your task list.
- [todo tasks](#todo-add-a-todo-task) (indicated with `[T]`)
- [deadline tasks](#deadline-add-a-deadline-task) (indicated with `[D]`)
- [event tasks](#event-add-an-event-task) (indicated with `[E]`)

### List tasks
You can [list](#list-list-all-tasks) all the tasks in your task list.

### Mark tasks
You can either mark tasks as done or not done.
Completed tasks are indicated with a `[X]`, whereas incomplete tasks are indicated with a `[ ]`.
- [mark as done](#mark-mark-a-task-as-done)
- [mark as not done](#unmark-mark-a-task-as-not-done)

### Delete tasks
You can [delete](#delete-delete-a-task) tasks from your task list.

### Find tasks
You can [find](#find-find-tasks-with-substring) tasks containing a particular string.

### Local save
Your task list is automatically saved locally and loaded upon startup.
If you want to transfer your saved tasks, simply copy and paste the `save.txt` file.

## Usage 

### `todo` Add a todo task
Adds a new todo task to your task list. A task description must be provided.

Format: `todo <description>`

Example:
- `todo buy groceries` creates a todo task with the description `buy groceries`.

### `deadline` Add a deadline task
Adds a new deadline task to your task list.
A task description and the deadline to be completed by must be provided.

Format: `deadline <description> /by <time>`

Example:
- `deadline do math homework /by Friday 3pm` creates a deadline task
  with the description `do math homework` and deadline `Friday 3pm`.

### `event` Add an event task
Adds a new event task to your task list.
A task description, as well as the start and end times of the event must be provided.

Format: `event <description> /from <start time> /to <end time>`

Example:
- `event attend lecture /from tomorrow 12pm /to 2pm` creates an event task with the description `attend lecture`,
  with start time `tomorrow 12pm` and end time `2pm`.

### `list` List all tasks
Displays all tasks currently in the task list, in the order they were added.

Format: `list`

### `mark` Mark a task as done
Marks the task with the given index as done.
The index must be an integer from 1 to the size of the task list.

Format: `mark <index>`

Example:
- `mark 2` marks the task at index 2 of the task list as done.

### `unmark` Mark a task as not done.
Marks the task with the given index as *not* done.
The index must be an integer from 1 to the size of the task list.

Format: `unmark <index>`

Example:
- `unmark 3` marks the at index 3 of the task list as not done.

### `delete` Delete a task
Deletes the task at the given index.
The index must be an integer from 1 to the size of the task list.

Format: `delete <index>`

Example:
- `delete 1` deletes the task at index 1 of the task list.

### `find` Find tasks with substring
Displays all tasks currently in the task list whose description contain the given substring.

Format: `find <search string>`

Example:
- `find eat` lists all tasks with `eat` in their description.

### `bye` Exit the program
Closes Duke.

Format: `bye`
