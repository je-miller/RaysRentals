# RaysRentals

This is the skeleton framework for performing the assigned task.

## Getting Started

Before you can build this project, you must install and configure the following dependencies on your machine:

1. MongoDb is used to store data. There is a docker container for quickly launching:

   `docker-compose -f src/main/docker/mongodb.yml up -d`

2. [Node.js][]: We use Node to run a development web server and build the project.
   Depending on your system, you can install Node either from source or as a pre-packaged bundle.

After installing Node, you should be able to run the following command to install development tools.
You will only need to run this command when dependencies change in [package.json](package.json).

    npm install

We use npm scripts and [Webpack][] as our build system.

Run the following commands in two separate terminals to create a blissful development experience where your browser auto-refreshes when files change on your hard drive.

    ./gradlew
    npm start
