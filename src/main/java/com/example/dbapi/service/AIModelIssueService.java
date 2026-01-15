package com.example.dbapi.service;

import com.example.dbapi.entity.AIModelIssue;
import com.example.dbapi.model.AIModelIssueRequest;
import com.example.dbapi.repository.AIModelIssueRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AIModelIssueService {

    private final AIModelIssueRepository repository;

    /**
     * Create a new AI model issue
     */
    public AIModelIssue createIssue(AIModelIssueRequest request) {
        log.info("Creating new AI model issue for traceId: {}", request.getTraceId());
        
        AIModelIssue issue = new AIModelIssue();
        issue.setSolutionId(request.getSolutionId());
        issue.setDesignId(request.getDesignId());
        issue.setTestCaseId(request.getTestCaseId());
        issue.setWorkItemId(request.getWorkItemId());
        issue.setReqId(request.getReqId());
        issue.setDeploymentId(request.getDeploymentId());
        issue.setTraceId(request.getTraceId());
        issue.setArtifactType(request.getArtifactType());
        issue.setArtifactDescription(request.getArtifactDescription());
        issue.setSourceRequirement(request.getSourceRequirement());
        issue.setDesignReference(request.getDesignReference());
        issue.setExpectedCode(request.getExpectedCode());
        issue.setActualCodeStatus(request.getActualCodeStatus());
        issue.setIssueType(request.getIssueType());
        issue.setLineageStatus(request.getLineageStatus());
        issue.setCodeImplementation(request.getCodeImplementation());
        issue.setLineage(request.getLineage());
        
        return repository.save(issue);
    }

    /**
     * Get paginated issues (default sort by createdAt desc)
     */
    @Transactional(readOnly = true)
    public Page<AIModelIssue> getIssues(int page, int size) {
        log.info("Fetching AI model issues - page: {}, size: {}", page, size);
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        return repository.findAll(pageable);
    }

    /**
     * Get issue by ID
     */
    @Transactional(readOnly = true)
    public Optional<AIModelIssue> getIssueById(UUID eventId) {
        log.info("Fetching AI model issue by ID: {}", eventId);
        return repository.findById(eventId);
    }

    /**
     * Get issues by trace ID with pagination
     */
    @Transactional(readOnly = true)
    public Page<AIModelIssue> getIssuesByTraceId(UUID traceId, int page, int size) {
        log.info("Fetching AI model issues by traceId: {}", traceId);
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        return repository.findByTraceId(traceId, pageable);
    }

    /**
     * Get issues by issue type with pagination
     */
    @Transactional(readOnly = true)
    public Page<AIModelIssue> getIssuesByType(String issueType, int page, int size) {
        log.info("Fetching AI model issues by type: {}", issueType);
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        return repository.findByIssueType(issueType, pageable);
    }

    /**
     * Delete an issue
     */
    public void deleteIssue(UUID eventId) {
        log.info("Deleting AI model issue: {}", eventId);
        repository.deleteById(eventId);
    }
}
