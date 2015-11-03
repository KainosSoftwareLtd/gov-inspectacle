            __         __
           /.-'       `-.\
          //             \\
         /j_______________j\
        /o.-==-. .-. .-==-.o\
        ||      )) ((      ||
         \\____//   \\____//
          `-==-'     `-==-'
    INSPECTACLE - GITLAB REPO POLICE

#### Dropwizard Wrapper around Gitlab API
This heroic application is designed to watch over your Gitlab repos, probing them for README files, and displaying whether or not they are active. Plug this into your build radiator, and expose those repos who fail to display common decency.

#### Configuration
Replace the following things in the InspectacleServiceConfig.yml configuration file:

privateKey: YOUR_PRIVATE_KEY

url: ROOT_URL_TO_GITLAB

Timeout for the DW Jersey client is high thanks to network latency. You might get away with shorter.

#### Running

```
./go
```

Compiles and runs, make sure you have the above settings configured. Don't commit your Private Key to a public repo ;)

Defaults to running on 9420.

```
    GET     /status (com.kainos.inspectacle.resources.StatusResource)
    Just tells you if it is running or not.
```

```
    GET     /projects (com.kainos.inspectacle.resources.ProjectsResource)
    Returns JSON list of Project Summaries
```

```
    GET     /projects/csv (com.kainos.inspectacle.resources.ProjectsResource)
    Returns CSV list of project summaries. So good for managing. Such Manage. So Spreadsheet. Very Jockey.
```


```
./runTests
```

Runs all the tests. There are no tests. I am a bad person. Excessive logging is my crutch. (RH)

