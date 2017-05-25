# host-tracker
This is a stand alone java application (maven based) that tracks after the server and let the developer know weather its alive or not

`host-tracker` sends the `URL` as a GET request and check for the response every **10** seconds for a **minute**.

If host tracker gets **4** out of **6** times a **200** HTML response code - it is OK, otherwise, it sends a mail to the preconfigured recipients

## Why did I do that
I needed to monitor the liveliness of my server. At first, I was looking for a SAAS solution that will help me with that.
I found some but they were a little expensive and not very user friendly, 

so... 
I've decided to create one of my own.
It was rather easy and fun and I let you decide weather to use it or not.

## Prerequisites
If you want `host-tracker` to work you need the following:
- working with linux OS (Ubuntu, Centos, etc...) that runs `cron` jobs
- create in advance in the requested server an `is_alive` GET request that returns something in response, doesn't really matter as long as it is return HTML status code of `200` in case of a success (in case of a fail it can return anything else)


## Configuration
Before you start using the application you need to fill the following variables that are in `HostTracker` class:
- `MAIL` the mail you use to send mails
- `PASSWORD` the mail password
- `URL` the URL you want Host-Tracker to check

Also, in `HostTracker` in the `main` method there are the following variables 
`from` who sends the mail
`subject` the mail subject
`message` the actual message

**Important**
If you consider using gmail as your mail provider you should pay attention to the first mail you send.
You suppose to get a response from gmail that says the following:

```
Review blocked sign-in attempt
------------------------------

Hi Shai,
Google just blocked someone from signing into your Google Account shai.amar@example.com from an app that may put your account at risk.
Less secure app

...

Are you the one who tried signing in?
Google will continue to block sign-in attempts from the app you're using because it has known security problems or is out of date. You can continue to use this app by ALLOWING ACCESS TO LESS SECURE APPS, but this may leave your account vulnerable.

The Google Accounts team
```
Click on the **allowing access to less secure apps** URL to let `host-tracker` send emails via this account.

## Deployment

### Compile and package

#### Prerequisites
- installed java
- installed maven

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

**Important**
cron has a limited environment so it is crucial that you specify the full path of the script and `java` program so `cron` will be able to run it properly.

Now you have a program that checks that the server is alive every 5 minutes.




