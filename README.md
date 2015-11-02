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
Replace the following things in the configuration file:

privateKey: YOUR_PRIVATE_KEY
url: ROOT_URL_TO_GITLAB

#### Running
./go <- compiles and runs, make sure you have the above settings configured. Don't commit your Private Key to a public repo ;)