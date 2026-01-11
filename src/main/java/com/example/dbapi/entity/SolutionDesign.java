package com.example.dbapi.entity;

import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "solution_designs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolutionDesign {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "design_id")
    private UUID designId;

    @Column(name = "trace_id", nullable = false)
    private UUID traceId;

    @Column(name = "req_id", nullable = false)
    private UUID reqId;

    @Column(name = "design_overview", nullable = false, columnDefinition = "TEXT")
    private String designOverview;

    @Column(name = "design_metadata", nullable = false, columnDefinition = "TEXT")
    private String designMetadata;

    @Type(JsonBinaryType.class)
    @Column(name = "affected_application_id", nullable = false, columnDefinition = "jsonb")
    private List<String> affectedApplicationId;

    @Column(name = "design_url", nullable = false)
    private String designUrl;

    @Column(name = "system_impact_analysis", columnDefinition = "TEXT")
    private String systemImpactAnalysis;

    @Column(name = "security_considerations", columnDefinition = "TEXT")
    private String securityConsiderations;

    @Column(name = "performance_considerations", columnDefinition = "TEXT")
    private String performanceConsiderations;

    @Type(JsonBinaryType.class)
    @Column(name = "upstream_dependencies", columnDefinition = "jsonb")
    private List<String> upstreamDependencies;

    @Type(JsonBinaryType.class)
    @Column(name = "downstream_dependencies", columnDefinition = "jsonb")
    private List<String> downstreamDependencies;

    @Column(name = "is_ai_generated")
    private Boolean isAiGenerated = false;

    @Column(name = "design_agent_id", length = 100)
    private String designAgentId;

    @Column(name = "generated_at")
    private LocalDateTime generatedAt;

    @Column(name = "architect_id", length = 50)
    private String architectId;

    @Column(name = "review_status", length = 50)
    private String reviewStatus = "PENDING";

    @Column(name = "reviewed_at")
    private LocalDateTime reviewedAt;

    @Column(name = "review_comments", columnDefinition = "TEXT")
    private String reviewComments;

    @Column(name = "review_evidence", columnDefinition = "TEXT")
    private String reviewEvidence;

    @Type(JsonBinaryType.class)
    @Column(name = "github_commit_ids", columnDefinition = "jsonb")
    private List<String> githubCommitIds;

    @Type(JsonBinaryType.class)
    @Column(name = "github_file_paths", columnDefinition = "jsonb")
    private List<String> githubFilePaths;

    @Type(JsonBinaryType.class)
    @Column(name = "github_file_url", columnDefinition = "jsonb")
    private List<String> githubFileUrl;

    @Column(name = "approver_id", length = 50)
    private String approverId;

    @Column(name = "approved_at")
    private LocalDateTime approvedAt;

    @Column(name = "signoff_document_url", length = 500)
    private String signoffDocumentUrl;

    @Column(name = "created_by", nullable = false, length = 50)
    private String createdBy;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_by", length = 50)
    private String updatedBy;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "create_time", nullable = false, updatable = false)
    private LocalDateTime createTime;

    @Column(name = "update_time", nullable = false)
    private LocalDateTime updateTime;

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.createTime = now;
        this.updateTime = now;
        this.updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        LocalDateTime now = LocalDateTime.now();
        this.updatedAt = now;
        this.updateTime = now;
    }
}
