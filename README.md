# Duke 

```
   ____        _        
|  _ \ _   _| | _____ 
| | | | | | | |/ / _ \
| |_| | |_| |   <  __/
|____/ \__,_|_|\_\___|
```
Duke is a task managing application optimised for the Command-Line Interface.
It allows you to create different types of tasks, such as todos, deadlines and events. The user guide for this application can be found at https://jinxuan-owyong.github.io/ip/

## Getting Started

### Prerequisites
- Java `11` or above is required for this project
- Maven is used as the package manager

### Installation
```sh
git clone https://github.com/jinxuan-owyong/ip.git
cd ip
mvn install
```

## Development
### Executing the program
```sh
mvn clean compile exec:java -Dexec.mainClass="duke.Duke"
```

### Building JAR with dependencies
```sh
mvn clean compile assembly:single
```
