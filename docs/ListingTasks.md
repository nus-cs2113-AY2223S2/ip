# Displaying Tasks

- Ability to list all current tasks
- Sort and display tasks according to deadline

## Usage

### `list`, `ls` - lists all current tasks

Lists all the tasks stored

Example of usage:

`list`

Expected outcome:

You should see a successful output showing the
all the tasks in the list with the total number of tasks
currently in the list as shown:

```
Here are the tasks in your list:
1. [T][ ] buy milk
2. [E][ ] CS2113 Exam (from: 24-02-2023 1500 to: 24-02-2023 1700)
3. [D][ ] submit CS2113 assignmnet (by: 24-02-2023 1700)
4. [D][ ] review PRs (by: 25-02-2023 1200)
5. [D][ ] buy flour (by: 22-02-2023 1500)
```

### `list-sorted`, `ls-s` - sorts and prints the list without saving to hard disk

Sorts the list and prints the sorted list

Example of usage:

`ls-a`, `list-sorted`

Expected outcome:

You should see a successful output showing the
sorted list based on end time:

Try comparing with the list above!

```
Sorting the list for you...
Here are the tasks in your list:
1. [T][ ] buy milk
2. [D][ ] buy flour (by: 22-02-2023 1500)
3. [E][ ] CS2113 Exam (from: 24-02-2023 1500 to: 24-02-2023 1700)
4. [D][ ] submit CS2113 assignmnet (by: 24-02-2023 1700)
5. [D][ ] review PRs (by: 25-02-2023 1200)
Now you have 5 tasks in the list.
```

### `sort` - sorts and prints the list and saves the sorted list to the hard disk

Sorts the list and prints the sorted list and saves the list

Example of usage:

`sort`

Expected outcome:

You should see a successful output showing the
sorted list based on end time:

```
Sorting and saving the list for you...
Here are the tasks in your list:
1. [T][ ] buy milk
2. [D][ ] buy flour (by: 22-02-2023 1500)
3. [E][ ] CS2113 Exam (from: 24-02-2023 1500 to: 24-02-2023 1700)
4. [D][ ] submit CS2113 assignmnet (by: 24-02-2023 1700)
5. [D][ ] review PRs (by: 25-02-2023 1200)
Now you have 5 tasks in the list.
```

Go [back](README.md)
