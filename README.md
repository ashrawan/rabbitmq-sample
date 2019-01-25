
**RabbitMQ** is an open-source message-broker which implements the (AMQP) Advanced Message Queuing Protocol.

 - Its a way of exchanging data between processes, applications, and servers.
 -  Provide asynchronous communication and independent system. ( removing inter-dependency between applications )
 
**Message Brokers** (RabbitMQ) acts like a middleman for accepting a message, queuing it and delivering it to the requesting party.

**Queues** are blocks where message is pushed into and later delivered and processed automatically.

[**See this Tutorial for Understanding RabbitMQ Basics**](https://www.cloudamqp.com/blog/2015-05-18-part1-rabbitmq-for-beginners-what-is-rabbitmq.html)


## **Readme**

Api Call => [http://localhost:8080/rabbitTest/producer](http://localhost:8080/rabbitTest/producer)

Response: "Message Successfully sent"

## Exchange:


![](https://lh3.googleusercontent.com/_xdss9mR0HScoD0LuoG7ZA07KIVFAzqNkLL-M1cVzpDt09AwBcqavsAQT0c77ogyclxOy2pjYPstD5R9h-fXdFVlZ4MmLG-cu0uctJfy6jffDIqvBPbIKewcC-S7IX7M3OcvQNOg)
  

## Queue:

![](https://lh5.googleusercontent.com/cS0PpS8DYsKAfISw3sMwtKLUTrSYm-rMaaghQg_AUibplhP0OiQ_UNA3KWeFrEijbwDhAB88E78EG006XPAdFWe7WnCn8H6IC-GWoqL1bj3-EK2nqvq2HBgV2lzUi8NHc4sG_7hf)

## Message in “dlq-lbQueue”
Negative Acknowledments on message

![](https://lh5.googleusercontent.com/gUUh0bzoiwPdsch-xnbpb3EQcCQwoIPceMa2Dv3w80Df2xeTPUzckVvL_ZIP6T7tEiqsHUep6mN9I_hLKJkJCpwfwmsfJByCUPWVQFVyR3SvgWf-w8xVGWBxFPZc_vKchV_NcBgs)
