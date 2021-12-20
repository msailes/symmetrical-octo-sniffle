# symmetrical-octo-sniffle

## Project Structure

```bash
├── infrastucture
│   ├── README.md
│   ├── cdk.json
│   ├── pom.xml
│   └── src
│       ├── main
│       │   └── java
│       │       └── com
│       │           └── myorg
│       │               ├── InfrastuctureApp.java
│       │               └── InfrastuctureStack.java
└── software
    ├── java
    │   ├── HelloWorldFunction
    │   │   ├── pom.xml
    │   │   └── src
    │   │       ├── main
    │   │       │   └── java
    │   │       │       └── helloworld
    │   │       │           └── App.java
    │   ├── README.md
    │   ├── events
    │   │   └── event.json
    │   └── template.yaml
    └── python
        ├── README.md
        ├── __init__.py
        ├── events
        │   └── event.json
        ├── hello_world
        │   ├── __init__.py
        │   ├── app.py
        │   └── requirements.txt
        ├── template.yaml

```

### Infrastructure
<h3>Using AWS Serverless to deploy Machine learning models</h3>

![Infrastructure](infrastructure.png)

## Prerequisite
* AWS CLI
* Install [JDK](https://www.oracle.com/java/technologies/downloads/)
* Install [Maven](https://maven.apache.org/install.html)
* Install NodeJS
* Install [CDK](https://docs.aws.amazon.com/cdk/latest/guide/getting_started.html#getting_started_install)

## Build

1. 

2. Java Project

```bash
cd software/java/HelloWorldFunction
mvn package
```

## Deploy

```bash
cd infrastructure
cdk bootstrap
cdk deploy
```
<b>Note</b>:
If you get a "could not assume role" error, try bootstrapping the stack using the below command. This should create all required roles automatically. 

```
cdk bootstrap --trust=ACCOUNT_ID --cloudformation-execution-policies=arn:aws:iam::aws:policy/AdministratorAccess 
```


## API

### REST API 

Make a request for a model calculation

`POST /`

```json

{
  "requestId": "UUID",
  "vechicalType": "Car | Truck | Van",
  "vechicalModel": "Ford Focus",
  "vechicalAge": 9
}

```
