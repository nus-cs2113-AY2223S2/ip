
#User Guide

Duke is a to-do list that serves as a reminder for you to keep track of the various types of tasks you may have.

##Features

Duke stores 3 types of tasks, todo, deadline and event.

Todo only requires the task description, deadline additionally requires the time the task is due, and event requires the start and the end timing of the event on top of the description.

##Todo list are automically saved when duke terminates

The list of tasks is always saved when closing duke, allowing the user to close duke and reaccess the task list in the future.

#Usage

###todo - Adds a todo task into the list

todo TASK_NAME

###### Example of usage:

`todo A-JavaDoc`

###### Expected outcome:

Adds a todo task with the label T to represent todo and an empty [ ] which indicates its status.

    ____________________________________________________________
    Added to list:
    [T][ ] A-JavaDoc
    Number of tasks: 1
    ____________________________________________________________


###`deadline` - Adds a deadline task into the list
deadline TASK_NAME /by DEADLINE

###### Example of usage:

`deadline Submit the final version /by Fri, Mar 3rd 2359`

###### Expected outcome:

Adds a deadline task with the label D to representing deadline and an empty [ ] which indicates its status.

    ____________________________________________________________
    Added to list:
    [D][ ] Submit the final version (by:Fri, Mar 3rd 2359)
    Number of tasks: 2
    ____________________________________________________________
### `event` - Adds an event into the list
event TASK_NAME /from START /to END.

###### Example of usage:

`event attend lecture /from 16:00 /to 18:00`

###### Expected outcome:

Adds an event into the list with an E to indicate event, [ ] indicates the status of the event.

    ____________________________________________________________
    Added to list:
    [E][ ] attend lecture (from: 16:00  to: 18:00)
    Number of tasks: 3
    ____________________________________________________________

##`list` - Lists all the tasks recorded
list

###### Example of usage:

`list`

###### Expected outcome:

Shows all the existing items in the list. In this test case, the 3 tasks added prior to calling the command list will be shown.

    ____________________________________________________________
    1.[T][ ] A-JavaDoc
    2.[D][ ] Submit the final version (by:Fri, Mar 3rd 2359)
    3.[E][ ] attend lecture (from: 16:00  to: 18:00)
    ____________________________________________________________

## `mark`- Mark specified task

mark TASK_INDEX

###### Example of usage:

`mark 2`

This marks the second item in the list as complete, indicated with a [X].

###### Expected outcome:

    ____________________________________________________________
    Nice! I've marked this task as done:
    [D][X] Submit the final version (by:Fri, Mar 3rd 2359)
    ____________________________________________________________

##`unmark` - Unmark specified task

unmark POS_OF_TASK

###### Example of usage:

`unmark 2`

This marks the second item in the list as incomplete, indicated with a [ ].

###### Expected outcome:

    ____________________________________________________________
        OK, I've marked this task as not done yet:
    [D][ ] Submit the final version (by:Fri, Mar 3rd 2359)
    ____________________________________________________________

## `find` - Find tasks that contains the keyword

find KEYWORD

###### Example of usage:

`find ice-skating`

###### Expected outcome:

Lists only the tasks containing the keyword in its TASK_NAME.

Here are the matches in your list!
    ____________________________________________________________
    2.[D][ ] Submit the final version (by:Fri, Mar 3rd 2359)
    3.[E][ ] attend the lecture (from: 16:00  to: 18:00)
    ____________________________________________________________

## `delete` - Delete task specified from duke

delete POS_OF_TASK

###### Example of usage:

`delete 3`

###### Expected outcome:

This deletes the third task in the list.

    ____________________________________________________________
    Deleting from list:
    [E][ ] attend the lecture (from: 16:00  to: 18:00)
    Number of tasks: 2
    ____________________________________________________________

## `bye` - Shuts down Duke
bye

###### Example of usage:

`bye`

###### Expected outcome:

Closes duke

    ____________________________________________________________
    Bye. Hope to see you again soon!
    ____________________________________________________________
