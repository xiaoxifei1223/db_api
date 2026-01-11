package com.example.dbapi.service;

import com.example.dbapi.entity.SolutionDesign;
import com.example.dbapi.repository.SolutionDesignRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class SolutionDesignService {

    private final SolutionDesignRepository repository;

    /**
     * Create a new solution design
     */
    public SolutionDesign createDesign(SolutionDesign design) {
        log.info("Creating new solution design for reqId: {}", design.getReqId());
        return repository.save(design);
    }

    /**
     * Update an existing solution design
     */
    public SolutionDesign updateDesign(UUID designId, SolutionDesign design) {
        log.info("Updating solution design: {}", designId);
        return repository.findById(designId)
                .map(existing -> {
                    updateDesignFields(existing, design);
                    return repository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Design not found: " + designId));
    }

    /**
     * Get a solution design by ID
     */
    @Transactional(readOnly = true)
    public Optional<SolutionDesign> getDesignById(UUID designId) {
        log.info("Fetching solution design: {}", designId);
        return repository.findById(designId);
    }

    /**
     * Get all solution designs
     */
    @Transactional(readOnly = true)
    public List<SolutionDesign> getAllDesigns() {
        log.info("Fetching all solution designs");
        return repository.findAll();
    }

    /**
     * Get designs by trace ID
     */
    @Transactional(readOnly = true)
    public List<SolutionDesign> getDesignsByTraceId(UUID traceId) {
        log.info("Fetching designs for traceId: {}", traceId);
        return repository.findByTraceId(traceId);
    }

    /**
     * Get designs by requirement ID
     */
    @Transactional(readOnly = true)
    public List<SolutionDesign> getDesignsByReqId(UUID reqId) {
        log.info("Fetching designs for reqId: {}", reqId);
        return repository.findByReqId(reqId);
    }

    /**
     * Get designs by review status
     */
    @Transactional(readOnly = true)
    public List<SolutionDesign> getDesignsByReviewStatus(String reviewStatus) {
        log.info("Fetching designs with review status: {}", reviewStatus);
        return repository.findByReviewStatus(reviewStatus);
    }

    /**
     * Get designs by architect ID
     */
    @Transactional(readOnly = true)
    public List<SolutionDesign> getDesignsByArchitectId(String architectId) {
        log.info("Fetching designs for architect: {}", architectId);
        return repository.findByArchitectId(architectId);
    }

    /**
     * Get AI-generated designs
     */
    @Transactional(readOnly = true)
    public List<SolutionDesign> getAiGeneratedDesigns() {
        log.info("Fetching AI-generated designs");
        return repository.findByIsAiGenerated(true);
    }

    /**
     * Update review status
     */
    public SolutionDesign updateReviewStatus(UUID designId, String reviewStatus, String reviewComments) {
        log.info("Updating review status for design: {}", designId);
        return repository.findById(designId)
                .map(design -> {
                    design.setReviewStatus(reviewStatus);
                    design.setReviewComments(reviewComments);
                    design.setReviewedAt(LocalDateTime.now());
                    return repository.save(design);
                })
                .orElseThrow(() -> new RuntimeException("Design not found: " + designId));
    }

    /**
     * Approve a design
     */
    public SolutionDesign approveDesign(UUID designId, String approverId, String signoffDocumentUrl) {
        log.info("Approving design: {} by approver: {}", designId, approverId);
        return repository.findById(designId)
                .map(design -> {
                    design.setReviewStatus("APPROVED");
                    design.setApproverId(approverId);
                    design.setApprovedAt(LocalDateTime.now());
                    design.setSignoffDocumentUrl(signoffDocumentUrl);
                    return repository.save(design);
                })
                .orElseThrow(() -> new RuntimeException("Design not found: " + designId));
    }

    /**
     * Delete a solution design
     */
    public void deleteDesign(UUID designId) {
        log.info("Deleting solution design: {}", designId);
        repository.deleteById(designId);
    }

    /**
     * Check if design exists
     */
    @Transactional(readOnly = true)
    public boolean designExists(UUID traceId, UUID reqId) {
        return repository.existsByTraceIdAndReqId(traceId, reqId);
    }

    /**
     * Helper method to update design fields
     */
    private void updateDesignFields(SolutionDesign existing, SolutionDesign updated) {
        if (updated.getDesignOverview() != null) {
            existing.setDesignOverview(updated.getDesignOverview());
        }
        if (updated.getDesignMetadata() != null) {
            existing.setDesignMetadata(updated.getDesignMetadata());
        }
        if (updated.getAffectedApplicationId() != null) {
            existing.setAffectedApplicationId(updated.getAffectedApplicationId());
        }
        if (updated.getDesignUrl() != null) {
            existing.setDesignUrl(updated.getDesignUrl());
        }
        if (updated.getSystemImpactAnalysis() != null) {
            existing.setSystemImpactAnalysis(updated.getSystemImpactAnalysis());
        }
        if (updated.getSecurityConsiderations() != null) {
            existing.setSecurityConsiderations(updated.getSecurityConsiderations());
        }
        if (updated.getPerformanceConsiderations() != null) {
            existing.setPerformanceConsiderations(updated.getPerformanceConsiderations());
        }
        if (updated.getUpstreamDependencies() != null) {
            existing.setUpstreamDependencies(updated.getUpstreamDependencies());
        }
        if (updated.getDownstreamDependencies() != null) {
            existing.setDownstreamDependencies(updated.getDownstreamDependencies());
        }
        if (updated.getGithubCommitIds() != null) {
            existing.setGithubCommitIds(updated.getGithubCommitIds());
        }
        if (updated.getGithubFilePaths() != null) {
            existing.setGithubFilePaths(updated.getGithubFilePaths());
        }
        if (updated.getGithubFileUrl() != null) {
            existing.setGithubFileUrl(updated.getGithubFileUrl());
        }
        existing.setUpdatedBy(updated.getUpdatedBy());
    }
}
