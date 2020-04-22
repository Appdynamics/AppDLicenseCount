!!!!! DEPRECATED and No longer Supported !!!!
===========
AppDLicenseCount
===========

The AppDLicenseCount is a solution is can be used to get a license count from an 
AppDynamics’ controller using the REST service. The solution start by collecting 
information from all of the applications, nodes and tiers then produced a license 
usage excel report. The solution counts only active agents and counts a license 
if it is up. The general active agent count is also provided so that active agents 
can be compared of the hours of the days.

Requirements:
-------------------
The AppDLicenseCount solution is dependent on the AppDRESTAPI-SDK 
               (https://github.com/Appdynamics/AppDRESTAPI-SDK.git) 
version 2.5.4 or higher is needed for the current version of the license count, this should be 
cloned and built before cloning this package. It is recommended that both packages 
share a base directory, this will make the dependency checking easier. Compile and 
package the AppDRESTAPI-SDK to insure files that are needed are present. Once this 
package has been cloned edit the file called ‘one_time_git.properties’, insure the 
location of AppDRESTAPI-SDK and the version of the jar file are correct. The file 
‘one_time_git.properties’ should not be sync'd with the git repository after it has 
been edited because the settings only apply to your environment.

The file contains two variables please insure that they are correct (insure that no 
extra spaces are present):

appd_rest_base=../AppDRESTAPI-SDK

appd_rest_jar=AppDRESTAPI-SDK_2.5.4.jar

Building:
-----------
To build the package run the following command within the appdLicenseCount directory
      ant -f AppD_build.xml

This will create a directory called execLib with all of the necessary libraries to run the tool.


Usage
--------
```
java -cp "execLib/*" org.appdynamics.licensecount.CountLicenses -c <FQDN-For-Controller> -P <PORT> -u<USER-NAME> -p <PASSWORD> -a <ACCOUNT-NAME> [-s] [-n] [-i] [-A <Apps>] [-d 1|2] [-g<PATH-TO-FILE>]

 -a,--account <a>    :   If controller is multi-tenant add the account

 -c,--controller <c> :   This is going to be the FQDN of the controller, for example: appdyn.saas.appdynamics.com

 -s,--ssl            :   Use SSL with connection

 -P,--port <P>       :   The is going to be the port that is used by the controller.

 -u,--username <u>   :   The user name to use for the connection

 -p,--passwd <p>     :   The password to use for the connection

 -f,--file <f>       :   Optional : This is going to be the file name that is going to be created. Default is <AccountName>_LicenseCount.xlsx.

 -i,--interval <i>   :   Optional : This is going to be the number of days we go back and run. Default is going back 7 days from midnight to midnight.

 -A,--apps           :   Optional : Comma separated list of Application names with no extra spaces that should be included in the report.

 -G,--granular       :   How granular you want the text.

 -n, --now               :   Include upto the last full hour in the count

 -U,--uptime <U>     :   Optional : The amount of uptime necessary for an agent to be up so that it is counted. Default value is .70 (70%) 

 -d,--debug <d>      :   Debug level to set the calls at.

 -g, --group <g>     :   Optional : This is going to create an additional worksheet labeled "Business Unit License Summary" at the end of the excel sheet with license count separated as specified in text file.  See "group.txt” for example text file format. 

 -N, --nodes <N>     :   Optional : This will produce an additional sheet with hourly node brake down.
```

Example:

java -Xmx512m -cp "execLib/*" org.appdynamics.licensecount.CountLicenses -cACME-CONTROLLER.saas.appdynamics.com -P443 -uACMEUSERNAME -pACMEPASSWORD -aACME -s -g/my/path/to/group.txt

If you have a large controller (75+ tiers  or 1000+ nodes or 20+ applications) then you should add the -Xmx options to insure that you have enough memory for the program to successfully run.

Support:
--------
For support please email gilbert.solorzano@appdynamics.com


