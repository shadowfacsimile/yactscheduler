# yactscheduler
A Spring Boot Scheduler for YACT [Yet Another COVID-19 Tracker]

A scheduler runs every hour and -
- Grabs csv files from Johns Hopkins CSSE github repo and converts them to JSON
- Grabs JSON files from api.covid19india.org
- Stores files on the server

A scheduler runs every 24 hours to archive the JSON files
