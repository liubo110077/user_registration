# Reference Documentation

This application is a standard spring boot RESTful web service.
Consumer can use this web service to register, edit, read and (soft)delete single or multiple user(s).

To simplify the dependencies, DB and MQ are both mocked. You don't need to installl any of them.

Upon successful registration, send out a welcome email is implemented by a fake MQ in memory. 
You will get the sending email message in log, the message is like "sending email to 2lcddd22c@sogou.com"


****

### Setting up and running 

You can run it by following ways.

a.How to set up your development environment in your laptop
1. Clone this repo to your laptop
2. Import this project as a maven project into your workspace
3. Start application by running com.pccw.user.registration.Application class 

b.How to run application by Docker
 
```
$ cd the root of this application
$ ./rundocker.sh
```


### Unit Testing
There are two kinds of UT, domain object and controller.Be sure to add UT when you fixing bugs, adding new features, etc.

UT for POJO,Exception,Repository and Infrastructure is not mandatory.


### API instruction

Swagger 
* [Swagger address](http://localhost:8080/register/swagger-ui/index.html)

Status code description

|  code   | description  |
| :-----| :----- |
| 1001  | invalid email |
| 1002  | no user found |
| 1003  | incorrect password |
| 200   | no exception |


### Performance Testing

We are using jmeter as the performance testing tool. You can get and learn it from it's official site https://jmeter.apache.org/.

Please do performance testing before and after every change.

Don't do any non-idempotent performance testing in UAT/QA/PROD environment.
Non-idempotent operation including register, edit, delete and batch delete.

You can do performance testing by using below shell after setting up Jmeter.

```
$ cd root of application
$ cd registration/src/test/performance
$ jmeter -n -t register_user.jmx -l register_user_result.jtl
$ jmeter -n -t retrieve_user.jmx -l retrieve_user_result.jtl
$ jmeter -n -t edit_user.jmx -l edit_user.jtl
$ jmeter -n -t delete_user.jmx -l delete_user_result.jtl
$ jmeter -n -t batch_delete_user.jmx -l batch_delete_user.jtl

```


****


