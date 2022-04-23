# Getting Started

### Reference Documentation

This application is a standard spring boot RESTful web service.
Consumer can use this web service to register ,edit, read and (soft)delete single or multiple user(s).

****

### Setting up and running 
a.How to set up your development environment in your laptop
1. Clone this repo to your laptop
2. Import this project as a maven project into your workspace
3. Start application by running Application class 

b.How to run application by Docker
 
```
$ cd root of this application
$ ./rundocker.sh
```


### Unit Testing
Make sure to add UT for domain object and controller.

UT for POJO,Exception,Repository and Infrastructure is not mandatory.

### API instruction

Swagger 
* [Swagger address](http://localhost:8080/register/swagger-ui/index.html)

Exception description

|  code   | description  |
| :-----| :----- |
| 1001  | invalid email |
| 1002  | no user found |
| 1003  | incorrect password |

### Performance Testing

Please do performance testing before and after every change.

Don't do any non-idempotent performance testing in UAT/QA/PROD environment.
Non-idempotent operation including register, edit, delete and batch delete.

```aidl
$ cd root of application
$ cd registration/src/test/performance
$ jmeter -n -t register_user.jmx -l register_user_result.jtl
$ jmeter -n -t retrieve_user.jmx -l retrieve_user_result.jtl
$ jmeter -n -t edit_user.jmx -l edit_user.jtl
$ jmeter -n -t delete_user.jmx -l delete_user_result.jtl
$ jmeter -n -t batch_delete_user.jmx -l batch_delete_user.jtl

```


****


