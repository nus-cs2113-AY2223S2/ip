# User Guide


## Contents
- [Quick Start](##Quick Start)
- [Features](##Features)
    - [Add a todo task](###Add a todo task)
    - [Add a deadline task](###Add a deadline task)
    - [Add an event task](###Add an event task)
    - [Mark a task as done](###Mark a task as done)
    - [Mark a task as not done](###Mark a task as not done)
    - [Delete a task](###Delete a task)
    - [List down all tasks and their statuses](###List down all tasks and their statuses)
    - [Find a task using keywords](###Find a task using keywords)
    - [Exit the program](###Exit the program)
    - [Saving tasks](##Saving tasks)

## Quick Start
1. Ensure that Java 11 is installed. This can be confirmed by entering `java --version` in the terminal.
2. Download the `Duke.jar` file from [here](https://github.com/ysl-28/ip/releases/tag/v0.2).
3. Move the `Duke.jar` file to one's preferred location.
4. Run the file with the command `java -jar Duke.jar`. If successful, the welcome message below should be shown.
5. Enter commands to Duke in the terminal.

Welcome message:
```
Hello from
 ____        _
|  _ \ _   _| | _____
| | | | | | | |/ / _ \
| |_| | |_| |   <  __/
|____/ \__,_|_|\_\___|

I'm Duke.
What can I do for you?
============================================
```

## Features 
Note that all commands are case-sensitive.
### Add a todo task
Adds a new todo task with a description.
- Format: `todo DESCRIPTION`

Example Input:
```
todo read book
```
Expected Output:
```
Got it. I've added this task:
[T][ ] read book
Now you have 1 task in the list.
============================================

```

### Add a deadline task

Adds a new deadline task consisting of the task description and duration.
- Format: `deadline DESCRIPTION /by DURATION`

Example Input:
```
deadline submit report /by 3pm
```

Expected Output:
```
Got it. I've added this task:
[D][ ] submit report (by: 3pm)
Now you have 1 task in the list.
============================================
```

### Add an event task
Adds a new event task consisting of the task description, start time and end time.
- Format: `event DESCRIPTION /from START TIME /to END TIME`

Example input:
```agsl
event eat lunch /from 1pm /to 3pm
```

Expected Output:
```
Got it. I've added this task:
[E][ ] eat lunch (from: 1pm to: 3pm)
Now you have 2 tasks in the list.
============================================
```

### Mark a task as done
Mark an existing task a done using its number in the task list.
- Format: `mark INTEGER`
- The provided integer must be in the range `1 <= INTEGER <= SIZE OF TASK LIST`


Example Input:
```
mark 2
```

Expected Output
```
Nice! I've marked this task as done:
[E][X] eat lunch (from: 1pm to: 3pm)
============================================
```
### Mark a task as not done
Mark an existing task as _not_ done using its number in the task list.
- Format: `unmark INTEGER`
- The provided integer must be in the range `1 <= INTEGER <= SIZE OF TASK LIST`

Example Input:
```
unmark 1
```
Expected Output
```
OK, I've marked this task as not done yet:
[T][ ] write report
============================================
```
### Delete a task
Removes a task from the list using its number in the task list.
- Format: `delete INTEGER`
- The provided integer must be in the range `1 <= INTEGER <= SIZE OF TASK LIST`

Example Input
```
delete 2
```

Expected Output
```
Noted. I've removed this task:
[D][ ] lab assignment (by: 6pm)
============================================
```
### List down all tasks and their statuses:
Allows the user to view all the tasks in the list.
- Format: `list`

Example Input
```
list
```

Expected Output
```
Here are the tasks in your list:
1.[T][ ] write report
2.[E][ ] project meeting (from: 5pm to: 6pm)
3.[D][X] submit assignment (by: Tuesday)
4.[E][X] CS2113 lecture (from: 1600 to: 1800)
============================================
```

### Find a task using keywords
- Format: `find KEYWORD`
- `KEYWORD` is case-sensitive.

Example Input
```
find CS2113
```

Expected Output
```
Here are the matching tasks in your list:
1.[E][X] CS2113 tutorial (from: 12pm to: 1pm)
2.[T][X] ip level 6 for CS2113
============================================
```
### Exit the program
- Format: `bye`

Example Input:
```
bye
```

Expected Output
```
Bye. Hope to see you again soon!
============================================
```

### Saving tasks
Tasks are saved under the file path `data/tasks.json` after any modification to the task list. No additional input is required from the user for saving tasks.

