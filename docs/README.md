# User Guide

## Features 


***Add***

You can add tasks to the list by specifying the type of task to be added, followed by the  details of the task.

***Delete***

You can delete tasks by specifying the task number in the list.

***Mark***

You can mark tasks as done or undone.

***List***

You can view the list of tasks at any time.

***Find***

You can find for specific tasks using keywords that match the task name.

## Usage




### `Todo` - add a Todo task to the list

The user can add Todo-type tasks to the list.
Todo-type tasks do not have deadlines.

Example of usage: `todo <task name>`


#### Expected outcome:

```
todo rent car
    _________________________________________
    added: rent car
    _________________________________________
    
    Now you have 1 tasks in your list!
```
#

### `Deadline` - add a Deadline task to the list

The user can add Deadline-type tasks to the list.
Deadline-type tasks have deadlines.
A '/' needs to be added to separate the task name and deadline.

Example of usage: `deadline <task name/deadline>`


#### Expected outcome:

```
deadline return car/4pm
    _________________________________________
    added: return car (by 4pm)
    _________________________________________
    
    Now you have 2 tasks in your list!
```
#

### `Event` - add a Event task to the list

The user can add Event-type tasks to the list.
Event-type tasks have start and end times.
A `/` needs to be added to separate the task name and start time.
A `|` also needs to be added to separate the start time and end time.


Example of usage: `event <task name/start time|end time>`


#### Expected outcome:

```
event topup petrol/4pm|6pm
    _________________________________________
    added: topup petrol (from: 4pm, to: 6pm)
    _________________________________________
    
    Now you have 3 tasks in your list!
```

#

### `list` - lists the tasks added to the list

When <code>list</code> is typed, the list of tasks will be displayed to the user.

Example of usage: `list`


#### Expected outcome:

If the list is empty,
```
list
    _________________________________________
    _________________________________________
```
if the list has tasks,

```
list
    _________________________________________
    1. [T][ ] rent car
    2. [D][ ] return car (by 4pm)
    3. [E][ ] topup petrol (from: 4pm, to: 6pm)
    _________________________________________
```

#

### `mark` - marks a task as done

The user can mark tasks in the list as "done".


Example of usage: `mark <task number>`


#### Expected outcome:

```
mark 1
    _________________________________________
    1.[X] rent car
    _________________________________________

```

#

### `unmark` - unmarks a task that was previously set as "done"

The user can unmark tasks that were previously marked.


Example of usage: `unmark <task number>`


#### Expected outcome:

```
unmark 1
    _________________________________________
    1.[ ] rent car
    _________________________________________
```
#

### `delete` - deletes a task from the list

The user can delete tasks from the list, one at a time.
The user needs to check the list to confirm the task number before deleting each time.



Example of usage: `delete <task number>`


#### Expected outcome:
When deleting a single task,
```
unmark 1
    _________________________________________
    1.[ ] rent car
    _________________________________________
```
When deleting multiple tasks,
```
list
    _________________________________________
    1. [D][ ] return car (by 4pm)
    2. [E][ ] topup petrol (from: 4pm, to: 6pm)
    3. [T][ ] rent car
    _________________________________________
     
delete 1
    _________________________________________
 [D][ ] return car (by 4pm)
    _________________________________________
    
list
    _________________________________________
    1. [E][ ] topup petrol (from: 4pm, to: 6pm)
    2. [T][ ] rent car
    _________________________________________
     
delete 1
    _________________________________________
 [E][ ] topup petrol (from: 4pm, to: 6pm)
    _________________________________________
```

#

### `find` - Returns tasks that match the keyword specified by the user

The user can search for tasks in the list by specifying a keyword.

Example of usage: `find <keyword>`


#### Expected outcome:

```
find car
    1. [T][ ] rent car
    2. [D][ ] return car (by 4pm)
```

#

### `bye` - Exits the program

The user can exit the program. Tasks in the list will be saved to disk automatically
and loaded automatically when program is launched again.

Example of usage: `bye`


#### Expected outcome:

```
bye
    Bye. Hope to see you again soon!
    _________________________________________
     

Process finished with exit code 0
```