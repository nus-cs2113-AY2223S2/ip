# User Guide

## Features 

### Add Task to task list

Users can choose to add `todo`, `deadline` or `event` tasks in to task list.

### Manage Task in task list

Users can `list` all the tasks, `find` some specific tasks, `mark` and `unmark` tasks as well as `delete` tasks that is no longer needed.

### Store task list data in a file

The task list data is stored in `duke.txt` file. The data is updated everytime a user make some changes to the task list.

## Usage

### `todo` - Add a Todo task
* Add a Todo task to the task list
* Format: `todo [description]`
* Example of usage: `todo do laundry`

### `deadline` - Add a Deadline task
* Add a Deadline task to the task list. Deadline task allows users to note their deadline in `YYYY-MM-DD` format or any `String` format.
* Format: `deadline [description] #by [YYYY-MM-DD]` or `deadline [description] #by [deadline]`
* Example of usage: `deadline CS2113 Quiz #by 2023-01-31` or
    `deadline CS2113 Quiz #by next Friday`

### `event` - Add an Event task

* Add an Event task to the task list. Event task allows users to note their start and end period. The start and end period can be any `String` input

* Format: `event [description] #from [start] #to [end]`

* Example of usage: `event workshop #from 4pm Monday #to 6pm Monday`

### `list` - List the tasks
* List all the tasks inside the task list

### `delete` - Delete a task
* Delete a task at a specific task number from the task list. The task number can be obtained from `list`.
* Format: `delete [task number]`
* Example of usage: `delete 1`

### `find` - Find tasks
* Find tasks from a specific keyword entered by a user. If there is no match task, it will not list anything out.
* Format: `find [task number]`
* Example of usage: `find laundry`

### `mark` - Mark a task
* Mark a specific task as done by the task number given from a user.
* Format: `mark [task number]`
* Example of usage: `mark 1`

### `unmark` - Unmark a task
* Unmark a specific task to not done yet by the task number given from a user.
* Format: `unmark [task number]`
* Example of usage: `find laundry`

### `bye` - Exit
* Exit and terminate the program.



