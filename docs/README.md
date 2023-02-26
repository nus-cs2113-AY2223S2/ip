# Rolex: The Task Manager  🗓 ✅

Rolex is a Task Manager developed using Java. It can support a wide range of operations which is mentioned in this user guide.

Given below are the operations Rolex can provide:
1. [Add Task](#add_task)
    1. [Todo](#todo) 
    2. [Event](#event)
    3. [Deadline](#deadline)    
2. [Mark/Unmark Task](#mum_task)
    1. [Mark Task](#mtask)
    2. [Unmark Task](#untask)
3. [Delete Task](#dtask)
4. [List Task](#ltask)
5. [Find Task](#ftask)
6. [Exit](#bye)
--------------------------------------------------------------------
### NOTE:
* Users are required to enter inputs as mentioned in the ```Command``` section under each operation.
* Failure to comply to these rules will result in errors.
--------------------------------------------------------------------

# 1. Add Task <a name = "add_task"></a>

There are 3 types of tasks you can add.

This is how you should add a task.

## 1.1. Todo <a name = "todo"></a>
* Command: ```todo {description}```
* Example:

Input:
```
todo watch Real Madrid game
```
Output:
```
--------------------------------------------------
Got it. I've added this task:
[T][ ] watch Real Madrid game

Now you have 1 tasks in the list.
--------------------------------------------------
```

## 1.2. Event <a name = "event"></a>
* Command: ```event {description} \from {description} \to {description}```
* Example:

Input:
```
event project meeting /from 6pm /to 9pm
```
Output:
```
--------------------------------------------------
Got it. I've added this task:
[E][ ] project meeting (from: 6pm to: 9pm)

Now you have 1 tasks in the list.
--------------------------------------------------
```

## 1.3. Deadline <a name = "deadline"></a>
* Command: ```deadline {description} \by yyyy-mm-dd hhmm```
* Example:

Input:
```
deadline submit assignment \by 2023-04-01 2359
```
Output:
```
--------------------------------------------------
Got it. I've added this task:
[D][ ] submit assignment (by:1 APRIL 2023, SATURDAY, 11:59PM)

Now you have 1 tasks in the list.
--------------------------------------------------
```

# 2. Mark/Unmark Task <a name = "mum_task"></a>

This is how you should mark or unmark tasks.

## 2.1. Mark Task <a name="mtask"></a>
* Command: ```mark {task_index}```
* Example:

Input:
```
mark 1
```
Output:
```
--------------------------------------------------
Well Done. This task is marked as done:
[T][X] watch Real Madrid game
--------------------------------------------------

```

## 2.2. Unmark Task <a name="untask"></a>
* Command: ```unmark {task_index}```
* Example:

Input:
```
unmark 1
```
Output:
```
--------------------------------------------------
Oh no, I've unmarked this task as it is not done:
[T][ ] watch Real Madrid game
--------------------------------------------------

```

# 3. Delete Task <a name = "dtask"></a>

This is how you should delete a task.

* Command: ```delete {task_index}```
* Example:

Input:
```
delete 1
```
Output:
```
--------------------------------------------------
Noted. I've removed this task.
[T][ ] watch Real Madrid game

Now you have 0 tasks in the list.
--------------------------------------------------

```

# 4. List Task <a name = "ltask"></a>

This is how you shoud list tasks.

* Command: ```list```
* Example:

Input:
```
list
```
Output:
```
--------------------------------------------------
Here are the tasks in your list:
1. [T][ ] watch Real Madrid game
2. [D][ ] submit work (by:5 APRIL 2023, WEDNESDAY, 02:56PM)
--------------------------------------------------

```

# 5. Find Task <a name = "ftask"></a>

This is how you should find a particular task.

* Command: ```find {keyword}```
* Example:

Input:
```
find game
```
Output:
```
--------------------------------------------------
Here are the matching tasks in your list:
2. [T][ ] watch Real Madrid game
--------------------------------------------------
```

# 6. Exit <a name = "bye"></a>

This is how you should exit.

Input:
```
bye
```
Output:
```
--------------------------------------------------
Bye. Hope to see you again soon!
--------------------------------------------------
```

--------------------------------------------------------------------------------
Hope you have a good time using Rolex. 😁
--------------------------------------------------------------------------------
