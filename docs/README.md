# Duke Project User Guide

_Duke_ is a Personal Assistant Chatbot that helps a person to keep track of various things.

<!-- TOC -->
* [Duke Project User Guide](#duke-project-user-guide)
  * [Quick Start](#quick-start)
  * [Features](#features)
  * [Notes about command format:](#notes-about-command-format-)
    * [ADD AN ENTRY: ```todo``` ```deadline``` ```event```](#add-an-entry--todo-deadline-event)
      * [3 ways to add an entry depending on the type of your task](#there-are-3-ways-to-add-an-entry-depending-on-the-type-of-your-task)
        * [Todo Tasks: ```todo```](#todo-tasks--todo)
        * [Deadline Tasks: ```deadline```](#deadline-tasks--deadline)
        * [Event Tasks: ```event```](#event-tasks--event)
    * [SHOW FULL LIST OF TASKS: ```list```](#show-full-list-of-tasks--list)
    * [SHOW LIST OF TASKS THAT CONTAINS KEYWORD(S): ```find```](#show-list-of-tasks-that-contains-keyword--s---find)
    * [MARK A TASK AS DONE: ```mark```](#mark-a-task-as-done--mark)
    * [UNMARK A TASK: ```unmark```](#unmark-a-task--unmark)
    * [DELETE AN ENTRY: ```delete```](#delete-an-entry--delete)
    * [EXIT DUKE: ```bye```](#exit-duke--bye)
  * [Command Summary](#command-summary)
<!-- TOC -->

## Quick Start

1. Ensure you have Java 11 or above installed on your Computer.
2. Update Intellij to the most recent version
3. Download the latest ip.jar from here.
4. Copy the file to the desired folder as the home folder for the program.
5. Open the command terminal, cd into the home folder of the program and use command java -jar ip.jar to run the application.

## Features

## Notes about command format:

1. Words in UPPER_CASE are the parameters to be supplied by the user.
2. TASK_NUMBER **must be a positive integer** 1,2,3,...


### ADD AN ENTRY: ```todo``` ```deadline``` ```event```
#### There are 3 ways to add an entry depending on the type of your task
##### Todo Tasks: ```todo```
      todo TASK_DESCRIPTION
      
      EXAMPLE: todo borrow book

Expected Outcome:<br>

```Got it. I've added this task:``` <br/>
```[T][ ] borrow book``` <br/>
```Now you have 7 tasks in the list.```



##### Deadline Tasks: ```deadline```
      deadline TASK_DESCRIPTION /by DATE

      EXAMPLE: deadline return book /by Sunday

Expected Outcome:<br>

```Got it. I've added this task:``` <br/>
```[D][ ] return book (by: Sunday)``` <br/>
```Now you have 7 tasks in the list.```

##### Event Tasks: ```event```
      event TASK_DESCRIPTION /from START_PERIOD /to END_PERIOD
      
      EXAMPLE: event project meeting /from Mon 2pm /to 4pm

Expected Outcome:<br>

```Got it. I've added this task:``` <br/>
```[E][ ] project meeting (from: Mon 2pm to: 4pm)``` <br/>
```Now you have 7 tasks in the list.```


### SHOW FULL LIST OF TASKS: ```list```
    Shows a list of all tasks
    Example: list
Expected Outcome:<br>

```Here are the tasks in your list:``` <br/>
```1.[T][X] read book```<br/>
```2.[D][X] return book (by: June 6th)```<br/>
```3.[E][X] project meeting (from: Aug 6th 2 to: 4pm)```<br/>
```4.[T][ ] join sports club```<br/>
```5.[D][X] return book (by: Sunday)```



### SHOW LIST OF TASKS THAT CONTAINS KEYWORD(S): ```find```
      Shows a list of all taks with the keyword(s) with their corresponding task number
      find KEYWORDS

      EXAMPLE: find book

Expected Outcome:<br>
```Here are the matching tasks in your list:```<br/>
```1.[T][X] read book```<br/>
```2.[D][X] return book (by: June 6th)```<br/>
```5.[D][X] return book (by: Sunday)```


### MARK A TASK AS DONE: ```mark```
      marks a task as done
      mark TASK_NUMBER
      
      EXAMPLE: mark 1

Expected Outcome:<br>
```Nice! I've marked this task as done:```<br/>
```[T][X] read book```

### UNMARK A TASK: ```unmark```
      unmarks a task
      unmark TASK_NUMBER
      
      EXAMPLE: unmark 4
Expected Outcome:<br>
```OK, I've marked this task as not done yet:```<br/>
```[T][ ] join sports club```

### DELETE AN ENTRY: ```delete```
    removes a task from the list
    delete TASK_NUMBER
      
      EXAMPLE: delete 5
Expected Outcome:<br>
```Noted. I've removed this task:```<br/>
```Now you have 7 tasks in the list.```


### EXIT DUKE: ```bye```
      exits the programme
      Example: bye

Expected Outcome:<br>
```Bye. Hope to see you again soon!```<br/>

## Command Summary

| Action            | Format, Examples                                                                                                      |
|-------------------|-----------------------------------------------------------------------------------------------------------------------|
| Add Todo task     | todo TASK_DESCRIPTION<br/>``` E.g. todo borrow book ```                                                               |
| Add Deadline task | deadline TASK_DESCRIPTION /by DATE<br/>``` E.g. deadline return book /by Sunday ```                                   |
| Add Event task    | event TASK_DESCRIPTION /from START_PERIOD /to END_PERIOD<br/>``` E.g. event project meeting /from Mon 2pm /to 4pm ``` |
| List              | list<br/>``` E.g. list ```                                                                                            |
| Find              | find KEYWORDS<br/>``` E.g. find book ```                                                                              |
| Mark task         | mark TASK_NUMBER<br/>``` E.g. mark 1 ```                                                                              |
| Unmark task       | unmark TASK_NUMBER<br/>``` E.g. unmark 4 ```                                                                          |
| Delete            | delete TASK_NUMBER<br/>``` E.g. delete 5 ```                                                                          |
| Exit              | bye<br/>``` E.g. bye ```                                                                                                 |










