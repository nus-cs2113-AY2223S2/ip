# User Guide

## Running Duke
* Download the latest release for ``ip.jar`` from [GitHub](https://github.com/NicholasChungJunJie/ip/releases)
* Open command prompt
* Go to the directory containing ``ip.jar``<br>
  ```cd [path to directory]```
* Run the command <br>
  ```java -jar ip.jar```
* If you have successfully downloaded and run Duke, you should see
```
    ____________________________________________________________

     All Tasks loaded from memory successfully.
    ____________________________________________________________

    ____________________________________________________________
     Hello! I'm Duke
     What can I do for you?
    ____________________________________________________________
```
<hr>

# Features

## Add Tasks

Add various types of tasks to the task tracker.

Types of tasks:
1. Todo
2. Deadline
3. Event

## Usage

### Todo
### `todo` - Adds a todo

Adds a todo task to the task tracker.

Example of usage:

`todo (arguments)`
```
todo read book
``` 

Expected outcome:

The todo task is added to the list.

```
    ____________________________________________________________

     Got it. I've added this task:
       [T][ ] read book
     Now you have 1 task in the list.
    ____________________________________________________________
```
<br>

### Deadline
### `deadline` - Adds a todo

Adds a deadline to the task tracker.

Example of usage:

`deadline (arguments) /by (deadline)`
```
deadline return book /by June 6th
``` 

Expected outcome:

The deadline is added to the list.

```
    ____________________________________________________________

     Got it. I've added this task:
       [D][ ] return book (by: June 6th)
     Now you have 2 tasks in the list.
    ____________________________________________________________
```
<br>

### Event
### `event` - Adds a todo

Adds an event to the task tracker.

Example of usage:

`event (arguments) /from (start) /to (end)`
```
event project meeting /from August 10th 2pm /to 4pm
``` 

Expected outcome:

The event is added to the list.

```
    ____________________________________________________________

     Got it. I've added this task:
       [E][ ] project meeting (from: August 10th 2pm to: 4pm)
     Now you have 3 tasks in the list.
    ____________________________________________________________
```


<hr>


## Mark or un-mark tasks

Marks tasks as done or un-marks tasks as not done.

## Usage

### Mark
### `mark` - mark as done

Marks the specified 1-th index task as done.

Example of usage:

`mark (1-th index task number)`
```
mark 1
``` 

Expected outcome:

The task is marked as done.

```
    ____________________________________________________________

     Nice! I've marked this task as done:

       [X] read book
    ____________________________________________________________
```
<br>

### Un-mark
### `unmark` - mark as not done

Marks the specified 1-th index task as not done.

Example of usage:

`unmark (1-th index task number)`
```
unmark 2
``` 

Expected outcome:

The task is marked as done.

```
    ____________________________________________________________

     OK, I've marked this task as not done yet:

       [ ] read book
    ____________________________________________________________
```
<hr>

## List all tasks

List all tasks in the tracker with its respective status and type.


## Usage

### list
### `list` 

Prints all tasks to terminal.

Example of usage:

`list`
```
list
``` 

Expected outcome:

All tasks are printed with its corresponding status and type.

```
    ____________________________________________________________

     Here are the tasks in your list:

     1.[T][X] read book
     2.[D][ ] return book (by: June 6th)
     3.[E][ ] project meeting (from: August 10th 2pm to: 4pm)
    ____________________________________________________________
```

#### Explanation of output
* tasks are prepended with [ _type of task_ ][ _status of task_ ]

> Example from the above output: <br>
> * [T][X] implies task is a todo and has been marked done
> * [D][&nbsp; ] implies task is a deadline and is not done
> * [E][&nbsp; ] implies task is an event and is not done

<hr>

## Delete tasks

Deletes tasks from list.


## Usage

### delete
### `delete`

Deletes the specified 1-th index task from the list.

Example of usage:

`delete (1-index task number)`
```
delete 2
``` 

Expected outcome:

Task 2 is deleted from the list.

```
    ____________________________________________________________

     Noted. I've removed this task:
       [D][ ] return book (by: June 6th)
     Now you have 2 tasks in the list.
    ____________________________________________________________
```
<hr>

## Find tasks

Find a task by searching for a keyword.

## Usage

### find
### `find`

Finds all tasks that contains the keyword.

Example of usage:

`find (keyword)`
```
find meeting
``` 

Expected outcome:

All tasks that contains the keywords is found and printed with its respective index number.

```
    ____________________________________________________________

     Here are the matching tasks in your list:
     2.[E][ ] project meeting (from: August 10th 2pm to: 4pm)
    ____________________________________________________________

```
<hr>

<!-- Template
# Features

## Add Tasks

Add various types of tasks to the task tracker

Types of tasks:
1. Todo
2. Deadline
3. Event

## Usage

### Todo
### `todo` - Adds a todo

Adds a todo task to the task tracker

Example of usage:

`todo (arguments)`
```
todo read book
``` 

Expected outcome:

The todo task is added to the list

```
    ____________________________________________________________

     Got it. I've added this task:
       [T][ ] read book
     Now you have 1 task in the list.
    ____________________________________________________________
```
<hr>
-->