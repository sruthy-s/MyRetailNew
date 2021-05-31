# Retail demo app

MyRetail app provides an interface to create, update and get product by ID.

### Techstack
* Java (1.8)
* Maven
* Spring boot
* Junit
* Cucumber
* Docker

### How to start app
#### Without docker
##### Pre-requisite: Java and mvn should be installed. 
1. Navigate to the cloned project directory.
2. `./build.sh start-lite`
3. API can be accessed on localhost at port 8080.

#### With docker
##### Pre-requisite: Docker desktop should be installed.
1. Navigate to the cloned project directory.
2. `./build.sh start`
3. API can be accessed on localhost at port 8080.

### Code coverage
1. Navigate to the cloned project directory.
2. Run `mvn clean install`.
3. Reports can be found in `target\site\jacoco\index.html`. The folder is relative to the cloned project directory.
