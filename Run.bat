set projectLocation=%cd%
cd %projectLocation%
set classpath=%projectLocation%\target\classes\;%projectLocation%\lib\*;
java org.testng.TestNG testng.xml
@pause