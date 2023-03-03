# User Guide

To Duke-king it out

## Introduction

Duke is a task tracker created to help Computer Engineering students who are constantly swarmed with assignments and projects, so much so that they cannot keep track of all their project meetings, assignment deadlines and exam dates.
Duke Dogstorm now offers to help you track your task in exchange for hiding a ninja in wano.

## Features

### Create Task

There are 3 types of task that can be created, a TODO, a DEADLINE and an EVENT.

Todos - task that u need to do, but maybe there is no rush to do it.(eg. Luffy wants to become the pirate king)

Deadline - task that u need to submit, so tells you by when you need to do it. (eg. Gecko moria needs to finish his fight before 3rd March 2023, 7.30am)

Event - task that is done over a certain duration, from a certain date and time to another date and time. (Straw Hat Crew needs to take train to enies lobby from 3rd March 2023 5pm to 3rd March 2023 7pm)

### List

List out all tasks that are currently in the system.

### Mark

Mark a task so you know that you have completed it.

### Unmark

Unmark a task so you can do it again, or maybe you actually have not completed it yet.

### Delete

Delete a task from the list.

### Find

Find a task based on the words and letters that the task description contains.

### Due

Check which tasks are due before a certain date time and which other tasks are due after.

### Save

Save your list so that you can access it the next time you boot up duke.

### bye

say goodbye to duke and close the program.

## Usage

### Todo

#### `todo <task>` - adds a todo

Example of usage:

`todo become pirate king`

Expected outcome:

The todo would be store in a temporary list.

Duke will confirm that you have added the task, and show you the task that you added, and the amount of tasks on the list.

```
Got it. I've added this task:
[T][ ] become the pirate king
Now you have 1 tasks in the list.
```

### Deadline

#### `deadline` - adds a deadline

#### `<description of task>` - adds the description of task

#### `<deadline of task>` - adds the deadline of task

Example of usage:

`deadline`
enter description:
`beat luffy up`  
enter deadline: eg: '2012-05-02T06:30' is equivalent May 02 2023 6.30am
`2023-03-03T07:30`

Expected outcome:

A task with a deadline is created and stored in the temporary list.

```
Got it. I've added this task:
[D][ ] beat luffy up (by: Mar 03 2023 07:30)
Now you have 2 tasks in the list.
```

### Event

#### `event` - adds a deadline

#### `<description of task>` - adds the description of task

#### `<start date time of task>` - adds the starting date/time of task

#### `<end date time of task>` - adds the ending date/time of task

Example of usage:

`event`
enter description:
`save ace`  
enter from datetime: eg: '2012-05-02T06:30' is equivalent May 02 2023 6.30am
`2023-03-03T07:30`
enter to datetime: eg: '2012-05-02T06:30' is equivalent May 02 2023 6.30am
`2023-03-03T12:00`

Expected outcome:

An event with starting and ending date time is created and stored in the temporary list.

```
Got it. I've added this task:
[E][ ] save ace (from: Mar 03 2023 07:30 to: Mar 03 2023 12:00)
Now you have 3 tasks in the list.
```

### List

#### `list` - command to show the elements of the temporary list

Example of usage:

`list`

Expected outcome:

Show all the elements in the temporary list.

```
list
Here are the tasks in your list:

1. [T][ ] become the pirate king
2. [D][ ] beat luffy up (by: Mar 03 2023 07:30)
3. [E][ ] save ace (from: Mar 03 2023 07:30 to: Mar 03 2023 12:00)

```

### Mark

#### `mark <index of selected task>` - mark a task

Example of usage:

`mark 1`

Expected outcome:

task with index of 1 is marked

```
list
Here are the tasks in your list:

1. [T][ ] become the pirate king
2. [D][ ] beat luffy up (by: Mar 03 2023 07:30)
3. [E][ ] save ace (from: Mar 03 2023 07:30 to: Mar 03 2023 12:00)

mark 1
Nice! I've marked this as done:
[T][X] become the pirate king

list
Here are the tasks in your list:

1. [T][X] become the pirate king
2. [D][ ] beat luffy up (by: Mar 03 2023 07:30)
3. [E][ ] save ace (from: Mar 03 2023 07:30 to: Mar 03 2023 12:00)
```

### Unmark

#### `unmark <index of selected task>` - unmark a task

Example of usage:

`unmark 1`

Expected outcome:

task with index of 1 is unmarked

```
list
Here are the tasks in your list:

1. [T][X] become the pirate king
2. [D][ ] beat luffy up (by: Mar 03 2023 07:30)
3. [E][ ] save ace (from: Mar 03 2023 07:30 to: Mar 03 2023 12:00)

unmark 1
Ok, I've marked this task as not done yet:
[T][ ] become the pirate king

list
Here are the tasks in your list:

1. [T][ ] become the pirate king
2. [D][ ] beat luffy up (by: Mar 03 2023 07:30)
3. [E][ ] save ace (from: Mar 03 2023 07:30 to: Mar 03 2023 12:00)
```

### Delete

#### `delete <index of selected task>` - delete a task

Example of usage:

`delete 2`

Expected outcome:

task with index of 2 is deleted

```
list
Here are the tasks in your list:

1. [T][ ] become the pirate king
2. [D][ ] beat luffy up (by: Mar 03 2023 07:30)
3. [E][ ] save ace (from: Mar 03 2023 07:30 to: Mar 03 2023 12:00)

delete 2
Noted. I've removed this task:
[D][] beat luffy up
Now you have 2 tasks in the list.

list
Here are the tasks in your list:

1. [T][ ] become the pirate king
2. [E][ ] save ace (from: Mar 03 2023 07:30 to: Mar 03 2023 12:00)


```

### Find

#### `find <keyword(s)>` - find task containing specific keyword(s)

Example of usage:

`find the`

Expected outcome:

task that contain the keyword will be shown

```
list
Here are the tasks in your list:

1. [T][ ] become the pirate king
2. [E][ ] save ace (from: Mar 03 2023 07:30 to: Mar 03 2023 12:00)

find the
1. [T][] become the pirate king
```

### Due

#### `due <chosen date-time>` - check what tasks are due before and after the chosen date-time

Example of usage:

`due 2023-03-03T08:00`

Expected outcome:

task due before 3rd march 2023 8am will show up above and tasks due after will show up below

```
list
Here are the tasks in your list:

1. [T][ ] become the pirate king
2. [E][ ] save ace (from: Mar 03 2023 07:30 to: Mar 03 2023 12:00)
3. [D][ ] take the bomb away from pel (by: Mar 03 2023 06:00)

due 2023-03-03T08:00
Due Before Mar 03 2023 08:00:
1. [D][] take the bomb away from pel (by: Mar 03 2023 06:00)

Due After Mar 03 2023 08:00:
1. [E][] save ace (from: Mar 03 2023 07:30 to: Mar 03 2023 12:00)
```

### Save

#### `save` - save the temporary list

Example of usage:

`save`

Expected outcome:

temporary list is saved into a data/duke.txt

```
list
Here are the tasks in your list:

1. [T][ ] become the pirate king
2. [E][ ] save ace (from: Mar 03 2023 07:30 to: Mar 03 2023 12:00)
3. [D][ ] take the bomb away from pel (by: Mar 03 2023 06:00)

save
File saved successfully
```

data/duke.txt

```
TODO | 0 | become the pirate king | null | null
EVENT | 0 | save ace | 2023-03-03T07:30 | 2023-03-03T12:00
DEADLINE | 0 | take the bomb away from pel | null | 2023-03-03T06:00
```

### Bye

#### `bye` - terminate the program

Example of usage:

`bye`

Expected outcome:

the program will close

```
bye
Bye. Hope to see you again soon!
```
