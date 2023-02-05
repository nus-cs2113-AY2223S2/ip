# Archduke

This is a school project for [CS2113 AY2022–23 Semester 2](https://nus-cs2113-ay2223s2.github.io/website/admin/ip-overview.html).

Current output (as of Level 8):

```
╭──────────────────────────────────────────────────────────────────────────────╮
│                                   __        __      __                       │
│                  ____ ___________/ /_  ____/ /_  __/ /_____                  │
│                 / __ `/ ___/ ___/ __ \/ __  / / / / //_/ _ \                 │
│                / /_/ / /  / /__/ / / / /_/ / /_/ / ,< /  __/                 │
│                \__,_/_/   \___/_/ /_/\__,_/\__,_/_/|_|\___/                  │
│                                                                              │
│ Hello! I'm Archduke. What do you want to do?                                 │
╰──────────────────────────────────────────────────────────────────────────────╯

> invalid-command
╭──────────────────────────────────────────────────────────────────────────────╮
│ ERROR: Unknown command: "invalid-command". Please try again.                 │
╰──────────────────────────────────────────────────────────────────────────────╯

> list
╭──────────────────────────────────────────────────────────────────────────────╮
│ Here are your tasks:                                                         │
│ You have 0 task(s) in the list.                                              │
╰──────────────────────────────────────────────────────────────────────────────╯

> todo read tenten kakumei
╭──────────────────────────────────────────────────────────────────────────────╮
│ Added task:                                                                  │
│   T □ read tenten kakumei                                                    │
│ You now have 1 task(s) in the list.                                          │
╰──────────────────────────────────────────────────────────────────────────────╯

> list
╭──────────────────────────────────────────────────────────────────────────────╮
│ Here are your tasks:                                                         │
│   T □ read tenten kakumei                                                    │
│ You have 1 task(s) in the list.                                              │
╰──────────────────────────────────────────────────────────────────────────────╯

> deadline watch tenten kakumei #5 /by 08/02/2023 21:00:00
╭──────────────────────────────────────────────────────────────────────────────╮
│ Added task:                                                                  │
│   D □ watch tenten kakumei #5 (by: Feb 8 2023, 9:00:00 pm)                   │
│ You now have 2 task(s) in the list.                                          │
╰──────────────────────────────────────────────────────────────────────────────╯

> event tenten kakumei #6 /from 08/02/2023 21:00:00 /to 08/02/2023 21:30:00
╭──────────────────────────────────────────────────────────────────────────────╮
│ Added task:                                                                  │
│   E □ tenten kakumei #6 (from: Feb 8 2023, 9:00:00 pm; to: Feb 8 2023,       │
│ 9:30:00 pm)                                                                  │
│ You now have 3 task(s) in the list.                                          │
╰──────────────────────────────────────────────────────────────────────────────╯

> mark 1
╭──────────────────────────────────────────────────────────────────────────────╮
│ The following task has been marked as done                                   │
│   T ■ read tenten kakumei                                                    │
╰──────────────────────────────────────────────────────────────────────────────╯

> mark 2
╭──────────────────────────────────────────────────────────────────────────────╮
│ The following task has been marked as done                                   │
│   D ■ watch tenten kakumei #5 (by: Feb 8 2023, 9:00:00 pm)                   │
╰──────────────────────────────────────────────────────────────────────────────╯

> unmark 1
╭──────────────────────────────────────────────────────────────────────────────╮
│ The following task has been marked as undone                                 │
│   T □ read tenten kakumei                                                    │
╰──────────────────────────────────────────────────────────────────────────────╯

> list
╭──────────────────────────────────────────────────────────────────────────────╮
│ Here are your tasks:                                                         │
│   T □ read tenten kakumei                                                    │
│   D ■ watch tenten kakumei #5 (by: Feb 8 2023, 9:00:00 pm)                   │
│   E □ tenten kakumei #6 (from: Feb 8 2023, 9:00:00 pm; to: Feb 8 2023,       │
│ 9:30:00 pm)                                                                  │
│ You have 3 task(s) in the list.                                              │
╰──────────────────────────────────────────────────────────────────────────────╯

> delete 1
╭──────────────────────────────────────────────────────────────────────────────╮
│ The following task has been deleted:                                         │
│   T □ read tenten kakumei                                                    │
│ You now have 2 task(s) in the list.                                          │
╰──────────────────────────────────────────────────────────────────────────────╯

> list
╭──────────────────────────────────────────────────────────────────────────────╮
│ Here are your tasks:                                                         │
│   D ■ watch tenten kakumei #5 (by: Feb 8 2023, 9:00:00 pm)                   │
│   E □ tenten kakumei #6 (from: Feb 8 2023, 9:00:00 pm; to: Feb 8 2023,       │
│ 9:30:00 pm)                                                                  │
│ You have 2 task(s) in the list.                                              │
╰──────────────────────────────────────────────────────────────────────────────╯

> bye
╭──────────────────────────────────────────────────────────────────────────────╮
│ Bye. Hope to see you again soon!                                             │
╰──────────────────────────────────────────────────────────────────────────────╯
```
