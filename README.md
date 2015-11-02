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
``` ./go

Compiles and runs, make sure you have the above settings configured. Don't commit your Private Key to a public repo ;)


``` ./runTests

Runs all the tests. There are no tests. I am a bad person. Excessive logging is my crutch. (RH)

#### Disclaimer
Yes, using the headers returned in the requests is a better way to do paging, instead of checking for null response (you'll see what I mean) but in my defence, i built this against an older version of Gitlab that did not include this. I swear I will fix it.