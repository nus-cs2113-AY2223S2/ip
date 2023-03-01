# User Guide

## Feature

### Genesis

Introducing a comprehensive task management system that empowers users to efficiently manage their tasks with ease! Stay
on top of your to-dos, deadlines and events effortlessly.

With our system, you can effortlessly mark completed tasks as done, ensuring you stay on top of your progress and manage
your time efficiently.

You will be greeted with this:

```
               ('-.       .-') _   ('-.    .-')              .-')    
             _(  OO)     ( OO ) )_(  OO)  ( OO ).           ( OO ).  
  ,----.    (,------.,--./ ,--,'(,------.(_)---\_)  ,-.-') (_)---\_) 
 '  .-./-')  |  .---'|   \ |  |\ |  .---'/    _ |   |  |OO)/    _ |  
 |  |_( O- ) |  |    |    \|  | )|  |    \  :` `.   |  |  \\  :` `.  
 |  | .--, \(|  '--. |  .     |/(|  '--.  '..`''.)  |  |(_/ '..`''.) 
(|  | '. (_/ |  .--' |  |\    |  |  .--' .-._)   \ ,|  |_.'.-._)   \ 
 |  '--'  |  |  `---.|  | \   |  |  `---.\       /(_|  |   \       / 
  `------'   `------'`--'  `--'  `------' `-----'   `--'    `-----'  


____________________________________________________________

What can I do for you?

____________________________________________________________
```

### Auto save and load

Our system ensures that your tasks are always stored locally and synchronized in real-time, so you can seamlessly manage
your tasks with ease.

Any changes made to your tasks are automatically saved and updated, ensuring that you never lose any important
information.

If you've previously saved your tasks, our system will automatically load them back.

As a new user, you'll start with a clean slate.

```
____________________________________________________________

There is no existing task list found. Initializing new task list.
____________________________________________________________
```

As an existing user, you'll resume with your previous data will be loaded

```
____________________________________________________________

Task list loaded successfully.
____________________________________________________________
```

## Usage

### `todo` - Add todo

Add a todo task to the task list

Command: `todo <task_description>`

Example: `todo revise CS2133T`

Expected output:

```
____________________________________________________________

Got it. I've added this task:
[T][ ] revise CS2113T
Now you have 1 tasks in the list.
____________________________________________________________
```

### `deadline` - Add deadline

Add a deadline task to the task list

Command: `deadline <task_description> /by <date>`

Example: `deadline CS2133T assignment /by 3/3/2023 2359`

Expected output:

```
____________________________________________________________

Got it. I've added this task:
[D][ ] CS2133T assignment (by: 3/3/2023 2359)
Now you have 2 tasks in the list.
____________________________________________________________
```

### `event` - Add event

Add an event task to the task list

Command: `event <task_description> /from <start_date> /to <end_date>`

Example: `event CS2133T exam /from 3/5/2023 0900 /to 3/5/2023 1100`

Expected output:

```
____________________________________________________________

Got it. I've added this task:
[E][ ] CS2133T exam (from: 3/5/2023 0900 to: 3/5/2023 1100)
Now you have 3 tasks in the list.
____________________________________________________________
```

### `list` - List tasks

List out all the tasks in the task list

Command: `list`

Expected output:

```
____________________________________________________________

Here are the tasks in your list:
1. [T][ ] revise CS2113T
2. [D][ ] CS2133T assignment (by: 3/3/2023 2359)
3. [E][ ] CS2133T exam (from: 3/5/2023 0900 to: 3/5/2023 1100)
____________________________________________________________
```

### `mark` - Mark task as done

Mark a task as done in the task list according to the task index number

Task index number can be obtained from `list`

Command: `mark <index>`

Example: `mark 2`

Expected output:

```
____________________________________________________________

Nice! I've marked this task as done:
[D][X] CS2133T assignment (by: 3/3/2023 2359)
____________________________________________________________
```

### `unmark` - Mark task as not done

Mark a task as **not** done in the task list according to the task index number

Task index number can be obtained from `list`

Command: `unmark <index>`

Example: `unmark 2`

Expected output:

```
____________________________________________________________

Nice! I've marked this task as not done:
[D][ ] CS2133T assignment (by: 3/3/2023 2359)
____________________________________________________________
```

### `delete` - Delete a task from task list

Delete any task from task list according to the task index number

Task index number can be obtained from `list`

Command: `delete <index>`

Example: `delete 2`

Expected output:

```
____________________________________________________________

Noted. I've removed this task:
[D][ ] CS2133T assignment (by: 3/3/2023 2359)
Now you have 2 tasks in the list.
____________________________________________________________
```

### `find` - Find a task in the task list

Find any task from task list according to the provided keyword

Command: `find <keyword>`

Example: `find exam`

Expected output:

```
____________________________________________________________

Here are the matching tasks in your list:
2. [E][ ] CS2133T exam (from: 3/5/2023 0900 to: 3/5/2023 1100)
____________________________________________________________
```

### `exit` - Exit Genesis

Exit the application

Command: `bye`

Expected output:

```
Bye. Hope to see you again soon!
```

## Errors

```
☹ OOPS!!! The description cannot be empty.
```

For todo, deadline and event. Enter a valid description.

---

```
☹ OOPS!!! Description for deadline is invalid
```

```
☹ OOPS!!! Description for event is invalid
```

For deadline and event, description need to have the correct format

`deadline <task_description> /by <date>`

`event <task_description> /from <start_date> /to <end_date>`

---

```
☹ OOPS!!! Task index does not exist
```

Task index provided does not exist in the task list. Enter a valid task index number.

---

```
☹ OOPS!!! The keyword you searched does not match any of the entries
```

Task entry with the given keyword does not exist.

---

```
I'm sorry, but I don't know what that means :-(
Please use one of the predefined command
...
```

Command used not recognised. Use the correct predefined command provided.

---

```
Task list is corrupted. Initializing new task list.
```

Your locally stored data have been corrupted.
Therefore, all previously stored data has been lost. 

To get you back up and running, a new data store will automatically
be created for you.


---