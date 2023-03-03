# Duke User Guide

Duke is a personal task tracker used through CLI. Users can add three types of tasks to Duke, which are ToDos, Deadlines, and Events.
One can create, read, and delete tasks using according commands in the CLI.

## Features Available

### Adding task 
Available task types are: todo, deadline, event
One needs to enter task type, followed by task information

Examplary commands:
> todo eat lunch

> deadline submit assignment /by Sunday

> event weekend /from Saturday /to Sunday

Not following the command format will result in an error. The error message will guide the user with the command format. 



### Listing tasks
Duke shows the list of tasks that has been entered so far.

Examplary command:
> list



### Mark/Unmarking task 
The user can mark the task according to their completion state. Completed tasks can also be unmarked, reverting the task back to incomplete.
Users can enter the task index shown in the task list to target specific task. 

Examplary command:
> mark 1

> unmark 4

Not following the command format will result in an error. The error message will guide the user with the command format. 



### Delete task 
The user can delete tasks that are no longer relevant. Deletion of task is based on the task index provided by the user.

Examplary command:
> delete 2

Not following the command format will result in an error. The error message will guide the user with the command format. 



### Find task
Users can enter keyword to search for tasks that are relevant to the keyword entered. Note that only one word can be entered as keyword. 

Examplary command:
> find assignment

> find book

Not following the command format will result in an error. The error message will guide the user with the command format. 
