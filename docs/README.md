# User Guide

#### by [Ong Yuan](https://github.com/yuanners)


-------------
__Duke__ is a chat bot application created for tasks tracking, optimized for use via a Command Line Interface (CLI) while still having the 
benefits of a Graphical User Interface (GUI).
-------------
## Table of Contents

* [1. Quick start](#1-quick-start)
* [2. Features](#2-features)
  * [2.1 Add Tasks](#21-add-tasks)
    * [2.1.1 Add Todo](#211-add-todo)
    * [2.1.2 Add Deadline](#212-add-deadline)
    * [2.1.3 Add Event](#213-add-event)
  * [2.2 List tasks](#22-list-tasks)
  * [2.3 Change task status](#23-change-task-status)
    * [2.3.1 Mark task](#231-mark-task)
    * [2.3.2 Un-mark task](#232-un-mark-task)
  * [2.4 Delete task](#24-delete-task)
  * [2.5 Find tasks](#25-find-tasks)
  * [2.6 Exit program](#26-exit-program)
* [3. Summary of features](#3-summary-of-features)
-------------
# 1. Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest release of `ip.jar` from [here](https://github.com/yuanners/ip/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your Duke.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar ip.jar` command to run the application.<br>
   
1. A GUI similar to the below should appear in a few seconds.
```
   Hello from
 ____        _        
|  _ \ _   _| | _____
| | | | | | | |/ / _ \
| |_| | |_| |   <  __/
|____/ \__,_|_|\_\___|

____________________________________________________________
Hello! I'm Duke
What can I do for you?
____________________________________________________________
```
-------------
# 2. Features

## 2.1 Add tasks

Add different types of tasks by using different input formats.

## 2.1.1 Add Todo

Add a todo task.

Format: ``todo <task description>``

Example: ``todo homework``

Expected output:
```
File found. Reading file content:
Successfully wrote to the file.
____________________________________________________________
Got it. I've added this task:
[T][ ] homework
Now you have 1 task in the list.
____________________________________________________________
```

## 2.1.2 Add Deadline

Add a deadline task.

Format: ``deadline <task description> /by <deadline>``

Example: ``deadline assigment /by Mon 2pm``

Expected output:
```
File found. Reading file content:
Successfully wrote to the file.
____________________________________________________________
Got it. I've added this task:
[D][ ] assigment (by: Mon 2pm)
Now you have 2 tasks in the list.
____________________________________________________________
```
## 2.1.3 Add Event

Add a event task.

Format: ``event <task description> /from <start> /to <end>``

Example: ``event Attend class /from 4pm /to 6pm``

Expected output:
```
File found. Reading file content:
Successfully wrote to the file.
____________________________________________________________
Got it. I've added this task:
[E][ ] Attend class (from: 4pm to: 6pm)
Now you have 3 tasks in the list.
____________________________________________________________
```
-------------
## 2.2 List tasks

List all the tasks stored.

Format: ``list``

Expected output:
```
File found. Reading file content:
____________________________________________________________
Here are the tasks in your list:
1. [T][ ] homework
2. [D][ ] assignment (by: Mon 2pm)
3. [E][ ] Attend class (from: 4pm to: 6pm)
____________________________________________________________
```
-------------
## 2.3 Change task status
Change the task status by mark or unmark a task.
## 2.3.1 Mark task
Mark a task as completed using task number.

Format: ``mark <task number>``

Example: ``mark 1``

Expected output:
```
Successfully wrote to the file.
____________________________________________________________
Nice! I've marked this task as done:
[T][X] homework
____________________________________________________________
```
## 2.3.2 Un-mark task
Unmark a task as not completed using task number.

Format: ``unmark <task number>``

Example: ``unmark 1``

Expected output:
```
Successfully wrote to the file.
____________________________________________________________
Nice! I've marked this task as undone:
[T][ ] homework
____________________________________________________________
```
-------------
## 2.4 Delete task
Delete a task using task number.

Format: ``delete <task number>``

Example: ``delete 2``

Expected output:
```
____________________________________________________________
Noted. I've removed this task:
	D[ ] assignment(by:Mon 2pm)
Now you have 1 tasks in the list.
____________________________________________________________
Successfully wrote to the file.
```
-------------
## 2.5 Find tasks
Find tasks using keyword.

Format: ``find <keyword>``

Example: ``find book``

Expected output:
```
____________________________________________________________
Here are the matching tasks in your list:
1. [T][ ] read book
2. [D][ ] return book (by: Mon)
3. [E][ ] book club (from: 4pm to: 6pm)
____________________________________________________________
```
-------------
## 2.6 Exit program
Exit the program.

Format: ``bye``

Expected output:
```
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```
-------------
## 3. Summary of features
Here is the table of summary for all the features.

| Feature      | Format                                               | Example                                  |
|--------------|------------------------------------------------------|------------------------------------------|
| Add todo     | ``todo <task description>``                          | ``todo homework``                        |
| Add deadline | ``deadline <task description> /by <deadline>``       | ``deadline assignment /by Mon 2pm``      |
| Add event    | ``event <task descirption> /from <start> /to <end>`` | ``event attend class /from 4pm /to 6pm`` |
| Mark task    | ``mark task number>``                                | ``mark 1``                               |
| Unmark task  | ``unmark <task number>``                             | ``unmark 1``                             |  
| List tasks   | ``list``                                             | ``list``                                 |
| Delete tasks | ``delete <task number>``                             | ``delete 2``                             |
| Find tasks   | ``find <keyword>``                                   | ``find book``                            |
| Exit program | ``exit``                                             | ``exit``                                 |