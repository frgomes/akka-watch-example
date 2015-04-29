akka-watch-example
==================

This is a simple example application which has:

1. a Client Actor which just sends one message to the Supervisor actor

2. a Supervisor which just responds to the Client actor and starts watching it

When the Client actor dies, the Supervisor actor prints a message onto the standard output.


For the impatient
-----------------

1. Build the code

    $ sbt compile

2. In a separate terminal, run the Supervisor

    $ echo 2 | sbt run

3. In a separate terminal, run the Client

    $ echo 1 | sbt run

4. Press ctrl-C after the Client starts and prints the message that comes from the Supervisor.

5. Go to the Supervisor terminal and observe the arrival of the Terminated message.
