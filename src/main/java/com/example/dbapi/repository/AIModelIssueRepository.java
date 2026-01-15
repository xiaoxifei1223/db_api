package com.example.dbapi.repository;

import com.example.dbapi.entity.AIModelIssue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AIModelIssueRepository extends JpaRepository<AIModelIssue, UUID> {

    /**
     * Find issues by trace ID with pagination
     */
    Page<AIModelIssue> findByTraceId(UUID traceId, Pageable pageable);

    /**
     * Find issues by issue type with pagination
     */
    Page<AIModelIssue> findByIssueType(String issueType, Pageable pageable);

    /**
     * Find issues by lineage status with pagination
     */
    Page<AIModelIssue> findByLineageStatus(String lineageStatus, Pageable pageable);
}
