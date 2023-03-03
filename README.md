# Duke
Welcome to Duke! This is a user guide for the task tracker Duke.

## Installation
Requires: JDK 11
1. Download the jar file for Duke under "Releases"
2. Extract it to an empty folder
3. Run the jar file
4. Enjoy!

### Commands
Below are the list of commands that can be used with Duke  
Please remember to follow the input format strictly!

1. list || (to see the current tasks saved)
2. `todo <task>` || Adds a task with no particular time to take note of
3. `event <task> /from <start time of event> /to <end time of event>` || Adds an event with a start and end time
4. `deadline <task> /by <deadline of task>` || Adds a task with a deadline
5. `mark <task number>` || To mark a task as done
6. `unmark <task number>` || To mark a task as not done
7. `delete <task number>` || To remove a task from the list
8. `bye` || To terminate the programme

## Usage

Example of usage:

`event study /from 2pm /tp 4pm`

Expected outcome:
```
Got it. I've added this task: 
[E][ ] study (From: 2pm to To: 4pm)
Now you have 1 tasks in the list. 
____________________________________________________________

What would you like to do?
```
