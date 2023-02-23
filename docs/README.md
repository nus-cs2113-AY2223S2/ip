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
**ToDos**: tasks without any date/time attached to it <span style="font-weight:400px">e.g. return book</span>
**Format**: `todo TASK`
**Example**:
    Input:
    ```agsl
    todo return book
    ```
    Output: 
    ```agsl
    ____________________________________________________________
      Got it. I've added this task:
        [T][ ]return book
      Now you have 8 tasks in the list.
    ____________________________________________________________
    ```

### Add event task
**Events**: tasks that start at a specific date/time and ends at a specific date/time <span style="font-weight:400px">e.g. team project meeting 2/10/2019 2-4pm</span>
**Format**: `event TASK /from START /to END`
**Example**:
    Input:
    ```agsl
    event project meeting /from Monday 2pm /to 4pm
    ```
    Output:
    ```agsl
    ____________________________________________________________
      Got it. I've added this task:
        [E][ ]project meeting (from: Monday 2pm to: 4pm)
      Now you have 10 tasks in the list.
    ____________________________________________________________
    ```

### Add deadline task
**DeadLines**: tasks that need to be done before a specific date/time  <span style="font-weight:400px">e.g. submit report by 11/10/2019 5pm</span>
**Format**: `deadline TASK /by YYYY-MM-DD`
**Example**:
    Input:
    ```agsl
    deadline submit report /by 2023-02-23
    ```
    Output:
    ```agsl
    ____________________________________________________________
    Got it. I've added this task:
    [D][ ]submit report (by: Feb 23 2023)
    Now you have 9 tasks in the list.
    ____________________________________________________________
    ```

### View tasks
**List**: View all the tasks in the list.
**Format**: `list`
**Example**:
    Input:
    ```agsl
    deadline submit report /by 2023-02-23
    ```
    Output:
    ```agsl
    ____________________________________________________________
      Here are the tasks in your list:
        1.[T][ ]return book
        2.[D][ ]submit report (by: Feb 23 2023)
        3.[E][ ]project meeting (from: Monday 2pm to: 4pm)
    ____________________________________________________________
    ```

### Update task status
Mark a task as done or change the status back to not done([]). 
Completed task is represented by [X] 
Uncompleted task is represented by [ ]
**Format**: `mark TASK_NUMBER`
**Example**:
    Input:
    ```agsl
    mark 1
    ```
    Output:
    ```agsl
    ____________________________________________________________
    Nice! I've marked this task as done:
    [T][X]return book
    ____________________________________________________________
    ```
**Format**: `unmark TASK_NUMBER`
**Example**:
    Input:
    ```agsl
    mark 1
    ```
    Output:
    ```agsl
    ____________________________________________________________
      OK, I've marked this task as not done yet:
        [T][ ]return book
    ____________________________________________________________
    ```

### Delete tasks
Delete a task from the list.
**Format**: `delete TASK_NUMBER`
**Example**:
    Input:
    ```agsl
    delete 1
    ```
    Output:
    ```agsl
    ____________________________________________________________
      Noted! I've removed this task:c
        [T][ ]return book
      Now you have 2 tasks in the list.
    ____________________________________________________________
    ```
   
### Search tasks
Search tasks using keywords or search deadlines using date
Delete a task from the list.
**Format**: `find KEYWORD`
**Example**:
    Input:
    ```agsl
    find report
    ```
    Output:
    ```agsl
    ____________________________________________________________
    Here are the matching tasks in your list:
    1.[D][X]submit report (by: Feb 23 2023)
    ____________________________________________________________
    ```
**Format**: `date DATE`
**Example**:
    Input:
    ```agsl
    date 2023-02-23
    ```
    Output:
    ```agsl
    ____________________________________________________________
    Here are the matching tasks in your list:
    1.[D][X]submit report (by: Feb 23 2023)
    ____________________________________________________________
    ```

### Exit program
**Format**: `bye`
**Example**:
    Input:
    ```agsl
    bye
    ```
    Output:
    ```agsl
    ____________________________________________________________
       Bye. Hope to see you again soon!
    ____________________________________________________________
    ```

## Trouble Shooting
### Unknown commands
Example:
    Input:
    ```agsl
    hello
    ```
    Output:
    ```agsl
    ____________________________________________________________
    OOPS!!! I'm sorry, but I don't know what that means :-(
    ____________________________________________________________
    ```
### Update data if the list is empty
**Delete From Empty List**
Example: 
   Input:
    ```agsl
    delete 1
    ```
   Output:
    ```agsl
    ____________________________________________________________
    The list is empty, there is nothing to be deleted
    ____________________________________________________________
    ```
**Update empty list**
Example:
    Input:
    ```agsl
    mark 1
    ```
    Output:
    ```agsl
    ____________________________________________________________
    The list is empty, there is nothing to be updated
    ____________________________________________________________
    ```
### Missing parameters
**Missing todo task descriptions**
   Example:
   Input:
    ```agsl
    todo 
    ```
   Output:
    ```agsl
    ____________________________________________________________
    OOPS!!! The parameters cannot be empty.
    ____________________________________________________________
    ```
### Task index out of range
**Delete tasks that are out of task list**
   Example:
   Input:
    ```agsl
    delete 0
    ```
   Output:
    ```agsl
    ____________________________________________________________
    The element you are looking for does not exist!!
    ____________________________________________________________
    ```
### Wrong input format
**Input wrong date format(YYYY-MM-DD)**
    Example:
    Input:
    ```agsl
    deadline return book /by 23/02/2023
    ```
    Output:
    ```agsl
    ____________________________________________________________
    Please follow date format YYYY-MM-DD!!
    ____________________________________________________________
    ```

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