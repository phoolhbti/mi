###rpgm###
RPGM web based school management system.

####Version####

* rpgm Version : 1.0-SNAPSHOT
* Build Number   : 0.0.1

####Main Technologies####

* Programming language    : Java 1.7
* DataBase                : MySQL Community Server 5.5.20
* Build Tool              : Maven 3.0.4


####How to Start####

1. Install WAMP/LAMP server.
   Import the SQL schema rpgm/data/v1.0.6/rpgm-1.0.6.sql into mysql server to create rpgm database for you.
   Load the data rpgm/data/v1.0.6/sampledata-1.0.6.sql
   If your mysql user,password is otherthan "root","" then update it in applicationContext.xml in both repository and rpgmweb modules. 

2. Install git setup and fork rpgm.

3. Install Maven, go to directory containing rpgm project root in command-prompt/terminal and run
  
 For Windows run 
  *  ```mvn install:install-file -Dfile=rpgm\modules\repository\lib\hbnpojogen-persistence-1.4.4.jar -DgroupId=com.felees -DartifactId=hbnpojogen-persistence -Dversion=1.4.4 -Dpackaging=jar```
   * ```mvn install:install-file -Dfile=rpgm\modules\analytics\lib\class-analyzer-1.0.3.jar -DgroupId=com.arima.classanalyzer -DartifactId=class-analyzer -Dversion=1.0.3 -Dpackaging=jar```

 For Linux run
   * ```mvn install:install-file -Dfile=rpgm/modules/repository/lib/hbnpojogen-persistence-1.4.4.jar -DgroupId=com.felees -DartifactId=hbnpojogen-persistence -Dversion=1.4.4 -Dpackaging=jar```
   * ```mvn install:install-file -Dfile=rpgm/modules/analytics/lib/class-analyzer-1.0.3.jar -DgroupId=com.arima.classanalyzer -DartifactId=class-analyzer -Dversion=1.0.3 -Dpackaging=jar```

4. Now jump into rpgm project root directory and run, with having an active internet connection.
   ```mvn clean install```

5. Run the command from rpgm main module (rpgm/rpgm)
   ```mvn clean jetty:run-war```

6. Open localhost:8080/rpgm in your browser to see rpgm up and running,type anything for username and password to get in.
