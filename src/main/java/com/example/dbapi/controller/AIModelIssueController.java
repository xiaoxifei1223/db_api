package com.example.dbapi.controller;

import com.example.dbapi.entity.AIModelIssue;
import com.example.dbapi.model.AIModelIssueRequest;
import com.example.dbapi.service.AIModelIssueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/issues")
@RequiredArgsConstructor
@Slf4j
public class AIModelIssueController {

    private final AIModelIssueService service;

    /**
     * POST - Create a new AI model issue
     * 
     * Request Body Example:
     * {
     *   "traceId": "uuid",
     *   "solutionId": "uuid",
     *   "artifactType": "CODE",
     *   "issueType": "IMPLEMENTATION_ERROR",
     *   ...
     * }
     */
    @PostMapping
    public ResponseEntity<AIModelIssue> createIssue(@RequestBody AIModelIssueRequest request) {
        log.info("REST request to create AI model issue");
        AIModelIssue created = service.createIssue(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * GET - Get paginated AI model issues
     * 
     * Default: page=0, size=10
     * Example: GET /api/v1/issues?page=0&size=10
     * 
     * Response includes:
     * - content: List of issues
     * - totalElements: Total number of records
     * - totalPages: Total number of pages
     * - size: Page size
     * - number: Current page number
     */
    @GetMapping
    public ResponseEntity<Page<AIModelIssue>> getIssues(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        log.info("REST request to get AI model issues - page: {}, size: {}", page, size);
        
        // Ensure size is maximum 10
        if (size > 10) {
            size = 10;
        }
        
        Page<AIModelIssue> issues = service.getIssues(page, size);
        return ResponseEntity.ok(issues);
    }

    /**
     * GET - Get issue by ID
     */
    @GetMapping("/{eventId}")
    public ResponseEntity<AIModelIssue> getIssueById(@PathVariable UUID eventId) {
        log.info("REST request to get AI model issue: {}", eventId);
        return service.getIssueById(eventId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * GET - Get issues by trace ID with pagination
     */
    @GetMapping("/trace/{traceId}")
    public ResponseEntity<Page<AIModelIssue>> getIssuesByTraceId(
            @PathVariable UUID traceId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        log.info("REST request to get issues by traceId: {}", traceId);
        
        if (size > 10) {
            size = 10;
        }
        
        Page<AIModelIssue> issues = service.getIssuesByTraceId(traceId, page, size);
        return ResponseEntity.ok(issues);
    }

    /**
     * GET - Get issues by issue type with pagination
     */
    @GetMapping("/type/{issueType}")
    public ResponseEntity<Page<AIModelIssue>> getIssuesByType(
            @PathVariable String issueType,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        log.info("REST request to get issues by type: {}", issueType);
        
        if (size > 10) {
            size = 10;
        }
        
        Page<AIModelIssue> issues = service.getIssuesByType(issueType, page, size);
        return ResponseEntity.ok(issues);
    }

    /**
     * DELETE - Delete an issue
     */
    @DeleteMapping("/{eventId}")
    public ResponseEntity<Void> deleteIssue(@PathVariable UUID eventId) {
        log.info("REST request to delete AI model issue: {}", eventId);
        service.deleteIssue(eventId);
        return ResponseEntity.noContent().build();
    }
}
