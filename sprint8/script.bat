javac -d . framework\src\*.java 

jar cf test_framework\src\WEB-INF\lib\framework.jar  etu1817

javac -cp "C:\Program Files\Apache Software Foundation\Tomcat 10.1\webapps\sprint8\test_framework\src\WEB-INF\lib\framework.jar" -d test_framework\src\WEB-INF\classes test_framework\src\WEB-INF\lib\*.java

jar -cvf sprint8_sprint.war -C test_framework\src .