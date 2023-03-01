# Duke: User Guide

Duke is a personal assistant chatbot that is designed to keep track of various tasks using a Command Line Interface.

## Quick start

```
Loading previously saved data...

Hi, I'm Duke!
What's up?
```

1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest `Duke.jar` from the Releases page.
3. Copy the file into the desired folder.
4. Using a command terminal, navigate into the folder, and run the command `java -jar Duke.jar`. You should see the above welcome message displayed. Note that Duke may load data from previously saved tasks first.
5. Type in a command and press Enter to execute it.

## Features 

### Command format

* Words in `UPPER_CASE` are parameters supplied by the user. e.g. in `todo TASK_DESCRIPTION`, `TASK_DESCRIPTION` is a parameter which can be used as `todo make coffee`.
* Parameters must be specified in the order indicated.

### Available commands

#### Adding a todo task: `todo`

Adds a todo task to the task list.

Format: `todo TASK_DESCRIPTION`

Examples:
* `todo homework`
* `todo make coffee`

#### Adding a deadline task: `deadline`

Adds a deadline task to the task list, which includes a specified end timing.

Format: `deadline TASK_DESCRIPTION /by END_TIMING`

Examples:
* `deadline assignment submission /by 2359`
* `deadline project work /by sometime next week`

#### Adding an event task: `event`

Adds an event task to the task list, which includes a specified start and end timing.

Format: `event TASK_DESCRIPTION /from START_TIMING /to END_TIMING`

Examples:
* `event lecture /from 1200 /to 1400`
* `event holidays /from today /to hopefully forever`

#### Listing all tasks: `list`

Lists all available tasks, including task from previous saved data which have not been deleted.

Format: `list`

Example output:
```
Here are your current tasks:
1.[T][ ] make coffee
2.[D][ ] assignment submission  (by: 2359)
3.[E][ ] lecture  (from: 1200  to: 1400)
```

#### Marking a task as done: `mark`

Marks a task as complete given its current index on the tasklist.

Format: `mark TASKLIST_INDEX`

Example:
* `mark 1`

#### Marking a task as not done: `unmark`

Marks a task as incomplete given its current index on the tasklist.

Format: `unmark TASKLIST_INDEX`

Example:
* `unmark 1`

#### Finding tasks: `find`

Finds all matching tasks given a keyword.

Format: `find KEYWORD`

Example input:
`find coffee`

Example output:
```
Here are the matching tasks in your list:
1.[T][ ] iced coffee
2.[T][ ] hot coffee
```

#### Deleting a task: `delete`

Deletes a task given its current index on the tasklist.

Format: `delete TASKLIST_INDEX`

Example input:
`delete 1`

Example output:
```
Got it! This task will be removed:
[T][ ] coffee
Number of tasks left: 2
```

#### Exiting the program: `bye`

Exits the program, and writes the remaining tasks into `./data/duke.txt` to be loaded the next time the program is opened.

Format: `bye`

Example output:
```
Bye! :D
```

## FAQ

Q: Is it possible to transfer data between devices?

A: Data from a device is saved under the folder `./data/duke.txt`. To access this data on another device, download `Duke.jar` on the new device, and place `duke.txt` in the same directory.