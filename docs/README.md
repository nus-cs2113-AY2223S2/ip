# User Guide

#### [© 2023 Nicholas Chung.](https://chungnicholas.com) 

# Content page

* [1. Installation](#1-installation)
* [2. Summary of features](#2-summary-of-features)
* [3. Features](#3-features)
  * [3.1 Add Tasks](#31-add-tasks)
    * [3.1.1 Add Todo](#311-add-todo)
    * [3.1.2 Add Deadline](#312-add-deadline)
    * [3.1.3 Add Event](#313-add-event)
  * [3.2 Mark and un-mark tasks](#32-mark-and-un-mark-tasks)
    * [3.2.1 Mark tasks](#321-mark-task)
    * [3.2.2 Un-mark tasks](#322-un-mark-task)
  * [3.3 List all tasks](#33-list-all-tasks)
  * [3.4 Delete tasks](#34-delete-tasks)
  * [3.5 Find tasks](#35-find-tasks)
  * [3.6 Exit the program](#36-exit-the-program)

# 1. Installation
1. Install Java 11 on your device.
1. Download the latest release for ``ip.jar`` from [GitHub](https://github.com/NicholasChungJunJie/ip/releases).
1. Open command prompt.
1. Go to the directory containing ``ip.jar``<br>
  ```cd [path to directory]```
1. Run the command <br>
  ```java -jar ip.jar```
1. If you have successfully downloaded and run Duke, you should see
    ````
        ____________________________________________________________
    
         All Tasks loaded from memory successfully.
        ____________________________________________________________
    
        ____________________________________________________________
         Hello! I'm Duke
         What can I do for you?
        ____________________________________________________________
    ````
1. A directory ``/data`` containing a file ``duke.txt`` will be created in the current working directory. 
   1. This file will be used to store tasks to memory and retrieved on every run. 
   1. Please do not modify or delete it to prevent losing your saved data.
1. To exit the program, use the command ``bye``. This will ensure all tasks are saved to storage for retrieval on the next run.

[_Back To Top_](#user-guide)

<hr>


# 2. Summary of features

#### This is a summary. 
#### You may find the full explanation of feature below in the [``Features``](#3-features) section.

| Command  | Syntax                                   | Example                                             |
|----------|------------------------------------------|-----------------------------------------------------|
| todo     | ``todo <task>``                          | ``todo read book``                                  |
| deadline | ``deadline <task> /by <deadline>``       | ``deadline return book /by June 6th``               |
| event    | ``event <task> /from <start> /to <end>`` | ``event project meeting /from May 1st 2pm /to 4pm`` |
| mark     | ``mark <index>``                         | ``mark 1``                                          |
| unmark   | ``unmark <index>``                       | ``unmark 1``                                        |  
| list     | ``list``                                 | ``list``                                            |
| delete   | ``delete <index>``                       | ``delete 2``                                        |
| find     | ``find <keyword>``                       | ``find meeting``                                    |

[_Back To Top_](#user-guide)

<hr>


# 3. Features

## 3.1 Add Tasks

Adds various types of tasks to the task tracker.

### 3.1.1 Add Todo

Adds a todo task to the list.

Syntax: ``todo <task>``

Example of usage: ``todo read book``

Expected outcome:
```
    ____________________________________________________________

     Got it. I've added this task:
       [T][ ] read book
     Now you have 1 task in the list.
    ____________________________________________________________
```

<br>

### 3.1.2 Add Deadline

Adds a deadline to the list.

Syntax: ``deadline <task> /by <deadline>``

Example of usage: ``deadline return book /by June 6th``

Expected outcome:
```
    ____________________________________________________________

     Got it. I've added this task:
       [D][ ] return book (by: June 6th)
     Now you have 2 tasks in the list.
    ____________________________________________________________
```

<br>

### 3.1.3 Add Event

Adds an event to the list.

Syntax: ``event <task> /from <start> /to <end>``

Example of usage: ``event project meeting /from May 1st 2pm /to 4pm``

Expected outcome:
```
    ____________________________________________________________

     Got it. I've added this task:
       [E][ ] project meeting (from: May 1st 2pm to: 4pm)
     Now you have 3 tasks in the list.
    ____________________________________________________________
```
[_Back To Top_](#user-guide)

<hr>


## 3.2 Mark and un-mark tasks

Marks tasks as done or not done.

### 3.2.1 Mark task

Marks task as done.

Syntax: ``mark <index>``

Example of usage: ``mark 1``

Expected outcome:
```
    ____________________________________________________________

     Nice! I've marked this task as done:

       [X] read book
    ____________________________________________________________
```

<br>

### 3.2.2 Un-mark task

Marks task as not done

Syntax: ``unmark <index>``

Example of usage: ``unmark 1``

Expected outcome:
```
    ____________________________________________________________

     OK, I've marked this task as not done yet:

       [ ] read book
    ____________________________________________________________
```
[_Back To Top_](#user-guide)

<hr>


## 3.3 List all tasks

List all tasks in the tracker with its respective status and type.

Any additional arguments after the command `list` will be **ignored**.

Syntax: ``list``

Example of usage: ``list``

Expected outcome:
```
    ____________________________________________________________

     Here are the tasks in your list:

     1.[T][X] read book
     2.[D][ ] return book (by: June 6th)
     3.[E][ ] project meeting (from: August 10th 2pm to: 4pm)
    ____________________________________________________________
```

#### Explanation of output
* Tasks are prepended with [ _type of task_ ][ _status of task_ ]

> Example from the above output: <br>
> * [T][X] implies task is a todo and has been marked done
> * [D][&nbsp; ] implies task is a deadline and is not done
> * [E][&nbsp; ] implies task is an event and is not done

[_Back To Top_](#user-guide)

<hr>


## 3.4 Delete tasks

Delete a task from the list.

Syntax: ``delete <index>``

Example of usage: ``delete 2``

Expected outcome:
```
    ____________________________________________________________

     Noted. I've removed this task:
       [D][ ] return book (by: June 6th)
     Now you have 2 tasks in the list.
    ____________________________________________________________
```
[_Back To Top_](#user-guide)

<hr>


## 3.5 Find tasks

Find all related tasks by searching for a keyword.

Syntax: ``find <keyword>``

Example of usage: ``find meeting``

Expected outcome:
```
    ____________________________________________________________

     Here are the matching tasks in your list:
     2.[E][ ] project meeting (from: August 10th 2pm to: 4pm)
    ____________________________________________________________
```
[_Back To Top_](#user-guide)

<hr>


## 3.6 Exit the program

Stop and quit the program.
#### This is the recommended way to close the program to ensure that your data is saved to memory, and retrieved on the next run.

Syntax: ``bye``

Example of usage: ``bye``

Expected outcome:
```
    ____________________________________________________________

     Bye. Hope to see you again soon!
    ____________________________________________________________
```
[_Back To Top_](#user-guide)

<hr>

<br>
