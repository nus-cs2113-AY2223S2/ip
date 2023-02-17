# Obama User Guide

Obama is a command line interface based task manager app.

## Setting up

Prerequisites: JDK 11,

1. Download Obama.jar into an empty folder
2. Open a terminal and `cd` to correct directory
3. Run the command `java -jar Obama.jar`.

## Features

1. List tasks
2. Add task to task list (Todo, Event, Deadline)
3. Mark tasks as completed or not
4. Remove tasks from list
5. Find task from term
6. Exit

Tasks will be automatically saved to the CSV file whenever changes are being made to the task manager.

## Usage

### List All Tasks

Displays all tasks that are currently in the list.

Format: `list`

Sample command:

```
list
________________________________________________
1. [T][X] eat
________________________________________________
```

### Add Tasks To List

#### Add Todo

Adds a todo.

Format: `todo <description>`

Sample command:

```
todo eat
____________________________________________________________
Got it. I've added this task:
[T][ ] eat

Now you have 1 tasks in the list.
____________________________________________________________
```

#### Add Deadline

Adds a deadline.

Format: `deadline <description> /by <deadline>`

Sample command:

```
deadline drink /by later
____________________________________________________________
Got it. I've added this task:
  [D][ ] drink (by: later)
Now you have 2 tasks in the list.
____________________________________________________________
```

#### Add Event

Adds a event.

Format: `event <description> /from <string> /to <string>`

Sample command:

```
event sleep /from 12am /to 11pm
____________________________________________________________
Got it. I've added this task:
  [E][ ] sleep (from: 12am to: 11pm)
Now you have 2 tasks in the list.
____________________________________________________________
```

### Mark Tasks

#### Mark done

Marks a task as completed.

Format: `mark <index>`

Sample command:

```
mark 1
____________________________________________________________
Nice! I've marked this task as done:
  [T][X] eat
____________________________________________________________
list
____________________________________________________________
1.[T][X] eat
2.[D][ ] drink (by: later)
3.[E][ ] sleep (from: 12am to: 11pm)
____________________________________________________________
```

#### Mark not done

Marks a task as not done.

Format: `unmark <index>`

Sample command:

```
unmark 1
____________________________________________________________
OK, I've marked this task as not done yet:
  [T][ ] eat
____________________________________________________________
list
____________________________________________________________
1.[T][ ] eat
2.[D][ ] drink (by: later)
3.[E][ ] sleep (from: 12am to: 11pm)
____________________________________________________________
```

### Find Task

Displays all tasks based on .

Format: `find <keyword>`

Sample command:

```
find ea
____________________________________________________________
Here are the matching tasks in your list:
1. [T][ ] eat
____________________________________________________________
find muthu
____________________________________________________________
There are no matching tasks found.
____________________________________________________________
```

### Remove

Removes a task.

Format: `remove <index>`

Sample command:

```
remove 1
____________________________________________________________
Noted. I've removed this task:
[T][ ] eat

Now you have 0 tasks in the list.
____________________________________________________________
```

### Exit Obama

Exits Obama

Format: `bye`

Sample command:

```
bye
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```
