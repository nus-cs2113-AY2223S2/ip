#!/bin/zsh
wd="$(cd "$(dirname "$0")" && pwd)"
rm -f $wd/actual.txt
rm -rf $wd/../bin
mkdir -p $wd/../bin
javac -d $wd/../bin $wd/../src/main/java/**/*.java
TESTING=true java -cp $wd/../bin dev.joulev.archduke.Archduke < $wd/input.txt > $wd/actual.txt
diff $wd/actual.txt $wd/expected.txt

if [ $? -eq 0 ]; then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi
