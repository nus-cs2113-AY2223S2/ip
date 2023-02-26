# User Guide

## Features 
- There are 3 types of Task:
  - ToDo
  - Event
  - Deadline
- Other functions include: 
  - list
  - delete
  - mark
  - unmark
  - find
  - bye
---
## Task Types
### Add a <code>**todo**</code> task

Adds a todo to the task list.

Input format: `todo DESCRIPTION`

<u>Example of Usage</u>:

Input:

`todo CS2113 IP`

Output:
```
____________________________________________________________
Got it. I've added this task:
[T][ ] CS2113 IP
Now you have 1 task(s) in the list.
____________________________________________________________
```
---
### Add a <code>**deadline**</code> task

Adds a deadline to the task list.

Input format: `deadline DESCRIPTION /by TIME`

<u>Example of Usage</u>:

Input:

`deadline Get flowers /by 14th Feb`

Output:
```
____________________________________________________________
Got it. I've added this task:
[D][ ] Get flowers (by: 14th Feb)
Now you have 1 task(s) in the list.
____________________________________________________________
```
---
### Add an <code>**event**</code> task

Adds an event to the task list.

Input format: `event DESCRIPTION /from TIME /to TIME`

<u>Example of Usage</u>:

Input:

`event CG2023 Midterms, 2 Mar /from 4pm /to 6pm`

Output:

```
____________________________________________________________
Got it. I've added this task:
[E][ ] CG2023 Midterms, 2 Mar (from: 4pm to: 6pm)
Now you have 1 task(s) in the list.
____________________________________________________________
```
---
## Other Functions
### <code>**list**</code> tasks

Outputs all the tasks in your current task list.

<u>Example of Usage</u>:

Input:

`list`

Output:
```
____________________________________________________________
Here are the tasks in your list:

1. [T][ ] CS2113 IP
2. [E][ ] CG2023 Midterms, 2 Mar (from: 4pm to: 6pm)
3. [D][ ] Get flowers (by: 14th Feb)
____________________________________________________________
```
---
### <code>**delete**</code> a task
Deletes a task from the task list.

Input Format: `delete TASK_INDEX`

<u>Example of Usage</u>:

Input:

`delete 2`

Output:
```
____________________________________________________________
Noted. I've removed this task:
[E][ ] CG2023 Midterms, 2 Mar (from: 4pm to: 6pm)
Now you have 2 task(s) in the list
____________________________________________________________
```
---
### <code>**mark**</code> a task as done

Marks a task in the task list as done with an 'X'.

Input Format: `mark TASK_INDEX`

<u>Example of Usage</u>:

Input: 
`mark 1`

Output:
```
____________________________________________________________
Nice! I've marked this task as done:
[T][X] CS2113 IP
____________________________________________________________
```
---
### <code>**unmark**</code> a task as not done

Un-marks a task in the task list as not done.

Input Format: `unmark TASK_INDEX`

<u>Example of Usage</u>:

Input:
`unmark 1`

Output:
```
____________________________________________________________
OK, I've marked this task as not done yet:
[T][ ] CS2113 IP
____________________________________________________________
```
---
### <code>**find**</code> a task

Search for a task in the task list.

*Note that the search is case-sensitive.*

Input Format: `find DESCRIPTION`

<u>Example of Usage</u>:

Input:
`find IP`

Output:
```
____________________________________________________________
Here are the matching task(s) in your list:

1.[T][ ] CS2113 IP
____________________________________________________________
```
---
### Saying <code>**bye**</code>

Exits the program.

<u>Example of Usage</u>:

Input:
`bye`

Output:
```
Bye. Hope to see you again soon!
```