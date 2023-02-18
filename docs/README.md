# User Guide

## Features 

### Feature-Track tasking

Allows user to track different types of task <ToDo, Deadline, Event> with datetime integration.

### Feature-XYZ

Description of the feature.

## Usage

### `list` - Describe action

Lists the task duke currently stores

Example of usage: 

`list`

Expected outcome:

Prints the tasks stored if there exist any. Else informs user that there is no task.

```
    _______________________________________
     1. [D][ ] return book (by: 2/12/2019 1800)
     2. [E][ ] return more books (from: 2/12/2019 2000 to: 2/12/2019 1800)
    ________________________________________
```

### `mark` - Describe action

Lists the task duke currently stores

Example of usage: 

`mark <Task Index>`

Expected outcome:

Marks the given task index.

```
    ________________________________________
     Nice! I've marked this task as done:
      [T][X] eat
    ________________________________________
```

### `unmark` - Describe action

Lists the task duke currently stores

Example of usage: 

`unmark <Task Index>`

Expected outcome:

Unmark the given task index.

```
    ________________________________________
     Nice! I've marked this task as done:
      [T][X] eat
    ________________________________________
```

### `delete` - Describe action

Lists the task duke currently stores

Example of usage: 

`delete <Task Index>`

Expected outcome:

delete the given task index.

```
    ________________________________________
     OK, I've marked this task as not done yet:
      [T][ ] eat
    ________________________________________
```

### `find` - Describe action

Finds task with matching keyword

Example of usage: 

`find <keyword>`

Expected outcome:

Find task that contains matching keyword in their description.

```
    ________________________________________
     1. [T][ ] eat
    ________________________________________
```

### `bye` - Describe action

Saves tasks and exit

Example of usage: 

`bye`

Expected outcome:

exits program

```
    ________________________________________
     Bye. Hope to see you again soon!
    ________________________________________
```