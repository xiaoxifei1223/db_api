package com.example.dbapi.entity;

import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "aimodelissues")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AIModelIssue {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "event_id")
    private UUID eventId;

    @Column(name = "solution_id")
    private UUID solutionId;

    @Column(name = "design_id")
    private UUID designId;

    @Column(name = "test_case_id")
    private UUID testCaseId;

    @Column(name = "work_item_id")
    private UUID workItemId;

    @Column(name = "req_id")
    private UUID reqId;

    @Column(name = "deployment_id")
    private UUID deploymentId;

    @Column(name = "trace_id", nullable = false)
    private UUID traceId;

    @Column(name = "artifacttype", length = 255)
    private String artifactType;

    @Column(name = "artifactdescription", columnDefinition = "TEXT")
    private String artifactDescription;

    @Column(name = "sourcerequirement", columnDefinition = "TEXT")
    private String sourceRequirement;

    @Column(name = "designreference", columnDefinition = "TEXT")
    private String designReference;

    @Column(name = "expectedcode", columnDefinition = "TEXT")
    private String expectedCode;

    @Column(name = "actualcodestatus", length = 255)
    private String actualCodeStatus;

    @Column(name = "issuetype", length = 255)
    private String issueType;

    @Column(name = "lineagestatus", length = 255)
    private String lineageStatus;

    @Column(name = "codeimplementation", columnDefinition = "TEXT")
    private String codeImplementation;

    @Column(name = "lineage", length = 255)
    private String lineage;

    @Column(name = "createdat", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updatedat", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
