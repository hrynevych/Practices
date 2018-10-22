@echo off 

javac -d ..\bin ua\khpi\hrynevych\task01\*.java

java -cp ..\bin ua.khpi.hrynevych.task01.Demo 

del ..\bin\ua\khpi\hrynevych\task01\*.class 