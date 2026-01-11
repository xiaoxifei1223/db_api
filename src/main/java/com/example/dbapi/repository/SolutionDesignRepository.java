package com.example.dbapi.repository;

import com.example.dbapi.entity.SolutionDesign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SolutionDesignRepository extends JpaRepository<SolutionDesign, UUID> {

    /**
     * Find designs by trace ID
     */
    List<SolutionDesign> findByTraceId(UUID traceId);

    /**
     * Find designs by requirement ID
     */
    List<SolutionDesign> findByReqId(UUID reqId);

    /**
     * Find designs by review status
     */
    List<SolutionDesign> findByReviewStatus(String reviewStatus);

    /**
     * Find designs by architect ID
     */
    List<SolutionDesign> findByArchitectId(String architectId);

    /**
     * Find designs created by a specific user
     */
    List<SolutionDesign> findByCreatedBy(String createdBy);

    /**
     * Find AI-generated designs
     */
    List<SolutionDesign> findByIsAiGenerated(Boolean isAiGenerated);

    /**
     * Find designs by review status and architect ID
     */
    List<SolutionDesign> findByReviewStatusAndArchitectId(String reviewStatus, String architectId);

    /**
     * Check if a design exists by trace ID and req ID
     */
    boolean existsByTraceIdAndReqId(UUID traceId, UUID reqId);
}
