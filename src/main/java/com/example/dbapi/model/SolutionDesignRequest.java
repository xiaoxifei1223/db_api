package com.example.dbapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolutionDesignRequest {
    private UUID traceId;
    private UUID reqId;
    private String designOverview;
    private String designMetadata;
    private List<String> affectedApplicationId;
    private String designUrl;
    private String systemImpactAnalysis;
    private String securityConsiderations;
    private String performanceConsiderations;
    private List<String> upstreamDependencies;
    private List<String> downstreamDependencies;
    private Boolean isAiGenerated;
    private String designAgentId;
    private String architectId;
    private String createdBy;
}
