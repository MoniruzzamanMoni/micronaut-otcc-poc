## Micronaut CLI commands
### Create Application
```
$ mn
mn> create-app moni.poc.micronaut-otcc-poc --build=maven --lang=java
>
| Application created at D:\Workshop\poc\micronaut-otcc-poc
```

```
$ mn create-controller otcc
←[34m|←[39m←[0m Rendered controller to src/main/java/moni/poc/OtccController.java
←[34m|←[39m←[0m Rendered test to src/test/java/moni/poc/OtccControllerTest.java

moni@moni-pc MINGW64 /d/Workshop/poc/micronaut-otcc-poc (master)
$ mvn mn:run
[INFO] Scanning for projects...
[INFO] 
[INFO] --------------------< moni.poc:micronaut-otcc-poc >---------------------
[INFO] Building micronaut-otcc-poc 0.1
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] >>> micronaut-maven-plugin:3.5.3:run (default-cli) > process-classes @ micronaut-otcc-poc >>>
[INFO] 
[INFO] --- maven-resources-plugin:3.2.0:resources (default-resources) @ micronaut-otcc-poc ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Using 'UTF-8' encoding to copy filtered properties files.
[INFO] Copying 2 resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.10.1:compile (default-compile) @ micronaut-otcc-poc ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 3 source files to D:\Workshop\poc\micronaut-otcc-poc\target\classes
[INFO] 
[INFO] <<< micronaut-maven-plugin:3.5.3:run (default-cli) < process-classes @ micronaut-otcc-poc <<<
[INFO]
[INFO] 
[INFO] --- micronaut-maven-plugin:3.5.3:run (default-cli) @ micronaut-otcc-poc ---
 __  __ _                                  _   
|  \/  (_) ___ _ __ ___  _ __   __ _ _   _| |_ 
| |\/| | |/ __| '__/ _ \| '_ \ / _` | | | | __|
| |  | | | (__| | | (_) | | | | (_| | |_| | |_
|_|  |_|_|\___|_|  \___/|_| |_|\__,_|\__,_|\__|
  Micronaut (v3.8.8)

```

```
moni@moni-pc MINGW64 /d/Workshop/poc/micronaut-otcc-poc (master)
$ mvn test
[INFO] Scanning for projects...
[INFO] 
[INFO] --------------------< moni.poc:micronaut-otcc-poc >---------------------
[INFO] Building micronaut-otcc-poc 0.1
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-resources-plugin:3.2.0:resources (default-resources) @ micronaut-otcc-poc ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Using 'UTF-8' encoding to copy filtered properties files.
[INFO] Copying 2 resources
[INFO]
[INFO] --- maven-compiler-plugin:3.10.1:compile (default-compile) @ micronaut-otcc-poc ---
[INFO] Nothing to compile - all classes are up to date
[INFO]
[INFO] --- maven-resources-plugin:3.2.0:testResources (default-testResources) @ micronaut-otcc-poc ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Using 'UTF-8' encoding to copy filtered properties files.
[INFO] skip non existing resourceDirectory D:\Workshop\poc\micronaut-otcc-poc\src\test\resources
[INFO]
[INFO] --- maven-compiler-plugin:3.10.1:testCompile (default-testCompile) @ micronaut-otcc-poc ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 2 source files to D:\Workshop\poc\micronaut-otcc-poc\target\test-classes
[INFO] /D:/Workshop/poc/micronaut-otcc-poc/src/test/java/moni/poc/OtccControllerTest.java: D:\Workshop\poc\micronaut-otcc-poc\src\test\java\moni\poc\OtccControllerTest.java uses unchecked or unsafe operations.
[INFO] /D:/Workshop/poc/micronaut-otcc-poc/src/test/java/moni/poc/OtccControllerTest.java: Recompile with -Xlint:unchecked for details.
[INFO]
[INFO] --- micronaut-maven-plugin:3.5.3:start-testresources-service (default-start-testresources-service) @ micronaut-otcc-poc ---
[INFO]
[INFO] --- maven-surefire-plugin:2.22.2:test (default-test) @ micronaut-otcc-poc ---
[INFO] 
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running moni.poc.MicronautOtccPocTest
17:37:33.850 [main] INFO  i.m.context.env.DefaultEnvironment - Established active environments: [test]
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 1.31 s - in moni.poc.MicronautOtccPocTest
[INFO] Running moni.poc.OtccControllerTest
17:37:34.646 [main] INFO  i.m.context.env.DefaultEnvironment - Established active environments: [test]
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.623 s - in moni.poc.OtccControllerTest
[INFO] 
[INFO] Results:
[INFO]
[INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  6.390 s
[INFO] Finished at: 2023-04-27T17:37:35+06:00
[INFO] ------------------------------------------------------------------------

```
