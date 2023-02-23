# User Guide

## Quick Start
1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest ip.jar.
3. Copy the file to the folder you want to use as the home folder for your Duke.
4. Open a command terminal, cd into the folder you put the jar file in, and use the `java -jar ip.jar` command to run the application.
5. An output similar to below will pop out on the terminal. Now Duke has started running. 
   ```
    ____________________________________________________________
      Hello from
       ____        _
      |  _ \ _   _| | _____
      | | | | | | | |/ / _ \
      | |_| | |_| |   <  __/
      |____/ \__,_|_|\_\___|
    ____________________________________________________________
      Hello! I'm Duke, your task assistant.
      Nice to meet you :D
      What can I do for you?
    ____________________________________________________________
   ```
   
## Features 

### Add todo task
**ToDos**: tasks without any date/time attached to it <span style="font-weight:400px">e.g. return book</span>\
**Format**: `todo TASK`\
**Example**:\
&nbsp;&nbsp;&nbsp;&nbsp;Input:
    ```
    todo return book
    ```\
&nbsp;&nbsp;&nbsp;&nbsp;Output:

    ____________________________________________________________
      Got it. I've added this task:
        [T][ ]return book
      Now you have 8 tasks in the list.
    ____________________________________________________________
    

### Add event task
**Events**: tasks that start at a specific date/time and ends at a specific date/time <span style="font-weight:400px">e.g. team project meeting 2/10/2019 2-4pm</span>\
**Format**: `event TASK /from START /to END`\
**Example**:\
&nbsp;&nbsp;&nbsp;&nbsp;Input:
    ```
    event project meeting /from Monday 2pm /to 4pm
    ```\
&nbsp;&nbsp;&nbsp;&nbsp;Output:

    ____________________________________________________________
      Got it. I've added this task:
        [E][ ]project meeting (from: Monday 2pm to: 4pm)
      Now you have 10 tasks in the list.
    ____________________________________________________________


### Add deadline task
**DeadLines**: tasks that need to be done before a specific date/time  <span style="font-weight:200px">e.g. submit report by 11/10/2019 5pm</span>\
**Format**: `deadline TASK /by YYYY-MM-DD`\
**Example**:\
&nbsp;&nbsp;&nbsp;&nbsp;Input:
    ```
    deadline submit report /by 2023-02-23
    ```\
&nbsp;&nbsp;&nbsp;&nbsp;Output:

    ____________________________________________________________
    Got it. I've added this task:
    [D][ ]submit report (by: Feb 23 2023)
    Now you have 9 tasks in the list.
    ____________________________________________________________

### View tasks
**List**: View all the tasks in the list.\
**Format**: `list`\
**Example**:\
&nbsp;&nbsp;&nbsp;&nbsp;Input:
    ```
    deadline submit report /by 2023-02-23
    ```\
&nbsp;&nbsp;&nbsp;&nbsp;Output:

    ____________________________________________________________
      Here are the tasks in your list:
        1.[T][ ]return book
        2.[D][ ]submit report (by: Feb 23 2023)
        3.[E][ ]project meeting (from: Monday 2pm to: 4pm)
    ____________________________________________________________

### Update task status
Mark a task as done or change the status back to not done([]). \
Completed task is represented by [X] \
Uncompleted task is represented by [ ]\
**Format**: `mark TASK_NUMBER`\
**Example**:\
&nbsp;&nbsp;&nbsp;&nbsp;Input:
    ```
    mark 1
    ```\
&nbsp;&nbsp;&nbsp;&nbsp;Output:

    ____________________________________________________________
    Nice! I've marked this task as done:
    [T][X]return book
    ____________________________________________________________

**Format**: `unmark TASK_NUMBER`\
**Example**:\
&nbsp;&nbsp;&nbsp;&nbsp;Input:
    ```
    mark 1
    ```\
&nbsp;&nbsp;&nbsp;&nbsp;Output:

    ____________________________________________________________
      OK, I've marked this task as not done yet:
        [T][ ]return book
    ____________________________________________________________

### Delete tasks
Delete a task from the list.\
**Format**: `delete TASK_NUMBER`\
**Example**:\
&nbsp;&nbsp;&nbsp;&nbsp;Input:
    ```
    delete 1
    ```\
&nbsp;&nbsp;&nbsp;&nbsp;Output:

    ____________________________________________________________
      Noted! I've removed this task:c
        [T][ ]return book
      Now you have 2 tasks in the list.
    ____________________________________________________________
   
### Search tasks
Search tasks using keywords or search deadlines using date\
Delete a task from the list.\
**Format**: `find KEYWORD`\
**Example**:\
&nbsp;&nbsp;&nbsp;&nbsp;Input:
    ```
    find report
    ```\
&nbsp;&nbsp;&nbsp;&nbsp;Output:

    ____________________________________________________________
    Here are the matching tasks in your list:
    1.[D][X]submit report (by: Feb 23 2023)
    ____________________________________________________________

**Format**: `date DATE`\
**Example**:\
&nbsp;&nbsp;&nbsp;&nbsp;Input:
    ```
    date 2023-02-23
    ```\`
&nbsp;&nbsp;&nbsp;&nbsp;Output:\
    
    ____________________________________________________________
    Here are the matching tasks in your list:
    1.[D][X]submit report (by: Feb 23 2023)
    ____________________________________________________________

### Exit program
**Format**: `bye`\
**Example**:\
&nbsp;&nbsp;&nbsp;&nbsp;Input:
    ```
    bye
    ```\
&nbsp;&nbsp;&nbsp;&nbsp;Output:
  
    ____________________________________________________________
       Bye. Hope to see you again soon!
    ____________________________________________________________

## Trouble Shooting
### Unknown commands
Example:\
&nbsp;&nbsp;&nbsp;&nbsp;Input:
    ```
    hello
    ```\
&nbsp;&nbsp;&nbsp;&nbsp;Output:
    
    ____________________________________________________________
    OOPS!!! I'm sorry, but I don't know what that means :-(
    ____________________________________________________________
    
### Update data if the list is empty
**Delete From Empty List**\
&nbsp;&nbsp;&nbsp;&nbsp;Example:\
&nbsp;&nbsp;&nbsp;&nbsp;Input:
    ```
    delete 1
    ```\
&nbsp;&nbsp;&nbsp;&nbsp;Output:
    
    ____________________________________________________________
    The list is empty, there is nothing to be deleted
    ____________________________________________________________
    
**Update empty list**\
&nbsp;&nbsp;&nbsp;&nbsp;Example:\
&nbsp;&nbsp;&nbsp;&nbsp;Input:
    ```
    mark 1
    ```\
&nbsp;&nbsp;&nbsp;&nbsp;Output:
    
    ____________________________________________________________
    The list is empty, there is nothing to be updated
    ____________________________________________________________
    
### Missing parameters
**Missing todo task descriptions**\
&nbsp;&nbsp;&nbsp;&nbsp;Example:\
&nbsp;&nbsp;&nbsp;&nbsp;Input:
    ```
    todo 
    ```\
&nbsp;&nbsp;&nbsp;&nbsp;Output:
    
    ____________________________________________________________
    OOPS!!! The parameters cannot be empty.
    ____________________________________________________________
    
### Task index out of range
**Delete tasks that are out of task list**\
&nbsp;&nbsp;&nbsp;&nbsp;Example:\
&nbsp;&nbsp;&nbsp;&nbsp;Input:
    ```
    delete 0
    ```\
&nbsp;&nbsp;&nbsp;&nbsp;Output:

    ____________________________________________________________
    The element you are looking for does not exist!!
    ____________________________________________________________
    
### Wrong input format
**Input wrong date format(YYYY-MM-DD)**\
&nbsp;&nbsp;&nbsp;&nbsp;Example:\
&nbsp;&nbsp;&nbsp;&nbsp;Input:
    ```
    deadline return book /by 23/02/2023
    ```\
&nbsp;&nbsp;&nbsp;&nbsp;Output:
  
    ____________________________________________________________
    Please follow date format YYYY-MM-DD!!
    ____________________________________________________________


## Command Summary
    
| Action                  | Commands                         |
|-------------------------|----------------------------------|
| Add todo task           | `todo TASK`                      |
| Add deadline task       | `event TASK /from START /to END` |
| Add event task          | `deadline TASK /by YYYY-MM-DD`   |
| View tasks              | `list`                           |
| Mark as done            | `mark TASK_NUMBER`               |
| Mark as not done        | `unmark TASK_NUMBER`             |
| Find task using keyword | `find KEYWORD`                   |
| Find task using date    | `date DATE`                      |
| Delete task             | `delete TASK_NUMBER`             |
| Exit program            | `bye`                            |

