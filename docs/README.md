# User Guide

## Features 

### Feature-Add Todo

    [User] todo individual project

Duke will add the task to your task list.


### Feature-Add Deadline

    [User] deadline coursemology by 2023-01-01

Duke will add task with the deadline to your task list.


### Feature-Add Event
    [User] event tutorial from 2023-01-25 to 2023-04-07

Duke will add task with the starting and end date to your task list.


### Feature-List Tasks
    [User] list

Duke will list all the tasks in your task list.

### Feature-(Un)Mark Task
    [User] mark 1

Duke will mark the 1st task in your task list as done.

    [User] unmark 1

Duke will mark the 1st task in your task list as undone.


### Feature-Delete Task
    [User] delete 1

Duke will delete the 1st task in your task list.


### Feature-Find Task
    [User] find book

Duke will find tasks in your task list that contains the keyword.

### Feature-Exit
    [User] bye

Duke will say goodbye and finish the program.

## Usage

### `todo` - add todo

Add todo task to your task list.

Example of usage: 

`todo TASK`

Expected outcome:

Duke will confirm adding after successful addition.

```
[DUKE] Okay:) You've got one more task added: 
       [T][ ] ip
[DUKE] Now you have <5> tasks in the list.
```


### `deadline` - add deadline

Add task with the deadline to your task list

Example of usage:

`deadline TASK by YYYY-MM-DD`

Expected outcome:

Duke will confirm adding after successful addition.

```
[DUKE] Okay:) You've got one more task added: 
       [D][ ] ip / by 2023-02-03
[DUKE] Now you have <6> tasks in the list.
```

### `event` - add event

Add task with the starting and end date to your task list.

Example of usage:

`event TASK from YYYY-MM-DD to YYYY-MM-DD`

Expected outcome:

Duke will confirm adding after successful addition.

```
[DUKE] Okay:) You've got one more task added: 
       [E][ ] ip / from 2023-01-22 / to 2023-03-02
[DUKE] Now you have <7> tasks in the list.
```

### `list` - list tasks

List all the tasks in your task list.

Example of usage:

`list`

Expected outcome:

Duke will print tasks in the task list.

```
[DUKE] Here are the tasks in your list: 
1.[E][ ] ip1 / from 1월 2 2023 / to 1월 2 2023
2.[E][ ] ip2 / from 10월 30 2023 / to 11월 3 2023
3.[T][ ] ip3
4.[D][ ] ip4 / by 2월 3 2023
```

### `(Un)Mark` - Mark/Unmark task

Mark the targeted task in your task list as done/undone.

Example of usage:

`mark INT`, `unmark INT`

Expected outcome:

Duke will confirm marking/unmarking.

```
[DUKE] Good job! I marked this task as done: 
[T][O] ip
```

```
[DUKE] OK, I've marked this task as not done yet: 
[T][ ] ip
```

### `delete` - delete task

Delete the targeted task in your task list

Example of usage:

`delete INT`

Expected outcome:

Duke will confirm the deletion.

```
[DUKE] OK, I removed this task from the list: 
       [T][ ] ip
[DUKE] Now you have <3> tasks in the list.
```

### `find` - find tasks with the keyword

Find tasks in your task list that contains the keyword.

Example of usage:

`find KEYWORD`

Expected outcome:

Duke will show you the list of tasks containing the keyword.

```
[DUKE] Here are the matching tasks in your list: 
1.[D][ ] ip / by 2월 3 2023
```

### `bye` - exit program

Exit program.

Example of usage:

`bye`

Expected outcome:

Duke will end the program after saying goodbye.

```
[DUKE] Bye. Hope to see you again soon!
```