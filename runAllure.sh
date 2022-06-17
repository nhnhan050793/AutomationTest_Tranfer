#!/bin/sh
echo "------ Step 01: Set project path to variable ------ "
project_path="/Users/admin-nhan/Desktop/Automation/2_B25_Share_Class_State"
echo "------ Step 02: Go to project path folder ------ "
cd "$project_path"
echo "------ Step 03: Run the testcase ------ "
java -javaagent:"$project_path/libAllureReport/aspectjweaver-1.8.10.jar" -classpath "$project_path/bin:$project_path/libAllureReport/*:$project_path/libExtentReportV4/*:$project_path/libLog4J/*:$project_path/lib/*:$project_path/libReportNG/*:$project_path/libWebDriverManager/*" org.testng.TestNG "$project_path/bin/runBankGuruTestcases.xml"
echo "------ Step 04: Load allure command line setting ------ "
source ~/.bash_profile
echo "------ Step 05: Generate Allure HTML Report------ "
allure serve ./allure-json/