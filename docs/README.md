# User Guide for Mike

## Features 

### Add tasks

Add tasks in the form of to-dos, deadlines, events, and it will be tracked in your task list.

### Delete Tasks

Delete tasks from your list if you no longer want to track them.

### Mark/ Unmark Tasks

Mark tasks as completed when they are done or unmark them if they are uncompleted.

### Find Tasks

Search for tasks relevant to a keyword that you give.

### List Tasks

List out all the tasks that are currently being tracked in your list.
## Usage
Mike can only take in 1 of 9 specific commands below. To use the command, follow the input format and replace the description, date of deadline etc. accordingly to get your desired output.

Your output should look similar to the sample input provided under each command.
1. `todo`: creates a todo task and adds it to the task list.
    1. Input format: `todo description of task`
    2. Sample output:
       ```
       Got it. I've added this task:
       [T][ ] description of task
       Now you have 1 task(s) in the list.
       ```
2. `deadline`: creates a deadline and adds it to the task list.
    1. Input format: `deadline description of task /by date of deadline`
    2. Sample output:
       ```
       Got it. I've added this task:
       [D][ ] description of task (by: date of deadline)
       Now you have 2 task(s) in the list.
       ```
3. `event`: creates an event and adds it to the list.
    1. Input format: `event description of task /from start date of event /to end date of event`
    2. Sample output:
       ```
       Got it. I've added this task:
       [E][ ] description of task (from: start date of event to: end date of event)
       Now you have 3 task(s) in the list.
       ```
4. `list`: shows all the current tasks in the task list.
    1. Input format: `list`
    2. Sample output:
       ```
       Here are the task(s) in your list:
       1.[T][ ] description of task
       2.[D][ ] description of task (by: date of deadline)
       3.[E][ ] description of task (from: start date of event to: end date of event)
       You have a total of 3 task(s).
       ```
5. `mark`: marks a certain task as completed.
    1. Input format: `mark (task number)`
    2. Sample output:
       ```
       OK, I've marked this task as completed:
         [X] description of task
       ```
6. `unmark`: unmarks a certain completed task.
    1. Input format: `unmark (task number)`
    2. Sample output:
       ```
       OK, I've marked this task as not done yet:
         [ ] description of task
       ```
7. `find`: searches for tasks that contains a given keyword.
    1. Input format: `find (keyword)`
    2. Sample output:
       ```
       Here's the list of task(s) in your list that matches your keyword: 
       1.[T][ ] description of task
       2.[D][ ] description of task (by: date of deadline)
       3.[T][X] description of task
       ```
8. `delete`: delete a certain task from the task list.
    1. Input format: `delete (task number)`
    2. Sample output:
       ```
       Noted. I've removed this task
       3.[E][ ] description of task (from: start date of event to: end date of event)
       ```
9. `bye`: exits the program.
    1. Input format: `bye`
    2. Sample output:
       ```
       Good bye! Hope to see you again soon.
       ```
