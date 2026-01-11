-- Create solution_designs table
CREATE TABLE IF NOT EXISTS solution_designs (
    design_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    trace_id UUID NOT NULL,
    req_id UUID NOT NULL,

    design_overview TEXT NOT NULL,
    design_metadata TEXT NOT NULL,
    affected_application_id JSONB NOT NULL,
    design_url TEXT NOT NULL,
    system_impact_analysis TEXT,
    security_considerations TEXT,
    performance_considerations TEXT,
    upstream_dependencies JSONB,
    downstream_dependencies JSONB,
    is_ai_generated BOOLEAN DEFAULT FALSE,
    design_agent_id VARCHAR(100),
    generated_at TIMESTAMP,

    architect_id VARCHAR(50),
    review_status VARCHAR(50) DEFAULT 'PENDING',
    reviewed_at TIMESTAMP,
    review_comments TEXT,
    review_evidence TEXT,

    github_commit_ids JSONB,
    github_file_paths JSONB,
    github_file_url JSONB,

    approver_id VARCHAR(50),
    approved_at TIMESTAMP,
    signoff_document_url VARCHAR(500),

    created_by VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(50),
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Create indexes for better query performance
CREATE INDEX IF NOT EXISTS idx_solution_designs_trace_id ON solution_designs(trace_id);
CREATE INDEX IF NOT EXISTS idx_solution_designs_req_id ON solution_designs(req_id);
CREATE INDEX IF NOT EXISTS idx_solution_designs_review_status ON solution_designs(review_status);
CREATE INDEX IF NOT EXISTS idx_solution_designs_architect_id ON solution_designs(architect_id);
CREATE INDEX IF NOT EXISTS idx_solution_designs_created_by ON solution_designs(created_by);
CREATE INDEX IF NOT EXISTS idx_solution_designs_is_ai_generated ON solution_designs(is_ai_generated);

-- Create GIN indexes for JSONB columns
CREATE INDEX IF NOT EXISTS idx_solution_designs_affected_app_id ON solution_designs USING GIN(affected_application_id);
CREATE INDEX IF NOT EXISTS idx_solution_designs_upstream_deps ON solution_designs USING GIN(upstream_dependencies);
CREATE INDEX IF NOT EXISTS idx_solution_designs_downstream_deps ON solution_designs USING GIN(downstream_dependencies);

-- Add comments
COMMENT ON TABLE solution_designs IS 'Solution design records';
COMMENT ON COLUMN solution_designs.design_id IS 'Unique design identifier';
COMMENT ON COLUMN solution_designs.trace_id IS 'Trace identifier for tracking';
COMMENT ON COLUMN solution_designs.req_id IS 'Requirement identifier';
COMMENT ON COLUMN solution_designs.review_status IS 'Review status: PENDING, IN_REVIEW, APPROVED, REJECTED, REVISED';
