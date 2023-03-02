# Duke - A chatbot to keep track of tasks
## Description
Duke is a command line chatbot which keeps track of your todos, events, and deadlines

## Accepted commands
### `list`
Lists current tasks

### `mark [TASK NUMBER]`
Marks task at index [TASK NUMBER] as done

### `unmark [TASK NUMBER]`
Marks task at index [TASK NUMBER] as not done

### `todo [DESCRIPTION]`
Adds a todo to the task list with the description [DESCRIPTION]

### `deadline [DESCRIPTION] /by [DUE DATE]`
Adds a deadline to the task list with the description [DESCRIPTION] and the due date [DUE DATE]

### `event [DESCRIPTION] /from [START DATE] /to [END DATE]`
Adds an event to the task list with the description [DESCRIPTION], start date [START DATE] and end date [END DATE]

### `delete [TASK NUMBER]`
Deletes task at index [TASK NUMBER]

### `find [STRING]`
Prints list of tasks which contain the substring [STRING]

