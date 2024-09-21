# Receipt Processor

## Description
The Receipt Processor is a Java-based application that processes receipts, extracting relevant information and assigning points based on predefined rules. It provides endpoints to submit receipts and retrieve points awarded for each receipt.

## Prerequisites
- Docker installed on your machine. You can download it from [Docker's official website](https://www.docker.com/get-started).

## Running the Application with Docker

### 1. Clone the Repository
Clone this repository to your local machine:
```bash
git clone https://github.com/yyyyh1/receipt_processor.git
cd receipt_processor
```
### 2. Build the Docker Image
Build the Docker image using the provided Dockerfile:
```bash
docker run -p 8080:8080 receipt_processor
```

### 3. Run the Docker Container
```bash
docker run -p 8080:8080 receipt_processor
```

### 4. Access the Application
Open your web browser and navigate to:
```bash
http://localhost:8080
```
