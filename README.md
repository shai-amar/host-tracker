# host-tracker
This is a tand alone java application (maven based) that tracks after the server and let the developer know weather its alive or not

`host-tracker` sends the `URL` as a GET request and check for the response

If the HTML response is **200** - it is OK, otherwise, it sends a mail to the preconfigured recipients

## Prerequisites
If you want `host-tracker` to work you need the following:
- working with linux OS (Ubuntu, Centos, etc...) that runs cron jobs
- create in advance in the seerver a GET request calls `is_alive` that returns something in response, doesn't really matter what as long as it is return HTML status code of `200` in case of a success


## Configuration
Before you start using the application you need to fill the following variables that are in `HostTracker` class:
- `MAIL` the mail you use to send mails
- `PASSWORD` the mail password
- `URL` the URL you want Host-Tracker to check

Also, in `HostTracker` in the `main` method there are the following variables 
`from` who sends the mail
`subject` the mail subject
`message` the actual message

## Deployment

### Compile and package
In order to package and run the application you need to do the following

```
cd ${HOST_TRACKER_HOME} // go to the host-tracker dir

mvn clean install

mvn package

//  to test the application
java -jar target/host-tracker-1.0-SNAPSHOT-shaded.jar

```

### Create the script

If we want `host-tracker` to run on a regular basis we need to create a script that will be activated on a regular basis with `crontab`

You need to create the following script named `host-tracker.sh` with the following command `vi host-tracker.sh`:

```
#!/bin/bash

echo "Starting Host-Tracker script"

/full/path/to/java/java -jar  full/path/to/host-tracker-1.0-SNAPSHOT.jar

echo "Done."
```

### Configure crontab

Now after the `host-tracker.sh` script was created successfully we need to configure the crontab to activate it

- open crontab with `crontab -e`
- we want the `host-tracker.sh` script to run every 5 minutes so we need to add the following line to crontab: `*/5 * * * * /home/ubuntu/ISITYOU/scripts/hosttracker/host-tracker`
- press `^X` and `Y` for save
and that is it

Now you have a program that checks that the server is alive every 5 minutes.




