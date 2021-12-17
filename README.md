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

Nice diagram

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
