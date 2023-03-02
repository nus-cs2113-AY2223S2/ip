# Psyduck User Guide

# Quick Start

1. Ensure you have Java 11 or above installed on your computer.
2. Download the latest version of Psyduck from [here](https://github.com/dendendenden04/ip/releases).
3. Move the file to a location where you want to run Psyduck from.
4. Use the following command to run the programme: `java -jar duke.jar`
6. If duke runs successfully, you will see the following welcome message
## Features 

Add tasks of 3 different types (To-do, Deadline, Event)

Remove a task from the to-do list.

Mark a task from the to-do list as done.

Unmark a task from the to-do list as not done.

List out all the tasks in the to-do list.

Find all the tasks in the list related to a keyword.


## Usage

### `todo` - Adds a to-do task.

Adds a to-do task to the list and shows the number of tasks currently in the list.

Example of usage: 

`todo Visit Mt.Moon`

Expected outcome:

The to-do task is added to the list. Shows the number of tasks currently in the list.

```
--------------------------------------------------------------------------------
Psyduck has added the task: [T][ ] Visit Mt.Moon
You now have: 1 task
--------------------------------------------------------------------------------
```
### `deadline` - Adds a deadline task.

Adds a deadline task to the list and shows the number of tasks currently in the list.

Example of usage:

`deadline Drink the juice in the fridge /by 19th February`

Expected outcome:

The deadline task is added to the list. Shows the number of tasks currently in the list.

```
--------------------------------------------------------------------------------
Psyduck has added the task: [D][ ] Drink the juice in the fridge  (by: 19th February)
You now have: 1 tasks
--------------------------------------------------------------------------------
```

### `event` - Adds an event task.

Adds an event task to the list and shows the number of tasks currently in the list.

Example of usage:

`event Festival at Celadon City /from Monday /to Saturday`

Expected outcome:

The event task is added to the list. Shows the number of tasks currently in the list.

```
--------------------------------------------------------------------------------
Psyduck has added the task: [E][ ] Festival at Celadon City  (from: Monday  to: Saturday)
You now have: 1 tasks
--------------------------------------------------------------------------------
```

### `event` - Adds an event task.

Adds an event task to the list and shows the number of tasks currently in the list.

Example of usage:

`event Festival at Celadon City /from Monday /to Saturday`

Expected outcome:

The event task is added to the list. Shows the number of tasks currently in the list.

```
--------------------------------------------------------------------------------
Psyduck has added the task: [E][ ] Festival at Celadon City  (from: Monday  to: Saturday)
You now have: 2 tasks
--------------------------------------------------------------------------------
```
### `list` - List out all the tasks.

Lists out all the tasks that are in the to-do list.

Example of usage:

`list`

Expected outcome:

The event task is added to the list. Shows the number of tasks currently in the list.

```
--------------------------------------------------------------------------------
Psyduck has added the task: [E][ ] Festival at Celadon City  (from: Monday  to: Saturday)
You now have: 3 tasks
--------------------------------------------------------------------------------
```