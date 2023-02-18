# Duke Project User Guide

_Duke_ is a Personal Assistant Chatbot that helps a person to keep track of various things.

<!-- TOC -->
* [Duke Project User Guide](#duke-project-user-guide)
  * [Quick Start](#quick-start)
  * [Features](#features)
    * [ADDING AN ENTRY:](#adding-an-entry-)
      * [There are 3 ways to add an entry depending on the type of your task](#there-are-3-ways-to-add-an-entry-depending-on-the-type-of-your-task)
        * [Todo Tasks:](#todo-tasks-)
        * [Deadline Tasks:](#deadline-tasks-)
        * [Event Tasks:](#event-tasks-)
    * [SHOW FULL LIST OF TASKS](#show-full-list-of-tasks)
    * [SHOW LIST OF TASKS THAT CONTAINS KEYWORD(S)](#show-list-of-tasks-that-contains-keyword--s-)
    * [MARK A TASK AS DONE](#mark-a-task-as-done)
    * [UNMARK A TASK](#unmark-a-task)
    * [DELETE AN ENTRY](#delete-an-entry)
    * [EXIT DUKE](#exit-duke)
  * [Command Summary](#command-summary)
<!-- TOC -->

## Quick Start

1. Ensure you have Java 11 or above installed on your Computer.
2. Update Intellij to the most recent version
3. Download the latest ChChing.jar from here.
4. Copy the file to the desired folder as the home folder for the program.
5. Open the command terminal, cd into the home folder of the program and use command java -jar ChChing.jar to run the application.

## Features

### ADDING AN ENTRY:
#### There are 3 ways to add an entry depending on the type of your task
##### Todo Tasks:
      todo [td/TASK DESCRIPTION]
      
      EXAMPLE: todo borrow book


##### Deadline Tasks:
      deadline [td/TASK DESCRIPTION] /by [d/DATE]

      EXAMPLE: deadline return book /by Sunday

##### Event Tasks:
      event [td/TASK DESCRIPTION] /from [sp/START PERIOD] /to [ep/END PERIOD]
      
      EXAMPLE: project meeting /from Mon 2pm /to 4pm

### SHOW FULL LIST OF TASKS
      list

### SHOW LIST OF TASKS THAT CONTAINS KEYWORD(S)
      find [kw/KEYWORDS]

      EXAMPLE: find book

### MARK A TASK AS DONE
      mark [tn/TASK NUMBER]
      
      EXAMPLE: mark 1

### UNMARK A TASK
      unmark [tn/TASK NUMBER]
      
      EXAMPLE: unmark 4

### DELETE AN ENTRY
    delete [tn/TASK NUMBER]
      
      EXAMPLE: delete 5

### EXIT DUKE
      bye

## Command Summary
![]("C:\Users\aviel\OneDrive - National University of Singapore\Pictures\Screenshots\Screenshot (145).png")










