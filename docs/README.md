# User Guide
Duke is singer-user CLI productivity tool which aids users in tracking outstanding tasks.

## Features 

### Add tasks

Users are able to add tasks of the following types into their task list:
- todo: consists of a task description
- deadline: consists of a task description and deadline
- event: consists of a task description, start date and end date

### Delete tasks 

Users are able to remove a specified task from their task list
### Update completion status

Users can modify the completion status of their tasks
- mark: sets status of the task to be completed
- unmark: sets status of the task to be uncompleted

### Display tasks

- list: displays complete list comprising all tasks
- find: displays tasks that contains user's search keyword
## Usage

### `todo` - adds a todo Task to user's task list

Example of usage: 

`todo math quiz`

Expected outcome:

```
____________________________________________________________ 

added: math quiz
____________________________________________________________ 

```
Exceptions:

The following error message will be displayed if the user does not input a task description

```
____________________________________________________________ 

OOPS! The description of task cannot be empty
____________________________________________________________ 

```
### `deadline` - adds a deadline Task to user's task list

Example of usage:

`deadline submit math assignment /by 15 Jan`

Expected outcome:

```
____________________________________________________________ 

added: submit math assignmnet
____________________________________________________________ 

```
### `event` - adds an event Task to user's task list

Example of usage:

`event attend concert /from 5pm /to 9pm`

Expected outcome:

```
____________________________________________________________ 

added: attend concert 
____________________________________________________________ 

```
### `list` - displays all tasks in user's task list

Example of usage:

`list`

Expected outcome:

```
____________________________________________________________ 

1. [T][ ] math quiz
2. [D][ ] submit math assignment (by: 15 Jan)
3. [E][ ] attend concert (from: 5pm , to: 9pm)

You have 3 tasks in your list.
____________________________________________________________ 

```
### `find` - displays tasks in user's task list that contains the search keyword

Example of usage:

`find math`

Expected outcome:

```
____________________________________________________________ 

Here are the matching tasks in your list:
1. [T][ ] math quiz
2. [D][ ] submit math assignment  (by: 15 Jan)
____________________________________________________________ 

```
### `mark` - sets status of task that corresponds to the task index as completed

Example of usage:

`mark 2`

Expected outcome:

```
____________________________________________________________ 

Nice! I've marked this task as done:

[D][X] submit math assignment  (by: 15 Jan)
____________________________________________________________ 

```
### `unmark` - sets status of task that corresponds to the task index as uncompletedd

Example of usage:

`unmark 3`

Expected outcome:

```
____________________________________________________________ 

OK, I've marked this task as not done yet: 

[E][ ] attend concert (from: 5pm , to: 9pm)
____________________________________________________________ 

```
### `delete` - removes task from user's task list

Example of usage:

`delete 3`

Expected outcome:

```
____________________________________________________________ 

Noted. I've removed this task: 

[E][ ] attend concert (from: 5pm , to: 9pm)

Now you have 2 tasks in the list.
____________________________________________________________ 

```
### `bye` - terminates Duke

Example of usage:

`bye`

Expected outcome:

```
____________________________________________________________ 

Bye. Hope to see you again soon! 
____________________________________________________________ 

```