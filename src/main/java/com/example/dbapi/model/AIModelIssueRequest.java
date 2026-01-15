package com.example.dbapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AIModelIssueRequest {
    
    private UUID solutionId;
    private UUID designId;
    private UUID testCaseId;
    private UUID workItemId;
    private UUID reqId;
    private UUID deploymentId;
    private UUID traceId;
    
    private String artifactType;
    private String artifactDescription;
    private String sourceRequirement;
    private String designReference;
    private String expectedCode;
    private String actualCodeStatus;
    private String issueType;
    private String lineageStatus;
    private String codeImplementation;
    private String lineage;
}
