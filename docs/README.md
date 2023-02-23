# User Guide

## Features

### Support 3 different types of Task

Flexible formats for users to input their tasks so that they can differentiate and keep track
of their tasks better through the visual aids

- todo task represented by [T]
    - The most flexible type of task with only a description of the Task
    - contains: `[DESCRIPTION]`
- deadline task represented by [D]
    - The type of task where there is a known deadline to be set e.g. a homework
    - contains: `[DESCRIPTION] & [DEADLINE]`
- event task represented by [E]
    - The type of task where there is a known starting time and ending time e.g. an exam
    - contains: `[DESCRIPTION] & [BEGIN] & [END]`

### Offline Database

- No Internet Connection required
- Allows users to save their tasks to their computer and continue their session another time

## Usage

### `todo` - Adds a todo task

Adds a todo task to the TaskList

Format:

```
todo [DESCRIPTION] 
```

Example of usage:

`todo homework`

Expected outcome:

- Adds a todo task to the TaskList
    - `COMMAND`: todo
    - `DESCRIPTION`: homework
- Shows a successful message to the user to check if he/she added the correct task
- Displays the amount of tasks (all 3 types added together) in the list currently

Description of the outcome.

```
____________________________________________________________
Got it. I've added this task:
  [T][ ] homework
Now you have 1 tasks in the list.
____________________________________________________________
```

****

### `deadline` - Adds a deadline task

Adds a deadline task to the TaskList

Format:

```
deadline [DESCRIPTION] /by [DEADLINE]
```

**" /by " format has to be followed or the program would not recognise the input**

Example of usage:

`deadline Math homework /by 2pm`

Expected outcome:

- Adds a deadline task to the TaskList
    - `COMMAND`: deadline
    - `DESCRIPTION`: Math homework
    - `DEADLINE`: 2pm
- Shows a successful message to the user to check if he/she added the correct task
- Displays the amount of tasks (all 3 types added together) in the list currently

Description of the outcome.

```
____________________________________________________________
Got it. I've added this task:
  [D][ ] Math homework (by: 2pm)
Now you have 1 tasks in the list.
____________________________________________________________
```

****

### `event` - Adds a event task

Adds an event task to the TaskList

Format:

```
event [DESCRIPTION] /from [BEGIN] /to [END] 
```

**" /from " and " /to " must be exactly in the above order or the program would not recognise the input**

Example of usage:

`event CS2113 Lecture /from 4pm /to 6pm`

Expected outcome:

- Adds an event task to the TaskList
    - `COMMAND`: event
    - `DESCRIPTION`: CS2113 Lecture
    - `BEGIN`: 4pm
    - `END`: 6pm
- Shows a successful message to the user to check if he/she added the correct task
- Displays the amount of tasks (all 3 types added together) in the list currently

Description of the outcome.

```
____________________________________________________________
Got it. I've added this task:
  [E][ ] CS2113 Lecture (from: 4pm to: 6pm)
Now you have 1 tasks in the list.
____________________________________________________________
```

****

### `list` - Lists all task

List all the tasks in the current TaskList saved by Duke

Format:

```
list
```

Example of usage:

`list`

Expected outcome:

- List all the tasks in the format of `[COMMAND][IS_MARKED] [TASK_DETAILS]`
- The tasks would be indexed which can be used later for deleting and marking

Description of the outcome (when there are tasks in the TaskList) :

```
____________________________________________________________
1.[E][ ] CS2113 Lecture (from: 4pm to: 6pm)
2.[T][ ] homework
3.[D][ ] Lab Report (by: Recess Week)
____________________________________________________________
```

****

### `mark` - Marks a task

Marks a task in the TaskList as completed

Format:

```
mark [TASK_INDEX] 
```

Example of usage:

`mark 1`

Expected outcome :

- Marks a task in the TaskList
- Shows a successful message to the user to check if he/she marked the correct task

Description of the outcome.

```
____________________________________________________________
Nice! I've marked this task as done:
  [E][X] CS2113 Lecture (from: 4pm to: 6pm)
____________________________________________________________
```

****

### `unmark` - Unmarks a task

Unmarks a task in the TaskList to show as not completed

Format:

```
unmark [TASK_INDEX] 
```

Example of usage :

`unmark 1`

Expected outcome:

- Unmarks a task in the TaskList
- Shows a successful message to the user to check if he/she unmarked the correct task

Description of the outcome :

```
____________________________________________________________
OK, I've marked this task as not done yet:
  [E][ ] CS2113 Lecture (from: 4pm to: 6pm)
____________________________________________________________
```

****

### `delete` - Deletes a task

Deletes a task in the TaskList completely based on index

Format:

```
delete [TASK_INDEX] 
```

Example of usage :

`delete 1`

Expected outcome:

- Deletes a task in the TaskList
- Shows a successful message to the user to check if he/she deleted the correct task
- Displays the remaining amount of tasks (all 3 types added together) in the list

Description of the outcome :

```
____________________________________________________________
Noted. I've removed this task:
  [E][ ] CS2113 Lecture (from: 4pm to: 6pm)
Now you have 2 tasks in the list.
____________________________________________________________
```

****

### `find` - Find tasks

Find tasks in the TaskList with similar keywords in their `DESCRIPTION`

Format:

```
find [KEYWORD] 
```

Example of usage :

`find home`

Expected outcome:

- Finds all tasks which description contains the string home
- Displays all the tasks in the TaskList that match the keyword with their index in the list

Description of the outcome :

```
____________________________________________________________
Here are the matching tasks in your list:
1.[T][ ] homework
____________________________________________________________
```

****

### `bye` - Exit

Program says goodbye and exits

Format:

```
bye
```

Example of usage :

`bye`

Expected outcome:

Program outputs a goodbye message to the user and the program terminates

Description of the outcome :

```
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```