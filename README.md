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