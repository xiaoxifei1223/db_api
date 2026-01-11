# API Documentation

## Base URL
```
http://localhost:8080/api/v1/designs
```

## Endpoints

### 1. Create Solution Design
**POST** `/api/v1/designs`

Request Body:
```json
{
  "traceId": "uuid",
  "reqId": "uuid",
  "designOverview": "string",
  "designMetadata": "string",
  "affectedApplicationId": ["app1", "app2"],
  "designUrl": "string",
  "systemImpactAnalysis": "string",
  "securityConsiderations": "string",
  "performanceConsiderations": "string",
  "upstreamDependencies": ["dep1", "dep2"],
  "downstreamDependencies": ["dep1", "dep2"],
  "isAiGenerated": false,
  "designAgentId": "string",
  "architectId": "string",
  "createdBy": "string"
}
```

### 2. Update Solution Design
**PUT** `/api/v1/designs/{designId}`

### 3. Get Solution Design by ID
**GET** `/api/v1/designs/{designId}`

### 4. Get All Solution Designs
**GET** `/api/v1/designs`

### 5. Get Designs by Trace ID
**GET** `/api/v1/designs/trace/{traceId}`

### 6. Get Designs by Requirement ID
**GET** `/api/v1/designs/requirement/{reqId}`

### 7. Get Designs by Review Status
**GET** `/api/v1/designs/status/{reviewStatus}`

Available statuses: PENDING, IN_REVIEW, APPROVED, REJECTED, REVISED

### 8. Get Designs by Architect ID
**GET** `/api/v1/designs/architect/{architectId}`

### 9. Get AI-Generated Designs
**GET** `/api/v1/designs/ai-generated`

### 10. Update Review Status
**PATCH** `/api/v1/designs/{designId}/review?reviewStatus=APPROVED&reviewComments=optional`

### 11. Approve Design
**POST** `/api/v1/designs/{designId}/approve?approverId=string&signoffDocumentUrl=optional`

### 12. Delete Solution Design
**DELETE** `/api/v1/designs/{designId}`

### 13. Check if Design Exists
**GET** `/api/v1/designs/exists?traceId=uuid&reqId=uuid`

## Configuration

Before running the application, update the database configuration in `src/main/resources/application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/your_database_name
    username: your_username
    password: your_password
```

## Running the Application

```bash
mvn spring-boot:run
```

Or build and run:
```bash
mvn clean package
java -jar target/db-api-1.0.0.jar
```

## Database Setup

Run the SQL script in `src/main/resources/schema.sql` to create the required table and indexes.
