---
title: Adding Tasks
---

## Usage

### `todo [description]` - Creates a new todo task

Creates a new todo task and adds to the list

Example of usage:

`todo buy milk`

Expected outcome:

You should see a successful output with the total number of tasks
currently in the list as shown:

```
Got it! Added 
[T][ ]buy milk
to the list.
Now you have 1 task(s) in the list.
```

### `deadline [description] /by [due date]` - Creates a new deadline task

`[due date]` - Can be in any format, but to save as a date use dd-MM-yyyy HHmm
else it will default to current date and time

Creates a new deadline task and adds to the list

Example of usage:

- `deadline buy milk /by 22-02-2023 1500`

Expected outcome:

You should see a successful output with the total number of tasks
currently in the list as shown:

```
Got it! Added 
[D][ ] buy milk (by: 22-02-2023 1500)
to the list.
Now you have 2 task(s) in the list.
```

- `deadline buy milk /by dinner time`

Expected outcome:

You should see a successful output with the total number of tasks
currently in the list as shown, however since input is not a date a prompt
will be given:

```
Not a date and time, you can add a datetime with dd-MM-yyyy HHmm format
Got it! Added 
[D][ ] buy milk (by: dinner time)
to the list.
Now you have 2 task(s) in the list.
```

### `event [description] /from [start date] /to [end date]` - Creates a new event task

`[start date]`, `[end date]` - Can be in any format, but to save as a date use dd-MM-yyyy HHmm
else it will default to current date and time

Creates a new event task and adds to the list

Example of usage:

- `event lecture time /from 24-02-2023 1600 /to 24-02-2023 1800`

Expected outcome:

You should see a successful output with the total number of tasks
currently in the list as shown:

```
Got it! Added 
[E][ ] lecture time (from: 24-02-2023 1600 to: 24-02-2023 1800)
to the list.
Now you have 3 task(s) in the list.
```

- `event lecture time /from afternoon /to evening `

Expected outcome:

You should see a successful output with the total number of tasks
currently in the list as shown, however since input is not a date a prompt
will be given:

```
Not a date and time, you can add a datetime with dd-MM-yyyy HHmm format
Got it! Added 
[E][ ] lecture time (from: afternoon to: evening)
to the list.
Now you have 4 task(s) in the list.
```

Go [back](README.md)
