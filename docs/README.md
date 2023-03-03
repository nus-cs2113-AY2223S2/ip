# User Guide for Duke

## Features of Duke
1. [Add a Task into the List](#add-a-task-into-the-list)
2. [Removing a Task from the List](#removing-a-task-from-the-list)
3. [Changing the Status of a Task](#changing-the-status-of-a-task)
4. [View all Tasks in the List](#view-all-tasks-in-the-list)
5. [Finding a Task in the List](#finding-a-task-in-the-list)
6. [Exiting Duke](#exiting-duke)

### Add a Task into the List
1. ``todo``
- this command allows users to add a task that does not specify a time or date

#### Format of Input

```
todo buy bread
```

#### Expected Output:
```
Got it. I've added this task:
[T][ ] buy bread
You currently have 1 tasks in your list.
```

2. ``deadline``
- This command allows users to add a task that needs to be done before a specific date or time

#### Format of Input

```
deadline finish tuna sandwich /by monday
```

#### Expected Output:
```
Got it. I've added this task:
[D][ ] finish tuna sandwich (by: monday)
You currently have 1 tasks in your list.
```

3. ``event``
- This command allows users to add a task that starts and ends at a specific date or time

#### Format of Input

```
event family dinner at joyden /from 6pm /to 9pm
```

#### Expected Output:
```
Got it. I've added this task:
[E][ ] family dinner at joyden (from: 6pm to: 9pm)
You currently have 1 tasks in your list.
```

### Removing a Task from the List
``delete``
- This command allows users to delete a task from the list
- The number after the command is the index of the task in the list

#### Format of Input

```
delete 3
```

#### Expected Output:
```
Noted. I've removed this task:
[T][ ] sell fridge
You currently have 2 tasks in your list.
```

### Changing the Status of a Task 
1. ``mark``
- This command allows users to mark a task as done
- The number after the command is the index of the task in the list
- The task on the list will be updated from `[ ]` to `[X]`

#### Format of Input

```
mark 1
```

#### Expected Output:
```
Nice! I've marked this task as done:
Here are the tasks in your list:
1. [T][X] pump petrol
```

2. ``unmark``
- This command allows users to mark a task as not done
- The number after the command is the index of the task in the list
- The task on the list will be updated from `[X]` to `[ ]`

#### Format of Input

```
unmark 1
```

#### Expected Output:
```
OK, I've marked this task as not done yet:
Here are the tasks in your list:
1. [T][ ] pump petrol
```

### View all Tasks in the List
``list``
- This command allows users to see all tasks in their list
  - Task of type `todo` will be displayed: `[T][ ] TASK_NAME`
  - Task of type `deadline` will be displayed: `[D][ ] TASK_NAME (by: DATE)`
  - Task of type `event` will be displayed: `[E][ ] TASK_NAME (from: START_DATE to: END_DATE)`

#### Format of Input

```
list
```

#### Expected Output:
```
Here are the tasks in your list:
1. [T][ ] paint a portrait of a horse
2. [D][ ] buy christmas presents (by: christmas)
3. [T][X] decorate christmas tree
```

### Finding a Task in the List
``find``
- This command allows users to see all tasks that contains the keyword in their list

#### Format of Input

```
find beef
```

#### Expected Output:
```
Here are the matching tasks in your list:
1. [D][ ] cook beef shank (by: friday)
2. [E][ ] beef noodle party (from: 2am to: 5am)
```

### Exiting Duke
``bye``
- This command allows users to exit the programme

#### Format of Input

```
bye
```

#### Expected Output:
```
Bye! Hope to see you again soon!
```
