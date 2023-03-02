# User Guide
Duke is a chatbot which can serve as your own to-do list.

## Features
### Add tasks
There are 3 types of tasks that you can add to your to-do list.

- todo (indicated by `[T]`)

- deadline (indicated by `[D]`)

- event (indicated by `[E]`)

### List tasks
You can list all the tasks in your to-do list.

### Mark/unmark tasks
You can mark tasks in your to-do list as done (indicated by `[X]`) or undone (indicated by `[ ]`).

- mark as done
- mark as undone

### Delete tasks
You can delete tasks from your to-do list.

### Find tasks
You can find tasks in your to-do list that contain keywords of your choice.

### Local storage
Your to-do list is automatically saved into a file named `data.txt` on your local device. On start-up, Duke will load
your to-do list from the `data.txt` file if there is one.


## Usage
### Add a todo task: `todo`
Adds a new todo task to your to-do list.

Format: `todo <description>`

Example: `todo buy calculator` creates a todo task with the description `buy calculator`.

### Add a deadline task: `deadline`
Adds a new deadline task to your to-do list.

Format: `deadline <description> /by <date and time>`

- The `<date and time>` must be given in `dd/mm/yyyy tttt` format, where `tttt` is the time in 24-hour clock. 

Example: `deadline CS2113 IP /by 12/02/2009 1000` creates a deadline task with the description `CS2113 IP` and deadline
`12/02/2009 1800`.


### Add an event task: `event`
Adds a new event task to your to-do list.

Format: `event <description> /from <start time> /to <end time>`

Example: `event CG2023 AS1 /from Thursday 2pm /to 6pm` creates an event task with the description `CG2023 AS1`, with 
start time `Thursday 2pm` and end time `6pm`.

### List all tasks: `list`
Display all tasks currently in the to-do list, in the order they were added.

Format: `list`


### Mark a task as done: `mark`
Marks the task with the given index as done. The index must be an integer from 1 to the size of the to-do list.

Format: `mark <index>`

Example: `mark 1` marks the task at index 1 of the to-do list as done.

### Mark a task as undone: `unmark`

Marks the task with the given index as undone. The index must be an integer from 1 to the size of the to-do list.

Format: `unmark <index>`

Example: `unmark 1` marks the task at index 1 of the to-do list as undone.

### Delete a task: `delete`
Deletes the task at the given index. The index must be an integer from 1 to the size of the to-do list.

Format: `delete <index>`

Example: `delete 3` deletes the task at index 3 of the to-do list.

### Find tasks with keywords: `find`
Displays all tasks currently in the to-do list whose description contain the given keywords.

Format: `find <keywords string>`
- The search is case-sensitive. e.g `CS2113` will not match `cs2113`
- Tasks matching the keyword string will be listed. e.g. `CS2113` will make Duke list tasks `CS2113 IP` and `CS2113 TP`.

Example: `find CS2113` lists all tasks with `CS2113` in their description.


### Exit the program: `bye`
Closes Duke.

Format: `bye`