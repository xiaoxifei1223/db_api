package com.example.dbapi.controller;

import com.example.dbapi.entity.SolutionDesign;
import com.example.dbapi.service.SolutionDesignService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/designs")
@RequiredArgsConstructor
@Slf4j
public class SolutionDesignController {

    private final SolutionDesignService service;

    /**
     * Create a new solution design
     */
    @PostMapping
    public ResponseEntity<SolutionDesign> createDesign(@RequestBody SolutionDesign design) {
        log.info("REST request to create solution design");
        SolutionDesign created = service.createDesign(design);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Update an existing solution design
     */
    @PutMapping("/{designId}")
    public ResponseEntity<SolutionDesign> updateDesign(
            @PathVariable UUID designId,
            @RequestBody SolutionDesign design) {
        log.info("REST request to update solution design: {}", designId);
        SolutionDesign updated = service.updateDesign(designId, design);
        return ResponseEntity.ok(updated);
    }

    /**
     * Get a solution design by ID
     */
    @GetMapping("/{designId}")
    public ResponseEntity<SolutionDesign> getDesignById(@PathVariable UUID designId) {
        log.info("REST request to get solution design: {}", designId);
        return service.getDesignById(designId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get all solution designs
     */
    @GetMapping
    public ResponseEntity<List<SolutionDesign>> getAllDesigns() {
        log.info("REST request to get all solution designs");
        List<SolutionDesign> designs = service.getAllDesigns();
        return ResponseEntity.ok(designs);
    }

    /**
     * Get designs by trace ID
     */
    @GetMapping("/trace/{traceId}")
    public ResponseEntity<List<SolutionDesign>> getDesignsByTraceId(@PathVariable UUID traceId) {
        log.info("REST request to get designs by trace ID: {}", traceId);
        List<SolutionDesign> designs = service.getDesignsByTraceId(traceId);
        return ResponseEntity.ok(designs);
    }

    /**
     * Get designs by requirement ID
     */
    @GetMapping("/requirement/{reqId}")
    public ResponseEntity<List<SolutionDesign>> getDesignsByReqId(@PathVariable UUID reqId) {
        log.info("REST request to get designs by requirement ID: {}", reqId);
        List<SolutionDesign> designs = service.getDesignsByReqId(reqId);
        return ResponseEntity.ok(designs);
    }

    /**
     * Get designs by review status
     */
    @GetMapping("/status/{reviewStatus}")
    public ResponseEntity<List<SolutionDesign>> getDesignsByReviewStatus(@PathVariable String reviewStatus) {
        log.info("REST request to get designs by review status: {}", reviewStatus);
        List<SolutionDesign> designs = service.getDesignsByReviewStatus(reviewStatus);
        return ResponseEntity.ok(designs);
    }

    /**
     * Get designs by architect ID
     */
    @GetMapping("/architect/{architectId}")
    public ResponseEntity<List<SolutionDesign>> getDesignsByArchitectId(@PathVariable String architectId) {
        log.info("REST request to get designs by architect: {}", architectId);
        List<SolutionDesign> designs = service.getDesignsByArchitectId(architectId);
        return ResponseEntity.ok(designs);
    }

    /**
     * Get AI-generated designs
     */
    @GetMapping("/ai-generated")
    public ResponseEntity<List<SolutionDesign>> getAiGeneratedDesigns() {
        log.info("REST request to get AI-generated designs");
        List<SolutionDesign> designs = service.getAiGeneratedDesigns();
        return ResponseEntity.ok(designs);
    }

    /**
     * Update review status
     */
    @PatchMapping("/{designId}/review")
    public ResponseEntity<SolutionDesign> updateReviewStatus(
            @PathVariable UUID designId,
            @RequestParam String reviewStatus,
            @RequestParam(required = false) String reviewComments) {
        log.info("REST request to update review status for design: {}", designId);
        SolutionDesign updated = service.updateReviewStatus(designId, reviewStatus, reviewComments);
        return ResponseEntity.ok(updated);
    }

    /**
     * Approve a design
     */
    @PostMapping("/{designId}/approve")
    public ResponseEntity<SolutionDesign> approveDesign(
            @PathVariable UUID designId,
            @RequestParam String approverId,
            @RequestParam(required = false) String signoffDocumentUrl) {
        log.info("REST request to approve design: {}", designId);
        SolutionDesign approved = service.approveDesign(designId, approverId, signoffDocumentUrl);
        return ResponseEntity.ok(approved);
    }

    /**
     * Delete a solution design
     */
    @DeleteMapping("/{designId}")
    public ResponseEntity<Void> deleteDesign(@PathVariable UUID designId) {
        log.info("REST request to delete solution design: {}", designId);
        service.deleteDesign(designId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Check if design exists
     */
    @GetMapping("/exists")
    public ResponseEntity<Boolean> designExists(
            @RequestParam UUID traceId,
            @RequestParam UUID reqId) {
        log.info("REST request to check if design exists");
        boolean exists = service.designExists(traceId, reqId);
        return ResponseEntity.ok(exists);
    }
}
