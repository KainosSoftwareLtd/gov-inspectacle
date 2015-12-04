            __         __
           /.-'       `-.\
          //             \\
         /j_______________j\
        /o.-==-. .-. .-==-.o\
        ||      )) ((      ||
         \\____//   \\____//
          `-==-'     `-==-'
    INSPECTACLE - GITLAB REPO POLICE

[![Build Status](https://travis-ci.org/KainosSoftwareLtd/gov-inspectacle.svg?branch=master)](https://travis-ci.org/KainosSoftwareLtd/gov-inspectacle)

#### Dropwizard Wrapper around Gitlab API
This heroic application is designed to watch over your Gitlab repos, probing them for README files, and displaying whether or not they are active. Plug this into your build radiator, and expose those repos who fail to display common decency.

Merge Request watcher added, currently coded to look for upvotes (:+1: comments) on open merge requests to say whether 2 independent upvotes are recieved. Again for use with a dashboard

#### Configuration
Replace the following things in the InspectacleServiceConfig.yml configuration file:

privateKey: YOUR_PRIVATE_KEY
url: ROOT_URL_TO_GITLAB

#### Running

```
./gradlew inspectacle-service:run
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
    GET     /merge-requests/ (com.kainos.inspectacle.resources.MergeRequestTrackerResource)
    Returns a list of open merge requests with some detail about them, and if they have recieved enough upvotes (2)
```


This can be paired up with [https://github.com/KainosSoftwareLtd/gov-gitlab-dashboard](https://github.com/KainosSoftwareLtd/gov-gitlab-dashboard) a dashing dashboard to display merge request status for open MRs.
