# Petroleum Data Management System ✨

## Quick Start 🚀

### Server

Step 0: Install JDK(>= 17.0.0) and Maven

Step 1: Install Dependencies:
```shell
mvn clean install
```

Step 2: Run the project with MVN:
```shell
cd petroleum-server
mvn spring-boot:run
```
As the same time, you can run the project at root directory:
```shell
pnpm run dev:server
```

### Web

Step 0: Install Node.js.

Step 1: Install PNPM with NPM:

```shell
npm i pnpm -g
```

Step 2: Install dependencies:

```shell
pnpm i 
```

Step 3: Run the project at root directory:
```shell
pnpm run dev
```

### BFF
We use a backend for frontend server to resolve the cors issue, and you can run it with PNPM at root directory:
```shell
pnpm run dev:bff
```

### Q&A

#### 1. BFF Failed to Start

- Check if the port `8080` is occupied.
- Verify that you have installed node.js, pnpm and ts-node **globally**.  
- Ensure that you have installed the other dependencies required by the BFF.

#### 2. Project Can't Work Correctly in Online Environment

Since the request base URL and port have not been configured for the online environment, you need to set them manually.

#### 3. Java Server Failed to Start

- Verify that the installed JDK version is correct.
- Ensure that your IDE has correctly configured the JDK, module build target version, and other settings.
