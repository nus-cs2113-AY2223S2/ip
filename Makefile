.SILENT: clean

all: compile

compile: ./src/wilsonoh/sagyo/**/*.java
	javac --release 11 -cp src/ ./src/wilsonoh/sagyo/Main.java

run: compile
	java -cp src/ wilsonoh.sagyo.Main

clean:
	fd -I -e class -xrm
