set ProjectPath=%~dp0
cd %ProjectPath%
echo %ProjectPath%
set p=%PATH%
java -classpath "%ProjectPath%bin;%ProjectPath%lib\*;%ProjectPath%libWebDriverManager\*" org.testng.TestNG "%ProjectPath%bin\runJqueryTestcases.xml"
pause