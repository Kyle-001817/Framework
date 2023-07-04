cd Framework
javac -d . *.java
jar -cvf framework.jar *
mkdir ..\Test\WEB-INF\lib
copy framework.jar ..\Test\WEB-INF\lib
cd ../Test
javac -classpath WEB-INF/lib/framework.jar -d WEB-INF/classes/ WEB-INF/classes/*.java
jar -cvf test.war *
copy test.war "C:\Program Files\Apache Software Foundation\Tomcat 10.1\webapps"