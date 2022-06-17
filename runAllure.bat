set ProjectPath=%~dp0
cd %ProjectPath%
echo %ProjectPath%
set p=%PATH%
java -javaagent:"%ProjectPath%libAllureReport\aspectjweaver-1.8.10.jar" -classpath "%ProjectPath%bin;%ProjectPath%libAllureReport\*;%ProjectPath%libExtentReportV4\*;%ProjectPath%libLog4J\*;%ProjectPath%lib\*;%ProjectPath%libReportNG\*;%ProjectPath%libWebDriverManager\*" org.testng.TestNG "%ProjectPath%bin\runBankGuruTestcases.xml"
pause